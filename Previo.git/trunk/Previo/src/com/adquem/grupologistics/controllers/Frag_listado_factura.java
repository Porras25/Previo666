package com.adquem.grupologistics.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.adquem.grupologistics.bussines.Buss_FragListadoFact_RevisionReferencia;
import com.adquem.grupologistics.bussines.Buss_Frag_Listado;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.adapters.Adp_Base_ListaReferencia;
import com.adquem.grupologistics.adapters.Adp_CustomList;
import com.adquem.grupologistics.adapters.Adp_Expandable_List;
import com.adquem.grupologistics.model.Factura;
import com.adquem.grupologistics.model.Referencia;
import com.adquem.grupologistics.utilities.Constantes;

import android.R.fraction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class Frag_listado_factura extends Fragment {
	private int Tipo;
	private long Padre;
	private int Estatus;
	private ListView lista;
	private TextView prueba, noRef, fecha_arribo, ejecutivo, plaza, clasificador, contenedor, cliente, ordenCompra;
	private Context app_context;
	
	Buss_Frag_Listado buss_Listado;
	
	ListView exlista;
	
	Buss_FragListadoFact_RevisionReferencia  bussines;
	int id_ref;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle args = getArguments();
		this.Padre = args.getLong("Padre");
		this.Tipo = args.getInt("TipoListado");
		this.Estatus = args.getInt("Estatus");
		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.lyt_fragment_listadofact, null);
//		lista = (ListView) root.findViewById(R.id.lv_lyt_listados_items);
		
		prueba = (TextView)root.findViewById(R.id.txv_tituloReferencia);
		noRef = (TextView)root.findViewById(R.id.txv_referenciaValor); 
		fecha_arribo = (TextView)root.findViewById(R.id.txv_arriboValor);
		ejecutivo = (TextView)root.findViewById(R.id.txv_ejecutivoValor);
		plaza = (TextView)root.findViewById(R.id.txv_plazaValor);
		clasificador = (TextView)root.findViewById(R.id.txv_clasificadorValor);
		contenedor = (TextView)root.findViewById(R.id.txv_contenedorValor);
		cliente = (TextView)root.findViewById(R.id.txv_clienteValor);
		ordenCompra = (TextView)root.findViewById(R.id.txv_ordenCompraValor);
		app_context = getActivity();
		
		exlista = (ListView) root.findViewById(R.id.lv_lyt_listados_items);
		
		Log.v("IDPadre Listafacturas", this.Padre+"");

		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		FragmentTransaction tx = getFragmentManager().beginTransaction();
//		Frag_Elemento facturas = new Frag_Elemento();
//		tx.replace(R.id.frm_lyt_mainMenu, facturas);

		Bundle args = getArguments();
		this.Padre = args.getLong("Padre");
		this.Tipo = args.getInt("TipoListado");
		this.Estatus = args.getInt("Estatus");
		
		Log.v("Id Padre recibido creaTED view", args.getInt("Padre") + "");

		bussines = new Buss_FragListadoFact_RevisionReferencia();
		Referencia datos = bussines
				.getReferencia(Padre, app_context);

		if(datos != null){
			prueba.setText(getText(R.string.str_listadofact_txv_Titulo) + " " + datos.getNoReferencia());
			noRef.setText(datos.getNoReferencia());
			fecha_arribo.setText(datos.getFecha_arribo());
			ejecutivo.setText(datos.getEjecutivo());
			plaza.setText(datos.getPlaza());
			clasificador.setText(datos.getClasificador());
			contenedor.setText(datos.getContenedor());
			cliente.setText(datos.getCliente());
			ordenCompra.setText(datos.getOrdenCompra());
		}
		/*final TextView NoReferencia = (TextView) getView().findViewById(
				R.id.txv_referenciaValor);
		final TextView FechaArribo = (TextView) getView().findViewById(
				R.id.txv_arriboValor);
		final TextView Ejecutivo = (TextView) getView().findViewById(
				R.id.txv_ejecutivoValor);
		final TextView Clasificador = (TextView) getView().findViewById(
				R.id.txv_clasificadorValor);
		final TextView Plaza = (TextView) getView().findViewById(
				R.id.txv_plazaValor);
		final TextView Pedimento = (TextView) getView().findViewById(
				R.id.txv_pedimentoValor);

		//NoReferencia.setText(datos.getFolio() + "");
		FechaArribo.setText(datos.getFecha_arribo());
		Ejecutivo.setText(datos.getEjecutivo());
		Clasificador.setText(datos.getClasificador());
		Plaza.setText(datos.getPlaza());
		Pedimento.setText(datos.getNoOperacion());
		final TextView prueba = (TextView) getView().findViewById(
				R.id.txv_tituloReferencia);
		*/
		buss_Listado = new Buss_Frag_Listado();
		
	
		final ListView lista = (ListView) getView().findViewById(
				R.id.lv_lyt_listados_items);
		final LinearLayout informacion = (LinearLayout) getView().findViewById(
				R.id.container);
		final LinearLayout bot = (LinearLayout) getView().findViewById(
				R.id.botones);
		informacion.setVisibility(View.GONE);
		bot.setVisibility(View.VISIBLE);
		lista.setVisibility(View.VISIBLE);

		prueba.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (lista.getVisibility() == View.VISIBLE) {
					lista.setVisibility(View.GONE);
					informacion.setVisibility(View.VISIBLE);
					bot.setVisibility(View.VISIBLE);
				} else {
					lista.setVisibility(View.VISIBLE);
					informacion.setVisibility(View.GONE);
					bot.setVisibility(View.VISIBLE);

				}
			}
		});

		ImageButton revision = (ImageButton) getView().findViewById(
				R.id.imgb_camara);

		revision.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub 
				Bundle argsFact = new Bundle();
				//bussines.insertFotosReferencia(1, imageFileNameFin, imageFin, Constantes.ID_CONCEPTO_FOTO_FIN, 1, 1, 1, "Referencia", getView().getContext());
				FragmentTransaction tx = getFragmentManager()
						.beginTransaction();
				tx.setCustomAnimations(R.animator.enter, R.animator.exit,
						R.animator.pop_enter, R.animator.pop_exit);
				Frag_PhotoIntent remplazo = new Frag_PhotoIntent();
				//tx.replace(R.id.frm_lyt_mainMenu, remplazo);
				tx.replace(R.id.frm_lyt_mainMenu, Act_Main.mFragmentStack.push(remplazo), Constantes.TIPOlISTADO_REFERENCIA + ":" + Constantes.ESTATUS_TODOS);
				Log.i("", "id pasado en el bundle listadoFact:  "+Padre);
				argsFact.putLong("IdRef", Padre);
				remplazo.setArguments(argsFact);
				
                tx.addToBackStack(null);
				tx.remove(Frag_listado_factura.this);
				tx.commit();
			}
		});
/*
		if (Estatus != 0) {
			for (int i = 0; i < listaReferencia.size(); i++) {
				Referencia referenciaFiltrada = listaReferencia.get(i);

				if (referenciaFiltrada.getIdEstatus() == Estatus)
					listaFiltrada.add(referenciaFiltrada);
			}
		} else {
			listaFiltrada = listaReferencia;
		}
*/		
		List<Factura> facturas = new ArrayList<Factura>();
		if(Padre != 0){
   		 	String[] argsFacs = { String.valueOf(Padre) };
			facturas = buss_Listado.obtenerFacturas(getView().getContext().getContentResolver().query(Uri.parse(MyContentProvider.URL+"Factura"), null, "IdReferencia = ?", argsFacs, null));
		}
		List<Factura> listaFacturas = new ArrayList<Factura>();
		if (Estatus != Constantes.ESTATUS_TODOS) {
			for (int i = 0; i < facturas.size(); i++) {
				Factura factura = facturas.get(i);
				if (factura.getEstatus() == Estatus)
					listaFacturas.add(factura);
			}
		} else {
			listaFacturas = facturas;
		}
		orderFacs(listaFacturas);
		Collections.reverse(listaFacturas);
		
		if(Padre != 0){
		      List<Factura> listadoFac = listaFacturas;
		      listaFacturas = new ArrayList<Factura>();
		      for(int i = 0; i <listadoFac.size(); i++){
		       //La factura ficticia tiene el nombre #F-SCA-AUTO#
		       if(listadoFac.get(i).getFactura().equals(Constantes.NOMBRE_FACTURA_FICTICIA))
		        listaFacturas.add(0, listadoFac.get(i));
		       else
		        listaFacturas.add(listadoFac.get(i));
		      }
		     }
		
		List<List<String>> contadoresitms = buss_Listado.getContadorClasif(listaFacturas, Constantes.TIPOlISTADO_FACTURA, getView().getContext());
		
/*		Adp_Base_ListaReferencia adapter = new Adp_Base_ListaReferencia(
				listaFiltrada, getActivity().getBaseContext());
		lista.setAdapter(adapter);
*/
		  Adp_CustomList adapListF = new Adp_CustomList(getActivity().getBaseContext(), R.layout.lyt_fragment_childrow, listaFacturas, Constantes.TIPOlISTADO_FACTURA, contadoresitms);
		//Adp_Expandable_List adapterFacs = new Adp_Expandable_List(listaFacturas, getActivity().getBaseContext(), Constantes.TIPOlISTADO_FACTURA, contadoresitms);
		exlista.setAdapter(adapListF);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		// lista = (ListView)
		// getView().findViewById(R.id.lv_lyt_listados_items);
		Button excedente = (Button) getView().findViewById(
				R.id.btn_agregarExcedente);
		
		Button forzarRevision = (Button) getView().findViewById(
				R.id.btn_forzarCierre);

		excedente.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentTransaction tx = getFragmentManager()
						.beginTransaction();
				tx.setCustomAnimations(R.animator.enter, R.animator.exit,
						R.animator.pop_enter, R.animator.pop_exit);
				Frag_AltaItem remplazo = new Frag_AltaItem();
				//tx.replace(R.id.frm_lyt_mainMenu, remplazo);
				tx.replace(R.id.frm_lyt_mainMenu, Act_Main.mFragmentStack.push(remplazo), Constantes.TIPOlISTADO_REFERENCIA + ":" + Constantes.ESTATUS_TODOS);
				Act_Main.fragmentos.push(remplazo);
				Act_Main.elementos.push("Frag_AltaItem");
				//////Referencia para alta item
				Bundle argsrefFact = new Bundle();
				argsrefFact.putLong("Referencia", Padre);
				remplazo.setArguments(argsrefFact);
				tx.addToBackStack(null);
				tx.remove(Frag_listado_factura.this);
				tx.commit();
			}
		});
		
		
		forzarRevision.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentTransaction tx = getFragmentManager()
						.beginTransaction();
				tx.setCustomAnimations(R.animator.enter, R.animator.exit,
						R.animator.pop_enter, R.animator.pop_exit);
				Frag_Forzar_Revision remplazo = new Frag_Forzar_Revision();
				//tx.replace(R.id.frm_lyt_mainMenu, remplazo);
				tx.replace(R.id.frm_lyt_mainMenu, Act_Main.mFragmentStack.push(remplazo), Constantes.TIPOlISTADO_REFERENCIA + ":" + Constantes.ESTATUS_TODOS);
				Act_Main.fragmentos.push(remplazo);
				Act_Main.elementos.push("Frag_Forzar_Revision");
				Bundle argsItem = new Bundle();
				argsItem.putLong("Padre", Padre);
				argsItem.putInt("TipoListado", Tipo);
				argsItem.putInt("Estatus", Estatus);
				remplazo.setArguments(argsItem);
				tx.addToBackStack(null);
				tx.remove(Frag_listado_factura.this);
				tx.commit();
			}
		});
/*
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String item = arg0.getItemAtPosition(arg2).toString();
				Toast.makeText(getActivity().getBaseContext(),
						"You selected : " + item, Toast.LENGTH_SHORT).show();
				switch (arg2) {

				default:
					FragmentTransaction tx = getFragmentManager()
							.beginTransaction();
					tx.setCustomAnimations(R.animator.enter, R.animator.exit,
							R.animator.pop_enter, R.animator.pop_exit);
					Frag_Item remplazo = new Frag_Item();
					tx.replace(R.id.frm_lyt_mainMenu, remplazo);
					tx.commit();
					break;
				}

			}
		});
*/
		
		
		exlista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				String seleccionado = "";
				FragmentTransaction tx = getFragmentManager()
						.beginTransaction();
				Bundle argsref = new Bundle();
				Factura factura = (Factura) adapter
						.getItemAtPosition(position);
				seleccionado = "Factura " + factura.getFactura();
				Log.v("Previo App", "valor seleccionado on long intofacs:" + factura.getFactura());
				tx.setCustomAnimations(R.animator.enter, R.animator.exit,
						R.animator.pop_enter, R.animator.pop_exit);
				Frag_Item remplazoF = new Frag_Item();
				//tx.replace(R.id.frm_lyt_mainMenu, remplazoF);
				tx.replace(R.id.frm_lyt_mainMenu, Act_Main.mFragmentStack.push(remplazoF),Constantes.TIPOlISTADO_ITEM + ":" + factura.getIdFactura());
				Act_Main.fragmentos.push(remplazoF);
				argsref.putInt("TipoListado", Constantes.TIPOlISTADO_ITEM);
				argsref.putInt("Estatus", Constantes.ESTATUS_TODOS);
				argsref.putLong("Padre", factura.getIdFactura());			
				remplazoF.setArguments(argsref);
				Act_Main.elementos.push("Frag_Item");
				
				SharedPreferences prefs = app_context.getSharedPreferences("PreferenciasPrevioApp",Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putInt("TipoListado", Constantes.TIPOlISTADO_ITEM);
				editor.putLong("Padre", factura.getIdFactura());
				editor.putInt("Estatus", Constantes.ESTATUS_TODOS);
				editor.commit();
				tx.addToBackStack(null);
				tx.remove(Frag_listado_factura.this);
				tx.commit();
				//return false;
			}
		});
	}
	
	
	private static void orderFacs(List<Factura> facturas){
		Collections.sort(facturas, new Comparator<Factura>() {
			@Override
			public int compare(Factura lhs, Factura rhs) {
				return ((Factura)lhs).getFactura().compareTo(((Factura)rhs).getFactura());
				//return Integer.valueOf(lhs.getFactura()) - Integer.valueOf(rhs.getFactura());
			}
		});
	}
	
}

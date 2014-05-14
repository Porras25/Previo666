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
import com.adquem.grupologistics.model.Item;
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
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class Frag_listado_item extends Fragment{
private int Tipo;
private long Padre;
private int Estatus;
private ListView lista;
ExpandableListView exlista;
Buss_Frag_Listado buss_Listado;
private Context context;
private TextView titulo, noReferencia, fecha, orden, razon_social, rfc, factura;
    
	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		Bundle args = getArguments();
		this.Padre=args.getLong("Padre");
		this.Tipo=args.getInt("TipoListado");
		this.Estatus=args.getInt("Estatus");
		
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lyt_fragment_items, null);
		
		titulo = (TextView)root.findViewById(R.id.txv_tituloFactura);
		noReferencia = (TextView)root.findViewById(R.id.txv_referenciaValor);
		fecha = (TextView)root.findViewById(R.id.txv_fechaArriboValor);
		orden = (TextView)root.findViewById(R.id.txv_ordenCompraValor);
		razon_social = (TextView)root.findViewById(R.id.txv_razonSocialValor);
		rfc = (TextView)root.findViewById(R.id.txv_RFCValor);
		factura = (TextView)root.findViewById(R.id.txv_noFacturaValor);		
		
		context = getActivity(); 
		
		Log.v("Previo App", "valor recuperado idpadre de factura" + Padre);
	         
        lista = (ListView) root.findViewById(R.id.lv_lyt_listados_items);
//			exlista = (ExpandableListView) root.findViewById(R.id.lv_lyt_listados_items);
			
		return root;
		
	    }
	
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		buss_Listado = new Buss_Frag_Listado();
		
		Factura facturaPadre = new Factura();
		Referencia ref = new Referencia();
		
		facturaPadre = buss_Listado.obtener_factura(Padre,context);
		
		if(facturaPadre != null){
			titulo.setText(getText(R.string.str_items_txv_Titulo) + " " + facturaPadre.getFactura());
			fecha.setText(facturaPadre.getFechaFactura());
			orden.setText(facturaPadre.getOrdenCompra());
			factura.setText(facturaPadre.getFactura());
			fecha.setText(facturaPadre.getFechaFactura());
			
			Buss_FragListadoFact_RevisionReferencia busRev = new Buss_FragListadoFact_RevisionReferencia();
			
			ref = busRev.getReferencia(facturaPadre.getIdReferencia(), context);
			
			if(ref != null){
				noReferencia.setText(ref.getNoReferencia());
				rfc.setText(ref.getRfc());
				razon_social.setText(ref.getCliente());
			}
			
		}
      
		
		List<Item> items = new ArrayList<Item>();
		Log.v("padre es", Padre+"");
		if(Padre != 0){
			String[] argsItm = {String.valueOf(Padre)};
			items = buss_Listado.obtenerItems(getView().getContext().getContentResolver().query(Uri.parse(MyContentProvider.URL+"Item"), null, "IdFactura = ?", argsItm, null));
		}
		List<Item> listaItems = new ArrayList<Item>();
		if (Estatus != Constantes.ESTATUS_TODOS) {
			for (int i = 0; i < items.size(); i++) {
				Item item = items.get(i);
				if (item.getEstatus() == Estatus)
					listaItems.add(item);
			}
		} else {
			listaItems = items;
		}
		orderItms(listaItems);
		Collections.reverse(listaItems);
		List<List<String>> contadoresnoms = buss_Listado.getContadorClasif(listaItems, Constantes.TIPOlISTADO_ITEM, getView().getContext());
		//Adp_Expandable_List adapterItms = new Adp_Expandable_List(listaItems, getActivity().getBaseContext(), Constantes.TIPOlISTADO_ITEM, contadoresnoms);
		Adp_CustomList adapListI = new Adp_CustomList(getActivity().getBaseContext(), R.layout.lyt_fragment_childrow, listaItems, Constantes.TIPOlISTADO_ITEM, contadoresnoms);
		lista.setAdapter(adapListI);
	    
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		SharedPreferences prefs = this.getActivity().getSharedPreferences("PreferenciasPrevioApp",Context.MODE_PRIVATE);	 
		this.Tipo = prefs.getInt("TipoListado", Constantes.TIPOlISTADO_REFERENCIA);
		this.Estatus = prefs.getInt("Estatus", Constantes.ESTATUS_TODOS);
		this.Padre = prefs.getLong("Padre", 0);
		Log.v("STACK", "Start fraglistado Item");
		
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		
		
		final LinearLayout layout = (LinearLayout) getView().findViewById(R.id.ln_lyt_contenedor);
		final ListView list = (ListView)getView().findViewById(R.id.lv_lyt_listados_items);
		
		
		titulo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			if(layout.getVisibility()==View.GONE){
				layout.setVisibility(View.VISIBLE);
				list.setVisibility(View.GONE);	
			}	
			else {
				layout.setVisibility(View.GONE);
				list.setVisibility(View.VISIBLE);	

				
			}
			}
		});
		
		
		
		

		  lista.setOnItemClickListener(new OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				String seleccionado = "";
				FragmentTransaction tx = getFragmentManager()
						.beginTransaction();
				Bundle argsref = new Bundle();
				Item item = (Item) adapter.getItemAtPosition(position);
				seleccionado = "Item " + item.getNoParte();
				Log.v("Previo App", "valor seleccionado on long item:" + item.getDescripcion());
				tx.setCustomAnimations(R.animator.enter,
						R.animator.exit, R.animator.pop_enter,
						R.animator.pop_exit);
				
				SharedPreferences prefs = getActivity().getBaseContext().getSharedPreferences("PreferenciasPrevioApp",Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putInt("TipoListado", Constantes.TIPOlISTADO_ITEM);
				editor.putLong("Padre", Padre);
				editor.putInt("Estatus", Constantes.ESTATUS_TODOS);
				editor.commit();
				Log.v("IDFACT","idFactura"+ Padre);
				Log.v("Preferences", "TipoListado"+prefs.getInt("TipoListado", Constantes.TIPOlISTADO_REFERENCIA));
				Log.v("Preferences", "Padre"+prefs.getLong("Padre", Constantes.TIPOlISTADO_REFERENCIA));
				Log.v("Preferences", "Estatus"+prefs.getInt("Estatus", Constantes.TIPOlISTADO_REFERENCIA));
				
				Frag_item_revision remplazoI = new Frag_item_revision();
				//tx.replace(R.id.frm_lyt_mainMenu,remplazoI).addToBackStack(null);
				tx.replace(R.id.frm_lyt_mainMenu, Act_Main.mFragmentStack.push(remplazoI),Constantes.TIPOlISTADO_ITEM + ":" + item.getIdFactura());
				argsref.putInt("TipoListado", Constantes.TIPOlISTADO_ITEM);
				argsref.putInt("Estatus", Constantes.ESTATUS_TODOS);
				argsref.putLong("Padre", item.getIdItem());
				Log.v("ID PADRE","ID PADRE "+item.getIdItem());
				remplazoI.setArguments(argsref);
				tx.addToBackStack(null);
				tx.remove(Frag_listado_item.this);
				tx.commit();
				//return false;
			}
		});
		
	}
	
	private static void orderItms(List<Item> items){
		Collections.sort(items, new Comparator<Item>() {
			@Override
			public int compare(Item lhs, Item rhs) {
				return ((Item)lhs).getNoParte().compareTo(((Item)rhs).getNoParte());
				//return Integer.valueOf(lhs.getNoParte()) - Integer.valueOf(rhs.getNoParte());
			}
		});
	}
	
}


/**
 * 
 */
package com.adquem.grupologistics.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.adquem.grupologistics.adapters.Adp_CustomList;
import com.adquem.grupologistics.bussines.Buss_Frag_Listado;
import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.model.Factura;
import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.model.Referencia;
import com.adquem.grupologistics.utilities.Constantes;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ListView;

/**
 * @author Usuario
 *
 */
public class Frag_Listado extends Fragment {

	private static final String TAG = "Listado Previo App";

	ListView lista;
	ExpandableListView exlista;
	private int estatus;
	private int tipoListado;
	private long padre;
	private String criterio;
	private Buss_Frag_Listado buss_Listado;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle args = getArguments();
		this.tipoListado = args.getInt("TipoListado");
		this.estatus = args.getInt("Estatus");
		this.padre = args.getLong("Padre");
		this.criterio = args.getString("Criterio");
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lyt_listados,null);
		
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		buss_Listado = new Buss_Frag_Listado();
		//exlista = (ExpandableListView) getView().findViewById(R.id.lv_lyt_listados_items);
		lista = (ListView) getView().findViewById(R.id.lv_lyt_listados_items);
		new CargarListView(getView().getContext()).execute();
		

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,	
					int position, long id) {
				String seleccionado = "";
				String tipo = adapter.getItemAtPosition(position).getClass().getName();
				if(Referencia.class.getName().equals(tipo)){
					tipoListado = Constantes.TIPOlISTADO_REFERENCIA;
				}
				else{
					if(Factura.class.getName().equals(tipo)){
						tipoListado = Constantes.TIPOlISTADO_FACTURA;
					}
					else{
						if(Item.class.getName().equals(tipo)){
							tipoListado = Constantes.TIPOlISTADO_ITEM;
						}
					}
				}
				FragmentTransaction tx = getFragmentManager()
						.beginTransaction();
				Bundle argsref = new Bundle();
				switch (tipoListado) {
					case Constantes.TIPOlISTADO_REFERENCIA:
						Referencia referencia = (Referencia) adapter
							.getItemAtPosition(position);
						seleccionado = "Referencia " + referencia.getNoReferencia();
					//Log.v(TAG, "valor seleccionado on long:" + referencia.getFolio());
					tx.setCustomAnimations(R.animator.enter, R.animator.exit,
							R.animator.pop_enter, R.animator.pop_exit);
					Frag_Factura remplazoR = new Frag_Factura();
					//tx.replace(R.id.frm_lyt_mainMenu, remplazoR);
					tx.replace(R.id.frm_lyt_mainMenu, Act_Main.mFragmentStack.push(remplazoR),Constantes.TIPOlISTADO_FACTURA + ":" + referencia.getIdReferencia());
					argsref.putInt("TipoListado", Constantes.TIPOlISTADO_REFERENCIA);
					argsref.putInt("Estatus", Constantes.ESTATUS_TODOS);
					argsref.putLong("Padre", referencia.getIdReferencia());
					remplazoR.setArguments(argsref);
					//Back
					tx.addToBackStack(null);
					tx.remove(Frag_Listado.this);
					tx.commit();
					
					
					break;
				case Constantes.TIPOlISTADO_FACTURA:
					Factura factura = (Factura) adapter
							.getItemAtPosition(position);
					seleccionado = "Factura " + factura.getFactura();
					Log.v(TAG, "valor seleccionado on long:" + factura.getFactura());
					tx.setCustomAnimations(R.animator.enter, R.animator.exit,
							R.animator.pop_enter, R.animator.pop_exit);
					Frag_Item remplazoF = new Frag_Item();
			//		tx.replace(R.id.frm_lyt_mainMenu, remplazoF);
					tx.replace(R.id.frm_lyt_mainMenu, Act_Main.mFragmentStack.push(remplazoF),Constantes.TIPOlISTADO_ITEM + ":" + factura.getIdFactura());
					argsref.putInt("TipoListado", Constantes.TIPOlISTADO_FACTURA);
					argsref.putInt("Estatus", Constantes.ESTATUS_TODOS);
					argsref.putLong("Padre", factura.getIdFactura());
					argsref.putString("Criterio", criterio);
					remplazoF.setArguments(argsref);
					//Back
				    tx.addToBackStack(null);
				    tx.remove(Frag_Listado.this);
					tx.commit();
					
					break;
				case Constantes.TIPOlISTADO_ITEM:
					Item item = (Item) adapter.getItemAtPosition(position);
					seleccionado = "Item " + item.getNoParte();
					Log.v(TAG, "valor seleccionado on long:" + item.getDescripcion());
					tx.setCustomAnimations(R.animator.enter,
							R.animator.exit, R.animator.pop_enter,
							R.animator.pop_exit);
					Frag_item_revision remplazoI = new Frag_item_revision();
					//tx.replace(R.id.frm_lyt_mainMenu,remplazoI);
					tx.replace(R.id.frm_lyt_mainMenu, Act_Main.mFragmentStack.push(remplazoI),Constantes.TIPOlISTADO_ITEM + ":" + Constantes.ESTATUS_TODOS);
					argsref.putInt("TipoListado", Constantes.TIPOlISTADO_ITEM);
					argsref.putInt("Estatus", Constantes.ESTATUS_TODOS);
					argsref.putLong("Padre", item.getIdItem());
					remplazoI.setArguments(argsref);
					//Back
					tx.addToBackStack(null);
					tx.remove(Frag_Listado.this);
					tx.commit();
					
					break;
				}
				//return false;
			}
		});
	}
		 
	
	private List<Referencia> searchRefDB(String criterio){
		String[] params = new String[]{"%" + criterio + "%","%" + criterio + "%", "%" + criterio + "%"};
		return buss_Listado.obtenerReferencias(getView().getContext().getContentResolver().query(Uri.parse(MyContentProvider.URL+"Referencia"), null, "NoReferencia LIKE ? OR Contenedor LIKE ?  OR OrdenCompra LIKE ?", params, null));
	}
	
	private List<Factura> searchFacDB(String criterio){
		String[] params = new String[]{"%" + criterio + "%","%" + criterio + "%"};
		return buss_Listado.obtenerFacturas(getView().getContext().getContentResolver().query(Uri.parse(MyContentProvider.URL+"Factura"), null, "Factura LIKE ? OR OrdenCompra LIKE ?", params, null));
	}
	
	private List<Item> searchItmDB(String criterio, long padre){
		String[] params;
		String criterios;
		if(padre != 0){
			params = new String[]{String.valueOf(padre),"%" + criterio + "%","%" + criterio + "%","%" + criterio + "%"};
			criterios = "IdFactura = ? AND ( Descripcion LIKE ? OR NoParte LIKE ? OR Marca LIKE ? )";
		}
		else{
			params = new String[]{"%" + criterio + "%","%" + criterio + "%","%" + criterio + "%"};
			criterios = "Descripcion LIKE ? OR NoParte LIKE ? OR Marca LIKE ?";
		}
		return buss_Listado.obtenerItems(getView().getContext().getContentResolver().query(Uri.parse(MyContentProvider.URL+"Item"), null, criterios, params, null));
	}
	
	private static void orderRefs(List<Referencia> referencias){
		Collections.sort(referencias, new Comparator<Referencia>() {
			@Override
			public int compare(Referencia lhs, Referencia rhs) {
				return lhs.getNoReferencia().compareTo(rhs.getNoReferencia());
//				return Integer.parseInt(lhs.getNoReferencia()) - Integer.valueOf(rhs.getNoReferencia());
			}
		});
	}
	
	private static void orderFacs(List<Factura> facturas){
		Collections.sort(facturas, new Comparator<Factura>() {
			@Override
			public int compare(Factura lhs, Factura rhs) {
				return ((Factura)lhs).getFactura().compareTo(((Factura)rhs).getFactura());
//				return Integer.valueOf(lhs.getFactura()) - Integer.valueOf(rhs.getFactura());
			}
		});
	}
	
	
	private static void orderItms(List<Item> items){
		Collections.sort(items, new Comparator<Item>() {
			@Override
			public int compare(Item lhs, Item rhs) {
				return ((Item)lhs).getNoParte().compareTo(((Item)rhs).getNoParte());
//				return Integer.valueOf(lhs.getNoParte()) - Integer.valueOf(rhs.getNoParte());
			}
		});
	}

	private class CargarListView extends AsyncTask<Void, Void, Adp_CustomList>{
	    Context context;
	    ProgressDialog pDialog;
	 
	    public CargarListView(Context context){
	        this.context = context;
	    }
	 
	    @Override
	    protected void onPreExecute() {
	        // TODO Auto-generated method stub
	        super.onPreExecute();
	 
	        pDialog = new ProgressDialog(context);
	        pDialog.setMessage("Cargando Lista");
	        pDialog.setCancelable(false);
	        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pDialog.show();
	    }
	 
	    @Override
	    protected Adp_CustomList doInBackground(Void... arg0) {
	        // TODO Auto-generated method stub
	 
	    	Adp_CustomList adaptador = null;
	    	
	    	
	    	Log.v("PrevioApp", "a listados llego siendo.." + estatus);
			if(estatus == Constantes.BUSQUEDA){
				Log.v("Previo App", "BUSQUEDA PARAMETERS <tipoLista:"+ tipoListado + "> -- <padre:" + padre + ">");
				List busqueda = new ArrayList<Object>();
				List<List<String>> contadores = new ArrayList<List<String>>();
				List referencias;
				List facturas;
				List items;
				switch(tipoListado){
					case Constantes.TIPOlISTADO_REFERENCIA:
						referencias = searchRefDB(criterio);
						facturas = searchFacDB(criterio);
						items = searchItmDB(criterio, 0);
						busqueda.addAll(referencias);
						busqueda.addAll(facturas);
						busqueda.addAll(items);
						contadores = buss_Listado.getContadorClasif(referencias, Constantes.TIPOlISTADO_REFERENCIA, getView().getContext());
						contadores.addAll(buss_Listado.getContadorClasif(facturas, Constantes.TIPOlISTADO_FACTURA, getView().getContext()));
						contadores.addAll(buss_Listado.getContadorClasif(items, Constantes.TIPOlISTADO_ITEM, getView().getContext()));
						break;
					case Constantes.TIPOlISTADO_FACTURA:
						facturas = searchFacDB(criterio);
						Log.v("Previo App", "Facs con criterio: " + facturas.size());
						items = searchItmDB(criterio, 0);
						Log.v("Previo App", "Items con criterio: " + items.size());
						if(padre != 0){
							List facturasRef = new ArrayList<Object>();
							List itemsFac = new ArrayList<Object>();
							for(int i = 0; i < facturas.size(); i++){
								if(((Factura) facturas.get(i)).getIdReferencia() == padre){
									facturasRef.add(facturas.get(i));
								}
							}
							Log.v("Previo App", "Facs encontrados en busqueda: " + facturasRef.size());
							busqueda.addAll(facturasRef);
							for(int j = 0; j < facturasRef.size(); j++){
								itemsFac = searchItmDB(criterio, ((Factura)facturasRef.get(j)).getIdFactura());
								for(int k = 0; k < itemsFac.size(); k++)
									busqueda.add(itemsFac.get(k));
							}
							contadores = buss_Listado.getContadorClasif(facturasRef, Constantes.TIPOlISTADO_FACTURA, getView().getContext());
							contadores.addAll(buss_Listado.getContadorClasif(itemsFac, Constantes.TIPOlISTADO_ITEM, getView().getContext()));
						}
						else{
							busqueda.addAll(facturas);
							busqueda.addAll(items);
							contadores = buss_Listado.getContadorClasif(facturas, Constantes.TIPOlISTADO_FACTURA, getView().getContext());
							contadores.addAll(buss_Listado.getContadorClasif(items, Constantes.TIPOlISTADO_ITEM, getView().getContext()));
						}
						break;
					case Constantes.TIPOlISTADO_ITEM:
						List<Item> itemsList;
						if(padre != 0){
							itemsList = searchItmDB(criterio, padre);
							busqueda.addAll(itemsList);
						}
						else{
							itemsList = searchItmDB(criterio, 0);
							busqueda.addAll(itemsList);
						}
						contadores = buss_Listado.getContadorClasif(itemsList, Constantes.TIPOlISTADO_ITEM, getView().getContext());
						break;
				}
				//Adp_CustomList adapList 
				adaptador = new Adp_CustomList(getActivity().getBaseContext(), R.layout.lyt_fragment_childrow, busqueda, Constantes.BUSQUEDA, contadores);
				//lista.setAdapter(adapList);
				//Adp_Expandable_List adapteRef = new Adp_Expandable_List(busqueda, getActivity().getBaseContext(), Constantes.BUSQUEDA, contadores);
				//exlista.setAdapter(adapteRef);
			}
			else{
				switch (tipoListado) {
				case Constantes.TIPOlISTADO_REFERENCIA:
				     List<Referencia> referencias = buss_Listado.obtenerReferencias(getView().getContext().getContentResolver().query(Uri.parse(MyContentProvider.URL+"Referencia"), null, null, null, null));
				     
				     List<Referencia> listaReferencia = new ArrayList<Referencia>();
				     if (estatus != Constantes.ESTATUS_TODOS) {
				      for (int i = 0; i < referencias.size(); i++) {
				       Referencia referencia = referencias.get(i);
				       if (referencia.getEstatus() == estatus){
				        if(referencia.getEstatus() == Constantes.ESTATUS_REVISADO){
				         //dd-mm-aaaa hh:mm:ss por default el valor es 01-01-2001 00:00:00
				         if(referencia.getFecha_fin_previo().contains(new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date()).subSequence(0, 8)))
				          listaReferencia.add(referencia);
				        }
				        else{       
				         listaReferencia.add(referencia);
				        }
				       }
				      }
				     } else {
				      listaReferencia = referencias;
				     }
				     orderRefs(listaReferencia);
				     Collections.reverse(listaReferencia);
				     List<List<String>> contadoresfacs = buss_Listado.getContadorClasif(listaReferencia, Constantes.TIPOlISTADO_REFERENCIA, getView().getContext());
				     //Adp_CustomList adapList 
				     adaptador = new Adp_CustomList(getActivity().getBaseContext(), R.layout.lyt_fragment_childrow, listaReferencia, Constantes.TIPOlISTADO_REFERENCIA, contadoresfacs);//Constantes.BUSQUEDA, listaReferencia);
				     //lista.setAdapter(adapList);
				     //Adp_Expandable_List adapteRef = new Adp_Expandable_List(listaReferencia, getActivity().getBaseContext(), Constantes.TIPOlISTADO_REFERENCIA, contadoresfacs);
				     //exlista.setAdapter(adapteRef);
				     break;
				case Constantes.TIPOlISTADO_FACTURA:
					List<Factura> facturas;// = obtenerListaFacturas();
					if(padre != 0){
						String sqlCreateFactura = "CREATE TABLE Factura (IdFactura INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdReferencia INTEGER, FolioFactura INTEGER,NumOrden TEXT, NumFactura TEXT, Cantidad INTERGER , FechaFactura TEXT, IdEstatus INTEGER, Proveedor TEXT, OrdenCompra TEXT,  FOREIGN KEY(IdEstatus) REFERENCES Estatus(IdEstatus), FOREIGN KEY(IdReferencia) REFERENCES Referencia(IdReferencia))";
			   		 	String[] argsFacs = { String.valueOf(padre) };
						facturas = buss_Listado.obtenerFacturas(getView().getContext().getContentResolver().query(Uri.parse(MyContentProvider.URL+"Factura"), null, "IdReferencia = ?", argsFacs, null));
					}
					else{
						facturas = buss_Listado.obtenerFacturas(getView().getContext().getContentResolver().query(Uri.parse(MyContentProvider.URL+"Factura"), null, null, null, null));
					}
					List<Factura> listaFacturas = new ArrayList<Factura>();
					if (estatus != Constantes.ESTATUS_TODOS) {
						for (int i = 0; i < facturas.size(); i++) {
							Factura factura = facturas.get(i);
							if (factura.getEstatus() == estatus)
								listaFacturas.add(factura);
						}
					} else {
						listaFacturas = facturas;
					}
					orderFacs(listaFacturas);
					Collections.reverse(listaFacturas);
					if(padre != 0){
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
					//Adp_CustomList adapListF 
					adaptador = new Adp_CustomList(getActivity().getBaseContext(), R.layout.lyt_fragment_childrow, listaFacturas, Constantes.TIPOlISTADO_FACTURA, contadoresitms);
					//lista.setAdapter(adapListF);
					//Adp_Expandable_List adapterFacs = new Adp_Expandable_List(listaFacturas, getActivity().getBaseContext(), Constantes.TIPOlISTADO_FACTURA, contadoresitms);
					//exlista.setAdapter(adapterFacs);
					break;
				case Constantes.TIPOlISTADO_ITEM:
					List<Item> items;
					if(padre != 0){
						String[] argsItm = {String.valueOf(padre)};
						items = buss_Listado.obtenerItems(getView().getContext().getContentResolver().query(Uri.parse(MyContentProvider.URL+"Item"), null, "IdFactura = ?", argsItm, null));
					}
					else{
						items = buss_Listado.obtenerItems(getView().getContext().getContentResolver().query(Uri.parse(MyContentProvider.URL+"Item"), null, null, null, null));
					}
					List<Item> listaItems = new ArrayList<Item>();
					if (estatus != Constantes.ESTATUS_TODOS) {
						for (int i = 0; i < items.size(); i++) {
							Item item = items.get(i);
							if (item.getEstatus() == estatus)
								listaItems.add(item);
						}
					} else {
						listaItems = items;
					}
					orderItms(listaItems);
					Collections.reverse(listaItems);
					List<List<String>> contadoresnoms = buss_Listado.getContadorClasif(listaItems, Constantes.TIPOlISTADO_ITEM, getView().getContext());
					//Adp_CustomList adapListI 
					adaptador = new Adp_CustomList(getActivity().getBaseContext(), R.layout.lyt_fragment_childrow, listaItems, Constantes.TIPOlISTADO_ITEM, contadoresnoms);
					//lista.setAdapter(adapListI);
					//Adp_Expandable_List adapterItms = new Adp_Expandable_List(listaItems, getActivity().getBaseContext(), Constantes.TIPOlISTADO_ITEM, contadoresnoms);
					//exlista.setAdapter(adapterItms);
					break;
				}
				
			}
	    	
	    	
//	        try{
//	            Thread.sleep(2000);
//	        }catch(Exception ex){
//	            ex.printStackTrace();
//	        }
	 
	       return adaptador;
	    }
	 
	    @Override
	    protected void onPostExecute(Adp_CustomList result) {
	        // TODO Auto-generated method stub
	        super.onPostExecute(result);
	        lista.setAdapter(result);
	        pDialog.dismiss();
	    }
	 
	}	
	
	
}

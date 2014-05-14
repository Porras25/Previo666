/**
 * 
 */
package com.adquem.grupologistics.bussines;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.method.MovementMethod;
import android.util.Log;

import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.model.Factura;
import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.model.Nom;
import com.adquem.grupologistics.model.NomItem;
import com.adquem.grupologistics.model.Referencia;
import com.adquem.grupologistics.utilities.Constantes;

/**
 * @author Usuario
 *
 */
public class Buss_Frag_Listado {
	
	
	/**
	 * Regresa la lista de referencias obtenidas del cursor recibido como parámetro.
	 * @param cursorR cursor generado por la consulta de referencias
	 * @return la lista de referencias obtenidas del cursor
	 */	public List<Referencia> obtenerReferencias(Cursor cursorR){
		Log.v("Previo App", "CursorRef tiene:" + cursorR.getCount());
		List<Referencia> referencias = new ArrayList<Referencia>();
		while(cursorR.moveToNext()){
			Referencia referencia = new Referencia();
			referencia.setIdReferencia(cursorR.getLong(cursorR.getColumnIndexOrThrow("IdReferencia")));
			referencia.setEstatus(cursorR.getInt(cursorR.getColumnIndexOrThrow("Estatus")));
			referencia.setNoReferencia(cursorR.getString(cursorR.getColumnIndexOrThrow("NoReferencia")));
			referencia.setCliente(cursorR.getString(cursorR.getColumnIndexOrThrow("Cliente")));
			referencia.setContenedor(cursorR.getString(cursorR.getColumnIndexOrThrow("Contenedor")));
			referencia.setFecha_fin_previo(cursorR.getString(cursorR.getColumnIndexOrThrow("Fecha_fin_previo")));
			referencia.setFecha_arribo(cursorR.getString(cursorR.getColumnIndexOrThrow("Fecha_arribo")));
			referencia.setEjecutivo(cursorR.getString(cursorR.getColumnIndexOrThrow("Ejecutivo")));
			referencia.setClasificador(cursorR.getString(cursorR.getColumnIndexOrThrow("Clasificador")));
			referencia.setPlaza(cursorR.getString(cursorR.getColumnIndexOrThrow("Plaza")));
			referencia.setRfc(cursorR.getString(cursorR.getColumnIndexOrThrow("RFC")));
			referencia.setSello_arribo(cursorR.getString(cursorR.getColumnIndexOrThrow("Sello_arribo")));
			referencia.setSello_asignado(cursorR.getString(cursorR.getColumnIndexOrThrow("Sello_asignado")));
			referencia.setNoOperacion(cursorR.getString(cursorR.getColumnIndexOrThrow("IdRazonCierre")));
			referencia.setComentarios_razon_Cierre(cursorR.getString(cursorR.getColumnIndexOrThrow("Comentarios_razon_cierre")));
			referencias.add(referencia);
		}
		Log.v("Previo App", "Referencias:" + referencias.size());
		cursorR.close();
		return referencias;
	}


	 /**
		 * Regresa la lista de facturas obtenidas del cursor recibido como parámetro.
		 * @param cursorF cursor generado por la consulta de facturas
		 * @return la lista de facturas obtenidas del cursor
		 */
	public List<Factura> obtenerFacturas(Cursor cursorF){
		Log.v("Previo App", "CursorFac tiene:" + cursorF.getCount());
		List<Factura> facturas = new ArrayList<Factura>();
		while(cursorF.moveToNext()){
			Factura factura = new Factura();
			factura.setIdFactura(cursorF.getLong(cursorF.getColumnIndexOrThrow("IdFactura")));
			factura.setEstatus(cursorF.getInt(cursorF.getColumnIndexOrThrow("Estatus")));
			factura.setFactura(cursorF.getString(cursorF.getColumnIndexOrThrow("Factura")));
			factura.setIdReferencia(cursorF.getLong(cursorF.getColumnIndexOrThrow("IdReferencia")));
			factura.setProveedor(cursorF.getString(cursorF.getColumnIndexOrThrow("Proveedor")));
			facturas.add(factura);
		}
		cursorF.close();
		Log.v("Previo App", "Facturas:" + facturas.size());
		return facturas;
	}
	

	/**
	 * Regresa la lista de items obtenidos del cursor recibido como parámetro.
	 * @param cursorI cursor generado por la consulta de items
	 * @return la lista de items obtenidos del cursor
	 */
	public List<Item> obtenerItems(Cursor cursorI){
		Log.v("Previo App", "cursorI tiene:" + cursorI.getCount());
		List<Item> items = new ArrayList<Item>();
		while(cursorI.moveToNext()){
			Item item = new Item();
			item.setIdItem(cursorI.getLong(cursorI.getColumnIndexOrThrow("IdItem")));
			item.setEstatus(cursorI.getInt(cursorI.getColumnIndexOrThrow("Estatus")));
			item.setNoParte(cursorI.getString(cursorI.getColumnIndexOrThrow("NoParte")));
			item.setDescripcion(cursorI.getString(cursorI.getColumnIndexOrThrow("Descripcion")));
			item.setCantidadEsp(cursorI.getFloat(cursorI.getColumnIndexOrThrow("CantidadEsp")));
			item.setCantidadRec(cursorI.getFloat(cursorI.getColumnIndexOrThrow("CantidadRec")));
			item.setComentarios(cursorI.getString(cursorI.getColumnIndexOrThrow("Comentarios")));
			item.setValorPartida(cursorI.getFloat(cursorI.getColumnIndexOrThrow("Valor_partida")));
			item.setFraccionArancelaria(cursorI.getString(cursorI.getColumnIndexOrThrow("Fraccion_arancelaria")));
			item.setSerie(cursorI.getString(cursorI.getColumnIndexOrThrow("Serie")));
			item.setMarca(cursorI.getString(cursorI.getColumnIndexOrThrow("Marca")));
			item.setIdFactura(cursorI.getLong(cursorI.getColumnIndexOrThrow("IdFactura")));
			item.setSku(cursorI.getString(cursorI.getColumnIndexOrThrow("Sku")));
			item.setIdUnidadMedida(cursorI.getLong(cursorI.getColumnIndexOrThrow("IdUnidadMedida")));
			item.setPesoKg(cursorI.getFloat(cursorI.getColumnIndexOrThrow("PesoKG")));
			items.add(item);
		}
		cursorI.close();
		Log.v("Previo App", "items:" + items.size());
		return items;
	}
	
	
	/**
	 * Regresa la lista de noms obtenidos del cursor recibido como parámetro.
	 * @param cursorN cursor generado por la consulta de noms
	 * @return la lista de noms obtenidos del cursor
	 */
	public List<NomItem> obtenerNoms(Cursor cursorN){
		List<NomItem> noms = new ArrayList<NomItem>();
		while(cursorN.moveToNext()){
				NomItem nom = new NomItem();
				nom.setEstatus(cursorN.getInt(cursorN.getColumnIndexOrThrow("Estatus")));
				noms.add(nom);
		}
		return noms;
	}


	/**
	 * Regresa la lista con los contadores de los elementos, que pertenecen a cada uno de los elementos de la lista recibida como parámetro,
	 * de acuerdo al estatus en que se encuentren, que pueden ser revisado, en revisión y sin revisar.   
	 * @param lista lista de elementos sobre la que se quiere obtener sus contadores
	 * @param tipo tipo de lista recibida, puede ser de referencias, facturas o items 
	 * @param ctx contexto de la aplicación
	 * @return lista de listas de cadenas con los estatus y los contadores
	 */
	public List<List<String>> getContadorClasif(List lista, int tipo, Context ctx){
		List<List<String>> listaConteo = new ArrayList<List<String>>();
		List facsItms = null;
		int estado = 0;
		String totalE = "Total: ";
		for(int j = 0; j < lista.size(); j++){
			List<String> conteo = new ArrayList<String>();
			if(tipo == Constantes.TIPOlISTADO_REFERENCIA){
				String[] argsFac = {String.valueOf(((Referencia)lista.get(j)).getIdReferencia())};
				facsItms = obtenerFacturas(ctx.getContentResolver().query(Uri.parse(MyContentProvider.URL+"Factura"), null, "IdReferencia = ?", argsFac, null));
				totalE = ctx.getResources().getString(R.string.str_lista_Facturas);
			}
			else{
				if(tipo == Constantes.TIPOlISTADO_FACTURA){
					String[] argsItm = {String.valueOf(((Factura)lista.get(j)).getIdFactura())};
					facsItms = obtenerItems(ctx.getContentResolver().query(Uri.parse(MyContentProvider.URL+"Item"), null, "IdFactura = ?", argsItm, null));
					totalE = ctx.getResources().getString(R.string.str_lista_Item);
				}
				else{
					if(tipo == Constantes.TIPOlISTADO_ITEM){
						Log.v("Previo App", "ingresando al contador de noms para items" + ((Item)lista.get(j)).getIdItem());
						String[] argsItm = {String.valueOf(((Item)lista.get(j)).getIdItem())};
						facsItms = obtenerNoms(ctx.getContentResolver().query(Uri.parse(MyContentProvider.URL+"NomItem"), null, "IdItem = ?", argsItm, null));
						totalE = ctx.getResources().getString(R.string.str_lista_Noms);
					}
				}
			}
			int revisadas = 0;
			int sinrevisar = 0;
			int enrevision = 0;
			for(int f = 0; f < facsItms.size(); f++){
				if(tipo == Constantes.TIPOlISTADO_REFERENCIA){
					estado = ((Factura)facsItms.get(f)).getEstatus();
				}
				else{
					if(tipo == Constantes.TIPOlISTADO_FACTURA){
						estado = ((Item)facsItms.get(f)).getEstatus();
					}
					else{
						if(tipo == Constantes.TIPOlISTADO_ITEM){
							estado = (int) ((NomItem)facsItms.get(f)).getEstatus();
						}
					}
				}
				switch(estado){
					case Constantes.ESTATUS_REVISADO:
						revisadas++;
						break;
					case Constantes.ESTATUS_EN_REVISION:
						enrevision++;
						break;
					case Constantes.ESTATUS_SIN_REVISAR:
						sinrevisar++;
						break;
				}
			}
			conteo.add(totalE + facsItms.size());
			conteo.add( ctx.getText(R.string.str_lista_R).toString() + revisadas);
			conteo.add(ctx.getText(R.string.str_lista_ER).toString() + enrevision);
			conteo.add(ctx.getText(R.string.str_lista_SR).toString() + sinrevisar);
	//		conteo.add("Total: " + facsItms.size()+",    R: " + revisadas+",    ER: " + enrevision+",    SR: " + sinrevisar);
			listaConteo.add(conteo);
		}
		
		return listaConteo;
	}
	
	
	
	/**
	 * Regresa la factura correspondiente al id recibido como parámetro.
	 * @param id_fact id de la factura que se quiere obtener
	 * @param context contexto de la aplicación
	 * @return la factura correspondiente al id
	 */
	public Factura obtener_factura(long id_fact, Context context){
		Factura factura = new Factura();
		/*String[] colFactura = { "IdFactura", "IdReferencia",
				 "Factura", "Cantidad", "FechaFactura",
				"Estatus", "Proveedor", "OrdenCompra" };*/
		
		Cursor cursor_fact = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Factura"), null,
				"idFactura = " + id_fact, null, null);
		
		if(cursor_fact.moveToFirst()){
			factura.setIdFactura(cursor_fact.getLong(0));
			factura.setIdReferencia(cursor_fact.getLong(1));
			factura.setFactura(cursor_fact.getString(2));
			factura.setCantidad(cursor_fact.getInt(3));
			factura.setFechaFactura(cursor_fact.getString(4));
			factura.setEstatus(cursor_fact.getInt(5));
			factura.setProveedor(cursor_fact.getString(6));
			factura.setOrdenCompra(cursor_fact.getString(7));
		}
		
		return factura;
		
	}
	
}

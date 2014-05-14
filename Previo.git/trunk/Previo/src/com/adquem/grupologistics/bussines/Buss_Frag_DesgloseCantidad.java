package com.adquem.grupologistics.bussines;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebChromeClient.CustomViewCallback;

import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.model.Desglose;
import com.adquem.grupologistics.model.Pais;
import com.adquem.grupologistics.utilities.Constantes;

/**
 * Clase que contiene las reglas de negocio asociadas a Desglose
 * 
 * @author Adquem S.C
 * @version 1.0
 * 
 */
public class Buss_Frag_DesgloseCantidad {

	/**
	 * M�todo que devuelve los desgloses asociados a un item en la base de datos
	 * 
	 * @param id_item
	 *            Id del ítem
	 * @param context
	 *            Contexto de la aplicación
	 * @return ArrayList de objetos tipo Desglose
	 */
	public ArrayList<Desglose> obtinene_degloses(long id_item, Context context) {

		String[] colDesglose = { "IdDesglose", "IdItem", "Cantidad",
				"Descripcion", "IdPais","Estatus" };

		Cursor cursor = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Desglose"), colDesglose,
				"idItem = " + id_item +" AND Estatus="+Constantes.ESTATUS_REVISADO, null, null);
		ArrayList<Desglose> desgloses_item = new ArrayList<Desglose>();
		int i = 0;

		if (cursor.moveToFirst() != false) {
			do {
				Desglose desglose_lista = new Desglose();
				desglose_lista.setIdDesglose(cursor.getLong(0));
				desglose_lista.setIdItem(cursor.getLong(1));
				desglose_lista.setCantidad(cursor.getInt(2));
				desglose_lista.setDescripcion(cursor.getString(3));
				desglose_lista.setIdPais(cursor.getLong(4));
				desgloses_item.add(i, desglose_lista);
				Log.v("idDesgose", cursor.getLong(0) + "");
				Log.v("idItem", "" + cursor.getLong(1));
				i = i + 1;
			} while (cursor.moveToNext());
		}

		cursor.close();

		return desgloses_item;

	}

	/**
	 * Método que inserta una lista de desgloses para un ítem determinado en la
	 * base de datos
	 * 
	 * @param id_item
	 *            Id del ítem al que se le asociarón los desgloses a insertar
	 * @param desgloses
	 *            Arreglo de desgloses a insertar en la base de datos
	 * @param context
	 *            Contexto de la aplicación
	 * @return
	 */
	public long[] crea_desgloses(long id_item, ArrayList<Desglose> desgloses,
			Context context) {

		ContentValues values_desglose = new ContentValues();
		Uri uri_desglose;
		//int count_desgloses = 0;
		long ids_desglose[];
		
		ids_desglose = new long[desgloses.size()];

		for (int i = 0; i < desgloses.size(); i++) {
			Log.v("busDesglose", "Entro for buss" + i);
			values_desglose.put("IdItem", id_item);
			// values_desglose.put("FolioDesglose", "NA");
			values_desglose.put("Descripcion", desgloses.get(i)
					.getDescripcion());
			values_desglose.put("Cantidad", desgloses.get(i).getCantidad());
			values_desglose.put("IdPais", desgloses.get(i).getIdPais());
			values_desglose.put("Estatus", Constantes.ESTATUS_REVISADO);

			uri_desglose = context.getContentResolver().insert(
					Uri.parse(MyContentProvider.URL + "Desglose"),
					values_desglose);

			String[] uri = uri_desglose.toString().split("/");

			if (Long.parseLong(uri[3]) > 0) {
				ids_desglose[i] = Long.parseLong(uri[3]);
						//count_desgloses = count_desgloses + 1;
			}
			Log.v("Bus desglose","id nuevo "+Long.parseLong(uri[3]));

		}

		return ids_desglose;

	}

	/**
	 * Método que realiza la eliminación de varios desgloses asociados a un ítem
	 * en la base de datos
	 * 
	 * @param desgloses
	 *            Lista de desgloswes a eliminar
	 * @param context
	 *            Contexto de la aplicación
	 * @return Número de inserciones exitosas en la base de datos
	 */
	public int elimina_desgloses(ArrayList<Desglose> desgloses, Context context) {

		int contador = 0;

		for (int i = 0; i < desgloses.size(); i++) {
			context.getContentResolver().delete(
					Uri.parse(MyContentProvider.URL + "Desglose"),
					"idDesglose = " + desgloses.get(i).getIdDesglose(), null);
			contador = contador + 1;
		}

		return contador;

	}

	/**
	 * Método que realiza la eliminación de un desglose en específico de la base
	 * de datos
	 * 
	 * @param id_desglose
	 *            Id del ítem
	 * @param contex
	 *            Contexto de la aplicación
	 * @return Número de registros eliminados
	 */
	public int elimina_desglose(long id_desglose, Context contex) {
		int delete;

		delete = contex.getContentResolver().delete(
				Uri.parse(MyContentProvider.URL + "Desglose"),
				"idDesglose = " + id_desglose, null);

		return delete;
	}

	/**
	 * Método que obtiene el país asociado al primer desglose de un ítem
	 * 
	 * @param id_item
	 *            Identificador del ítem
	 * @param context
	 *            Contexto de la aplicación
	 * @return Nombre del primer país en los desgloses
	 */
	public String obtiene_primer_pais(long id_item, Context context) {

		String[] col_desglose = { "IdDesglose", "IdItem", "IdPais" };
		Cursor cursor = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Desglose"), col_desglose,
				"idItem = " + id_item, null, null);
		long id_pais;
		String primer_pais = null;

		if (cursor.moveToFirst() != false) {
			id_pais = cursor.getLong(2);

			String[] col_pais = { "IdPais", "Pais" };
			Cursor cursor_pais = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Pais"), col_pais,
					"idPais = " + id_pais, null, null);

			if (cursor_pais.moveToFirst() != false)
				primer_pais = cursor_pais.getString(1);

			cursor_pais.close();
		}
		cursor.close();

		return primer_pais;

	}

	/**
	 * Obtiene la lista de países disponibles
	 * 
	 * @param context
	 *            Contexto de la aplicación
	 * @return Lista de registros en la entidad Pais
	 */
	public ArrayList<Pais> obtiene_paises(Context context) {

		Cursor cursor_pais = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Pais"), null, null, null,
				null);

		ArrayList<Pais> paises = new ArrayList<Pais>();
		Log.v("tamanio cursor", cursor_pais.getCount() + "");

		while (cursor_pais.moveToNext()) {

			Pais pais = new Pais();
			Log.v("previo",
					"id IdPais:"
							+ cursor_pais.getLong(cursor_pais
									.getColumnIndexOrThrow("IdPais")));
			Log.v("previo",
					"Pais:"
							+ cursor_pais.getString(cursor_pais
									.getColumnIndexOrThrow("Pais")));
			pais.setIdPais(cursor_pais.getLong(cursor_pais
					.getColumnIndexOrThrow("IdPais")));
			pais.setPais(cursor_pais.getString(cursor_pais
					.getColumnIndexOrThrow("Pais")));
			paises.add(pais);
		}

		cursor_pais.close();

		return paises;

	}

	/**
	 * Obtiene el nombre de un país
	 * 
	 * @param id_pais
	 *            Identificador de un país
	 * @param context
	 *            Contexto de la aplicación
	 * @return Nombre del país
	 */
	public String getPais(long id_pais, Context context) {
		String[] col_pais = { "IdPais", "Pais" };
		String pais = "";
		Cursor cursor_pais = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Pais"), col_pais,
				"IdPais = " + id_pais, null, null);
		if (cursor_pais.moveToFirst()) {
			pais = cursor_pais.getString(1);
		}

		cursor_pais.close();

		return pais;
	}

	/**
	 * Obtiene lista de desgloses de un conjunto de ítems
	 * 
	 * @param Items
	 *            Lista de ítems
	 * @param context
	 *            Contexto de la aplicación
	 * @return Lista de desgloses
	 */
	public ArrayList<Desglose> obtinene_todos_degloses(List<Item> Items,
			Context context) {
		ArrayList<Desglose> desgloses_item = new ArrayList<Desglose>();
		String[] colDesglose = { "IdDesglose", "IdItem", "Cantidad",
				"Descripcion", "IdPais" };
		for (int r = 0; r < Items.size(); r++) {
			Cursor cursor = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Desglose"), colDesglose,
					"idItem = " + Items.get(r).getIdItem()+" AND Estatus="+Constantes.ESTATUS_REVISADO, null, null);
			

			int i = 0;

			if (cursor.moveToFirst() != false) {
				do {
					Desglose desglose_lista = new Desglose();
					desglose_lista.setIdDesglose(cursor.getLong(0));
					desglose_lista.setIdItem(cursor.getLong(1));
					desglose_lista.setCantidad(cursor.getInt(2));
					desglose_lista.setDescripcion(cursor.getString(3));
					desglose_lista.setIdPais(cursor.getLong(4));
					desgloses_item.add(i, desglose_lista);
					Log.v("idDesglose", cursor.getLong(0) + "");
					Log.v("idItem", "" + cursor.getLong(1));
					i = i + 1;
				} while (cursor.moveToNext());
			}
			cursor.close();
		}

		return desgloses_item;

	}

}

package com.adquem.grupologistics.bussines;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.model.Nom;
import com.adquem.grupologistics.model.UnidadMedida;
import com.adquem.grupologistics.utilities.Constantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Capa de negocio para el alta del ítem
 * 
 * @author Adquem S.C
 * @version 1.0
 * 
 */
public class Buss_Frag_AltaItem {

	private static final String TAG = "Listado Previo App";

	/**
	 * Realiza el alta de un nuevo ítem en la base de datos
	 * 
	 * @param num_Parte
	 *            Número de parte del ítem
	 * @param descripcion
	 *            Comentarios del ítem
	 * @param cantidad
	 *            Cantidad del ítem
	 * @param unidad_medida
	 *            Identificador de la unidad de medida
	 * @param noms_id
	 *            Arreglo de identificadores de las nom
	 * @param context
	 *            Contexto de la aplicación
	 * @param id_referencia
	 *            Identificador de la referncia
	 * @return Identificador del ítem nuevo
	 */
	public long alta(String num_Parte, String descripcion, float cantidad,
			long unidad_medida, ArrayList<Long> noms_id, Context context,
			long id_referencia) {
		long itemNvo;
		long id_fact_ficticia = 0;
		int count_nomItem = 0;

		Log.v("BussAlta", "Num parte " + num_Parte);

		id_fact_ficticia = existeFacturaFicticia(id_referencia, context);

		if (id_fact_ficticia == 0) {
			id_fact_ficticia = creaFacturaFicticia(id_referencia, context);
		}

		Log.v("FECHA AHORA", "Después de crear factura");
		
		ContentValues values_item = new ContentValues();
		values_item.put("IdItem",generateUniqueId());
		values_item.put("IdFactura", id_fact_ficticia);
		values_item.put("CantidadEsp", 0f);
		values_item.put("CantidadRec", cantidad);
		values_item.put("NoParte", num_Parte);
		values_item.put("SKU", Constantes.VALUE_DEFAULT_STRING);
		values_item.put("IdUnidadMedida", unidad_medida);
		values_item.put("Valor_partida", 0f);
		values_item.put("Fraccion_arancelaria", Constantes.VALUE_DEFAULT_STRING);
		values_item.put("Descripcion", Constantes.VALUE_DEFAULT_STRING);
		values_item.put("PesoKg", 0f);
		values_item.put("Marca", Constantes.VALUE_DEFAULT_STRING);
		values_item.put("Serie", Constantes.VALUE_DEFAULT_STRING);
		values_item.put("Comentarios", descripcion);
		values_item.put("Estatus", Constantes.ESTATUS_REVISADO);

		Uri uri_item = context.getContentResolver().insert(
				Uri.parse(MyContentProvider.URL + "Item"), values_item);

		String[] uri = uri_item.toString().split("/");

		itemNvo = Long.parseLong(uri[3]);

		Log.v(TAG, "valor inserted id Item:" + itemNvo);

		if (Long.parseLong(uri[3]) > 0) {
			ContentValues values_nom_item = new ContentValues();
			Uri uri_nom_item = null;

			for (int i = 0; i < noms_id.size(); i++) {
				values_nom_item.put("IdNom", noms_id.get(i));
				values_nom_item.put("IdItem", itemNvo);
				values_nom_item.put("Estatus", Constantes.ESTATUS_SIN_REVISAR);
				uri_nom_item = context.getContentResolver().insert(
						Uri.parse(MyContentProvider.URL + "NomItem"),
						values_nom_item);

				String[] uri2 = uri_nom_item.toString().split("/");

				if (Long.parseLong(uri2[3]) > 0)
					count_nomItem++;
			}

		}
		Log.v("BusAltaItem", "NomsInsertadas " + count_nomItem);

		return itemNvo;

	}

	/**
	 * Crea factura ficticia en la base de datos
	 * 
	 * @param id_referencia
	 *            Identificador de la referencia
	 * @param context
	 *            Contexto de la aplicación
	 * @return Identificador de la nueva factura
	 */
	public long creaFacturaFicticia(long id_referencia, Context context) {

		long facturaNva = 0;

		ContentValues values = new ContentValues();
		values.put("IdReferencia", id_referencia);
		values.put("IdFactura", generateUniqueId());
		values.put("Factura", Constantes.NOMBRE_FACTURA_FICTICIA);
		values.put("Cantidad", 0);
		values.put("FechaFactura", getFechaActual());
		values.put("Estatus", Constantes.ESTATUS_EN_REVISION);
		values.put("Proveedor", Constantes.VALUE_DEFAULT_STRING);
		values.put("OrdenCompra", Constantes.VALUE_DEFAULT_STRING);

		Uri uri_fact = context.getContentResolver().insert(
				Uri.parse(MyContentProvider.URL + "Factura"), values);

		String[] uri = uri_fact.toString().split("/");

		if (Long.parseLong(uri[3]) != 0) {

			facturaNva = Long.parseLong(uri[3]);

			Log.v(TAG, "valor inserted id Factura:" + facturaNva);

		}

		return facturaNva;

	}

	/**
	 * Obtiene la fecha actual con el formato dd/MM/yy hh:mm:ss
	 * 
	 * @return Fecha actual con el formato dd/MM/yy hh:mm:ss
	 */
	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
		Log.v("FECHA AHORA", "ahora: " + ahora.getTime());
		return formateador.format(ahora);
	}

	/**
	 * Valida la existencia de una factura ficticia para una referencia
	 * 
	 * @param id_referencia
	 *            Identificador de una referencia
	 * @param context
	 *            Contexto de la aplicación
	 * @return Identificador de la factura ficticia, 0 si no existe
	 */
	public long existeFacturaFicticia(long id_referencia, Context context) {

		long id_factura = 0;

		String[] colums = { "IdFactura", "Factura" };
		Cursor cursor = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Factura"), colums,
				"idReferencia = " + id_referencia + "", null, null);
		if (cursor.moveToFirst() != false) {
			do {
				if (cursor.getString(1).equals(
						Constantes.NOMBRE_FACTURA_FICTICIA)) {

					Log.i("IdFactura", cursor.getLong(0) + "");
					Log.i("Factura", cursor.getString(1));

					id_factura = cursor.getLong(0);
					break;
				}
			} while (cursor.moveToNext());
		}

		cursor.close();

		return id_factura;

	}

	/**
	 * Obtiene identificador de una referencia en base al id del ítem
	 * 
	 * @param id_item
	 *            Identificador del ítem
	 * @param context
	 *            Contexto de la aplicación
	 * @return Identificador de la referencia
	 */
	public long obtiene_referencia(long id_item, Context context) {

		String[] colFactura = { "IdFactura", "IdReferencia" };
		String[] colItem = { "IdItem", "IdFactura" };

		long id_fact;
		long id_ref = 0;

		Cursor cursor_item = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Item"), colItem,
				"IdItem = " + id_item, null, null);

		if (cursor_item.moveToFirst() != false) {
			id_fact = cursor_item.getLong(1);

			Cursor cursor_fact = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Factura"), colFactura,
					"idFactura = " + id_fact, null, null);

			if (cursor_fact.moveToFirst() != false) {
				id_ref = cursor_fact.getLong(1);

			}
		}

		cursor_item.close();

		return id_ref;
	}

	/**
	 * Obtiene arreglo de Noms
	 * 
	 * @param context
	 *            Contexto de la aplicación
	 * @return Lista de objetos tipo Nom
	 */
	public ArrayList<Nom> obtiene_noms(Context context) {

		ArrayList<Nom> noms = new ArrayList<Nom>();
		Cursor cursor_nom;

		cursor_nom = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Nom"),
				new String[] { "DISTINCT IdNom", "Nom" }, // DISTINCT
				null, // WHERE
				null, null);

		if (cursor_nom.moveToFirst() != false) {
			do {
				Nom nom = new Nom();
				nom.setIdNom(cursor_nom.getLong(cursor_nom
						.getColumnIndexOrThrow("IdNom")));
				nom.setNom(cursor_nom.getString(cursor_nom
						.getColumnIndexOrThrow("Nom")));

				noms.add(nom);
			} while (cursor_nom.moveToNext());
		}

		cursor_nom.close();

		return noms;

	}

	/**
	 * Lista de unidades de medida
	 * 
	 * @param context
	 *            Contexto de la aplicación
	 * @return Lista de objetos tipo UnidadMedida
	 */
	public ArrayList<UnidadMedida> obtiene_unidad_medida(Context context) {

		ArrayList<UnidadMedida> unidades = new ArrayList<UnidadMedida>();

		Cursor cursor_unidad = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "CatunidadMedida"), null,
				null, null, null);

		Log.v("tamanio cursor", cursor_unidad.getCount() + "");

		while (cursor_unidad.moveToNext()) {
			UnidadMedida unidad = new UnidadMedida();
			Log.v("previo",
					"id um:"
							+ cursor_unidad.getLong(cursor_unidad
									.getColumnIndexOrThrow("IdUnidadMedida")));
			Log.v("previo",
					"unidad:"
							+ cursor_unidad.getString(cursor_unidad
									.getColumnIndexOrThrow("Unidad")));
			unidad.setIdUnidadMedida(cursor_unidad.getLong(cursor_unidad
					.getColumnIndexOrThrow("IdUnidadMedida")));
			unidad.setUnidad(cursor_unidad.getString(cursor_unidad
					.getColumnIndexOrThrow("Unidad")));
			unidades.add(unidad);
		}

		cursor_unidad.close();

		return unidades;

	}

	/**
	 * Obtiene estatus de referencia
	 * 
	 * @param id_ref
	 *            Identificador de referencia
	 * @param context
	 *            Contexto de la aplicación
	 * @return Estatus de referencia
	 */
	public int obtiene_estatus_referencia(long id_ref, Context context) {

		Cursor cursor_referencia = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Referencia"), null,
				"idReferencia = " + id_ref, null, null);

		int estatus = 0;

		if (cursor_referencia.moveToFirst()) {
			estatus = cursor_referencia.getInt(cursor_referencia
					.getColumnIndexOrThrow("Estatus"));
		}

		return estatus;
	}
	
	   public static int generateUniqueId() {      
	        UUID idOne = UUID.randomUUID();
	        String str=""+idOne;        
	        int uid=str.hashCode();
	        String filterStr=""+uid;
	        str=filterStr.replaceAll("-", "");
	        return Integer.parseInt(str);
	    }

}

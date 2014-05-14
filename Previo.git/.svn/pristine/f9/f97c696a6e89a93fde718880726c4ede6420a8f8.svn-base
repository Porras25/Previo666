package com.adquem.grupologistics.bussines;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.model.Nom;
import com.adquem.grupologistics.utilities.Constantes;

/**
 * Clase que contiene las reglas de negocio relacionadas con la revisión del
 * ítem
 * @author Adquem S.C
 * @version 1.0
 */
public class Buss_Frag_RevisionItem {

	/**
	 * Método que obtiene el item de acuerdo a su identificador
	 * 
	 * @param id_item
	 *            Identificador del ítem
	 * @param context
	 *            Contexto de la aplicación
	 * @return Item encontrado
	 */
	public Item obtiene_item(long id_item, Context context) {

		String[] col_item = { "IdItem", "IdFactura", "CantidadEsp",
				"CantidadRec", "NoParte", "SKU", "IdUnidadMedida",
				"Valor_partida", "Fraccion_arancelaria", "Descripcion",
				"PesoKg", "Marca", "Serie", "Comentarios", "Estatus" };

		Item item = new Item();
		Cursor cursor_item = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Item"), col_item,
				"idItem = " + id_item, null, null);
		if (cursor_item.moveToFirst() != false) {
			item.setIdItem(cursor_item.getLong(0));
			item.setIdFactura(cursor_item.getLong(1));
			item.setCantidadEsp(cursor_item.getFloat(2));
			item.setCantidadRec(cursor_item.getFloat(3));
			item.setNoParte(cursor_item.getString(4));
			item.setSku(cursor_item.getString(5));
			item.setIdUnidadMedida(cursor_item.getLong(6));
			item.setValorPartida(cursor_item.getFloat(7));
			item.setFraccionArancelaria(cursor_item.getString(8));
			item.setDescripcion(cursor_item.getString(9));
			item.setPesoKg(cursor_item.getFloat(10));
			item.setMarca(cursor_item.getString(11));
			item.setSerie(cursor_item.getString(12));
			item.setComentarios(cursor_item.getString(13));
			item.setEstatus(cursor_item.getInt(14));
		}

		cursor_item.close();

		return item;

	}

	/**
	 * Actualización del campo de comentarios de un item
	 * 
	 * @param item
	 *            Ítem a actualizar
	 * @param comentarios
	 *            Comentarios del ítem en la base de datos
	 */
	public void actualiza_comentarios(Item item, String comentarios) {
		item.setComentarios(comentarios);
	}

	/**
	 * Obtiene el número de referencia y cliente en base a un id de factura
	 * @param id_Fact Identificador de una factura
	 * @param context Contexto de la aplicación
	 * @return Número de referencia y cliente
	 */
	public String[] obtiene_referencia(long id_Fact, Context context) {

		String[] colReferencia = { "IdReferencia", "NoReferencia", "Cliente" };
		String[] colFactura = { "IdFactura", "IdReferencia" };

		String datosRef[] = { "", "" };
		Cursor cursor_fact = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Factura"), colFactura,
				"idFactura = " + id_Fact, null, null);

		if (cursor_fact.moveToFirst() != false) {
			long id_ref = cursor_fact.getLong(1);

			Cursor cursor_ref = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Referencia"),
					colReferencia, "idReferencia = " + id_ref, null, null);

			if (cursor_ref.moveToFirst() != false) {
				datosRef[0] = cursor_ref.getString(1);
				datosRef[1] = cursor_ref.getString(2);
			}

			cursor_ref.close();

		}

		cursor_fact.close();

		return datosRef;
	}

	/**
	 * Obtiene el nombre de la unidad de medida en base a su id
	 * @param id_unidadMed Identificador de la unidad de medida
	 * @param context Contexto de la aplicación
	 * @return Unidad de medida
	 */
	public String obtiene_unidadMedida(long id_unidadMed, Context context) {
		String[] coltUnidadMedida = { "IdUnidadMedida", "Unidad" };

		String unidad = "";

		Cursor cursor_unidad = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "CatunidadMedida"),
				coltUnidadMedida, "IdUnidadMedida = " + id_unidadMed, null,
				null);

		if (cursor_unidad.moveToFirst() != false) {
			unidad = cursor_unidad.getString(1);
		}

		cursor_unidad.close();

		return unidad;
	}

	/**
	 * Método que obtiene el pais del primer desglose encontrado en la base de
	 * datos
	 * @param id_item
	 *            Identificador del ítem
	 * @param context
	 *            Contexto de la aplicación
	 * @return Nombre del país
	 */
	public String obtiene_pais_origen(long id_item, Context context) {
		Log.v("buss ", "Entra a obtiene pais origen");
		String[] colDesglose = { "IdDesglose", "IdItem", "IdPais" };
		String[] colPais = { "IdPais", "Pais" };

		long id_pais;
		String pais_origen = "";

		Cursor cursor_desglose = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Desglose"), colDesglose,
				"IdItem = " + id_item, null, null);

		if (cursor_desglose.moveToFirst() != false) {
			id_pais = cursor_desglose.getLong(2);

			Cursor cursor_pais = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Pais"), colPais,
					"IdPais = " + id_pais, null, null);

			if (cursor_pais.moveToFirst() != false) {
				pais_origen = cursor_pais.getString(1);
			}

			cursor_pais.close();
		}

		cursor_desglose.close();

		return pais_origen;
	}

	/**
	 * Método que actualiza la cantidad recibida de un item
	 * 
	 * @param item
	 *            Ítem a actualizar
	 * @param cantidad_recibida
	 *            Cantidad recibida
	 */
	public void actualiza_cantidad(Item item, float cantidad_recibida) {
		item.setCantidadRec(cantidad_recibida);
	}

	/**
	 * Método que obtiene las noms asociadas a un item
	 * 
	 * @param id_item
	 *            Identificador de ítem
	 * @param context
	 *            Contexto de la aplicación
	 * @return Lista de noms asociados a un ítem
	 */
	public ArrayList<Nom> obtiene_noms(long id_item, Context context) {

		String[] col_nom_item = { "IdNom_Item", "IdNom", "IdItem" };
		long id_nom[] = {};
		ArrayList<Nom> noms = new ArrayList<Nom>();
		int i = 0;

		Cursor cursor_nom_item = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "NomItem"), col_nom_item,
				"idItem = " + id_item, null, null);

		if (cursor_nom_item.moveToFirst() != false) {
			do {
				// Log.i("IdFactura", cursor.getInt(0)+"");
				// Log.i("NumFactura", cursor.getString(1));

				id_nom[i] = cursor_nom_item.getLong(1);
			} while (cursor_nom_item.moveToNext());
		}

		String[] col_nom = { "IdNom", "IdColNom" };
		Cursor cursor_nom = null;
		Nom nom = null;

		for (int j = 0; j < id_nom.length; j++) {
			cursor_nom = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Nom"), col_nom,
					"IdNom = " + id_nom[j], null, null);

			if (cursor_nom.moveToFirst() != false) {
				do {
					nom.setIdNom(cursor_nom.getLong(0));
					// nom.setIdColNom(1);
					// Log.i("IdFactura", cursor.getInt(0)+"");
					// Log.i("NumFactura", cursor.getString(1));
					noms.add(j, nom);
					nom = null;
				} while (cursor_nom.moveToNext());
			}

		}
		cursor_nom.close();
		cursor_nom_item.close();
		return noms;

	}

	/**
	 * Actualiza el ítem de acuerdo a su identificador
	 * 
	 * @param id_item
	 *            Identificador del ítem
	 * @param cantidad_rec
	 *            Cantidad recibida del ítem
	 * @param comentarios
	 *            Comentarios del ítem
	 * @param context
	 *            Contexto de la aplicación
	 * @return Número de registros actualizados en la base de datos
	 */
	public int actualiza_revision(long id_item, float cantidad_rec,
			String comentarios, Context context) {

		int update = 0;

		ContentValues updateValues = new ContentValues();
		updateValues.put("CantidadRec", cantidad_rec);
		updateValues.put("Comentarios", comentarios);

		try {
			update = context.getContentResolver().update(
					Uri.parse(MyContentProvider.URL + "Item"), updateValues,
					"idItem = " + id_item, null);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Buss_Frag_RevisionItem", "actualiza_revision", e);
			e.printStackTrace();
		}

		return update;

	}

	/**
	 * Relaiza validaciones correspondientes para determinar la finalización de la revisión de la referencia
	 * @param id_item Identificador de un ítem
	 * @param context Contexto de la aplicación
	 * @return True si la revisión de la referencia puede ser finalizada
	 */
	public boolean terminar_revision(long id_item, Context context) {
		boolean revisado = false;
		boolean campos = false;
		boolean fotos = false;
		boolean desgloses = false;
		boolean noms = false;

		// Revisa fotos
		// String[] colAdj_Itm = { "IdAdj", "Concepto", "IdItem"};
		try {

			Cursor cursor_campos = context
					.getContentResolver()
					.query(Uri.parse(MyContentProvider.URL + "Item"),
							null,
							"IdItem = "
									+ id_item
									+ " and (CantidadRec is null or CantidadRec = 0 or Comentarios is null)",
							null, null);

			if (cursor_campos.getCount() == 0)
				campos = true;

			Cursor cursor = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Adjunto_Itm"),
					null,
					"IdItem = " + id_item + " and Concepto = "
							+ Constantes.ID_CONCEPTO_FOTO_ITEM, null, null);
			Log.v("Bus Rev item", "tam cursor " + cursor.getCount());
			if (cursor.getCount() > 0)
				fotos = true;

			// Revisa desglose

			Cursor cursor2 = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Desglose"), null,
					"IdItem = " + id_item, null, null);

			Log.v("Bus Rev item", "tam cursor2 " + cursor2.getCount());
			if (cursor2.getCount() > 0)
				desgloses = true;

			// Revisa nom

			Cursor cursor3 = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "NomItem"),
					null,
					"IdItem = " + id_item + " and Estatus = "
							+ Constantes.ESTATUS_SIN_REVISAR, null, null);

			/*
			 * Cursor cursor4 = context.getContentResolver().query(
			 * Uri.parse(MyContentProvider.URL + "NomItem"), null, "IdItem = " +
			 * id_item + " and (Estatus = " + Constantes.ESTATUS_REVISADO+")",
			 * null, null);
			 */

			Log.v("Bus Rev item", "tam cursor3 " + cursor3.getCount());
			// Log.v("Bus Rev item", "tam cursor4 "+cursor4.getCount());

			if (cursor3.getCount() == 0)
				noms = true;
			Log.v("Bus Rev item", "noms " + noms);

			Log.v("BusFrag Revision", "footos " + fotos + "desglsoes "
					+ desgloses);
			if (fotos && desgloses && noms && campos)
				revisado = true;

			cursor_campos.close();
			cursor.close();
			cursor2.close();
			cursor3.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("Buss_Frag_RevisionItem", "terminar_revision", e);
			e.printStackTrace();
			return false;
		}

		return revisado;

	}

	/**
	 * Realiza la actualización del estatus del ítem
	 * @param id_item Identificador del ítem
	 * @param estatus Nuevo estatus del ítem
	 * @param context Contexto de la aplicación
	 */
	public void actualiza_estatus_item(long id_item, int estatus, Context context) {
		Log.v("Frag revision", " actualiza_estatus_item");
		ContentValues updateValues = new ContentValues();
		updateValues.put("Estatus", estatus);

		try {
			context.getContentResolver().update(
					Uri.parse(MyContentProvider.URL + "Item"), updateValues,
					"idItem = " + id_item, null);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Buss_Frag_RevisionItem", "actualiza_revision", e);
			e.printStackTrace();
		}

	}

	/**
	 * Realiza la actualización del estatus de la factura
	 * @param id_factura Identificador de la factura
	 * @param estatus Nuevo estatus de la factura
	 * @param context Contexto de la aplicación
	 */
	public void actualiza_estatus_factura(long id_factura, int estatus,
			Context context) {
		Log.v("id_fact", "Actualiza estatus");
		ContentValues updateValues = new ContentValues();
		updateValues.put("Estatus", estatus);

		try {
			context.getContentResolver().update(
					Uri.parse(MyContentProvider.URL + "Factura"), updateValues,
					"IdFactura = " + id_factura, null);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Buss_Frag_RevisionItem", "actualiza_factura", e);
			e.printStackTrace();
		}

	}

	/**
	 * Actualiza el estatus de la referencia
	 * @param id_ref Identificador del estatus de la referencia
	 * @param estatus Nuevo estatus de la referencia
	 * @param context Contexto de la aplicación
	 */
	public void actualiza_estatus_referencia(long id_ref, int estatus,
			Context context) {

		ContentValues updateValues = new ContentValues();
		updateValues.put("Estatus", estatus);

		try {
			context.getContentResolver().update(
					Uri.parse(MyContentProvider.URL + "Referencia"),
					updateValues, "IdReferencia = " + id_ref, null);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Buss_Frag_RevisionItem", "actualiza_estatus_referencia", e);
			e.printStackTrace();
		}

	}

	/**
	 * Realiza validación del estatus de la factura
	 * @param id_fact Identificador de la factura
	 * @param context Contexto de la aplicación
	 */
	public void valida_factura(long id_fact, Context context) {
		Log.v("Frag revision", " valida_factura");
		Cursor cursor_factura = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Item"),
				null,
				"IdFactura = " + id_fact + " and (Estatus = "
						+ Constantes.ESTATUS_SIN_REVISAR + " or Estatus = "
						+ Constantes.ESTATUS_EN_REVISION + ")", null, null);

		long id_ref = 0;
		id_ref = getId_referencia(id_fact, context);

		if (cursor_factura.getCount() == 0) {
			actualiza_estatus_factura(id_fact, Constantes.ESTATUS_REVISADO,
					context);

			Log.v("Bus item rev", "valida_factura " + id_ref);

			if (id_ref != 0) {
				//valida_referencia(id_ref, context);
				Buss_FragListadoFact_RevisionReferencia bus_ref = new Buss_FragListadoFact_RevisionReferencia();
				bus_ref.valida_estatus_Ref(id_ref, context);
			}

		} else {
			actualiza_estatus_factura(id_fact, Constantes.ESTATUS_EN_REVISION,
					context);

			if (id_ref != 0) {
				actualiza_estatus_referencia(id_ref,
						Constantes.ESTATUS_EN_REVISION, context);
			}
		}

		cursor_factura.close();
	}

	/**
	 * Relaiza la validación del estatus de la referencia en  base a los estatus de facturas
	 * @param id_ref Identificador de la referencia
	 * @param context Contexto de la aplicación
	 */
	public void valida_referencia(long id_ref, Context context) {

		Cursor cursor_factura = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Factura"),
				null,
				"IdReferencia = " + id_ref + " and (Estatus = "
						+ Constantes.ESTATUS_SIN_REVISAR + " or Estatus = "
						+ Constantes.ESTATUS_EN_REVISION + ")", null, null);
		Log.v("Bus item rev", "valida referencia " + id_ref);
		if (cursor_factura.getCount() == 0) {
			actualiza_estatus_referencia(id_ref, Constantes.ESTATUS_REVISADO,
					context);
			
			ContentValues updateValues = new ContentValues();
			 updateValues.put("Fecha_fin_previo", getFechaActual() );
			 Log.v("FECHA REVISION","FECHA REVISION"+ getFechaActual());
			 try {
				 context.getContentResolver().update(Uri.parse(MyContentProvider.URL+"Referencia"), updateValues, "IdReferencia = "+id_ref, null);	
			} catch (Exception e) {
				Log.v("BusAltaItem", "Falló actualización fecha fin referencia");
			}	
			 

		} else {
			actualiza_estatus_referencia(id_ref,
					Constantes.ESTATUS_EN_REVISION, context);
		}
		cursor_factura.close();
	}
	
	/**
	 * Obtiene fecha al instante en el formato dd/MM/yy hh:mm:ss 
	 * @return Fecha con el formato dd/MM/yy hh:mm:ss
	 */
	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
		return formateador.format(ahora);
	}

	/**
	 * Obtiene el id de la referencia asociado a un id de una factura
	 * @param id_fact Identificador de la factura
	 * @param context Contexto de la aplicación
	 * @return Identificador de la referencia
	 */
	public long getId_referencia(long id_fact, Context context) {
		String[] colFactura = { "IdFactura", "IdReferencia", "Factura",
				"Estatus" };
		long id_referencia = 0;

		Cursor cursor_fact = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Factura"), colFactura,
				"IdFactura = " + id_fact, null, null);
		
		if (cursor_fact.moveToFirst()) {
			id_referencia = cursor_fact.getLong(1);
		}
		cursor_fact.close();
		return id_referencia;
	}
	
	/**
	 * Obtiene el id de la referencia asociado a un id de una factura
	 * @param id_ref Identificador de la referencia
	 * @param context Contexto de la aplicación
	 * @return Identificador de la factura
	 */
	public long getId_factura(long id_ref, Context context) {
		String[] colFactura = { "IdFactura", "IdReferencia", "Factura",
				"Estatus" };
		long id_fact = 0;

		Cursor cursor_fact = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Factura"), colFactura,
				"IdReferencia = " + id_ref + " and Factura = '" + Constantes.NOMBRE_FACTURA_FICTICIA+"'", null , null);
		
		if (cursor_fact.moveToFirst()) {
			id_fact = cursor_fact.getLong(0);
		}
		cursor_fact.close();
		return id_fact;
	}

	/**
	 * Obtiene el estatus de un ítem
	 * @param id_item Identificador de un ítem
	 * @param context Contexto de la aplicación
	 * @return Estatus del ítem
	 */
	public int obtiene_estatus_item(long id_item, Context context){
		
		Cursor cursor_item = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Item"), null,
				"idItem = "+id_item, null, null);
		
		int estatus = 0;
		
		if(cursor_item.moveToFirst()){
			estatus = cursor_item.getInt(cursor_item
					.getColumnIndexOrThrow("Estatus"));
		}
		
		return estatus;
	}
	
}

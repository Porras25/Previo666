package com.adquem.grupologistics.bussines;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.model.Adjunto;
import com.adquem.grupologistics.model.RazonCierre;
import com.adquem.grupologistics.model.Referencia;
import com.adquem.grupologistics.utilities.Constantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * Capa de negocio entre para finalizar la revisión de una referencia
 * 
 * @author Adquem S.C
 * @version 1.0
 * 
 */
public class Buss_FragListadoFact_RevisionReferencia {

	Constantes constantes;
	Referencia referencia;
	Uri uri;
	Adjunto tipoAdjunto;

	int countFotoInicio, countFotoFin;

	/**
	 * Inserta un adjunto asociado a una referencia en la base de datos
	 * 
	 * @param IdReferencia
	 *            Identificador de la referencia
	 * @param Nombre
	 *            Nombre del adjunto
	 * @param Descripcion
	 *            Ruta donde se guarda el adjunto
	 * @param Concepto
	 *            Identiicador del tipo de adjunto
	 * @param context
	 *            Contexto de la aplicación
	 * @return True si se se almacenó correctamente
	 */
	public boolean insertAdjRef(long IdReferencia, String Nombre,
			String Descripcion, int Concepto, Context context) {

		try {

			ContentValues valuesTipoAdjunto = new ContentValues();

			valuesTipoAdjunto.put("Nombre", Nombre);
			valuesTipoAdjunto.put("Descripcion", Descripcion);
			valuesTipoAdjunto.put("Concepto", Concepto);
			valuesTipoAdjunto.put("IdReferencia", IdReferencia);
			valuesTipoAdjunto.put("Estatus", Constantes.ESTATUS_REVISADO);

			uri = context.getContentResolver().insert(
					Uri.parse(MyContentProvider.URL + "Adjunto_Ref"),
					valuesTipoAdjunto);

			valida_estatus_Ref(IdReferencia, context);

			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("Buss_FragListadoFact_RevisionReferencia", "forzarRevision",
					e);
			Toast.makeText(context, "Algo salio mal :(", Toast.LENGTH_SHORT)
					.show();
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Inserta un Adjunto asociado an ítem
	 * 
	 * @param idItem
	 *            Identificador del ítem
	 * @param Nombre
	 *            Nombre del adjunto
	 * @param Descripcion
	 *            Ruta donde se almacena el adjunto
	 * @param Concepto
	 *            Identificador del tipo de adjunto
	 * @param context
	 *            Contexto de la aplicación
	 * @return True si se almacenó correctamente
	 */
	public boolean insertAdjuntoItm(long idItem, String Nombre,
			String Descripcion, int Concepto, Context context) {

		try {

			ContentValues valuesTipoAdjunto = new ContentValues();

			valuesTipoAdjunto.put("Nombre", Nombre);
			valuesTipoAdjunto.put("Descripcion", Descripcion);
			valuesTipoAdjunto.put("Concepto", Concepto);
			valuesTipoAdjunto.put("IdItem", idItem);
			valuesTipoAdjunto.put("Estatus", Constantes.ESTATUS_REVISADO);

			uri = context.getContentResolver().insert(
					Uri.parse(MyContentProvider.URL + "Adjunto_Itm"),
					valuesTipoAdjunto);

			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("Buss_FragListadoFact_RevisionReferencia", "forzarRevision",
					e);
			Toast.makeText(context, "Algo salio mal :(", Toast.LENGTH_SHORT)
					.show();
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Valida el estaus de la referencia de acuerdo a la fotografías de inicio y
	 * fin y los estatus de sus facturas
	 * 
	 * @param idReferencia
	 *            Identificador de la referencia
	 * @param context
	 *            Contexto de la aplicación
	 */
	public void valida_estatus_Ref(long idReferencia, Context context) {
		try {

			String[] colAdjuntoRef = { "IdAdj", "Nombre", "Descripcion",
					"Concepto", "idReferencia" };

			String statementInicio = "idReferencia = " + idReferencia
					+ " and Concepto=" + Constantes.ID_CONCEPTO_FOTO_INICIO;
			String statementFin = "idReferencia = " + idReferencia + ""
					+ " and Concepto=" + Constantes.ID_CONCEPTO_FOTO_FIN;

			// Cusor para saber el no de fotos inicio
			Cursor crs_tipoAdjunto_finicio = context.getContentResolver()
					.query(Uri.parse(MyContentProvider.URL + "Adjunto_Ref"),
							colAdjuntoRef, statementInicio, null, null);
			Cursor crs_tipoAdjunto_ffin = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Adjunto_Ref"),
					colAdjuntoRef, statementFin, null, null);

			Buss_Frag_RevisionItem bus_revision = new Buss_Frag_RevisionItem();

			if (crs_tipoAdjunto_finicio.getCount() > 0
					&& crs_tipoAdjunto_ffin.getCount() > 0) {

				bus_revision.valida_referencia(idReferencia, context);

			} else {
				bus_revision.actualiza_estatus_referencia(idReferencia,
						Constantes.ESTATUS_EN_REVISION, context);
			}

			crs_tipoAdjunto_finicio.close();
			crs_tipoAdjunto_ffin.close();

		} catch (Exception e) {
			Log.e("Buss_FragListadoFact_RevisionReferencia",
					"validacionReferencia", e);
			e.printStackTrace();
		}
	}

	/**
	 * Valida el estaus de la referencia de acuerdo a la fotografías de inicio y
	 * fin para el forzado de revisión
	 * 
	 * @param idReferencia
	 *            Identificador de la referencia
	 * @param context
	 *            Contexto de la aplicación
	 * @return True si tiene foto inicio y foto finy se actualizó el estaus a
	 *         revisado
	 */
	public boolean validacionReferencia(long idReferencia, Context context) {

		try {

			String[] colAdjuntoRef = { "IdAdj", "Nombre", "Descripcion",
					"Concepto", "idReferencia" };

			String statementInicio = "idReferencia = " + idReferencia
					+ " and Concepto=" + Constantes.ID_CONCEPTO_FOTO_INICIO;
			String statementFin = "idReferencia = " + idReferencia + ""
					+ " and Concepto=" + Constantes.ID_CONCEPTO_FOTO_FIN;

			// Cusor para saber el no de fotos inicio
			Cursor crs_tipoAdjunto_finicio = context.getContentResolver()
					.query(Uri.parse(MyContentProvider.URL + "Adjunto_Ref"),
							colAdjuntoRef, statementInicio, null, null);
			Cursor crs_tipoAdjunto_ffin = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Adjunto_Ref"),
					colAdjuntoRef, statementFin, null, null);

			if (crs_tipoAdjunto_finicio.getCount() > 0
					&& crs_tipoAdjunto_ffin.getCount() > 0) {

				ContentValues updateValues = new ContentValues();
				updateValues.put("Estatus", Constantes.ESTATUS_REVISADO);
				updateValues.put("Fecha_fin_previo", getFechaActual());

				String statement = "IdReferencia = " + idReferencia;

				context.getContentResolver().update(
						Uri.parse(MyContentProvider.URL + "Referencia"),
						updateValues, statement, null);

				crs_tipoAdjunto_ffin.close();
				crs_tipoAdjunto_finicio.close();
				return true;

			} else {
				crs_tipoAdjunto_ffin.close();
				crs_tipoAdjunto_finicio.close();
				return false;
			}

		} catch (Exception e) {
			Log.e("Buss_FragListadoFact_RevisionReferencia",
					"validacionReferencia", e);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Valida foto inicio y foto fin de una referencia
	 * 
	 * @param idReferencia
	 *            Identificador de la referencia
	 * @param context
	 *            Contexto de la aplicación
	 * @return True si tiene foto inicio y foto fin
	 */
	public boolean validacionFotos(long idReferencia, Context context) {

		try {
			String[] colAdjuntoRef = { "IdAdj", "Nombre", "Descripcion",
					"Concepto", "idReferencia" };

			String statementInicio = "idReferencia = " + idReferencia
					+ " and Concepto=" + Constantes.ID_CONCEPTO_FOTO_INICIO;
			String statementFin = "idReferencia = " + idReferencia + ""
					+ " and Concepto=" + Constantes.ID_CONCEPTO_FOTO_FIN;

			Cursor crs_tipoAdjunto_finicio = context.getContentResolver()
					.query(Uri.parse(MyContentProvider.URL + "Adjunto_Ref"),
							colAdjuntoRef, statementInicio, null, null);
			Cursor crs_tipoAdjunto_ffin = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Adjunto_Ref"),
					colAdjuntoRef, statementFin, null, null);

			if (crs_tipoAdjunto_finicio.getCount() > 0
					&& crs_tipoAdjunto_ffin.getCount() > 0) {

				crs_tipoAdjunto_ffin.close();
				crs_tipoAdjunto_finicio.close();

				return true;

			} else {
				crs_tipoAdjunto_ffin.close();
				crs_tipoAdjunto_finicio.close();
				return false;
			}
		} catch (Exception e) {
			Log.e("Buss_FragListadoFact_RevisionReferencia",
					"validacionReferencia", e);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Forza la revisión de una referencia
	 * 
	 * @param idReferencia
	 *            Identificador de la referencia
	 * @param ComentariosCierre
	 *            Comentarios de cierre
	 * @param idRazonCierre
	 *            Identificador de la razón de cierre
	 * @param context
	 *            Contexto de la aplicación
	 * @return True si el forzado de la revisión de la referencia se realizó
	 */
	public boolean forzarRevision(long idReferencia, String ComentariosCierre,
			int idRazonCierre, Context context) {

		try {
			ContentValues updateValues = new ContentValues();
			updateValues.put("Estatus", Constantes.ESTATUS_REVISADO);
			updateValues.put("Comentarios_razon_cierre", ComentariosCierre);
			updateValues.put("IdRazonCierre", idRazonCierre);
			updateValues.put("Fecha_fin_previo", getFechaActual());

			String statement = "IdReferencia = " + idReferencia;

			int updateBoolean = context.getContentResolver().update(
					Uri.parse(MyContentProvider.URL + "Referencia"),
					updateValues, statement, null);

			String[] col = { "IdReferencia", "Estatus" };

			Cursor cursor = context.getContentResolver().query(
					Uri.parse(MyContentProvider.URL + "Referencia"), col,
					"IdReferencia = " + idReferencia, null, null);

			if (cursor.moveToFirst()) {
				Log.v("Estatus ref", "Estatus ref" + cursor.getInt(1));
			}

			cursor.close();

			if (updateBoolean == 1) {

				return true;
			} else
				return false;

		} catch (Exception e) {
			Log.e("Buss_FragListadoFact_RevisionReferencia", "forzarRevision",
					e);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Obtiene la fecha actual con el formato dd/MM/yy hh:mm:ss
	 * 
	 * @return Fecha con el formato dd/MM/yy hh:mm:ss
	 */
	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
		return formateador.format(ahora);
	}

	/**
	 * Obtiene una Referencia en base alidentificador
	 * 
	 * @param id_referencia
	 *            Identificador de la referencia
	 * @param context
	 *            Contexto de la aplicación
	 * @return Objeto tipo Referencia
	 */
	public Referencia getReferencia(long id_referencia, Context context) {
		String[] colReferencia = { "IdReferencia", "Fecha_arribo", "Ejecutivo",
				"Clasificador", "Plaza", "NoOperacion",
				"Comentarios_razon_cierre", "IdRazonCierre", "Rfc",
				"NoReferencia", "Cliente", "Contenedor", "OrdenCompra" };
		Cursor cursor = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Referencia"), colReferencia,
				"IdReferencia = " + id_referencia, null, null);

		Log.v("Bus forzar", "id_referencia " + id_referencia);
		Log.v("Bus forzar", "cursorRef " + cursor.getCount());

		if (cursor.moveToFirst()) {
			Log.v("Bus forzar", "cursor.getString(6) " + cursor.getString(6));
			referencia = new Referencia();
			referencia.setIdReferencia(cursor.getLong(0));
			referencia.setFecha_arribo(cursor.getString(1));
			referencia.setEjecutivo(cursor.getString(2));
			referencia.setClasificador(cursor.getString(3));
			referencia.setPlaza(cursor.getString(4));
			referencia.setNoOperacion(cursor.getString(5));
			referencia.setComentarios_razon_Cierre(cursor.getString(6));
			referencia.setIdRazonCierre(cursor.getInt(7));
			referencia.setRfc(cursor.getString(8));
			referencia.setNoReferencia(cursor.getString(9));
			referencia.setCliente(cursor.getString(10));
			referencia.setContenedor(cursor.getString(11));
			referencia.setOrdenCompra(cursor.getString(12));
		}
		cursor.close();
		Log.v("Bus forzar", "Referenica " + referencia);
		return referencia;

	}

	/**
	 * Obtiene lista de razones de cierre disponibles
	 * 
	 * @param context
	 *            Contexto de la aplicación
	 * @return Lista de objetos tipo RazonCierre
	 */
	public ArrayList<RazonCierre> obtiene_razones(Context context) {

		Cursor cursor_razones = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "RazonCierre"), null, null,
				null, null);

		ArrayList<RazonCierre> razones = new ArrayList<RazonCierre>();
		Log.v("tamanio cursor", cursor_razones.getCount() + "");
		int i = 0;
		while (cursor_razones.moveToNext()) {

			RazonCierre razon = new RazonCierre();
			Log.v("previo",
					"id IdRazón:"
							+ cursor_razones.getLong(cursor_razones
									.getColumnIndexOrThrow("IdRazon")));
			Log.v("previo",
					"Razón:"
							+ cursor_razones.getString(cursor_razones
									.getColumnIndexOrThrow("Razon_cierre")));
			razon.setIdRazon(cursor_razones.getLong(cursor_razones
					.getColumnIndexOrThrow("IdRazon")));
			razon.setRazon_cierre(cursor_razones.getString(cursor_razones
					.getColumnIndexOrThrow("Razon_cierre")));
			razones.add(razon);
			i++;
		}
		cursor_razones.close();
		return razones;

	}

	/**
	 * Actualiza el eststus de la referencia
	 * 
	 * @param id_Ref
	 *            Identificador de la referencia
	 * @param estatus
	 *            Nuevo estatus de la referencia
	 * @param context
	 *            Contexto de la aplicación
	 */
	public void actualiza_estatus(long id_Ref, int estatus, Context context) {

		ContentValues updateValues = new ContentValues();
		updateValues.put("Estatus", estatus);

		try {
			context.getContentResolver().update(
					Uri.parse(MyContentProvider.URL + "Referencia"),
					updateValues, "IdReferencia = " + id_Ref, null);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Buss_Frag_RevisionReferencia", "actualiza_referencia: "
					+ estatus, e);
			e.printStackTrace();
		}

	}

	/**
	 * Elimina adjunto de la base de datos asociado a un ítem o a una referencia
	 * 
	 * @param path
	 *            Ruta donde se encuentra el adjunto
	 * @param Concepto
	 *            Identificador del tipo de adjunto
	 * @param context
	 *            Contexto de la aplicación
	 */
	public void deleteAdjunto(String path, String Concepto, Context context) {

		String deleteQuery = "Descripcion= '" + path + "'";

		Log.v("Bus listado", "path " + path);
		int eliminados = 0;

		if (Concepto.equals(Constantes.CONCEPTO_ELIMINA_ADJUNTO_REFERENCIA)) {
			eliminados = context.getContentResolver().delete(
					Uri.parse(MyContentProvider.URL + "Adjunto_Ref"),
					deleteQuery, null);

			if (eliminados != 0) {

				Log.v("Bus listado", "eliminados " + eliminados);

				Log.i("Eliminar Nom", "Todo se elimino bien :)");
			}

		} else if (Concepto.equals(Constantes.CONCEPTO_ELIMINA_ADJUNTO_ITEM)) {

			eliminados = context.getContentResolver().delete(
					Uri.parse(MyContentProvider.URL + "Adjunto_Itm"),
					deleteQuery, null);

			Log.v("Bus listado", "eliminados " + eliminados);

			Log.i("Eliminar Nom", "Todo se elimino bien :)");
		}

		Cursor cursor = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Adjunto_Ref"), null, null,
				null, null);

		if (cursor.moveToFirst()) {
			do {
				Log.v("Valor adjunto",
						"Path "
								+ cursor.getString(cursor
										.getColumnIndexOrThrow("Descripcion")));
			} while (cursor.moveToNext());
		}
		cursor.close();
	}

}

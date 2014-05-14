package com.adquem.grupologistics.bussines;

import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.utilities.Constantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class Buss_Frag_RevisionNom {

	Context contexto;

	// Mostrar Nom

	public Buss_Frag_RevisionNom(Context contexto) {
		super();
		this.contexto = contexto;
		// TODO Auto-generated constructor stub
	}

	// Guardar Revisión

	public boolean insertNom() {

		return false;
	}

	public String[][] getNomsItem(long idItem, Context context) {
		Log.v("BUS", "entra nomItem IDiTEM =" + idItem);
		String[] colNomItem = { "IdNom", "IdItem", "Estatus" };
		String[] colNom = { "IdNom", "IdCampo", "Nom" };
		String[][] noms = null;

		Cursor cursor = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "NomItem"), colNomItem,
				"IdItem = " + idItem, null, null);

		Log.v("noms indice bug", "cursor NOMITEM " + cursor.getCount());

		if (cursor.moveToFirst()) {
			noms = new String[cursor.getCount()][2];
			int count = 0;
			Cursor cursor2 = null;

			Log.v("noms indice bug", "Entra 1er moveto first");
			do {
				Log.v("noms indice bug", "Entra 1er while");

				cursor2 = context.getContentResolver().query(
						Uri.parse(MyContentProvider.URL + "Nom"),
						new String[] { "DISTINCT IdNom", "Nom" }, // DISTINCT
						"IdNom = " + cursor.getLong(0), // WHERE
						null, null);
				if (cursor2.moveToFirst()) {
					do {
						Log.v("from",
								"\""
										+ cursor2.getString(cursor2
												.getColumnIndex("IdNom"))
										+ "\"");
						Log.v("text",
								"\""
										+ cursor2.getString(cursor2
												.getColumnIndex("Nom")) + "\"");

					} while (cursor2.moveToNext());
				}

				Log.v("noms indice bug", "cursor 2 " + cursor2.getCount());

				if (cursor2.moveToFirst()) {
					Log.v("noms indice bug", "Entra moveto first");
					do {
						Log.v("noms indice bug", "Entra while");
						Log.v("noms indice bug",
								"IdNom "
										+ cursor2.getString(cursor2
												.getColumnIndex("IdNom")));
						Log.v("noms indice bug",
								"Nom "
										+ cursor2.getString(cursor2
												.getColumnIndex("Nom")));

						noms[count][0] = cursor2.getString(cursor2
								.getColumnIndex("IdNom"));
						Log.v("noms indice bug", noms[count][0]);
						String estatus_nom;

						if (cursor.getInt(cursor.getColumnIndex("Estatus")) == Constantes.ESTATUS_REVISADO) {
							estatus_nom = context.getResources().getString(
									R.string.str_Listados_tab_Revisado);
						} else
							estatus_nom = context.getResources().getString(
									R.string.str_Listados_tab_SinRevision);

						/*noms[count][1] = cursor2.getString(cursor2
								.getColumnIndex("Nom")) + " " + estatus_nom;*/
						noms[count][1] = cursor2.getString(cursor2.getColumnIndex("Nom")) + "\n" + estatus_nom;

						Log.v("noms texto bug", noms[count][1]);
						count++;
					} while (cursor2.moveToNext());
					cursor2.close();
				} else
					Log.v("noms indice bug", "cursor2 vac'io");
			} while (cursor.moveToNext());
			cursor.close();
		} else {
			Log.v("noms indice bug", "No hay NOMITEM");
		}
		return noms;
	}

	public Boolean setNomEnRevision(long idNom, long idItem, Context context) {

		try {
			ContentValues updateValues = new ContentValues();
			updateValues.put("Estatus", Constantes.ESTATUS_EN_REVISION);

			String statement = "IdNom = " + idNom + " and IdItem=" + idItem;

			int updateBoolean = context.getContentResolver().update(
					Uri.parse(MyContentProvider.URL + "NomItem"), updateValues,
					statement, null);

			if (updateBoolean == 1) // Si no hubo errores
				return true;
			else
				return false;

		} catch (Exception e) {
			Log.e("Buss_Frag_RevisionNom", "CambiarNom a En revisión", e);
			e.printStackTrace();
			return false;
		}

	}

}

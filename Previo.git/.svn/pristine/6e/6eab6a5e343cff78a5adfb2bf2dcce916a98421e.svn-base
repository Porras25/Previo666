package com.adquem.grupologistics.bussines;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.adquem.grupologistics.dao.MyContentProvider;

/**
 * Capa de negocio para el escaneo de código de barras
 * @author Adquem S.C
 * @version 1.0
 */
public class Buss_Frag_Busqueda_Upc {

	/**
	 * Realiza búsqueda del ítem por el upc
	 * @param criterio UPC escaneado
	 * @param context Contexto de la aplicación
	 * @return Identificador del ítem 
	 */
	public long searchUpc(String criterio, Context context) {
		String[] colUpc = { "Upc","IdItem"};
		long idUpc = 0;
		Cursor cursorUpc = context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Upc"), null,
				"Upc = " + criterio, null, null);

		Log.v("", "tamanio cursor " + cursorUpc.getCount());

		if (cursorUpc.moveToFirst()) {
			idUpc = cursorUpc.getInt(cursorUpc.getColumnIndexOrThrow("IdItem"));

			Log.i("bus Frag upc",
					"upc"
							+ cursorUpc.getString(cursorUpc
									.getColumnIndexOrThrow("Upc")));
			Log.i("bus Frag upc", "IdUpc: " + idUpc);

		}
		cursorUpc.close();
		return idUpc;
	}
}

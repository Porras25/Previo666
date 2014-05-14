package com.adquem.grupologistics.bussines;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.text.ICUCompat;
import android.util.Log;

import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.dao.MyContentProvider.CreateDBProvider;
import com.adquem.grupologistics.model.Adjunto;
import com.adquem.grupologistics.model.Excedentes;
import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.model.ItemExc;
import com.adquem.grupologistics.model.NomItem;
import com.adquem.grupologistics.model.Referencia;
import com.adquem.grupologistics.model.RespuestaJSON;
import com.adquem.grupologistics.model.Respuestas;
import com.adquem.grupologistics.model.iResultado;
import com.adquem.grupologistics.utilities.Constantes;
import com.adquem.grupologistics.utilities.Utl_RESTful_Client;
import com.adquem.grupologistics.utilities.Utl_SharedPreferences;

public class Buss_REST_Sync {
	Utl_RESTful_Client cliente;
	ContentResolver mContentResolver;
	Buss_Frag_Listado buss_fraglistado;
	Buss_Frag_DesgloseCantidad buss_desglose;
	List<Item> Items;
	Context mContext;
	List<Referencia> Referencias;
	List<Adjunto> Adjuntos;
	Utl_SharedPreferences prefs;
	Buss_Frag_RevisionNom buss_revision_nom;

	public Buss_REST_Sync(Context context) {
		super();
		this.cliente = new Utl_RESTful_Client(context);
		buss_fraglistado = new Buss_Frag_Listado();
		mContentResolver = context.getContentResolver();
		mContext = context;
		prefs = new Utl_SharedPreferences(context);
		buss_desglose = new Buss_Frag_DesgloseCantidad();
	}

	public boolean putArdRef() {
		Referencias = buss_fraglistado
				.obtenerReferencias(mContentResolver.query(
						Uri.parse(MyContentProvider.URL + "Referencia"),
						CreateDBProvider.colReferencia, "Estatus=4", null, null));

		if (Referencias.size() > 0) {
			for (int u = 0; Referencias.size() > u; u++) {
				Adjuntos = obtenerAdjuntos(mContentResolver.query(
						Uri.parse(MyContentProvider.URL + "Adjunto_Ref"),
						CreateDBProvider.colAdjunto_Ref, "IdReferencia="
								+ Referencias.get(u).getIdReferencia(), null,
						null), true);
				if (cliente
						.putAdjuntoRef(
								Constantes.VALUE_PREFERENCE_URL
										// +
										// "FilesItem?token_user=11ed7cea1874eb62d4dd796113ff8015&IdReferencia="+Referencias.get(u).getIdReferencia()+"&Concepto=",Adjuntos);
										+ "FilesReferencia?token_user="
										+ prefs.getPreferencesSettings()
												.getString(
														Constantes.VALUE_PREFERENCE_TOKEN,
														null)
										+ "&IdReferencia="
										+ Referencias.get(u).getIdReferencia()
										+ "&Concepto=", Adjuntos)) {
					ContentValues updateValues = new ContentValues();
					updateValues
							.put("Estatus", Constantes.ESTATUS_SINCRONIZADO);
					mContentResolver.update(
							Uri.parse(MyContentProvider.URL + "Adjunto_Ref"),
							updateValues, " Estatus ="
									+ String.valueOf(Constantes.ESTATUS_REVISADO), null);
				}
			}
		}
		return false;
	}

	public boolean putArdItm() {

		Items = buss_fraglistado.obtenerItems(mContentResolver.query(
				Uri.parse(MyContentProvider.URL + "Item"),
				CreateDBProvider.colItem, " Estatus ="
						+ String.valueOf(Constantes.ESTATUS_SINCRONIZADO), null, null));
		if (Items.size() > 0) {
			for (int u = 0; Items.size() > u; u++) {
				Adjuntos = obtenerAdjuntos(mContentResolver.query(
						Uri.parse(MyContentProvider.URL + "Adjunto_Itm"),
						CreateDBProvider.colAdjunto_Itm,
						"IdItem=" + Items.get(u).getIdItem(), null, null),
						false);
				int iditem;
				Cursor icruror= mContentResolver.query(
						Uri.parse(MyContentProvider.URL + "ItemExcedente"),
						CreateDBProvider.colItemExcedente, "IdItemApp ="+ Items.get(u).getIdItem()
								, null, null);
				
				if(icruror.moveToFirst()){
					iditem= icruror.getInt(icruror
							.getColumnIndexOrThrow("IdItem"));
				}else{
					iditem=(int) Items.get(u).getIdItem();
				}
				
				
				
				
				if (cliente
						.putAdjuntoItm(
								Constantes.VALUE_PREFERENCE_URL
										// +
										// "FilesItem?token_user=11ed7cea1874eb62d4dd796113ff8015&IdItem="+Items.get(u).getIdItem()+"&Concepto=",Adjuntos);
										+ "FilesItem?token_user="
										+ prefs.getPreferencesSettings()
												.getString(
														Constantes.VALUE_PREFERENCE_TOKEN,
														null) + "&IdItem="
										+ iditem
										+ "&Concepto=", Adjuntos)) {
					ContentValues updateValues = new ContentValues();
					updateValues
							.put("Estatus", Constantes.ESTATUS_SINCRONIZADO);
					mContentResolver.update(
							Uri.parse(MyContentProvider.URL + "Adjunto_Itm"),
							updateValues, " Estatus ="
									+ String.valueOf(Constantes.ESTATUS_REVISADO), null);
				}
			}
		}
		return false;
	}

	public boolean putReferencia() {
		Referencias = buss_fraglistado
				.obtenerReferencias(mContentResolver.query(
						Uri.parse(MyContentProvider.URL + "Referencia"),
						CreateDBProvider.colReferencia, "Estatus="+String.valueOf(Constantes.ESTATUS_REVISADO), null, null));
		if (Referencias.size() > 0) {
			if (cliente.putReferencia(
					Constantes.VALUE_PREFERENCE_URL
							// +
							// "Referencia?token_user=11ed7cea1874eb62d4dd796113ff8015",
							+ "Referencia?token_user="
							+ prefs.getPreferencesSettings().getString(
									Constantes.VALUE_PREFERENCE_TOKEN, null),
					Referencias)) {

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public boolean putItem() {
		Items = buss_fraglistado.obtenerItems(mContentResolver.query(
				Uri.parse(MyContentProvider.URL + "Item"),
				CreateDBProvider.colItem, " Estatus ="+String.valueOf(Constantes.ESTATUS_REVISADO), null, null));
		// List<Item> Ficticias = new ArrayList<Item>();
		List<Item> Existentes = new ArrayList<Item>();
		// List<Excedentes> Excedentes = new ArrayList<Excedentes>();

		if (Items.size() > 0) {
			for (int s = 0; Items.size() > s; s++) {
				Cursor factficticia = mContentResolver.query(
						Uri.parse(MyContentProvider.URL + "Factura"),
						CreateDBProvider.colFactura, "IdFactura="
								+ Items.get(s).getIdFactura()
								+ " AND Factura LIKE '"
								+ Constantes.NOMBRE_FACTURA_FICTICIA + "'",
						null, null);

				if (!factficticia.moveToFirst()) {
					Existentes.add(Items.get(s));

				}

			}
			// if (Ficticias.size()>0){
			//
			// cliente
			// .putItemExcedente(
			// Constantes.VALUE_PREFERENCE_URL
			// //+ "Item?token_user=11ed7cea1874eb62d4dd796113ff8015",
			// +
			// "Item?token_user="+prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN,
			// null),
			// Ficticias);
			// }
			if (cliente.putItem(
					Constantes.VALUE_PREFERENCE_URL
							// +
							// "Item?token_user=11ed7cea1874eb62d4dd796113ff8015",
							+ "Item?token_user="
							+ prefs.getPreferencesSettings().getString(
									Constantes.VALUE_PREFERENCE_TOKEN, null),
					Existentes)) {
				ContentValues updateValues = new ContentValues();
				updateValues.put("Estatus", Constantes.ESTATUS_SINCRONIZADO);
				mContentResolver.update(
						Uri.parse(MyContentProvider.URL + "Item"),
						updateValues, " Estatus ="+String.valueOf(Constantes.ESTATUS_REVISADO), null);
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean putDesglose() {
		Items = buss_fraglistado.obtenerItems(mContentResolver.query(
				Uri.parse(MyContentProvider.URL + "Item"),
				CreateDBProvider.colItem, " Estatus ="
						+ Constantes.ESTATUS_SINCRONIZADO, null, null));
		List<Item> Existentes = new ArrayList<Item>();
		// List<Excedentes> Excedentes = new ArrayList<Excedentes>();

		if (Items.size() > 0) {
			for (int s = 0; Items.size() > s; s++) {
				Cursor factficticia = mContentResolver.query(
						Uri.parse(MyContentProvider.URL + "Factura"),
						CreateDBProvider.colFactura, "IdFactura="
								+ Items.get(s).getIdFactura()
								+ " AND Factura LIKE '"
								+ Constantes.NOMBRE_FACTURA_FICTICIA + "'",
						null, null);

				if (!factficticia.moveToFirst()) {
					Existentes.add(Items.get(s));

				}

			}
		}

		if (cliente.postDesglose(
				Constantes.VALUE_PREFERENCE_URL
						+ "Desglose?token_user="
						+ prefs.getPreferencesSettings().getString(
								Constantes.VALUE_PREFERENCE_TOKEN, null),
				buss_desglose.obtinene_todos_degloses(Existentes, mContext))) {
			ContentValues updateValues = new ContentValues();
			updateValues.put("Estatus", Constantes.ESTATUS_SINCRONIZADO);
			mContentResolver.update(
					Uri.parse(MyContentProvider.URL + "Desglose"),
					updateValues, " Estatus =" + String.valueOf(Constantes.ESTATUS_REVISADO),
					null);
			return true;
		} else {
			return false;
		}

	}

	public boolean postRespuestas() {
		Items = buss_fraglistado.obtenerItems(mContentResolver.query(
				Uri.parse(MyContentProvider.URL + "Item"),
				CreateDBProvider.colItem, " Estatus ="
						+ String.valueOf(Constantes.ESTATUS_SINCRONIZADO), null, null));

		List<Item> Existentes = new ArrayList<Item>();
		// List<Excedentes> Excedentes = new ArrayList<Excedentes>();

		if (Items.size() > 0) {
			for (int s = 0; Items.size() > s; s++) {
				Cursor factficticia = mContentResolver.query(
						Uri.parse(MyContentProvider.URL + "Factura"),
						CreateDBProvider.colFactura, "IdFactura="
								+ Items.get(s).getIdFactura()
								+ " AND Factura LIKE '"
								+ Constantes.NOMBRE_FACTURA_FICTICIA + "'",
						null, null);

				if (!factficticia.moveToFirst()) {
					Existentes.add(Items.get(s));

				}

			}
		}
		for (int i = 0; Existentes.size() > i; i++) {

			List<NomItem> Nom = new ArrayList<NomItem>();
			if (cliente
					.postRespustas(
							Constantes.VALUE_PREFERENCE_URL
									// +
									// "Noms?token_user=1dffa5511d81f4ce135e91a89a5c0fcc",
									+ "Noms?token_user="
									+ prefs.getPreferencesSettings().getString(
											Constantes.VALUE_PREFERENCE_TOKEN,
											null),
							obtenerRespuestas(mContentResolver.query(
									Uri.parse(MyContentProvider.URL
											+ "Respuesta"),
									CreateDBProvider.colRespuesta,
									"IdItem="
											+ String.valueOf(Existentes.get(i)
													.getIdItem())
											+ " AND Estatus="
											+ String.valueOf(Constantes.ESTATUS_REVISADO),
									null, null)))) {
				ContentValues updateValues = new ContentValues();
				updateValues.put("Estatus", Constantes.ESTATUS_SINCRONIZADO);
				mContentResolver.update(
						Uri.parse(MyContentProvider.URL + "Respuesta"),
						updateValues, " Estatus ="
								+ String.valueOf(Constantes.ESTATUS_REVISADO), null);
			}

		}
		return false;

	}

	public boolean postExcedentes() {

		Excedentes excedenteCompleto = new Excedentes();
		Referencias = buss_fraglistado
				.obtenerReferencias(mContentResolver.query(
						Uri.parse(MyContentProvider.URL + "Referencia"),
						CreateDBProvider.colReferencia, "Estatus="+String.valueOf(Constantes.ESTATUS_REVISADO), null, null));
		if (Referencias.size() > 0) {
			for (int r = 0; Referencias.size() > r; r++) {

				Cursor factficticia = mContentResolver.query(
						Uri.parse(MyContentProvider.URL + "Factura"),
						CreateDBProvider.colFactura, "IdReferencia="
								+ Referencias.get(r).getIdReferencia()
								+ " AND Factura LIKE '"
								+ Constantes.NOMBRE_FACTURA_FICTICIA + "'",
						null, null);
				if (factficticia.moveToFirst()) {

					// factura = new FacturaExc();
					excedenteCompleto.setIdFactura(factficticia
							.getInt(factficticia
									.getColumnIndexOrThrow("IdFactura")));
					excedenteCompleto.setEstatus(factficticia
							.getInt(factficticia
									.getColumnIndexOrThrow("Estatus")));
					excedenteCompleto.setFactura(factficticia
							.getString(factficticia
									.getColumnIndexOrThrow("Factura")));
					excedenteCompleto.setIdReferencia(factficticia
							.getInt(factficticia
									.getColumnIndexOrThrow("IdReferencia")));
					excedenteCompleto.setProveedor(factficticia
							.getString(factficticia
									.getColumnIndexOrThrow("Proveedor")));
					Cursor itemsExc = mContentResolver.query(
							Uri.parse(MyContentProvider.URL + "Item"),
							CreateDBProvider.colItem, "IdFactura="
									+ excedenteCompleto.getIdFactura(), null,
							null);
					if (itemsExc.moveToFirst()) {
						excedenteCompleto.setItems(obtenerItemsExc(itemsExc));
						// excedenteCompleto.setFactura(excedenteCompleto);
					}
					itemsExc.close();
				}
				factficticia.close();

			}
		}
		if (excedenteCompleto.getIdFactura() != 0) {
		 cliente.putExcedente(
					Constantes.VALUE_PREFERENCE_URL
					+ "Facturas?token_user="
					+ prefs.getPreferencesSettings().getString(
							Constantes.VALUE_PREFERENCE_TOKEN, null),
			excedenteCompleto);
			
		}
		return false;

	}

	public List<ItemExc> obtenerItemsExc(Cursor cursorI) {
		Log.v("Previo App", "cursorI tiene:" + cursorI.getCount());
		List<ItemExc> items = new ArrayList<ItemExc>();
		if (cursorI.moveToFirst()) {
			do {
				ItemExc item = new ItemExc();
				item.setIdItem(cursorI.getLong(cursorI
						.getColumnIndexOrThrow("IdItem")));
				item.setEstatus(cursorI.getInt(cursorI
						.getColumnIndexOrThrow("Estatus")));
				item.setNoParte(cursorI.getString(cursorI
						.getColumnIndexOrThrow("NoParte")));
				item.setDescripcion(cursorI.getString(cursorI
						.getColumnIndexOrThrow("Descripcion")));
				item.setCantidadEsp(cursorI.getFloat(cursorI
						.getColumnIndexOrThrow("CantidadEsp")));
				item.setCantidadRec(cursorI.getFloat(cursorI
						.getColumnIndexOrThrow("CantidadRec")));
				item.setComentarios(cursorI.getString(cursorI
						.getColumnIndexOrThrow("Comentarios")));
				item.setValorPartida(cursorI.getFloat(cursorI
						.getColumnIndexOrThrow("Valor_partida")));
				item.setFraccionArancelaria(cursorI.getString(cursorI
						.getColumnIndexOrThrow("Fraccion_arancelaria")));
				item.setSerie(cursorI.getString(cursorI
						.getColumnIndexOrThrow("Serie")));
				item.setMarca(cursorI.getString(cursorI
						.getColumnIndexOrThrow("Marca")));
				item.setIdFactura(cursorI.getLong(cursorI
						.getColumnIndexOrThrow("IdFactura")));
				item.setSku(cursorI.getString(cursorI
						.getColumnIndexOrThrow("Sku")));
				item.setIdUnidadMedida(cursorI.getLong(cursorI
						.getColumnIndexOrThrow("IdUnidadMedida")));
				item.setPesoKg(cursorI.getFloat(cursorI
						.getColumnIndexOrThrow("PesoKG")));
				item.setRespuestas(obtenerRespuestas(mContentResolver.query(
						Uri.parse(MyContentProvider.URL + "Respuesta"),
						CreateDBProvider.colRespuesta,
						"IdItem=" + item.getIdItem(), null, null)));
				item.setDesgloses(buss_desglose.obtinene_degloses(
						item.getIdItem(), mContext));
				items.add(item);
			} while (cursorI.moveToNext());
		}
		cursorI.close();
		Log.v("Previo App", "items:" + items.size());
		return items;
	}

	public List<Respuestas> obtenerRespuestas(Cursor iCursor) {
		// "IdResp","IdNom", "IdItem","IdCampo","Valor"
		List<Respuestas> respuestas = new ArrayList<Respuestas>();

		if (iCursor.moveToFirst() != false) {
			do {
				Respuestas respuat = new Respuestas();
				respuat.setIdResp(iCursor.getLong(iCursor
						.getColumnIndexOrThrow("IdResp")));
				respuat.setIdCampo(iCursor.getLong(iCursor
						.getColumnIndexOrThrow("IdCampo")));
				respuat.setIdItem(iCursor.getLong(iCursor
						.getColumnIndexOrThrow("IdItem")));
				respuat.setIdNom(iCursor.getLong(iCursor
						.getColumnIndexOrThrow("IdNom")));
				respuat.setValor(iCursor.getString(iCursor
						.getColumnIndexOrThrow("Valor")));
				respuestas.add(respuat);
			} while (iCursor.moveToNext());

		}

		return respuestas;

	}

	public List<Adjunto> obtenerAdjuntos(Cursor iCursor, Boolean tipo) {
		// "IdAdj", "Nombre", "Descripcion", "Concepto", "IdReferencia"}
		// true= referencia false=item
		List<Adjunto> respuestas = new ArrayList<Adjunto>();

		if (iCursor.moveToFirst() != false) {
			do {
				Adjunto respuat = new Adjunto();
				respuat.setIdAdj(iCursor.getInt(iCursor
						.getColumnIndexOrThrow("IdAdj")));
				if (tipo) {
					respuat.setIdForaneo(iCursor.getInt(iCursor
							.getColumnIndexOrThrow("IdReferencia")));
				} else {
					respuat.setIdForaneo(iCursor.getInt(iCursor
							.getColumnIndexOrThrow("IdItem")));
				}
				respuat.setDescripcion(iCursor.getString(iCursor
						.getColumnIndexOrThrow("Descripcion")));
				respuat.setConcepto(iCursor.getInt(iCursor
						.getColumnIndexOrThrow("Concepto")));
				respuat.setNombre(iCursor.getString(iCursor
						.getColumnIndexOrThrow("Nombre")));
				respuestas.add(respuat);
			} while (iCursor.moveToNext());
		}

		return respuestas;

	}
}

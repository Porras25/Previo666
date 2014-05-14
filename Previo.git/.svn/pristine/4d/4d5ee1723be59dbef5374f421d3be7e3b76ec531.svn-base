package com.adquem.grupologistics.dao;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.adquem.grupologistics.dao.MyContentProvider.CreateDBProvider;
import com.adquem.grupologistics.utilities.Constantes;
import com.adquem.grupologistics.model.*;

public class SyncLT {

	public void GETSync(Object object, int position, String[] NombresTabla,
			ContentResolver contenR, Boolean update) {

		switch (position) {
		case 0:// usos
			try {
				ArrayList<CatUso> usos = (ArrayList<CatUso>) object;
				// looping through All
				for (int i = 0; i < usos.size(); i++) {

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					// values.put(CreateDBProvider.colCatUso[0],
					// usos.get(i).getIdCatalogo());
					values.put(CreateDBProvider.colCatUso[1], usos.get(i)
							.getNombre());
					if (!update) {
						Uri uri = contenR.insert(
								Uri.parse(MyContentProvider.URL + "catUso"),
								values);
						uri.toString();
					} else {
						// Logica del update
//						Uri uri = contenR. (
//								Uri.parse(MyContentProvider.URL + "catUso"),
//								values);
//						uri.toString();
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 1:// paises
			try {
				ArrayList<Pais> paises = (ArrayList<Pais>) object;
				// looping through All
				for (int i = 0; i < paises.size(); i++) {
					// JSONObject c = Json.getJSONObject(i);

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					values.put(CreateDBProvider.colPais[0], paises.get(i)
							.getIdPais());
					values.put(CreateDBProvider.colPais[1], paises.get(i)
							.getPais());
					values.put(CreateDBProvider.colPais[2], paises.get(i)
							.getCodigoPais());
					if (!update) {
						Uri uri = contenR.insert(
								Uri.parse(MyContentProvider.URL + "Pais"),
								values);
						uri.toString();
					} else {
						// Logica del update
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 2:// unidadmedida
			try {
				// looping through All
				ArrayList<UnidadMedida> Unidades = (ArrayList<UnidadMedida>) object;
				for (int i = 0; i < Unidades.size(); i++) {
					// JSONObject c = Json.getJSONObject(i);

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					values.put(CreateDBProvider.colUnidadMedida[0], Unidades
							.get(i).getIdUnidadMedida());
					values.put(CreateDBProvider.colUnidadMedida[1], Unidades
							.get(i).getNombre());
					values.put(CreateDBProvider.colUnidadMedida[2], Unidades
							.get(i).getDescripcion());
					values.put(CreateDBProvider.colUnidadMedida[3], Unidades
							.get(i).getUnidad());
					if (!update) {
						Uri uri = contenR.insert(
								Uri.parse(MyContentProvider.URL
										+ "CatUnidadMedida"), values);
						uri.toString();
					} else {
						// Logica Update
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 3:// RazonCierre
			try {
				// looping through All
				ArrayList<RazonCierre> RazonCierre = (ArrayList<RazonCierre>) object;
				for (int i = 0; i < RazonCierre.size(); i++) {
					// JSONObject c = Json.getJSONObject(i);

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					// values.put(CreateDBProvider.colRazonCierre[0],
					// Integer.parseInt(c.getString("idRazonCierre")));
					values.put(CreateDBProvider.colRazonCierre[1], RazonCierre
							.get(i).getRazon_cierre());
					if (!update) {
						Uri uri = contenR.insert(Uri
								.parse(MyContentProvider.URL + "RazonCierre"),
								values);
						uri.toString();
					} else {

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 4:// ColumnasNom
			try {
				// looping through All
				ArrayList<ColumnasNom> ColumnsNom = (ArrayList<ColumnasNom>) object;
				for (int i = 0; i < ColumnsNom.size(); i++) {
					// JSONObject c = Json.getJSONObject(i);

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					values.put(CreateDBProvider.colColumnasNom[0], ColumnsNom
							.get(i).getIdCampo());
					values.put(CreateDBProvider.colColumnasNom[1], ColumnsNom
							.get(i).getNombre());
					values.put(CreateDBProvider.colColumnasNom[2], ColumnsNom
							.get(i).getOrden());
					values.put(CreateDBProvider.colColumnasNom[3], ColumnsNom
							.get(i).getTipoDato());
					// values.put(CreateDBProvider.colColumnasNom[4],
					// ColumnsNom.get(i).isRequerido());
					values.put(CreateDBProvider.colColumnasNom[5], ColumnsNom
							.get(i).getTablaFuente());
					values.put(CreateDBProvider.colColumnasNom[6], ColumnsNom
							.get(i).getIdFuente());
					values.put(CreateDBProvider.colColumnasNom[7], ColumnsNom
							.get(i).getTextoFuente());
					if (ColumnsNom.get(i).isRequerido()) {
						values.put(CreateDBProvider.colColumnasNom[4],
								Constantes.Boolean_TRUE);
					} else {
						values.put(CreateDBProvider.colColumnasNom[4],
								Constantes.Boolean_FALSE);
					}
					if (!update) {
						Uri uri = contenR.insert(Uri
								.parse(MyContentProvider.URL + "ColumnasNom"),
								values);
						uri.toString();
					} else {

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case 5:// sino
			try {
				// looping through All
				ArrayList<CatSiNo> Sino = (ArrayList<CatSiNo>) object;
				for (int i = 0; i < Sino.size(); i++) {
					// JSONObject c = Json.getJSONObject(i);

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					values.put(CreateDBProvider.colCatSiNo[0], Sino.get(i)
							.getIdCatalogo());
					values.put(CreateDBProvider.colCatSiNo[1], Sino.get(i)
							.getRespuesta());
					if (!update) {
						Uri uri = contenR.insert(
								Uri.parse(MyContentProvider.URL + "CatSiNo"),
								values);
						uri.toString();
					} else {

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case 6:// EstatusAparatos
			try {
				// looping through All
				ArrayList<CatEstatusAparatos> Sino = (ArrayList<CatEstatusAparatos>) object;
				for (int i = 0; i < Sino.size(); i++) {
					// JSONObject c = Json.

					ContentValues values = new ContentValues();
					values.put(CreateDBProvider.colEstatusAparatos[0], Sino
							.get(i).getIdCatalogo());
					values.put(CreateDBProvider.colEstatusAparatos[1], Sino
							.get(i).getNombre());
					if (!update) {
						Uri uri = contenR.insert(
								Uri.parse(MyContentProvider.URL
										+ "CatEstatusAparatos"), values);
						uri.toString();
					} else {

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 7:// Noms
			try {
				// looping through All
				ArrayList<Nom> Nom = (ArrayList<Nom>) object;
				for (int i = 0; i < Nom.size(); i++) {
					// JSONObject c = Json.getJSONObject(i);

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					values.put(CreateDBProvider.colNom[1], Nom.get(i)
							.getIdNom());
					values.put(CreateDBProvider.colNom[2], Nom.get(i)
							.getIdCampo());
					values.put(CreateDBProvider.colNom[3], Nom.get(i).getNom());

					if (!update) {
						Uri uri = contenR.insert(
								Uri.parse(MyContentProvider.URL + "Nom"),
								values);
						uri.toString();
					} else {

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case 8:// UPc
			try {
				// looping through All
				ArrayList<Upc> UPC = (ArrayList<Upc>) object;
				for (int i = 0; i < UPC.size(); i++) {
					// JSONObject c = Json.getJSONObject(i);

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					// values.put(CreateDBProvider.colUPC[0],
					// UPC.get(i).getIdArticulo());
					values.put(CreateDBProvider.colUPC[1], UPC.get(i).getUpc());
					values.put(CreateDBProvider.colUPC[2], UPC.get(i)
							.getIdItem());
					if (!update) {
						Uri uri = contenR.insert(
								Uri.parse(MyContentProvider.URL + "Upc"),
								values);
						uri.toString();
					} else {

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case 9:// Referencia
			try {
				// looping through All
				ArrayList<Referencia> Referencia = (ArrayList<Referencia>) object;
				for (int i = 0; i < Referencia.size(); i++) {
					// JSONObject c = Json.getJSONObject(i);

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					values.put(CreateDBProvider.colReferencia[0], Referencia
							.get(i).getIdReferencia());
					values.put(CreateDBProvider.colReferencia[1], Referencia
							.get(i).getCliente());
					values.put(CreateDBProvider.colReferencia[2], Referencia
							.get(i).getContenedor());
					values.put(CreateDBProvider.colReferencia[3], Referencia
							.get(i).getRfc());
					values.put(CreateDBProvider.colReferencia[4], Referencia
							.get(i).getNoReferencia());
					values.put(CreateDBProvider.colReferencia[5], Referencia
							.get(i).getFecha_arribo());
					values.put(CreateDBProvider.colReferencia[6], Referencia
							.get(i).getEjecutivo());
					values.put(CreateDBProvider.colReferencia[7], Referencia
							.get(i).getClasificador());
					values.put(CreateDBProvider.colReferencia[8], Referencia
							.get(i).getPlaza());
					values.put(CreateDBProvider.colReferencia[9], Referencia
							.get(i).getSello_arribo());
					values.put(CreateDBProvider.colReferencia[10], Referencia
							.get(i).getSello_asignado());
					values.put(CreateDBProvider.colReferencia[11], Referencia
							.get(i).getNoOperacion());
					values.put(CreateDBProvider.colReferencia[12], Referencia
							.get(i).getFecha_fin_previo());
					values.put(CreateDBProvider.colReferencia[13], Referencia
							.get(i).getIdRazonCierre());
					values.put(CreateDBProvider.colReferencia[14], Referencia
							.get(i).getComentarios_razon_Cierre());
					values.put(CreateDBProvider.colReferencia[15], Referencia
							.get(i).getOrdenCompra());

					// String[] colE = {"IdEstatus"};
					// Cursor cursorE =
					// contenR.query(Uri.parse(MyContentProvider.URL+"Estatus"),
					// colE, "IdEstatus = " +
					// Integer.parseInt(c.getString("idEstatus")), null, null);
					// if(cursorE.moveToFirst())
					values.put(CreateDBProvider.colReferencia[16], Referencia
							.get(i).getEstatus());
					// cursorE.close();
					if (!update) {
						Uri uri = contenR
								.insert(Uri.parse(MyContentProvider.URL
										+ "Referencia"), values);
						uri.toString();
					} else {

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case 10:// facturas
			try {
				// looping through All
				ArrayList<Factura> Factura = (ArrayList<Factura>) object;
				for (int i = 0; i < Factura.size(); i++) {
					// JSONObject c = Json.getJSONObject(i);

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					values.put(CreateDBProvider.colFactura[0], Factura.get(i)
							.getIdFactura());
					values.put(CreateDBProvider.colFactura[1], Factura.get(i)
							.getIdReferencia());
					values.put(CreateDBProvider.colFactura[2], Factura.get(i)
							.getFactura());
					values.put(CreateDBProvider.colFactura[3], Factura.get(i)
							.getCantidad());
					values.put(CreateDBProvider.colFactura[4], Factura.get(i)
							.getFechaFactura());
					values.put(CreateDBProvider.colFactura[5], Factura.get(i)
							.getEstatus());
					values.put(CreateDBProvider.colFactura[6], Factura.get(i)
							.getProveedor());
					values.put(CreateDBProvider.colFactura[7], Factura.get(i)
							.getOrdenCompra());
					if (!update) {
						Uri uri = contenR.insert(
								Uri.parse(MyContentProvider.URL + "Factura"),
								values);
						uri.toString();
					} else {

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case 11:// Item
			try {
				// looping through All
				ArrayList<Item> Items = (ArrayList<Item>) object;
				for (int i = 0; i < Items.size(); i++) {
					// JSONObject c = Json.getJSONObject(i);

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					values.put(CreateDBProvider.colItem[0], Items.get(i)
							.getIdItem());
					// String[] colR = { "IdFactura" };
					// Cursor cursorR = contenR.query(
					// Uri.parse(MyContentProvider.URL + "Factura"),
					// colR,
					// "FolioFactura = "
					// + Integer.parseInt(c
					// .getString("folioFactura")), null,
					// null);
					// if (cursorR.moveToFirst())
					values.put(CreateDBProvider.colItem[1], Items.get(i)
							.getIdFactura());

					values.put(CreateDBProvider.colItem[2], Items.get(i)
							.getCantidadEsp());
					values.put(CreateDBProvider.colItem[3], Items.get(i)
							.getCantidadRec());
					values.put(CreateDBProvider.colItem[4], Items.get(i)
							.getNoParte());
					values.put(CreateDBProvider.colItem[5], Items.get(i)
							.getSku());

					values.put(CreateDBProvider.colItem[6], Items.get(i)
							.getIdUnidadMedida());

					values.put(CreateDBProvider.colItem[7], Items.get(i)
							.getValorPartida());
					values.put(CreateDBProvider.colItem[8], Items.get(i)
							.getFraccionArancelaria());
					values.put(CreateDBProvider.colItem[9], Items.get(i)
							.getDescripcion());
					values.put(CreateDBProvider.colItem[10], Items.get(i)
							.getPesoKg());
					values.put(CreateDBProvider.colItem[11], Items.get(i)
							.getMarca());
					values.put(CreateDBProvider.colItem[12], Items.get(i)
							.getSerie());
					values.put(CreateDBProvider.colItem[13], Items.get(i)
							.getComentarios());
					values.put(CreateDBProvider.colItem[14], Items.get(i)
							.getEstatus());// default sin
					// revisar

					Uri uri = contenR.insert(
							Uri.parse(MyContentProvider.URL + "Item"), values);
					uri.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 12:// Nomitem
			try {
				// looping through All
				ArrayList<NomItem> NomItem = (ArrayList<NomItem>) object;
				for (int i = 0; i < NomItem.size(); i++) {
					// JSONObject c = Json.getJSONObject(i);

					// Storing each json item in variable

					ContentValues values = new ContentValues();
					// values.put(CreateDBProvider.colNomItem[0],
					// Integer.parseInt(c.getString("idNom")));
					values.put(CreateDBProvider.colNomItem[1], NomItem.get(i)
							.getIdNom());
					values.put(CreateDBProvider.colNomItem[2], NomItem.get(i)
							.getIdItem());
					// values.put(CreateDBProvider.colNomItem[3],
					// NomItem.get(i).getEstatus());
				
					values.put(CreateDBProvider.colNomItem[3], Constantes.ESTATUS_SIN_REVISAR);
					Cursor cursor = contenR.query(
							Uri.parse(MyContentProvider.URL + "NomItem"), CreateDBProvider.colNomItem,
							"IdItem = " + NomItem.get(i)
							.getIdItem()+" AND idNom="+NomItem.get(i)
							.getIdNom() , null, null);
					
					if(!cursor.moveToFirst()){
					Uri uri = contenR.insert(
							Uri.parse(MyContentProvider.URL + "NomItem"),
							values);
					uri.toString();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 13:// Desglose

			break;
		default:
			break;
		}

	}

	public void PUTSync() {

	}
}
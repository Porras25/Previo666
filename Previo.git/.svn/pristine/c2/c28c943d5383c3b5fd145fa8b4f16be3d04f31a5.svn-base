/**
 * 
 */
package com.adquem.grupologistics.utilities;

import java.util.Random;

import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.model.Desglose;
import com.adquem.grupologistics.model.Factura;
import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.model.Pais;
import com.adquem.grupologistics.model.Referencia;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;


/**
 * @author Usuario
 *
 */
public class DummyData {
	
	Context ctx;
	
	int contpais = 0;
	

	
	public DummyData(Context context, int numRefs) {
		this.ctx = context;
/*		long er = agregarEstatus(Constantes.ESTATUS_EN_REVISION,String.valueOf(Constantes.ESTATUS_EN_REVISION));
		Log.v("Previo App", "en revision es:" + er);
		long r = agregarEstatus(Constantes.ESTATUS_REVISADO, String.valueOf(Constantes.ESTATUS_REVISADO));
		Log.v("Previo App", "revisado es:" + r);
		long sr = agregarEstatus(Constantes.ESTATUS_SIN_REVISAR, String.valueOf(Constantes.ESTATUS_SIN_REVISAR));
		Log.v("Previo App", "sin revisar es:" + sr);
*/		long rc = agregarRazonCierre();
		
		agregarNom(agregarColumNom());
		crearReferencias(numRefs, Constantes.ESTATUS_EN_REVISION, rc);
		crearReferencias(numRefs, Constantes.ESTATUS_REVISADO, rc);
		crearReferencias(numRefs, Constantes.ESTATUS_SIN_REVISAR, rc);
		//agregarCatNom();
		
		//deleteAll();

	}
	
	
	//////////////////////////////////////////////////// REFERENCIAS
	
	public int crearReferencias(int total, long estatus, long razon){
		int creados = 0;
		for(int i = 0; i < total; i++){
			int idCreado = agregaReferencia(i, razon, estatus);
			if(idCreado != -1){
				creados++;
				Log.v("Previo App", "Referencia creada:" + idCreado + " con estatus " + estatus);
				crearFacturas(3, idCreado, estatus);
			}
		}
		return creados;
	}
	
	
//CREATE TABLE Referencia (IdReferencia INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Cliente TEXT, Contenedor TEXT, RFC TEXT, NoReferencia TEXT, 
//Fecha_arribo TEXT, Ejecutivo TEXT, Clasificador TEXT, Plaza TEXT, Sello_arribo TEXT, Sello_asignado TEXT, NoOperacion TEXT, Fecha_fin_previo TEXT, 
//IdRazonCierre INTEGER, Comentarios_razon_cierre TEXT, Estatus INTEGER NOT NULL,IdAdj INTEGER, FOREIGN KEY(IdRazonCierre) REFERENCES RazonCierre(IdRazon), 
//FOREIGN KEY(IdAdj) REFERENCES Adjunto_Ref(IdAdj))";	
	public int agregaReferencia(int num, long razon, long estatus){
		ContentValues cvr = new ContentValues();
		cvr.put("Cliente", "cliente " + num);
		cvr.put("Contenedor", "contenedor " + num);
		cvr.put("RFC", "rfc" + num);
		cvr.put("NoReferencia", String.valueOf(new Random().nextInt(1000000000)));
		cvr.put("Fecha_arribo", new Random().nextInt(30) + "-04-2014 12:00:00");
		cvr.put("Ejecutivo", "ejecutivo " + num);
		cvr.put("Clasificador", "clasificador " + num);
		cvr.put("Plaza", "plaza" + num);
		cvr.put("Sello_arribo", "sello_arribo" + num);
		cvr.put("Sello_asignado", "sello_asignado" + num);
		cvr.put("NoOperacion", "noOperacion" + num);
		cvr.put("Fecha_Fin_Previo", new Random().nextInt(30) + "-04-2014 12:00:00");
		cvr.put("IdRazonCierre", razon);		
		cvr.put("Comentarios_Razon_Cierre", "comentariosCierre " + num);
		cvr.put("Estatus", estatus);
		//cvr.put("IdAdj", );
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"Referencia"), cvr).toString().split("/")[3]);
	}
	
	
	public int deleteAllReferencias(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"Referencia"), null, null);
	}
	
	
	//////////////////////////////////////////////// FACTURAS
	
	public int crearFacturas(int total, long ref, long estatus){
		int creados = 0;
		for(int i = 0; i < total; i++){
			long idCreado = agregaFactura(i, ref, estatus);
			if(idCreado != -1){
				creados++;
				Log.v("Previo App", "Factura creada:" + idCreado + " con estatus: " + estatus);
				crearItems(5, idCreado, estatus);
			}
		}
		return creados;
	}

//"CREATE TABLE Factura (IdFactura INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdReferencia INTEGER NOT NULL, Factura TEXT, Cantidad INTERGER , 
//FechaFactura TEXT, Estatus INTEGER NOT NULL, Proveedor TEXT, OrdenCompra TEXT, FOREIGN KEY(IdReferencia) REFERENCES Referencia(IdReferencia))";	
	public long agregaFactura(int num, long ref, long estatus){
		ContentValues cvf = new ContentValues();
		cvf.put("IdReferencia", ref);
		cvf.put("Factura", new Random().nextInt(1000));
		cvf.put("Cantidad", num);
		cvf.put("FechaFactura", new Random().nextInt(30) + "-04-2014 12:00:00");
		cvf.put("Estatus", estatus);
		cvf.put("Proveedor", "Proveedor " + num);		
		cvf.put("OrdenCompra", "ordenCompra" + num);
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"Factura"), cvf).toString().split("/")[3]);
	}
	
	
	public int deleteAllFacturas(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"Factura"), null, null);
	}
	
	
	///////////////////////////////////////////////// ITEMS	
	public int crearItems(int total, long fac, long estatus){
		int creados = 0;
		long pais = agregarPais();
		long um = agregarUnidMed();
		//long upc = agregarUpc();
		for(int i = 0; i < total; i++){
			long idCreado = agregaItem(i, fac, um, estatus); 
			if(idCreado != -1){
				Log.v("Previo App", "item creado:" + idCreado + " con estatus: " + estatus);
				agregarDesglose(idCreado, pais);
				agregarNomItem(1, idCreado);
				agregarUpc(idCreado);
				creados++;
			}
		}
		return creados;
	}

//"CREATE TABLE Item (IdItem INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdFactura INTEGER, CantidadEsp INTERGER, CantidadRec INTEGER, NoParte VARCHAR,
//Sku TEXT, IdUnidadMedida INTEGER,Valor_partida INTEGER, Fraccion_arancelaria TEXT, Descripcion TEXT, PesoKG INTEGER, Marca TEXT, Serie TEXT, 
//Comentarios TEXT, Estatus INTEGER NOT NULL, FOREIGN KEY(IdFactura) REFERENCES Facturas(IdFactura), FOREIGN KEY(IdUnidadMedida) REFERENCES UnidadMedida(IdUnidadMedida))";
	public long agregaItem(int num, long fac, long unidMed, long estatus){
		ContentValues cvi = new ContentValues();
		cvi.put("IdFactura", fac);
		//cvi.put("FolioItem", new Random().nextInt(1000));
		cvi.put("CantidadEsp", new Random().nextInt(30));
		cvi.put("CantidadRec", new Random().nextInt(30));
		cvi.put("NoParte", num);
		cvi.put("Sku", "SKU " + num);
		cvi.put("IdUnidadMedida", unidMed);
		cvi.put("Valor_partida", num);
		cvi.put("Fraccion_arancelaria", "FraccionArancelaria " + num);
		cvi.put("Descripcion", "Descripcion " + num);
		cvi.put("PesoKG", num);
		cvi.put("Marca", "Marca " + num);
		cvi.put("Serie", "Serie " + num);
		cvi.put("Comentarios", "comentarios" + num);
		cvi.put("Estatus", estatus);		
		//cvi.put("IdUpc", upc);
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"Item"), cvi).toString().split("/")[3]);
	}
	
	
	public int deleteAllItems(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"Item"), null, null);
	}
	
	///////////////////////// desglose
//"CREATE TABLE Desglose (IdDesglose INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdItem INTEGER, FolioDesglose INTEGER, Descripcion TEXT, Cantidad INTEGER, 
//IdPais INTERGER, FOREIGN KEY(IdItem) REFERENCES Item(IdItem), FOREIGN KEY(IdPais) REFERENCES Pais(IdPais))";	
	 public long agregarDesglose(long idItem, long idPais){
		 ContentValues cvd = new ContentValues();
		 cvd.put("IdItem", idItem);
		 cvd.put("FolioDesglose", new Random().nextInt(100));
		 cvd.put("Descripcion", "Descripcion desglose");
		 cvd.put("Cantidad", new Random().nextInt(30));
		 cvd.put("IdPais", idPais);
		 return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"Desglose"), cvd).toString().split("/")[3]);
	 }
	
	public int deleteAllDesgloses(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"Desglose"), null, null);
	}
	/////////////////////////// pais
//"CREATE TABLE Pais (IdPais INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Pais TEXT, CodigoPais TEXT)";	
	public long agregarPais(){
		ContentValues cvp = new ContentValues();
		cvp.put("Pais", "China" + contpais++);
		cvp.put("CodigoPais", "Ch");
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"Pais"), cvp).toString().split("/")[3]);
	}
	
	
	public int deleteAllPaises(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"Pais"), null, null);
	}
	
	
	//////////////////////////////////// unidad medida
//"CREATE TABLE CatunidadMedida (IdUnidadMedida INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdUnidadMedidaGL INTEGER, Nombre TEXT, Descripcion TEXT, 
//Unidad TEXT)";	
	public long agregarUnidMed(){
		ContentValues cvum = new ContentValues();
		//cvum.put("IdUnidadMedida", 1);
		cvum.put("Nombre", "Peso");
		cvum.put("Descripcion", "peso en kilogramos");
		cvum.put("Unidad", "kg");
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"CatunidadMedida"), cvum).toString().split("/")[3]);
	}
	
	public int deleteAllUniMed(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"CatunidadMedida"), null, null);
	}
	
	
	///////////////////////////////////   upc
//"CREATE TABLE Upc (IdArticulo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Upc TEXT NOT NULL, IdItem INTEGER, FOREIGN KEY(IdItem) REFERENCES Item(IdItem) )";	
	public long agregarUpc(long idItm){
		ContentValues cvp = new ContentValues();
		cvp.put("Upc", "UPC sample");
		cvp.put("IdItem", idItm);
		//cvp.put("NoParte", "Noparte sample");
		//cvp.put("Sku", "skusample");
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"Upc"), cvp).toString().split("/")[3]);
	}
	
	public int deleteAllUpc(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"Upc"), null, null);
	}
	
	////////////////////////////////////  estatus
//"CREATE TABLE Estatus (IdEstatus INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Estatus TEXT)";	
	public long agregarEstatus(int id, String estatus){
		ContentValues cves = new ContentValues();
		cves.put("IdEstatus", id);
		cves.put("Estatus", estatus);
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"Estatus"), cves).toString().split("/")[3]);
	}
	
	public int deleteAllEstatus(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"Estatus"), null, null);
	}
	
	////////////////////////////////////  razon cierre
//"CREATE TABLE RazonCierre (IdRazon INTEGER NOT NULL, Razon_Cierre TEXT)";	
	public long agregarRazonCierre(){
		ContentValues cvrc = new ContentValues();
		cvrc.put("Razon_Cierre", "Razon_Cierre porque si");
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"RazonCierre"), cvrc).toString().split("/")[3]);
	}
		
	public int deleteAllRazones(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"RazonCierre"), null, null);
	}

//"CREATE TABLE ColumnasNom (IdCampo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,Nombre TEXT, Orden INTEGER, IdTipoDato INTEGER, Requerido INTEGER, 
//TablaFuente TEXT, IdFuente INTEGER, TextoFuente TEXT )";
	public long agregarColumNom(){
		ContentValues cvcn = new ContentValues();
		//cvcn.put("IdCampo", 1);
		cvcn.put("Nombre", "nombre");
		cvcn.put("Orden", 1);
		cvcn.put("TipoDato", 1);
		cvcn.put("Requerido", 1);
		cvcn.put("TablaFuente", "tabla");
		cvcn.put("IdFuente", 1);
		cvcn.put("TextoFuente", "texto");
		//cvcn.put("ValorCapturado", "texto");
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"ColumnasNom"), cvcn).toString().split("/")[3]);
	}
	
	public int deleteAllColumNoms(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"ColumnasNom"), null, null);
	}
	
	
//"CREATE TABLE Nom (IdNom INTEGER NOT NULL PRIMARY KEY, IdCampo INTEGER, Nom TEXT,  FOREIGN KEY(IdCampo) REFERENCES ColumnasNom(IdCampo))";	
	public long agregarNom(long idColNom){
		ContentValues cvn = new ContentValues();
		cvn.put("IdNom", 1);
		cvn.put("IdCampo", idColNom);
		cvn.put("Nom", idColNom);
		//cvn.put("Estatus", Constantes.ESTATUS_SIN_REVISAR);
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"Nom"), cvn).toString().split("/")[3]);
	}
	
	public int deleteAllNoms(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"Nom"), null, null);
	}
	
//"CREATE TABLE NomItem (IdNom_Item INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdNom INTEGER, IdItem INTEGER, FOREIGN KEY(IdNom) REFERENCES Nom(IdNom), FOREIGN KEY(IdItem) REFERENCES Item(IdItem))";
	
	public long agregarNomItem(long idnom, long iditem){
		ContentValues cvn = new ContentValues();
		cvn.put("IdNom", idnom);
		cvn.put("IdItem", iditem);
		cvn.put("Estatus", Constantes.ESTATUS_SIN_REVISAR);
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"NomItem"), cvn).toString().split("/")[3]);
	}
	
	public int deleteAllNomsItems(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"NomItem"), null, null);
	}
	
	//////Crea catalogo normas
/*	public long agregarCatNom(){
	
		ContentValues cvn = new ContentValues();
		//cvn.put("IdNom", 1);
		cvn.put("IdNomGL", 1);
		cvn.put("Nombre", "NOM-800");
		return Integer.valueOf(this.ctx.getContentResolver().insert(Uri.parse(MyContentProvider.URL+"CatNormas"), cvn).toString().split("/")[3]);
	}
	
	public int deleteAllCatNoms(){
		return this.ctx.getContentResolver().delete(Uri.parse(MyContentProvider.URL+"CatNormas"), null, null);
	}
*/	
	
	private void deleteAll(){
		deleteAllReferencias();
		deleteAllFacturas();
		deleteAllItems();
		deleteAllDesgloses();
		deleteAllPaises();
		deleteAllEstatus();
		deleteAllRazones();
		deleteAllUniMed();
		deleteAllUpc();
		deleteAllColumNoms();
		deleteAllNomsItems();
		deleteAllNoms();
	}
	
	
}

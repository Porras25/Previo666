package com.adquem.grupologistics.dao;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

public class MyContentProvider extends ContentProvider {
	private SQLiteDatabase db;

	static final String PROVIDER_NAME = "com.adquem.grupologistics.dao.MyContentProvider";
	public static String URL = "content://" + PROVIDER_NAME + "/";
	static final Uri CONTENT_URI = Uri.parse(URL);

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub

		Context context = getContext();
		CreateDBProvider dbHelper = new CreateDBProvider(context);

		/**
		 * Create a write able database which will trigger its creation if it
		 * doesn't already exist.
		 */
		db = dbHelper.getWritableDatabase();
		return (db == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] colums, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub

		String[] tabla = uri.toString().split("/");
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		qb.setTables(tabla[3]);

		Cursor c = qb.query(db, colums, selection, selectionArgs, null, null,
				sortOrder);

		/**
		 * register to watch a content URI for changes
		 */
		c.setNotificationUri(getContext().getContentResolver(), uri);

		return c;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		/**
		 * Add a new student record
		 */
		String[] tabla = uri.toString().split("/");


		long rowID = db.insertWithOnConflict(tabla[3], "", values, SQLiteDatabase.CONFLICT_IGNORE);

		/**
		 * If record is added successfully
		 */
		if (rowID > 0) {
			Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
			getContext().getContentResolver().notifyChange(_uri, null);
			Log.i("URI insert", uri.toString());
			return _uri;
		}
		throw new SQLException("Failed to add a record into " + uri);
	}
	
	public Uri insertorreplace(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		/**
		 * Add a new student record
		 */
		String[] tabla = uri.toString().split("/");

		long rowID = db.insertWithOnConflict(tabla[3], "", values, SQLiteDatabase.CONFLICT_REPLACE);
		/**
		 * If record is added successfully
		 */
		if (rowID > 0) {
			Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
			getContext().getContentResolver().notifyChange(_uri, null);
			Log.i("URI insert", uri.toString());
			return _uri;
		}
		throw new SQLException("Failed to add a record into " + uri);
	}
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		int count = 0;
		String[] tabla = uri.toString().split("/");

		count = db.delete(tabla[3], selection, null);

		getContext().getContentResolver().notifyChange(uri, null);
		return count;

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		int count = 0;
		String[] tabla = uri.toString().split("/");
		count = db.update(tabla[3], values, selection, selectionArgs);

		getContext().getContentResolver().notifyChange(uri, null);

		return count;
	}

 	public static class CreateDBProvider extends SQLiteOpenHelper {
		public static final String[] colEstatusAparatos = { "IdCatalogo",
				"Nombre" };
		public static final String[] colCatNormas = { "IdNom", "IdCampo", "Nom" };
		public static final String[] colCatSiNo = { "IdCatalogo","Respuesta" };
		public static final String[] colCatUso = { "IdCatalogo", "Nombre" };
		public static final String[] colEstatus = { "IdEstatus", "Estatus" };
		public static final String[] colAdjunto_Ref = { "IdAdj", "Nombre", "Descripcion", "Concepto", "IdReferencia", "Estatus"};
		public static final String[] colAdjunto_Itm = { "IdAdj", "Nombre", "Descripcion", "Concepto", "IdItem", "Estatus"};
		public static final String[] colCatIdioma = { "IdIdioma", "Idioma" };
		public static final String[] colPais = { "IdPais", "Pais", "CodigoPais" };
		public static final String[] colRazonCierre = { "IdRazon", "Razon_cierre" };
		public static final String[] colUnidadMedida = { 
				"IdUnidadMedida", "Nombre", "Descripcion", "Unidad" };
		public static final String[] colUPC = { "IdArticulo", "Upc", "IdItem"};
		public static final String[] colColumnasNom = { "IdCampo","Nombre", "Orden", "TipoDato", "Requerido", "TablaFuente", "IdFuente", "TextoFuente" };
		public static final String[] colReferencia = { "IdReferencia",
				"Cliente", "Contenedor", "Rfc", "NoReferencia", "Fecha_arribo",
				"Ejecutivo", "Clasificador", "Plaza", "Sello_arribo",
				"Sello_asignado", "NoOperacion", "Fecha_Fin_Previo",
				"IdRazonCierre", "Comentarios_Razon_Cierre", "OrdenCompra", "Estatus"};
		public static final String[] colFactura = { "IdFactura", "IdReferencia",
				 "Factura", "Cantidad", "FechaFactura",
				"Estatus", "Proveedor", "OrdenCompra" };
		public static final String[] colNom = {"IdNom_campo", "IdNom", "IdCampo","Nom"};
		public static final String[] colRespuesta = { "IdResp","IdNom", "IdItem","IdCampo","Valor", "Estatus" };
		public static final String[] colItem = { "IdItem", "IdFactura",
				"CantidadEsp", "CantidadRec", "NoParte", "Sku",
				"IdUnidadMedida", "Valor_partida", "Fraccion_arancelaria",
				"Descripcion", "PesoKG", "Marca", "Serie", "Comentarios",
				"Estatus"};
		public static final String[] colDesglose = { "IdDesglose", "IdItem", "Cantidad",
				"Descripcion", "IdPais", "Estatus"};
		public static final String[] colNomItem = { "IdNom_Item", "IdNom", "IdItem" ,"Estatus"};
		public static final String[] colItemExcedente = { "IdItemExcedente", "IdItemApp" ,"IdItem"};
	    public static final String[] colUsuario = { "IdUsuario", "Nombre", "Ap_Paterno",
				"Ap_Materno", "NombreUsuario", "IdIdioma" };
		String[] nomColumnas = { "CatEstatusAparatos", "CatSiNo",
				"CatUso","Adjunto_Ref","Adjunto_Itm", "Pais",
				"RazonCierre", "CatunidadMedida", "Upc", "ColumnasNom",
				"Referencia", "Factura", "Nom", "Item", "Desglose", "NomItem",
				"Adjunto_Ref", "Adjunto_Itm","Respuesta", "Usuario" };
		String sqlCreateCatEstatusAparatos = "CREATE TABLE CatEstatusAparatos (IdCatalogo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Nombre TEXT)";
//		String sqlCreateCatNormas = "CREATE TABLE CatNormas (IdNom INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdNomGL INTEGER, Nombre TEXT)";
		String sqlCreateCatSiNo = "CREATE TABLE CatSiNo (IdCatalogo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Respuesta TEXT)";
		String sqlCreateCatUso = "CREATE TABLE CatUso (IdCatalogo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Nombre TEXT)";
//	  	String sqlCreateCatEstatus = "CREATE TABLE Estatus (IdEstatus INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Estatus TEXT)";
//      String sqlCreateConcepto = "CREATE TABLE Concepto (IdConcepto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Descripcion TEXT)";
//		String sqlCreateCatIdioma = "CREATE TABLE IFOREIGN KEY(IdRazonCierre) REFERENCES RazonCierre(IdRazon)dioma (IdIdioma INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Idioma TEXT)";
		String sqlCreateAdjunto_Ref = "CREATE TABLE Adjunto_Ref (IdAdj INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Descripcion TEXT, Concepto INTEGER, IdReferencia INTEGER, Estatus INTEGER NOT NULL, FOREIGN KEY(IdReferencia) REFERENCES Referencia(IdReferencia))";
		String sqlCreateAdjunto_Itm = "CREATE TABLE Adjunto_Itm (IdAdj INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Descripcion TEXT, Concepto INTEGER, IdItem INTEGER, Estatus INTEGER NOT NULL, FOREIGN KEY(IdItem) REFERENCES Item(IdItem))";
		String sqlCreatePais = "CREATE TABLE Pais (IdPais INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Pais TEXT, CodigoPais TEXT)";
		String sqlCreateRazonCierre = "CREATE TABLE RazonCierre (IdRazon INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Razon_cierre TEXT)";
		String sqlCreateCatUnidadMedida = "CREATE TABLE CatunidadMedida (IdUnidadMedida INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Descripcion TEXT, Unidad TEXT)";
		String sqlCreateUPC = "CREATE TABLE Upc (IdArticulo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Upc TEXT NOT NULL, IdItem INTEGER, FOREIGN KEY(IdItem) REFERENCES Item(IdItem) )";
		String sqlCreateColumnasNom = "CREATE TABLE ColumnasNom (IdCampo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,Nombre TEXT, Orden INTEGER, TipoDato TEXT, Requerido INTEGER, TablaFuente TEXT, IdFuente INTEGER, TextoFuente TEXT )";
		String sqlCreateReferencia = "CREATE TABLE Referencia (IdReferencia INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Cliente TEXT, Contenedor TEXT, RFC TEXT, NoReferencia TEXT, Fecha_arribo TEXT, Ejecutivo TEXT, Clasificador TEXT, Plaza TEXT, Sello_arribo TEXT, Sello_asignado TEXT, NoOperacion TEXT, Fecha_fin_previo TEXT, IdRazonCierre INTEGER, Comentarios_razon_cierre TEXT, OrdenCompra TEXT, Estatus INTEGER NOT NULL, FOREIGN KEY(IdRazonCierre) REFERENCES RazonCierre(IdRazon))";
		String sqlCreateFactura = "CREATE TABLE Factura (IdFactura INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdReferencia INTEGER NOT NULL, Factura TEXT, Cantidad INTERGER , FechaFactura TEXT, Estatus INTEGER NOT NULL, Proveedor TEXT, OrdenCompra TEXT, FOREIGN KEY(IdReferencia) REFERENCES Referencia(IdReferencia))";
		String sqlCreateNom = "CREATE TABLE Nom (IdNom_campo INTEGER NOT NULL PRIMARY KEY,IdNom INTEGER, IdCampo INTEGER, Nom TEXT,  FOREIGN KEY(IdCampo) REFERENCES ColumnasNom(IdCampo))";
		String sqlCreateItem = "CREATE TABLE Item (IdItem INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdFactura INTEGER, CantidadEsp REAL, CantidadRec REAL, NoParte VARCHAR,Sku TEXT, IdUnidadMedida INTEGER,Valor_partida REAL, Fraccion_arancelaria TEXT, Descripcion TEXT, PesoKG REAL, Marca TEXT, Serie TEXT, Comentarios TEXT, Estatus INTEGER NOT NULL, FOREIGN KEY(IdFactura) REFERENCES Facturas(IdFactura), FOREIGN KEY(IdUnidadMedida) REFERENCES UnidadMedida(IdUnidadMedida))";
		String sqlCreateDesglose = "CREATE TABLE Desglose (IdDesglose INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdItem INTEGER, Descripcion TEXT, Cantidad INTEGER, IdPais INTERGER, Estatus INTEGER NOT NULL, FOREIGN KEY(IdItem) REFERENCES Item(IdItem), FOREIGN KEY(IdPais) REFERENCES Pais(IdPais))";
		String sqlCreateNomItem = "CREATE TABLE NomItem (IdNom_Item INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdNom INTEGER, IdItem INTEGER, Estatus INTEGER NOT NULL, FOREIGN KEY(IdNom) REFERENCES Nom(IdNom), FOREIGN KEY(IdItem) REFERENCES Item(IdItem))";
		String sqlCreateRespuesta = "CREATE TABLE Respuesta (IdResp INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdNom INTEGER, IdItem INTEGER, IdCampo INTEGER, Valor TEXT, Estatus INTEGER NOT NULL, FOREIGN KEY(IdItem) REFERENCES Item(IdItem), FOREIGN KEY(IdCampo) REFERENCES CloumnasNom(IdCampo),FOREIGN KEY(IdNom) REFERENCES NomItem(IdNom))";
		String sqlCreateUsuario = "CREATE TABLE Usuario (IdUsuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Ap_Paterno TEXT, Ap_Materno TEXT, NombreUsuario TEXT, Idioma INTEGER)";
		String sqlCreateItemExcedente = "CREATE TABLE ItemExcedente (IdItemExcedente INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdItemApp INTEGER, IdItem INTEGER)";

		private static final String DATABASE_NAME = "previo.db";
		private static final int DATABASE_VERSION = 1;

		CreateDBProvider(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		  

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(sqlCreateUsuario);
			db.execSQL(sqlCreateCatEstatusAparatos);
//			db.execSQL(sqlCreateCatNormas);
			db.execSQL(sqlCreateCatSiNo);
			db.execSQL(sqlCreateCatUso);
//			db.execSQL(sqlCreateCatEstatus);
//			db.execSQL(sqlCreateConcepto);
//			
			db.execSQL(sqlCreatePais);
			db.execSQL(sqlCreateRazonCierre);
			db.execSQL(sqlCreateCatUnidadMedida);
			db.execSQL(sqlCreateAdjunto_Ref);
			db.execSQL(sqlCreateAdjunto_Itm);
			
			db.execSQL(sqlCreateColumnasNom);
			db.execSQL(sqlCreateReferencia);
			
			db.execSQL(sqlCreateFactura);
			
			db.execSQL(sqlCreateNom);
			db.execSQL(sqlCreateItem);
			db.execSQL(sqlCreateUPC);
			db.execSQL(sqlCreateDesglose);
			db.execSQL(sqlCreateNomItem);
			db.execSQL(sqlCreateRespuesta);
			db.execSQL(sqlCreateItemExcedente);
		

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

	}
}
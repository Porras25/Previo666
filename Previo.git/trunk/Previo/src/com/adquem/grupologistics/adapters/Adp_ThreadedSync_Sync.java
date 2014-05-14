package com.adquem.grupologistics.adapters;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.adquem.grupologistics.bussines.Buss_REST_Sync;
import com.adquem.grupologistics.dao.SyncLT;
import com.adquem.grupologistics.model.CatEstatusAparatos;
import com.adquem.grupologistics.model.CatSiNo;
import com.adquem.grupologistics.model.CatUso;
import com.adquem.grupologistics.model.ColumnasNom;
import com.adquem.grupologistics.model.Desglose;
import com.adquem.grupologistics.model.Factura;
import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.model.Nom;
import com.adquem.grupologistics.model.NomItem;
import com.adquem.grupologistics.model.Pais;
import com.adquem.grupologistics.model.RazonCierre;
import com.adquem.grupologistics.model.Referencia;
import com.adquem.grupologistics.model.UnidadMedida;
import com.adquem.grupologistics.model.Upc;
import com.adquem.grupologistics.utilities.Constantes;
import com.adquem.grupologistics.utilities.Utl_RESTful_Client;
import com.adquem.grupologistics.utilities.Utl_SharedPreferences;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

public class Adp_ThreadedSync_Sync extends AbstractThreadedSyncAdapter {

	SyncLT Sync;
	Utl_RESTful_Client cliente;

	private static final String TAG = null;
	ContentResolver mContentResolver;
	Buss_REST_Sync buss_REST_sync;
	String [] Tablas;
	Utl_SharedPreferences prefs;
	/**
	 * Set up the sync adapter
	 */
	public Adp_ThreadedSync_Sync(Context context, boolean autoInitialize) {
		super(context, autoInitialize);
		/*
		 * If your app uses a content resolver, get an instance of it from the
		 * incoming Context
		 */
		buss_REST_sync= new Buss_REST_Sync(context);
		mContentResolver = context.getContentResolver();
		Tablas= Constantes.nomMetodosGet;
		android.os.Debug.waitForDebugger();
		
		prefs = new Utl_SharedPreferences(context);
	}

	@Override
	public void onPerformSync(Account account, Bundle extras, String authority,
			ContentProviderClient provider, SyncResult syncResult) {
		// TODO Auto-generated method stub
		  Log.i(TAG, "Beginning network synchronization");
	        try {
	        	  
	        	   buss_REST_sync.postExcedentes();
                   buss_REST_sync.putItem();
                   buss_REST_sync.postRespuestas();
                   buss_REST_sync.putDesglose();   
                   buss_REST_sync.putReferencia();
                   buss_REST_sync.putArdItm(); 
                   buss_REST_sync.putArdRef();
                  
               	Utl_RESTful_Client cleinte = new Utl_RESTful_Client();
				SyncLT sync = new SyncLT();
				// try {
				for (int i = 0; Tablas.length > i; i++) {

					switch (i) {
					case 0:
						ArrayList<CatUso> Usos = cleinte
								.getUsos(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Usos, i, Constantes.nomTablas,
								mContentResolver, true);
						break;
					case 1:
						ArrayList<Pais> Paises = cleinte
								.getPaises(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());

						sync.GETSync(Paises, i, Constantes.nomTablas,
								mContentResolver, true);
						break;
					case 2:
						ArrayList<UnidadMedida> UnidadMedida = cleinte
								.getUnidadMedida(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(UnidadMedida, i, Constantes.nomTablas,
								mContentResolver, true);
						break;
					case 3:
						ArrayList<RazonCierre> RazonCierre = cleinte
								.getRazonCierre(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(RazonCierre, i, Constantes.nomTablas,
								mContentResolver, true);
						break;

					case 4:
						ArrayList<ColumnasNom> ColumnasNoms = cleinte
								.getColumnasNoms(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(ColumnasNoms, i, Constantes.nomTablas,
								mContentResolver, true);
						break;
					case 5:
						ArrayList<CatSiNo> SiNO = cleinte
								.getSiNo(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(SiNO, i, Constantes.nomTablas,
								mContentResolver, true);
						break;
					case 6:
						ArrayList<CatEstatusAparatos> EstatusAparatos = cleinte
								.getCatEstatusAparatos(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(EstatusAparatos, i, Constantes.nomTablas,
								mContentResolver, true);
						break;
					case 7:
						ArrayList<Nom> Noms = cleinte
								.getNom(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Noms, i, Constantes.nomTablas,
								mContentResolver, true);
						break;
					case 8:
						ArrayList<Upc> Upcs = cleinte
								.getUpc(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Upcs, i, Constantes.nomTablas,
								mContentResolver, false);
						break;
					case 9:
						ArrayList<Referencia> Referencias = cleinte
								.getReferencia(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Referencias, i, Constantes.nomTablas,
								mContentResolver, false);
						break;
					case 10:
						ArrayList<Factura> Facturas = cleinte
								.getFactura(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Facturas, i, Constantes.nomTablas,
								mContentResolver, false);
						break;
					case 11:
						ArrayList<Item> Items = cleinte
								.getItem(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null)_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Items, i, Constantes.nomTablas,
								mContentResolver, false);
						break;
					case 12:
						ArrayList<NomItem> NomsItems = cleinte
								.getNomItem(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(NomsItems, i, Constantes.nomTablas,
								mContentResolver, false);
						break;
					case 13:
						ArrayList<Desglose> Desgloses = cleinte
								.getDesglose(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + prefs.getPreferencesSettings().getString(Constantes.VALUE_PREFERENCE_TOKEN, null));

						// objetoReferencias.get("Referencia");
//						publishProgress(Tablas[i] + " completas...");
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Desgloses, i, Constantes.nomTablas,
								mContentResolver, false);
						break;

					default:
						break;
					}
				}
	          	          //}
	            InputStream stream = null;

	            try {
	                Log.i(TAG, "Streaming data from network: " + 0);
	      
	                updateLocalFeedData(stream, syncResult);
	                // Makes sure that the InputStream is closed after the app is
	                // finished using it.
	            } finally {
	                if (stream != null) {
	                    stream.close();
	                }
	            }
	        } catch (MalformedURLException e) {
	            Log.wtf(TAG, "Feed URL is malformed", e);
	            syncResult.stats.numParseExceptions++;
	            return;
	        } catch (IOException e) {
	            Log.e(TAG, "Error reading from network: " + e.toString());
	            syncResult.stats.numIoExceptions++;
	            return;
	      
	        }
	        Log.i(TAG, "Network synchronization complete");
		

	}
	 private void updateLocalFeedData(InputStream stream, SyncResult syncResult) {
		// TODO Auto-generated method stub
		
	}


}

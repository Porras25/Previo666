package com.adquem.grupologistics.utilities;

import com.adquem.grupologistics.utilities.Configuracion;

import android.content.Context;
import android.content.SharedPreferences;


public class Utl_SharedPreferences {
	
	SharedPreferences settings;
	
	public Utl_SharedPreferences(Context cnx){
		settings = cnx.getSharedPreferences(Constantes.NOMBRE_PREFERENCE_CONFIG, Context.MODE_PRIVATE);
	}
	
	public void createPreferencesSettings( ){
		 
		
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(Constantes.TITLE_PREFERENCE_LENGUAJE, Constantes.VALUE_PREFERENCE_LENGUAJE_ESP);
		editor.putString(Constantes.TITLE_PREFERENCE_URL, Constantes.VALUE_PREFERENCE_URL);
		editor.putInt(Constantes.TITLE_PREFERENCE_PUERTO, Constantes.VALUE_PREFERENCE_PUERTO);
		editor.putString(Constantes.TITLE_PREFERENCE_APIKEY, Constantes.VALUE_PREFERENCE_APIKEY);
		editor.putString(Constantes.TITLE_PREFERENCE_RALM, Constantes.VALUE_PREFERENCE_RALM);
		editor.commit();
		
		
	}
	
	public SharedPreferences getPreferencesSettings(){
		
		return settings;
		
	}
	
  public SharedPreferences.Editor getEditorPreferencesSettings(){
		
		return settings.edit();
		
	}
	
  public void updatePreferencesSettings(Configuracion configuracion){
		
	    SharedPreferences.Editor editor = settings.edit();
		editor.putString(Constantes.TITLE_PREFERENCE_LENGUAJE, configuracion.getLenguage());
		editor.putString(Constantes.TITLE_PREFERENCE_URL, configuracion.getUrl());
		editor.putInt(Constantes.TITLE_PREFERENCE_PUERTO, configuracion.getPuerto());
		editor.putString(Constantes.TITLE_PREFERENCE_APIKEY, configuracion.getApikey());
		editor.putString(Constantes.TITLE_PREFERENCE_RALM, configuracion.getRalm());
		editor.commit();
		
	}
  
  

}

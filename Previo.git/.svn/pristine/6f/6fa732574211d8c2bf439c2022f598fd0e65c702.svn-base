package com.adquem.grupologistics.bussines;

import android.content.Context;
import android.content.SharedPreferences;

import com.adquem.grupologistics.utilities.Configuracion;
import com.adquem.grupologistics.utilities.Constantes;
import com.adquem.grupologistics.utilities.Utl_SharedPreferences;

public class Buss_Frag_Configuracion {

	Configuracion configuracion;
	Utl_SharedPreferences preferences;
	
	
	public Configuracion mostrarDatos(Context cnx) {
		configuracion = new Configuracion();
		preferences = new Utl_SharedPreferences(cnx);
		
		String idioma = preferences.getPreferencesSettings().getString(
				Constantes.TITLE_PREFERENCE_LENGUAJE, "");
		/*String url = preferences.getPreferencesSettings().getString(
				Constantes.VALUE_PREFERENCE_URL, "");*/
		String url = Constantes.VALUE_PREFERENCE_URL;
		int puerto = preferences.getPreferencesSettings().getInt(
				Constantes.TITLE_PREFERENCE_PUERTO, 0);
		/*String apikey = preferences.getPreferencesSettings().getString(
				Constantes.VALUE_PREFERENCE_APIKEY, "");*/
		String apikey = Constantes.VALUE_PREFERENCE_APIKEY;
		String ralm = preferences.getPreferencesSettings().getString(
				Constantes.VALUE_PREFERENCE_RALM, "");

		configuracion.setLenguage(idioma);
		configuracion.setUrl(url);
		configuracion.setPuerto(puerto);
		configuracion.setApikey(apikey);
		configuracion.setRalm(ralm);


		return configuracion;
	}

	public void updateConfiguracion(Configuracion configuracion, Context cnx) {
		
		preferences = new Utl_SharedPreferences(cnx);
		
		preferences.updatePreferencesSettings(configuracion);
		
		/*preferences.getEditorPreferencesSettings().putString("email", "modificado@email.com");
		preferences.getEditorPreferencesSettings().putString("email", "modificado@email.com");
		preferences.getEditorPreferencesSettings().putString("email", "modificado@email.com");
		preferences.getEditorPreferencesSettings().putString("email", "modificado@email.com");
		preferences.getEditorPreferencesSettings().putString("email", "modificado@email.com");
		preferences.getEditorPreferencesSettings().commit();*/
		
	}

}

package com.adquem.grupologistics.controllers;

import java.util.Stack;

import com.adquem.grupologistics.bussines.Buss_Frag_Configuracion;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.utilities.Configuracion;
import com.adquem.grupologistics.utilities.Constantes;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Frag_configuracion extends Fragment{

	Buss_Frag_Configuracion configBuss;
	Configuracion configBean;
	TextView lenguaje, apikey, url;
	Button saveConfig;
	ViewGroup root;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;
	  @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		  
		  configBuss = new Buss_Frag_Configuracion();
		  configBean = new Configuracion();
		  
		  root = (ViewGroup) inflater.inflate(R.layout.lyt_fragment_configuracion ,null);
		  lenguaje = (TextView) root.findViewById(R.id.txt_lenguaje);
		  
		  apikey = (TextView) root.findViewById(R.id.txt_puerto);
		  apikey.setFocusable(false);
		  url = (TextView) root.findViewById(R.id.txt_url);	
		  url.setFocusable(false);
		  saveConfig = (Button) root.findViewById(R.id.btn_guardar);
		  saveConfig.setVisibility(View.GONE);
		  
		  configBean = configBuss.mostrarDatos(root.getContext());	  
			
		  
		  	prefs = getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
			editor = prefs.edit();
			String idioma = null;
			// Cambiar idioma
			switch (prefs.getInt(Constantes.TITTLE_USER_LENGUAGE, 0)) {
			case 0:
				//idioma = "es";
				lenguaje.setText("Lenguaje: " +Constantes.VALUE_PREFERENCE_LENGUAJE_ESP);
				break;
			case 1:
				//idioma = "en";
				lenguaje.setText("Lenguage: " + Constantes.VALUE_PREFERENCE_LENGUAJE_ING);
				break;
			default:
				break;
			}
			
			 
		  apikey.setText("APIKEY: " + configBean.getApikey() + "");
		  url.setText("URL: " + configBean.getUrl());
		 
		return root;
	    }  
		
}

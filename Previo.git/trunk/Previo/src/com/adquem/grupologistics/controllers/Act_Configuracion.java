package com.adquem.grupologistics.controllers;

import java.util.Locale;
import java.util.Stack;

import com.adquem.grupologistics.bussines.Buss_Frag_Configuracion;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.utilities.Configuracion;
import com.adquem.grupologistics.utilities.Constantes;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Act_Configuracion extends Activity {

	Buss_Frag_Configuracion configBuss;
	Configuracion configBean;
	TextView lenguaje, puerto, url;
	public static String valor;
	public static Context contexto;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3262A2")));
		
		prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
		editor = prefs.edit();
		String idioma = null;
		// Cambiar idioma
		switch (prefs.getInt(Constantes.TITTLE_USER_LENGUAGE, 0)) {
		case 0:
			idioma = "es";
			break;
		case 1:
			idioma = "en";
			break;
		default:
			break;
		}

		Resources res = this.getResources();
		// Change locale settings in the app.
		DisplayMetrics dm = res.getDisplayMetrics();
		android.content.res.Configuration conf = res.getConfiguration();
		conf.locale = new Locale(idioma.toLowerCase());
		res.updateConfiguration(conf, dm);
				
		setContentView(R.layout.lyt_fragment_configuracion);
		configBuss = new Buss_Frag_Configuracion();
		configBean = new Configuracion();

		lenguaje = (TextView) findViewById(R.id.txt_lenguaje);
		puerto = (TextView) findViewById(R.id.txt_puerto);
		url = (TextView) findViewById(R.id.txt_url);

		configBean = configBuss.mostrarDatos(getApplicationContext());

		if (configBean != null) {
			
			lenguaje.setText(configBean.getLenguage());
			puerto.setText(configBean.getApikey()+ "");
			url.setText(configBean.getUrl());
		
		}else{
			Toast.makeText(getApplicationContext(), "CofigBean is Null",
					Toast.LENGTH_SHORT).show();
		}

		Button save = (Button) findViewById(R.id.btn_guardar);
		save.setOnClickListener(new OnClickListener() {
			//
			@Override
			public void onClick(View v) {
				// // TODO Auto-generated method stub
				guardarConfig();
				
				 Intent inten = new Intent(Act_Configuracion.this, Login.class);
		    	 startActivity(inten);
		    	 finish();
			}
		});

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		 Intent inten = new Intent(Act_Configuracion.this, Login.class);
   	 startActivity(inten);
   	 finish();
		super.onBackPressed();
	}

	public void guardarConfig() {

		configBuss = new Buss_Frag_Configuracion();
		configBean = new Configuracion();

		lenguaje = (TextView) findViewById(R.id.txt_lenguaje);
		puerto = (TextView) findViewById(R.id.txt_puerto);
		url = (TextView) findViewById(R.id.txt_url);

		configBean.setLenguage(lenguaje.getText().toString());
		configBean.setPuerto(Integer.parseInt(puerto.getText().toString()));
		configBean.setUrl(url.getText().toString());

		configBuss.updateConfiguracion(configBean, getApplicationContext());
		Toast.makeText(getApplicationContext(), "Datos guardados",
				Toast.LENGTH_SHORT).show();

	}
}

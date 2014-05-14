package com.adquem.grupologistics.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;

import com.adquem.grupologistics.utilities.Constantes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.MediaController;

/**
 * Clase Controlador entre la Vista y la Capa de Negocio para la Visualización
 * de las Fotos de Referencia e Ítem
 * 
 * @author Adquem
 * @version 1.0
 */
public class Act_FotoIntent extends Activity {
	DisplayMetrics dm;
	SurfaceView sur_View;
	MediaController media_Controller;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;

	private ImageView imagePlayer;

	/**
	 * Crea la actividad de mostrar las Foto de Referencia e Ítem
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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

		setContentView(R.layout.lyt_mostrarfoto);

		imagePlayer = (ImageView) findViewById(R.id.imgv_mostrarFoto);
		imagePlayer.setKeepScreenOn(true);

		Bundle extras = getIntent().getExtras();
		String path = extras.getString("path");

		File imgFile = new File(path);
		if (imgFile.exists()) {

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = Constantes.BITMAP_SIZE;
			// Bitmap
			// preview_bitmap=BitmapFactory.decodeStream(is,null,options);
			// Bitmap myBitmap =
			// BitmapFactory.decodeFile(imgFile.getAbsolutePath());
			Bitmap myBitmap = null;
			try {
				myBitmap = BitmapFactory.decodeStream(new FileInputStream(
						imgFile.getAbsolutePath()), null, options);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Drawable d = new BitmapDrawable(getResources(), myBitmap);
			imagePlayer.setImageBitmap(myBitmap);
		}
	}
}

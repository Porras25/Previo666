package com.adquem.grupologistics.controllers;

import java.util.Locale;

import com.adquem.grupologistics.utilities.Constantes;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Clase Controlador entre la Vista y la Capa de Negocio para la Visualización
 * de los Videos de Referencia e Ítem
 * 
 * @author Adquem
 * @version 1.0
 */
public class Act_VideoIntent extends Activity implements OnCompletionListener,
		OnPreparedListener {
	VideoView video_player_view;
	DisplayMetrics dm;
	SurfaceView sur_View;
	MediaController media_Controller;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;

	private VideoView videoPlayer;

	/**
	 * Crea la actividad de mostrar los Videos de Referencia e Ítem
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
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
		setContentView(R.layout.lyt_reproducirvideo);

		videoPlayer = (VideoView) findViewById(R.id.vidv_reproducirVideo);
		videoPlayer.setOnPreparedListener(this);
		videoPlayer.setOnCompletionListener(this);
		videoPlayer.setKeepScreenOn(true);

		Bundle extras = getIntent().getExtras();
		String path = extras.getString("path");

		videoPlayer.setVideoPath(path);
	}

	/**
	 * Inicia la Reproducción del Video seleccionado
	 */
	@Override
	public void onPrepared(MediaPlayer vp) {
		if (videoPlayer.canSeekForward())
			videoPlayer.seekTo(videoPlayer.getDuration() / 5);
		videoPlayer.start();
	}

	/**
	 * Finaliza la Reproducción del Video previamente seleccionado
	 */
	@Override
	public void onCompletion(MediaPlayer mp) {
		this.finish();
	}

	/**
	 * Permite hacer Pausa e Iniciar nuevamente la Reproducción del Video que se
	 * selecciooo
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			if (videoPlayer.isPlaying()) {
				videoPlayer.pause();
			} else {
				videoPlayer.start();
			}
			return true;
		} else {
			return false;
		}
	}
}

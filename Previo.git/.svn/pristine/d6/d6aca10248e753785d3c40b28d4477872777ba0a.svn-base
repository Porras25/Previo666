package com.adquem.grupologistics.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.adquem.grupologistics.bussines.Buss_FragListadoFact_RevisionReferencia;
import com.adquem.grupologistics.bussines.Buss_Frag_AltaItem;
import com.adquem.grupologistics.utilities.Constantes;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Clase Controlador entre la Vista y la Capa de Negocio para la Visualización
 * de la Galería de Fotos de Inicio de Referencia
 * 
 * @author Adquem
 * @version 1.0
 */
public class Act_GalleryReferenciaFoto extends Activity {
	public ListView listaFotos;
	public ImageButton basura;
	public TextView tituloGaleria;
	ArrayList<String> fotoFileList = new ArrayList<String>();
	ArrayList<String> pathDelete = new ArrayList<String>();
	cargaGaleriaFotos syncFotos;
	MyThumbnaildapter adapter;
	List<Bitmap> imagenes = new ArrayList<Bitmap>();
	SharedPreferences.Editor editor;
	SharedPreferences prefs;

	private long Padre;

	public Buss_FragListadoFact_RevisionReferencia buss = new Buss_FragListadoFact_RevisionReferencia();

	/**
	 * Clase para mostrar las Fotos contenidas en la Galería de Fotos de Inicio
	 * de Referencia y la Eliminación de Fotos
	 */
	public class MyThumbnaildapter extends ArrayAdapter<String> {
		/**
		 * Constructor de la Clase adaptador MyThumbnaildapter
		 * 
		 * @param context
		 * @param textViewResourceId
		 * @param objects
		 */
		public MyThumbnaildapter(Context context, int textViewResourceId,
				ArrayList<String> objects) {
			super(context, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}

		/**
		 * Crea la Vista para Mostrar las Fotos de Inicio de Referencia
		 * 
		 * @param position
		 *            Posición de la Foto a mostrar
		 * @param convertView
		 *            Fila que se mostrará en la vista
		 * @param parent
		 *            Vista que va a llenarse con la Foto
		 */
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			View row = convertView;

			if (row == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.lyt_galerias, parent, false);
			}

			final TextView textfilePath = (TextView) row
					.findViewById(R.id.txv_pathNombre);
			textfilePath.setText(fotoFileList.get(position));

			ImageView imageThumbnail = (ImageView) row
					.findViewById(R.id.imgv_imagenGaleria);

			imageThumbnail.setImageBitmap(imagenes.get(position));

			row.setOnClickListener(new OnClickListener() {
				/**
				 * Realiza el Intento para la Visualización de las Fotos de
				 * Inicio de Referencia pasando el path de las Fotos a mostrar
				 */
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent foto = new Intent(Act_GalleryReferenciaFoto.this,
							Act_FotoIntent.class);
					foto.putExtra("path", textfilePath.getText().toString());
					startActivity(foto);
				}
			});

			final CheckBox chebox = (CheckBox) row
					.findViewById(R.id.chkb_itemSeleccionado);
			if (chebox.isChecked()) {
				chebox.setChecked(false);
			}

			chebox.setOnClickListener(new OnClickListener() {
				/**
				 * Realiza la Eliminación de las Fotos de Inicio de Referencia
				 * pasando el path de las Fotos a eliminar
				 */
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (chebox.isChecked()) {
						pathDelete.add(textfilePath.getText().toString());
						;
					} else {
						for (int i = 0; i < pathDelete.size(); i++) {
							if (pathDelete.get(i).equals(
									textfilePath.getText().toString())) {
								pathDelete.remove(i);
							}
						}
					}
				}
			});
			return row;
		}
	}

	/**
	 * Crea la Vista de Galería para mostrar la Lista de Fotos de Inicio de
	 * Referencia
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
		setContentView(R.layout.lyt_fragment_gallery);

		tituloGaleria = (TextView) findViewById(R.id.txv_tituloGaleria);
		tituloGaleria
				.setText(R.string.str_galerias_txtv_tituloGaleriaFotoInicioRef);

		listaFotos = (ListView) findViewById(R.id.lstv_fotoVideoGalerias);
		basura = (ImageButton) findViewById(R.id.imgb_eliminar);

		basura.setOnClickListener(new OnClickListener() {
			/**
			 * Realiza la Eliminación de las Fotos de Inicio de la Referencia
			 * eliminándolas de la Base de Datos, pasando el path de las Fotos a
			 * eliminar
			 */
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int estatus = 0;
				Buss_Frag_AltaItem bus_alta_item = new Buss_Frag_AltaItem();

				estatus = bus_alta_item.obtiene_estatus_referencia(Padre,
						getBaseContext());

				if (estatus != Constantes.ESTATUS_SINCRONIZADO) {

					for (int i = 0; i < pathDelete.size(); i++) {
						for (int j = 0; j < fotoFileList.size(); j++) {
							if (pathDelete.get(i).equals(fotoFileList.get(j))) {

								File file = new File(fotoFileList.get(j));
								boolean bol = file.delete();

								fotoFileList.remove(adapter.getItem(j));
								imagenes.remove(j);
								Log.v(getString(R.string.str_galeriaRef_log_eliminarFotoInicioTag),
										getString(R.string.str_galeriaRef_log_eliminarFotoInicioMsg)
												+ pathDelete.get(i));
								buss.deleteAdjunto(
										pathDelete.get(i),
										Constantes.CONCEPTO_ELIMINA_ADJUNTO_REFERENCIA,
										getBaseContext());
								break;
							}
						}
					}

					Buss_FragListadoFact_RevisionReferencia bus = new Buss_FragListadoFact_RevisionReferencia();

					if (!bus.validacionFotos(Long.parseLong(Padre + ""),
							getBaseContext())) {
						bus.actualiza_estatus(Padre,
								Constantes.ESTATUS_EN_REVISION,
								getBaseContext());
					}
					adapter.notifyDataSetChanged();
				} else {
					Toast.makeText(
							getBaseContext(),
							getString(R.string.str_galeriaRef_toast_eliminarFotoRefSinc),
							Toast.LENGTH_LONG).show();
				}
			}
		});

		this.Padre = getIntent().getExtras().getLong("IdRef");

		syncFotos = new cargaGaleriaFotos();
		syncFotos.execute();
	}

	/**
	 * Clase para Generar el Dialogo de Progreso al Cargar las Fotos a mostar en
	 * la Galeria de Fotos de Inicio de Referencia
	 */
	public class cargaGaleriaFotos extends AsyncTask<Void, Void, Void> {
		ProgressDialog progress;
		/**
		 * Muestra el Progreso en la Carga de Fotos de la Galeria
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progress = new ProgressDialog(Act_GalleryReferenciaFoto.this);
			progress.setTitle(getString(R.string.str_galerias_asyncTask_galeriaFotosInicioTiulo));
			progress.setMessage(getString(R.string.str_galerias_asyncTask_galeriasMensaje));
			progress.setCancelable(false);
			progress.show();
		}

		/**
		 * Genera Listado de Fotos de Inicio de Referencia agregándolas a un
		 * Archivo para ser mostradas en la Galería obteniendo el directorio
		 * personzalizado
		 */
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if (Padre != 0) {
				String ExternalStorageDirectoryPath = Environment
						.getExternalStoragePublicDirectory(
								Environment.DIRECTORY_PICTURES)
						.getAbsolutePath();

				String path = ExternalStorageDirectoryPath
						+ Constantes.RUTA_REF_FOTO_INICIO + "/" + Padre;

				File f = new File(path);
				File array[] = f.listFiles();

				if (array != null) {
					if (array.length != 0) {
						for (int j = 0; j < array.length; j++) {
							fotoFileList.add(array[j].toString());

							File file = new File(array[j].toString());
							Bitmap b = decodeFile(file);
							imagenes.add(b);
						}
					}
				} else {
					Toast.makeText(
							getApplicationContext(),
							getString(R.string.str_galerias_toast_sinFotosInicio),
							Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(getApplicationContext(),
						getString(R.string.str_galerias_toast_sinFotosInicio),
						Toast.LENGTH_LONG).show();
				Log.i(getString(R.string.str_galerias_log_sinDirectorioFotoInicioMsg),
						getString(R.string.str_galerias_log_sinDirectorioFotoInicioTag));
			}
			return null;
		}

		/**
		 * Transforma el Archivo que contiene la Foto a un formato de Bitmap
		 * 
		 * @param f
		 *            Archivo a transformar
		 * @return Valor nulo
		 */
		private Bitmap decodeFile(File f) {
			try {
				BitmapFactory.Options o = new BitmapFactory.Options();
				o.inJustDecodeBounds = true;
				BitmapFactory.decodeStream(new FileInputStream(f), null, o);

				final int REQUIRED_SIZE = 70;

				int scale = 1;
				while (o.outWidth / scale / 2 >= REQUIRED_SIZE
						&& o.outHeight / scale / 2 >= REQUIRED_SIZE)
					scale *= 2;

				BitmapFactory.Options o2 = new BitmapFactory.Options();
				o2.inSampleSize = scale;
				return BitmapFactory.decodeStream(new FileInputStream(f), null,
						o2);
			} catch (FileNotFoundException e) {
				Log.i(getString(R.string.str_galerias_log_transformarArchivoFotoItemTag),
						getString(R.string.str_galerias_log_transformarArchivoFotoItemMsg) +e);
			}
			return null;
		}

		/**
		 * Muestra la Vista de Galería de Fotos de Inicio de Referencia después
		 * de mostrar el Dialogo de Progreso
		 */
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			adapter = new MyThumbnaildapter(getBaseContext(),
					R.layout.lyt_galerias, fotoFileList);

			listaFotos.setAdapter(adapter);

			try {
				progress.dismiss();
			} catch (Exception e) {
				// TODO: handle exception
				progress = null;
			}
		}
	}
}

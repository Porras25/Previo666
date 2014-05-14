package com.adquem.grupologistics.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.adquem.grupologistics.bussines.Buss_FragListadoFact_RevisionReferencia;
import com.adquem.grupologistics.bussines.Buss_Frag_AltaItem;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.utilities.Constantes;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Clase Controlador entre la Vista y la Capa de Negocio para tomar Fotos y
 * Videos de la Referencia
 * 
 * @author Adquem
 * @version 1.0
 */
public class Frag_PhotoIntent extends Fragment {
	private static final int ACTION_TAKE_PHOTO_INICIO = 1;
	private static final int ACTION_TAKE_PHOTO_FIN = 2;
	private static final int ACTION_TAKE_VIDEO = 3;

	private static final String JPEG_FILE_PREFIX = "IMG_";
	private static final String JPEG_FILE_SUFFIX = ".jpg";
	private static final String VIDEO_FILE_PREFIX = "VID_";
	private static final String VIDEO_FILE_SUFFIX = ".mp4";

	private String mCurrentPhotoPath;
	private long Padre;
	private int sincronizado = 0;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;

	SharedPreferences preferences;
	Editor edit;

	private Buss_FragListadoFact_RevisionReferencia bussines;

	/**
	 * Crea la Vista para la Galería de Fotos y Videos de la Referencia
	 * 
	 * @param inflater
	 *            Genera el contenido de la Vista
	 * @param container
	 * @param savedInstanceState
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(getString(R.string.str_galeriaRef_log_idPadreRefTag),
				getString(R.string.str_galeriaRef_log_idPadreRefMsg) + " "
						+ Padre);

		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.lyt_fragment_galeriareferencia, null);
		bussines = new Buss_FragListadoFact_RevisionReferencia();

		preferences = PreferenceManager
				.getDefaultSharedPreferences(getActivity());

		return rootView;
	}

	/**
	 * Muestra el Listado de Fotografías de Inicio de la Referencia desde path
	 * personalizado
	 */
	public void listadoGaleriaInicio() {
		if (Padre != 0) {
			String ExternalStorageDirectoryPath = Environment
					.getExternalStoragePublicDirectory(
							Environment.DIRECTORY_PICTURES).getAbsolutePath();

			String targetPath = ExternalStorageDirectoryPath
					+ Constantes.RUTA_REF_FOTO_INICIO + "/" + Padre;

			File targetDirector = new File(targetPath);

			File[] files = targetDirector.listFiles();

			if (files != null) {
				if (files.length != 0) {
					Intent i = new Intent(getActivity(),
							Act_GalleryReferenciaFoto.class);
					i.putExtra("IdRef", Padre);
					getActivity().startActivity(i);

					Log.i(getString(R.string.str_galeriaRef_log_idPadreRefGalTag),
							getString(R.string.str_galeriaRef_log_idPadreRefGalMsg)
									+ Padre);
				} else {
					Toast.makeText(
							getActivity(),
							getString(R.string.str_galerias_toast_sinFotosInicio),
							Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(getActivity(),
						getString(R.string.str_galerias_toast_sinFotosInicio),
						Toast.LENGTH_LONG).show();
				Log.i(getString(R.string.str_fotos_log_sinDirectorioFotoInicioTag),
						getString(R.string.str_galerias_log_sinDirectorioFotoInicioMsg));
			}
		}
	}

	/**
	 * Muestra el Listado de Fotografías de Fin de la Referencia desde path
	 * personalizado
	 */
	public void listadoGaleriaFin() {
		if (Padre != 0) {
			String ExternalStorageDirectoryPath = Environment
					.getExternalStoragePublicDirectory(
							Environment.DIRECTORY_PICTURES).getAbsolutePath();

			String targetPath = ExternalStorageDirectoryPath
					+ Constantes.RUTA_REF_FOTO_FIN + "/" + Padre;

			File targetDirector = new File(targetPath);

			File[] files = targetDirector.listFiles();

			if (files != null) {
				if (files.length != 0) {
					Intent p = new Intent(getActivity(),
							Act_GalleryReferenciaFotoFin.class);
					p.putExtra("IdRefFin", Padre);
					startActivity(p);

					Log.i(getString(R.string.str_galeriaRef_log_idPadreRefGalFinTag),
							getString(R.string.str_galeriaRef_log_idPadreRefGalFinMsg)
									+ Padre);
				} else {
					Toast.makeText(getActivity(),
							getString(R.string.str_galerias_toast_sinFotosFin),
							Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(getActivity(),
						getString(R.string.str_galerias_toast_sinFotosFin),
						Toast.LENGTH_LONG).show();
				Log.i(getString(R.string.str_fotos_log_sinDirectorioFotoFinTag),
						getString(R.string.str_galerias_log_sinDirectorioFotoFinMsg));
			}
		}
	}

	/**
	 * Muestra el Listado de Videos de la Referencia desde path personalizado
	 */
	public void listadoGaleriaVideo() {
		if (Padre != 0) {
			String ExternalStorageDirectoryPath = Environment
					.getExternalStoragePublicDirectory(
							Environment.DIRECTORY_PICTURES).getAbsolutePath();

			String targetPath = ExternalStorageDirectoryPath
					+ Constantes.RUTA_REF_VIDEO + "/" + Padre;

			File targetDirector = new File(targetPath);

			File[] files = targetDirector.listFiles();

			if (files != null) {
				if (files.length != 0) {
					Intent i = new Intent(getActivity(),
							Act_GalleryReferenciaVideo.class);
					i.putExtra("IdRef", Padre);
					getActivity().startActivity(i);
					Log.i(getString(R.string.str_galeriaRef_log_idPadreRefGalVideoTag),
							getString(R.string.str_galeriaRef_log_idPadreRefGalVideoMsg)
									+ Padre);
				} else {
					Toast.makeText(
							getActivity(),
							getString(R.string.str_galerias_toast_sinVideosRef),
							Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(getActivity(),
						getString(R.string.str_galerias_toast_sinVideosRef),
						Toast.LENGTH_LONG).show();
				Log.i(getString(R.string.str_fotos_log_sinDirectorioVideoRefTag),
						getString(R.string.str_galerias_log_sinDirectorioVideoRefMsg));
			}
		}
	}

	/**
	 * Realiza el Intento para tomar la Fotografía de Inicio o Fin de la
	 * Referecia
	 * 
	 * @param actionCode
	 *            Permite elegir entre Foto de Inicio o Foto de Fin de la
	 *            Referencia
	 */
	private void dispatchTakePictureIntent(int actionCode) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		switch (actionCode) {
		case ACTION_TAKE_PHOTO_INICIO:
			File f = null;

			try {
				f = createImageFile();
				mCurrentPhotoPath = f.getAbsolutePath();
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(f));
			} catch (IOException e) {
				e.printStackTrace();
				f = null;
				mCurrentPhotoPath = null;
			}
			break;

		case ACTION_TAKE_PHOTO_FIN:
			File fs = null;

			try {
				fs = createImageFileFin();
				mCurrentPhotoPath = fs.getAbsolutePath();
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(fs));
			} catch (IOException e) {
				e.printStackTrace();
				fs = null;
				mCurrentPhotoPath = null;
			}
			break;

		default:
			break;
		}
		startActivityForResult(takePictureIntent, actionCode);

	}

	/**
	 * Realiza el Intento para tomar el Video de la Referencia
	 */
	private void dispatchTakeVideoIntent(int actionCode) {
		Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

		File fv = null;

		try {
			fv = createVideoFile();
			mCurrentPhotoPath = fv.getAbsolutePath();
			takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fv));
		} catch (IOException e) {
			e.printStackTrace();
			fv = null;
			mCurrentPhotoPath = null;
		}
		startActivityForResult(takeVideoIntent, ACTION_TAKE_VIDEO);
	}

	/**
	 * Genera el Archivo con path personalizado para almacenar la Foto de Inicio
	 * de la Referencia
	 * 
	 * @return Archivo para almacenar la Foto de Inicio de la Referencia
	 * @throws IOException
	 */
	private File createImageFile() throws IOException {
		File imageInicio = null;
		if (Padre != 0) {
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())) {
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
						.format(new Date());
				String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_"
						+ Padre + "_";

				String externalStorageDirectoryPath = Environment
						.getExternalStoragePublicDirectory(
								Environment.DIRECTORY_PICTURES)
						.getAbsolutePath();
				String targetPath = externalStorageDirectoryPath
						+ Constantes.RUTA_REF_FOTO_INICIO + "/" + Padre;

				File albumInicio = new File(targetPath);
				albumInicio.mkdirs();
				imageInicio = File.createTempFile(imageFileName,
						JPEG_FILE_SUFFIX, albumInicio);

				edit = preferences.edit();
				edit.putString("idImagen", imageInicio.getPath());
				edit.commit();

				edit = preferences.edit();
				edit.putString("albumInicio", albumInicio.getPath());
				edit.commit();

				imageInicio.delete();
			} else {
				Log.d(getString(R.string.str_galeriaRef_log_rutaDirFotoInicioTag),
						getString(R.string.str_galeriaRef_log_rutaDirSinSDMsg));
			}
		} else {
			Toast.makeText(
					getActivity(),
					getString(R.string.str_galeriaRef_toast_rutaArchivoFotoInicio),
					Toast.LENGTH_LONG).show();
			Log.i(getString(R.string.str_galeriaRef_log_rutaArchivoFotoInicioTag),
					getString(R.string.str_galeriaRef_log_rutaArchivoFotoInicioMsg));
		}
		return imageInicio;
	}

	/**
	 * Genera el Archivo con path personalizado para almacenar la Foto de Fin de
	 * la Referencia
	 * 
	 * @return Archivo para almacenar la Foto de Fin de la Referencia
	 * @throws IOException
	 */
	private File createImageFileFin() throws IOException {
		File imageFin = null;
		if (Padre != 0) {
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())) {
				String timeStampFin = new SimpleDateFormat("yyyyMMdd_HHmmss")
						.format(new Date());
				String imageFileNameFin = JPEG_FILE_PREFIX + timeStampFin + "_"
						+ Padre + "_";

				String externalStorageDirectoryPath = Environment
						.getExternalStoragePublicDirectory(
								Environment.DIRECTORY_PICTURES)
						.getAbsolutePath();
				String targetPath = externalStorageDirectoryPath
						+ Constantes.RUTA_REF_FOTO_FIN + "/" + Padre;

				File albumFin = new File(targetPath);
				albumFin.mkdirs();
				imageFin = File.createTempFile(imageFileNameFin,
						JPEG_FILE_SUFFIX, albumFin);

				edit = preferences.edit();
				edit.putString("idImagen", imageFin.getPath());
				edit.commit();

				edit = preferences.edit();
				edit.putString("albumFin", albumFin.getPath());
				edit.commit();

				imageFin.delete();
			} else {
				Log.d(getString(R.string.str_galeriaRef_log_rutaDirFotoFinTag),
						getString(R.string.str_galeriaRef_log_rutaDirSinSDMsg));
			}
		} else {
			Toast.makeText(
					getActivity(),
					getString(R.string.str_galeriaRef_toast_rutaArchivoFotoFin),
					Toast.LENGTH_LONG).show();
			Log.i(getString(R.string.str_galeriaRef_log_rutaArchivoFotoFinTag),
					getString(R.string.str_galeriaRef_log_rutaArchivoFotoFinMsg));
		}
		return imageFin;
	}

	/**
	 * Genera el Archivo con path personalizado para almacenar el Video de la
	 * Referencia
	 * 
	 * @return Archivo para almacenar el Video de la Referencia
	 * @throws IOException
	 */
	private File createVideoFile() throws IOException {
		File video = null;
		if (Padre != 0) {
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())) {
				String timeStampVideo = new SimpleDateFormat("yyyyMMdd_HHmmss")
						.format(new Date());
				String videofileName = VIDEO_FILE_PREFIX + timeStampVideo + "_"
						+ Padre + "_";

				String externalStorageDirectoryPath = Environment
						.getExternalStoragePublicDirectory(
								Environment.DIRECTORY_PICTURES)
						.getAbsolutePath();
				String targetPath = externalStorageDirectoryPath
						+ Constantes.RUTA_REF_VIDEO + "/" + Padre;
				File albumVideo = new File(targetPath);
				albumVideo.mkdirs();
				video = File.createTempFile(videofileName, VIDEO_FILE_SUFFIX,
						albumVideo);

				edit = preferences.edit();
				edit.putString("idVideoRef", video.getPath());
				edit.commit();

				edit = preferences.edit();
				edit.putString("pathVideoRef", albumVideo.getPath());
				edit.commit();

				video.delete();
			} else {
				Log.d(getString(R.string.str_galeriaRef_log_rutaDirVideoTag),
						getString(R.string.str_galeriaRef_log_rutaDirSinSDMsg));
			}
		} else {
			Toast.makeText(getActivity(),
					getString(R.string.str_galeriaRef_toast_rutaArchivoVideo),
					Toast.LENGTH_LONG).show();
			Log.i(getString(R.string.str_galeriaRef_log_rutaArchivoVideoTag),
					getString(R.string.str_galeriaRef_log_rutaArchivoVideoMsg));
		}
		return video;
	}

	/**
	 * Crea la Actividad, recupera el Id de la Referencia y Genera la Acción de
	 * tomar Fotos y Video, Galería de Fotos y Videos
	 */
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		prefs = getActivity().getSharedPreferences("MisPreferencias",
				Context.MODE_PRIVATE);
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

		Bundle argsRef = getArguments();
		this.Padre = argsRef.getLong("IdRef");

		Buss_Frag_AltaItem bus_alta_item = new Buss_Frag_AltaItem();

		sincronizado = bus_alta_item.obtiene_estatus_referencia(Padre,
				getActivity().getBaseContext());

		((Button) getView().findViewById(R.id.imgb_fotoInicioReferencia))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (sincronizado != Constantes.ESTATUS_SINCRONIZADO) {
							dispatchTakePictureIntent(ACTION_TAKE_PHOTO_INICIO);
						} else {
							Toast.makeText(
									getActivity().getApplicationContext(),
									getString(R.string.str_galeriaRef_toast_agregarFotoRefSync),
									Toast.LENGTH_LONG).show();
						}
					}
				});

		((Button) getView().findViewById(R.id.imgb_fotoFinReferencia))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (sincronizado != Constantes.ESTATUS_SINCRONIZADO) {
							dispatchTakePictureIntent(ACTION_TAKE_PHOTO_FIN);
						} else {
							Toast.makeText(
									getActivity().getApplicationContext(),
									getString(R.string.str_galeriaRef_toast_agregarFotoRefSync),
									Toast.LENGTH_LONG).show();
						}
					}
				});

		((Button) getView().findViewById(R.id.imgb_videoReferencia))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (sincronizado != Constantes.ESTATUS_SINCRONIZADO) {
							dispatchTakeVideoIntent(ACTION_TAKE_VIDEO);
						} else {
							Toast.makeText(
									getActivity().getApplicationContext(),
									getString(R.string.str_galeriaRef_toast_agregarVideoRefSync),
									Toast.LENGTH_LONG).show();
						}
					}
				});

		((Button) getView().findViewById(R.id.imgb_galeriaReferenciaInicio))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						listadoGaleriaInicio();
					}
				});

		((Button) getView().findViewById(R.id.imgb_galeriaReferenciaFin))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						listadoGaleriaFin();
					}
				});

		((Button) getView().findViewById(R.id.imgb_galeriaVideo))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						listadoGaleriaVideo();
					}
				});

	}

	/**
	 * Guarda la Fotografía de Inicio, Fin y Video de la Referencia en la BD y
	 * se guardan los path's en variables
	 * 
	 * @param requestCode
	 *            Permite elegir entre tomar Foto de Inicio o Foto de Fin o
	 *            Video
	 * @param resultCode
	 *            Permite ver que actividad iniciara (Foto de Inicio o Foto de
	 *            Fin o Video)
	 * @param data
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case ACTION_TAKE_PHOTO_INICIO:
			if (resultCode == Activity.RESULT_OK) {
				redimensionarImagen();

				String path = preferences.getString("idImagen", "NA");
				String[] nombre = path.split("/");
				String nom = nombre[nombre.length - 1];
				String dir = preferences.getString("albumInicio", "NA");

				Log.i(getString(R.string.str_galeriaRef_log_nombreTomarFotoInicio),
						nombre.toString());

				bussines.insertAdjRef(Padre, nom, path,
						Constantes.ID_CONCEPTO_FOTO_INICIO, getActivity());
				dispatchTakePictureIntent(ACTION_TAKE_PHOTO_INICIO);
			}
			break;

		case ACTION_TAKE_PHOTO_FIN:
			if (resultCode == Activity.RESULT_OK) {
				redimensionarImagen();

				String path = preferences.getString("idImagen", "NA");
				String[] nombre = path.split("/");
				String nom = nombre[nombre.length - 1];
				String dir = preferences.getString("albumFin", "NA");

				Log.i(getString(R.string.str_galeriaRef_log_nombreTomarFotoFin),
						nombre.toString());

				bussines.insertAdjRef(Padre, nom, path,
						Constantes.ID_CONCEPTO_FOTO_FIN, getActivity());
				dispatchTakePictureIntent(ACTION_TAKE_PHOTO_FIN);
			}
			break;

		case ACTION_TAKE_VIDEO:
			if (resultCode == Activity.RESULT_OK) {

				String path = preferences.getString("idVideoRef", "NA");
				String[] nombre = path.split("/");
				String nom = nombre[nombre.length - 1];
				String dir = preferences.getString("pathVideoRef", "NA");

				Log.i(getString(R.string.str_galeriaRef_log_nombreTomarVideo),
						nombre.toString());

				bussines.insertAdjRef(Padre, nom, path,
						Constantes.ID_CONCEPTO_VIDEO_REF, getActivity());
			}
			break;

		default:
			break;
		}
	}

	/**
	 * Crea Archivo para almacenar Imagen de la Fotografía de Inicio o
	 * Fotografía de Fin de la Referencia con cambio en el tamaño especifico de
	 * la Imagen
	 */
	public void redimensionarImagen() {
		String path = preferences.getString("idImagenRef", "NA");

		if (path != "NA") {
			Bitmap bit = BitmapFactory.decodeFile(path);
			Bitmap resize = getResizedBitmap(bit, 540, 960);

			try {
				File file = new File(path);
				FileOutputStream outputStream = new FileOutputStream(file);
				resize.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
				outputStream.flush();
				outputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Log.e(getString(R.string.str_galeriaRef_log_errorImagenTag),
						getString(R.string.str_galeriaRef_log_errorSinImagenRef)
								+ e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(getString(R.string.str_galeriaRef_log_errorImagenTag),
						getString(R.string.str_galeriaRef_log_errorDimensionarImagenRef)
								+ e);
			}
		}
	}

	/**
	 * Permite cambiar el tamaño por default de la Foto de la Referencia
	 * 
	 * @param bm
	 *            Imagen que será modificada en su tamaño
	 * @param newHeight
	 *            Altura que tendrá la Imagen
	 * @param newWidth
	 *            Ancho que tendrá la Imagen
	 * @return Bitmap de la Imagen con tamaño modificado
	 */
	public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);
		return resizedBitmap;
	}
}

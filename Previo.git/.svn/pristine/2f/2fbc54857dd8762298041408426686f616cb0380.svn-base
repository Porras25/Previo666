package com.adquem.grupologistics.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.adquem.grupologistics.bussines.Buss_FragListadoFact_RevisionReferencia;
import com.adquem.grupologistics.bussines.Buss_Frag_RevisionItem;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.utilities.Constantes;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Clase Controlador entre la Vista y la Capa de Negocio para tomar Fotos y
 * Videos del Ítem
 * 
 * @author Adquem
 * @version 1.0
 */
public class Frag_PhotoIntentItem extends Fragment {
	private static final int ACTION_TAKE_PHOTO = 100;
	private static final int ACTION_TAKE_VIDEO = 200;

	private static final String JPEG_FILE_PREFIX = "IMG_";
	private static final String JPEG_FILE_SUFFIX = ".jpg";
	private static final String VIDEO_FILE_PREFIX = "VID_";
	private static final String VIDEO_FILE_SUFFIX = ".mp4";

	private String mCurrentPhotoPath;
	private int sincronizado;
	private long Padre, id_fact;

	private Buss_FragListadoFact_RevisionReferencia bussines;
	private Buss_Frag_RevisionItem bussinesitem;

	Buss_Frag_RevisionItem buss_revision = new Buss_Frag_RevisionItem();

	SharedPreferences preferences;
	Editor edit;

	/**
	 * Crea la Vista para la Galería de Fotos y Videos del Ítem
	 * 
	 * @param inflater
	 *            Genera el contenido de la Vista
	 * @param container
	 * @param savedInstanceState
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.lyt_fragment_galeriaitem, null);
		bussines = new Buss_FragListadoFact_RevisionReferencia();

		preferences = PreferenceManager
				.getDefaultSharedPreferences(getActivity());

		return rootView;
	}

	/**
	 * Muestra el Listado de Fotografías del Ítem desde path personalizado
	 */
	public void listadoGaleriaItem() {
		if (Padre != 0) {
			String ExternalStorageDirectoryPath = Environment
					.getExternalStoragePublicDirectory(
							Environment.DIRECTORY_PICTURES).getAbsolutePath();

			String targetPath = ExternalStorageDirectoryPath
					+ Constantes.RUTA_ITEM_FOTO + "/" + Padre;

			File targetDirector = new File(targetPath);

			File[] files = targetDirector.listFiles();

			if (files != null) {
				if (files.length != 0) {
					Intent i = new Intent(getActivity(),
							Act_GalleryItemFoto.class);
					i.putExtra("Padre", Padre);
					i.putExtra("idFact", id_fact);
					getActivity().startActivity(i);

					Log.i(getString(R.string.str_galeriaItem_log_idPadreItemGalTag),
							getString(R.string.str_galeriaItem_log_idPadreItemGalMsg)
									+ Padre);
				} else {
					Toast.makeText(getActivity(),
							getString(R.string.str_galerias_toast_sinFotos),
							Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(getActivity(),
						getString(R.string.str_galerias_toast_sinFotos),
						Toast.LENGTH_LONG).show();
				Log.i(getString(R.string.str_galerias_log_sinDirectorioFotoTag),
						getString(R.string.str_galerias_log_sinDirectorioFotoMsg));
			}
		}
	}

	/**
	 * Muestra el Listado de Videos del Ítem desde path personalizado
	 */
	public void listadoGaleriaItemVideo() {
		if (Padre != 0) {
			String ExternalStorageDirectoryPath = Environment
					.getExternalStoragePublicDirectory(
							Environment.DIRECTORY_PICTURES).getAbsolutePath();

			String targetPath = ExternalStorageDirectoryPath
					+ Constantes.RUTA_ITEM_VIDEO + "/" + Padre; // Cambio Padre

			File targetDirector = new File(targetPath);

			File[] files = targetDirector.listFiles();

			if (files != null) {
				if (files.length != 0) {
					Intent i = new Intent(getActivity(),
							Act_GalleryItemVideo.class);
					i.putExtra("Padre", Padre);
					getActivity().startActivity(i);
					Log.i(getString(R.string.str_galeriaItem_log_idPadreItemGalVideoTag),
							getString(R.string.str_galeriaItem_log_idPadreItemGalVideoMsg)
									+ Padre);
				} else {
					Toast.makeText(
							getActivity(),
							getString(R.string.str_galerias_toast_sinVideosItem),
							Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(getActivity(),
						getString(R.string.str_galerias_toast_sinVideosItem),
						Toast.LENGTH_LONG).show();
				Log.i(getString(R.string.str_galerias_log_sinDirectorioVideoTag),
						getString(R.string.str_galerias_log_sinDirectorioVideoMsg));
			}
		}
	}

	/**
	 * Realiza el Intento para tomar la Fotografía del Ítem
	 */
	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		File f = null;

		try {
			f = createImageFile();
			mCurrentPhotoPath = f.getAbsolutePath();
			takePictureIntent
					.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		} catch (IOException e) {
			e.printStackTrace();
			f = null;
			mCurrentPhotoPath = null;
		}
		startActivityForResult(takePictureIntent, ACTION_TAKE_PHOTO);
	}

	/**
	 * Realiza el Intento para tomar el Video del Ítem
	 */
	private void dispatchTakeVideoIntent() {
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
	 * Genera el Archivo con path personalizado para almacenar la Foto del Ítem
	 * 
	 * @return Archivo para almacenar la Foto del Ítem
	 * @throws IOException
	 */
	private File createImageFile() throws IOException {
		File imageF = null;
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
						+ Constantes.RUTA_ITEM_FOTO + "/" + Padre;

				File albumF = new File(targetPath);
				albumF.mkdirs();
				imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX,
						albumF);

				edit = preferences.edit();
				edit.putString("idImagen", imageF.getPath());
				edit.commit();

				edit = preferences.edit();
				edit.putString("albumF", albumF.getPath());
				edit.commit();

				imageF.delete();
			} else {
				Log.d(getString(R.string.str_galeriaItem_log_rutaDirFotoItemTag),
						getString(R.string.str_galeriaItem_log_rutaDirSinSDMsg));
			}
		} else {
			Toast.makeText(
					getActivity(),
					getString(R.string.str_galeriaItem_toast_rutaArchivoFotoItem),
					Toast.LENGTH_LONG).show();
			Log.i(getString(R.string.str_galeriaItem_log_rutaArchivoFotoItemTag),
					getString(R.string.str_galeriaItem_log_rutaArchivoFotoItemMsg));
		}
		return imageF;
	}

	/**
	 * Genera el Archivo con path personalizado para almacenar el Video del Ítem
	 * 
	 * @return Archivo para almacenar el Video del Ítem
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
						+ Constantes.RUTA_ITEM_VIDEO + "/" + Padre;
				File albumVideo = new File(targetPath);
				albumVideo.mkdirs();
				video = File.createTempFile(videofileName, VIDEO_FILE_SUFFIX,
						albumVideo);

				edit = preferences.edit();
				edit.putString("idVideo", video.getPath());
				edit.commit();

				edit = preferences.edit();
				edit.putString("pathVideo", albumVideo.getPath());
				edit.commit();

				video.delete();
			} else {
				Log.d(getString(R.string.str_galeriaRef_log_rutaDirVideoTag),
						getString(R.string.str_galeriaRef_log_rutaDirSinSDMsg));
			}
		} else {
			Toast.makeText(
					getActivity(),
					getString(R.string.str_galeriaItem_toast_rutaArchivoVideoItem),
					Toast.LENGTH_LONG).show();
			Log.i(getString(R.string.str_galeriaItem_log_rutaArchivoVideoItemTag),
					getString(R.string.str_galeriaItem_log_rutaArchivoVideoItemMsg));
		}
		return video;
	}

	/**
	 * Crea la Actividad, Recupera los Id's de la Referencia y la Factura,
	 * Genera la Acción de tomar Foto y Video, Galería de Foto y Videos
	 */
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Bundle args = getArguments();
		this.Padre = args.getLong("Padre");
		this.id_fact = args.getLong("idFact");

		Log.v(getString(R.string.str_galeriaItem_log_idPadreItemTag),
				getString(R.string.str_galeriaItem_log_idPadreItemMsg) + " "
						+ Padre);

		bussinesitem = new Buss_Frag_RevisionItem();

		sincronizado = bussinesitem.obtiene_estatus_item(Padre, getActivity()
				.getBaseContext());
		Log.v("sincronizado foto", "sincronizado foto " + sincronizado);
		((Button) getView().findViewById(R.id.imgb_fotoItem))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (sincronizado != Constantes.ESTATUS_SINCRONIZADO) {
							dispatchTakePictureIntent();
						} else {
							Toast.makeText(
									getActivity().getApplicationContext(),
									getString(R.string.str_galeriaItem_toast_agregarFotoItemSync),
									Toast.LENGTH_LONG).show();
						}
					}
				});

		((Button) getView().findViewById(R.id.imgb_videoItem))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (sincronizado != Constantes.ESTATUS_SINCRONIZADO) {
							dispatchTakeVideoIntent();
						} else {
							Toast.makeText(
									getActivity().getApplicationContext(),
									getString(R.string.str_galeriaItem_toast_agregarVideoItemSync),
									Toast.LENGTH_LONG).show();
						}
					}
				});

		((Button) getView().findViewById(R.id.imgb_galeriaItem))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						listadoGaleriaItem();
					}
				});

		((Button) getView().findViewById(R.id.imgb_galeriaItemVideo))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						listadoGaleriaItemVideo();
					}
				});

	}

	/**
	 * Guarda la Fotografía y Video del Ítem en la BD y se guardan los path's en
	 * variables
	 * 
	 * @param requestCode
	 *            Permite elegir entre tomar Foto o Video
	 * @param resultCode
	 *            Permite ver que actividad iniciara (Foto o Video)
	 * @param data
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case ACTION_TAKE_PHOTO:
			if (resultCode == Activity.RESULT_OK) {

				redimensionarImagen();

				String path = preferences.getString("idImagen", "NA");
				String[] nombre = path.split("/");
				String nom = nombre[nombre.length - 1];
				String dir = preferences.getString("pathImagen", "NA");

				Log.i(getString(R.string.str_galeriaItem_log_nombreTomarFotoItem),
						nombre.toString());

				bussines.insertAdjuntoItm(Padre, nom, path,
						Constantes.ID_CONCEPTO_FOTO_ITEM, getActivity());
				dispatchTakePictureIntent();
				valida_estatus();
			}
			break;

		case ACTION_TAKE_VIDEO:
			if (resultCode == Activity.RESULT_OK) {
				String path = preferences.getString("idVideo", "NA");
				String[] nombre = path.split("/");
				String nom = nombre[nombre.length - 1];
				String dir = preferences.getString("pathVideo", "NA");

				Log.i(getString(R.string.str_galeriaItem_log_nombreTomarVideoItem),
						nombre.toString());

				bussines.insertAdjuntoItm(Padre, nom, path,
						Constantes.ID_CONCEPTO_VIDEO_ITEM, getActivity());
			}
			break;

		default:
			break;
		}
	}

	/**
	 * Verifica en que Estatus se encuentra la Revisión del Ítem
	 */
	public void valida_estatus() {
		boolean revisado = false;

		revisado = buss_revision.terminar_revision(Padre, getActivity());
		Log.v(getString(R.string.str_galeriaItem_log_fragRevisionTag),
				getString(R.string.str_galeriaItem_log_fragRevisionMsg)
						+ revisado + "");
		if (revisado == true) {
			Log.v(getString(R.string.str_galeriaItem_log_fragItemRevisadoTag),
					getString(R.string.str_galeriaItem_log_fragItemRevisadoMsg));
			buss_revision.actualiza_estatus_item(Padre,
					Constantes.ESTATUS_REVISADO, getActivity());

			buss_revision.valida_factura(id_fact, getActivity());

			Toast.makeText(getActivity(),
					getString(R.string.str_galeriaItem_toast_revisionItemTerm),
					Toast.LENGTH_LONG).show();
		} else {
			buss_revision.actualiza_estatus_item(Padre,
					Constantes.ESTATUS_EN_REVISION, getActivity());
			buss_revision.valida_factura(id_fact, getActivity());
		}
	}

	/**
	 * Crea Archivo para almacenar Imagen de la Fotografía del Ítem con cambio
	 * en el tamaño especifico de la Imagen
	 */
	public void redimensionarImagen() {
		String path = preferences.getString("idImagen", "NA");

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
				Log.e(getString(R.string.str_galeriaItem_log_errorImagenItemTag),
						getString(R.string.str_galeriaItem_log_errorSinImagenItem)
								+ e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(getString(R.string.str_galeriaItem_log_errorImagenItemTag),
						getString(R.string.str_galeriaItem_log_errorDimensionarImagenItem)
								+ e);
			}
		}
	}

	/**
	 * Permite cambiar el tamaño por default de la Foto del Ítem
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

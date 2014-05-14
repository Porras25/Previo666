package com.adquem.grupologistics.controllers;

import com.adquem.grupologistics.bussines.Buss_Frag_RevisionItem;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.utilities.Constantes;
import com.adquem.grupologistics.utilities.Dialogs;
import com.adquem.grupologistics.utilities.Utl_CustomDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Frag_nom_revision extends Fragment implements Dialogs {

	private int opcion = 0;
	Uri uri;
	public static Utl_CustomDialog editNameDialog;
	int consecutivo_campo = 1;
	int consecutivo_campo2 = 1;
	// int consecutivo_campo2 = 1;
	int contadorRequerido = 0;
	public static long id_item, id_nom, id_fact;
	int Item;
	public static Context app_context;
	public static FragmentTransaction tx;
	ViewGroup root;
	String Estatus;
	Buss_Frag_RevisionItem buss_revision = new Buss_Frag_RevisionItem();;
	private int sincronizado;
	public static final String[] colNomItem = { "IdNom_Item", "IdNom",
			"IdItem", "Estatus" };
	public static final String[] colNom = { "IdNom_campo", "IdNom", "IdCampo",
			"Nom" };
	public static final String[] colColumnasNom = { "IdCampo", "Nombre",
			"Orden", "TipoDato", "Requerido", "TablaFuente", "IdFuente",
			"TextoFuente" };
	public static final String[] colCatSiNo = { "IdCatalogo", "Respuesta" };
	public static final String[] colCatUso = { "IdCatalogo", "Nombre" };

	public static final String[] colRespuesta = { "IdResp", "IdNom", "IdItem",
			"IdCampo", "Valor","Estatus" };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		Bundle args = getArguments();
		this.id_item = args.getLong("id_item");
		this.id_nom = args.getLong("id_nom");
		this.id_fact = args.getLong("idFact");
		
		tx = getFragmentManager().beginTransaction();

		Log.v("Frag Revision item", "id_item = " + id_item + " id_nom = "
				+ id_nom);
		app_context = getActivity();

		// Buscar si la nom esta sincronizada

		Estatus = getEstatusNom();  

		// Buscar en nom todos los registros dnde idNom des igual a padre
		String getNomsQuery = "IdNom = " + id_nom;
		Log.i("idNom::::::::::", id_nom + "");
		Cursor getNoms = app_context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Nom"), colNom, getNomsQuery,
				null, null);

		// del resultado, buscar todos los acampos donde coincidan los ids
		if (getNoms.moveToFirst()) {
			TextView tituloNom = (TextView) root.findViewById(R.id.txv_revisionNomTitulo);
			   tituloNom.setText(app_context.getText(R.string.nom_titulo)+" "+getNoms.getString(3));
			do {

				// conseguir el id del campo
				long idColNom = getNoms.getLong(2);

				String getNomCamposQuery = "IdCampo = " + idColNom;
				
				// Verificar si tiene respuesta

				String respuesta = getRespuestaCampoVerify(idColNom);
				Log.i("Respuesta recibida: ", respuesta
						+ "::::::::::::::::::::::::::::::::");

			

				// ir al id del campo y armar el valor

				Cursor getNomCampos = app_context.getContentResolver().query(
						Uri.parse(MyContentProvider.URL + "ColumnasNom"),
						colColumnasNom, getNomCamposQuery, null, "Orden ASC");

				if (getNomCampos.moveToFirst()) {
					do {

						long id_campo = getNomCampos.getLong(0);
						String nombre_campo = getNomCampos.getString(1);
						int orden_campo = getNomCampos.getInt(2);
						String tipoDato = getNomCampos.getString(3);
						int requerido = getNomCampos.getInt(4);
						String tablaFuente = getNomCampos.getString(5);
						String idFuente = getNomCampos.getString(6);
						String textoFuente = getNomCampos.getString(7);

						Log.i("Datos de nom: ", id_campo + " " + nombre_campo
								+ " " + orden_campo + " " + tipoDato + " "
								+ requerido + " " + tablaFuente + " "
								+ idFuente + " " + textoFuente

						);

						
						// Establecer el nombre en el layout lyt_armar_nom

						LinearLayout lty_revision_revision = (LinearLayout) root
								.findViewById(R.id.lyt_armar_nom);

						if (tipoDato.equals("entero") && tablaFuente.equals("")) { // Numerico

							Log.i("Datos de nom entero: ", id_campo + " "
									+ nombre_campo + " " + orden_campo + " "
									+ tipoDato + " requerido: " + requerido
									+ " " + tablaFuente + " " + idFuente + " "
									+ textoFuente + " consecutivo:"
									+ consecutivo_campo);

							// Armar int
							EditText txt_nom_entero = new EditText(
									getActivity());
							// txt_nom_entero.setText(nombre_campo);
							txt_nom_entero.setId(consecutivo_campo);
							consecutivo_campo++;
							txt_nom_entero.setHint(nombre_campo);
							txt_nom_entero
									.setInputType(InputType.TYPE_CLASS_NUMBER);
							
							int maxLength = 9;    
							txt_nom_entero.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
							

							if (Estatus.equals(Constantes.ESTATUS_SINCRONIZADO)) {
								txt_nom_entero.setEnabled(false);

							}
							if (respuesta != "" && respuesta != null) {
								if (respuesta.equals("NA")) {
									//txt_nom_entero.setHint("Campo Requerido "+nombre_campo);
									txt_nom_entero.setHint(getResources().getString(R.string.nom_requerido) + " " + nombre_campo);
									txt_nom_entero.setHintTextColor(Color.RED);
								}else{
									Log.i("Respuesta recibida: ", respuesta
											+ ":::::::::::" + nombre_campo
											+ ":::::::::::::::::::::");
									txt_nom_entero.setText(respuesta);
								}
								
							}
							/*
							 * txt_nombreNom.setLayoutParams(new LayoutParams(
							 * LayoutParams.FILL_PARENT,
							 * LayoutParams.WRAP_CONTENT));
							 */

							lty_revision_revision.addView(txt_nom_entero);

						} else if (tipoDato.equals("double")
								&& tablaFuente.equals("")) { // Double
							Log.i("Datos de nom double: ", id_campo + " "
									+ nombre_campo + " " + orden_campo + " "
									+ tipoDato + " requerido: " + requerido
									+ " " + tablaFuente + " " + idFuente + " "
									+ textoFuente + " consecutivo:"
									+ consecutivo_campo);
							// Armar int
							EditText txt_nom_entero = new EditText(
									getActivity());
							// txt_nom_entero.setText(nombre_campo);
							txt_nom_entero.setId(consecutivo_campo);
							consecutivo_campo++;
							txt_nom_entero.setHint(nombre_campo);
							// txt_nom_entero.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
							txt_nom_entero
									.setRawInputType(InputType.TYPE_CLASS_NUMBER
											| InputType.TYPE_NUMBER_FLAG_DECIMAL);
							int maxLength = 45;    
							txt_nom_entero.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
							
							if (Estatus.equals(Constantes.ESTATUS_SINCRONIZADO)) {
								txt_nom_entero.setEnabled(false);

							}
							if (respuesta != "" && respuesta != null) {
								if (respuesta.equals("NA")) {
									//txt_nom_entero.setHint("Campo Requerido "+nombre_campo);
									txt_nom_entero.setHint(getResources().getString(R.string.nom_requerido) + " " + nombre_campo);
									txt_nom_entero.setHintTextColor(Color.RED);
								}else{
									Log.i("Respuesta recibida: ", respuesta
											+ ":::::::::::" + nombre_campo
											+ ":::::::::::::::::::::");
									txt_nom_entero.setText(respuesta);
								}
								
							}
							/*
							 * txt_nombreNom.setLayoutParams(new LayoutParams(
							 * LayoutParams.FILL_PARENT,
							 * LayoutParams.WRAP_CONTENT));
							 */

							lty_revision_revision.addView(txt_nom_entero);

						} else if (tipoDato.equals("string")
								&& tablaFuente.equals("")) { // String
							Log.i("Datos de nom string: ", id_campo + " "
									+ nombre_campo + " " + orden_campo + " "
									+ tipoDato + " requerido: " + requerido
									+ " " + tablaFuente + " " + idFuente + " "
									+ textoFuente + " consecutivo:"
									+ consecutivo_campo);
							// Armar string
							EditText txt_nom_string = new EditText(
									getActivity());
							// txt_nom_string.setText(nombre_campo);
							txt_nom_string.setId(consecutivo_campo);
							consecutivo_campo++;
							txt_nom_string.setHint(nombre_campo);
							txt_nom_string
									.setInputType(InputType.TYPE_CLASS_TEXT);
							
							int maxLength = 250;    
							txt_nom_string.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
							
							if (Estatus.equals(Constantes.ESTATUS_SINCRONIZADO)) {
								txt_nom_string.setEnabled(false);

							}
							if (respuesta != "" && respuesta != null) {
								if (respuesta.equals("NA")) {
									//txt_nom_string.setHint("Campo Requerido "+nombre_campo);
									txt_nom_string.setHint(getResources().getString(R.string.nom_requerido) + " " + nombre_campo);
									txt_nom_string.setHintTextColor(Color.RED);									
								}else{
									Log.i("Respuesta recibida: ", respuesta
											+ ":::::::::::" + nombre_campo
											+ ":::::::::::::::::::::");
									txt_nom_string.setText(respuesta);
								}
								
							}
							lty_revision_revision.addView(txt_nom_string);

						}

						if (tablaFuente != null
								&& tablaFuente.equals("catSiNo")) {

							Log.i("Datos de nom catSiNo: ", id_campo + " "
									+ nombre_campo + " " + orden_campo + " "
									+ tipoDato + " requerido: " + requerido
									+ " " + tablaFuente + " " + idFuente + " "
									+ textoFuente + " consecutivo:"
									+ consecutivo_campo);

							// Armando el catalogo de SI NO
							Spinner siNo = new Spinner(app_context);
							// String []opciones={"SI","NO"};
							String[] opciones = getOpcionesSiNo(idFuente);
							siNo.setId(consecutivo_campo);
							if (Estatus.equals(Constantes.ESTATUS_SINCRONIZADO)) {
								siNo.setEnabled(false);

							}
							
							consecutivo_campo++;

							TextView txt_nom_entero = new TextView(app_context);							
							SpannableString spanString = new SpannableString(nombre_campo);
							spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);						
							txt_nom_entero.setText(spanString);
							lty_revision_revision.addView(txt_nom_entero);
							
							ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
									getActivity(),
									android.R.layout.simple_spinner_item,
									opciones);
							spinnerAdapter
									.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
							siNo.setAdapter(spinnerAdapter);
							if (respuesta != "" && respuesta != null) {
								int r = 0;
								while (r < opciones.length) {
									
								

									if (r == Integer.parseInt(respuesta)-1) {
										Log.i("Respuesta recibida: ", respuesta
												+ ":::::::::::" + nombre_campo
												+ ":::::::opcion select::::"
												+ opciones[r] + "::::::::::");
										siNo.setSelection(r);
									}
									r++;
								}
							}
							lty_revision_revision.addView(siNo);

						} else if (tablaFuente != null
								&& tablaFuente.equals("catUso")) {

							Log.i("Datos de nom catUso: ", id_campo + " "
									+ nombre_campo + " " + orden_campo + " "
									+ tipoDato + " requerido: " + requerido
									+ " " + tablaFuente + " " + idFuente + " "
									+ textoFuente + " consecutivo:"
									+ consecutivo_campo);

							// Ir al catalogo de CatUso y armar las listas
							// Armando el catalogo de SI NO
							Spinner uso = new Spinner(app_context);
							String[] opciones = getOpcionesUso(idFuente);
							uso.setId(consecutivo_campo);
							if (Estatus.equals(Constantes.ESTATUS_SINCRONIZADO)) {
								uso.setEnabled(false);

							}
							
							consecutivo_campo++;

							// Titulo
							/*TextView txt_nom_entero = new TextView(app_context);
							txt_nom_entero.setText(nombre_campo);
							lty_revision_revision.addView(txt_nom_entero);*/

							TextView txt_nom_entero = new TextView(app_context);							
							SpannableString spanString = new SpannableString(nombre_campo);
							spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);						
							txt_nom_entero.setText(spanString);
							lty_revision_revision.addView(txt_nom_entero);
							
							ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
									getActivity(),
									android.R.layout.simple_spinner_item,
									opciones);
							spinnerAdapter
									.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
							uso.setAdapter(spinnerAdapter);
							if (respuesta != "" && respuesta != null) {
								int r = 0;
								while (r < opciones.length) {

									if (r == Integer.parseInt(respuesta)-1) {
										uso.setSelection(r);
									}

									r++;
								}
							}
							lty_revision_revision.addView(uso);

						} else if (tablaFuente != null
								&& tablaFuente.equals("catEstatusAparatos")) {

							Log.i("Datos de nom catEstatusAparatos: ", id_campo
									+ " " + nombre_campo + " " + orden_campo
									+ " " + tipoDato + " requerido: "
									+ requerido + " " + tablaFuente + " "
									+ idFuente + " " + textoFuente
									+ " consecutivo:" + consecutivo_campo);

							// Ir al catalogo de CatUso y armar las listas
							// Armando el catalogo de SI NO
							Spinner uso = new Spinner(app_context);
							String[] opciones = getOpcionesEstatusAparato(idFuente);
							uso.setId(consecutivo_campo);
							if (Estatus.equals(Constantes.ESTATUS_SINCRONIZADO)) {
								uso.setEnabled(false);

							}
							
							consecutivo_campo++;

							// Titulo
							/*TextView txt_nom_entero = new TextView(app_context);
							txt_nom_entero.setText(nombre_campo);
							lty_revision_revision.addView(txt_nom_entero);*/
							
							TextView txt_nom_entero = new TextView(app_context);							
							SpannableString spanString = new SpannableString(nombre_campo);
							spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);						
							txt_nom_entero.setText(spanString);
							lty_revision_revision.addView(txt_nom_entero);

							ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
									getActivity(),
									android.R.layout.simple_spinner_item,
									opciones);
							spinnerAdapter
									.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
							uso.setAdapter(spinnerAdapter);
							
							if (respuesta != "" && respuesta != null) {
								int r = 0;
								while (r < opciones.length) {

									if (r == Integer.parseInt(respuesta)-1) {
										uso.setSelection(r);
										
									}
									r++;
								}
							}
							lty_revision_revision.addView(uso);

						} else {
							Log.i("Guardando la NOM",
									"Algo paso tablaFuente Null campo abierto: "
											+ nombre_campo);
						}

					} while (getNomCampos.moveToNext());
					getNomCampos.close();
					
					
				}

			} while (getNoms.moveToNext());
			getNoms.close();
			
			
		}
		
		sincronizado = buss_revision.obtiene_estatus_item(id_item,getActivity().getBaseContext());
		
		Button guardarNom = (Button) root
				.findViewById(R.id.imgb_aceptarNom);
		guardarNom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(sincronizado != Constantes.ESTATUS_SINCRONIZADO){
					getDatosRespuesta();
				}
				else{
					Toast.makeText(getActivity().getApplicationContext(), getString(R.string.str_nomRevision_toast_guardarItemSincronizado), Toast.LENGTH_LONG).show();
				}
			}

		});
		
		ImageButton eliminarNom = (ImageButton) root
				.findViewById(R.id.imgb_revisionDeleteNom);
		eliminarNom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(sincronizado != Constantes.ESTATUS_SINCRONIZADO){
					String titulo = getActivity().getResources().getText(R.string.str_alta_dialog_titulo_eliminaNOM).toString();
					String mensaje = getActivity().getResources().getText(R.string.str_alta_dialog_msg_eliminaNOM).toString();
					String tag = "Todos";
					dialogo(tag, titulo, mensaje);
				}
				else{
					Toast.makeText(getActivity().getApplicationContext(), getString(R.string.str_nomRevision_toast_eliminarItemSincronizado), Toast.LENGTH_LONG).show();
				}
			}

		});

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = (ViewGroup) inflater.inflate(R.layout.lyt_fragment_nomrevision, null);
		
		return root;
	}

	public String[] getOpcionesUso(String iduso) {

		String[] colCatUso = { "IdCatalogo", "Nombre" };
		String getUsosQuery = "IdCatalogo =" + iduso; // + " order by Nombre";
		String[] error = { "CatUso", "No hay registros" };
		Cursor getUsos = app_context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "CatUso"), colCatUso,
				getUsosQuery, null, null);

		int countUsos = getUsos.getCount();
		String[] opciones = new String[countUsos];
		int count = 0;
		if (getUsos.moveToFirst()) {

			do {
				if (getUsos.getString(1) != null) {
					opciones[count] = getUsos.getString(1);
					count++;
				} else {
					opciones[count] = "NA";
					count++;
				}

			} while (getUsos.moveToNext());
			getUsos.close();
		}
		if (opciones.length > 0)
			return opciones;
		else
			return error;
	}

	public String[] getOpcionesSiNo(String idSiNo) {

		String[] colCatSiNo = { "IdCatalogo", "Respuesta" };
		String getSiNoQuery = "IdCatalogo =" + idSiNo; // + " order by Nombre";
		String[] error = { "CatSiNo", "No hay registros" };
		Cursor getSiNo = app_context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "CatSiNo"), colCatSiNo,
				getSiNoQuery, null, null);

		int countSiNo = getSiNo.getCount();
		String[] opciones = new String[countSiNo];
		int count = 0;
		// if (getNoms.moveToFirst()){

		if (getSiNo.moveToFirst()) {

			do {

				opciones[count] = getSiNo.getString(1);
				count++;

			} while (getSiNo.moveToNext());
			getSiNo.close();
		}
		// }
		if (opciones.length > 0)
			return opciones;
		else
			return error;
	}

	public String[] getOpcionesEstatusAparato(String idEstatusA) {

		String[] colCatestatus = { "IdCatalogo", "Nombre" };
		String getSiNoQuery = "IdCatalogo =" + idEstatusA; // +
															// " order by Nombre";
		String[] error = { "CatEstatus", "No hay registros" };
		Cursor getSiNo = app_context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "CatEstatusAparatos"),
				colCatestatus, getSiNoQuery, null, null);

		int countSiNo = getSiNo.getCount();
		String[] opciones = new String[countSiNo];
		int count = 0;
		// if (getNoms.moveToFirst()){
		if (getSiNo.moveToFirst()) {

			do {

				opciones[count] = getSiNo.getString(1);
				count++;

			} while (getSiNo.moveToNext());
			getSiNo.close();
		}
		// }
		if (opciones.length > 0)
			return opciones;
		else
			return error;
	}

	public void getDatosRespuesta() { ///////////////////////////////////////////////////////////////Guardar

		// Buscar en nom todos los registros dnde idNom des igual a padre
		String getNomsQuery = "IdNom = " + id_nom;
		Cursor getNoms = app_context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Nom"), colNom, getNomsQuery,
				null, null);

		// del resultado, buscar todos los acampos donde coincidan los ids
		if (getNoms.moveToFirst()) {
			do {

				// conseguir el id del campo
				long idColNom = getNoms.getLong(2);

				String getNomCamposQuery = "IdCampo = " + idColNom;
				// + " order by Orden";

				// ir al id del campo y armar el valor

				Cursor getNomCampos = app_context.getContentResolver().query(
						Uri.parse(MyContentProvider.URL + "ColumnasNom"),
						colColumnasNom, getNomCamposQuery, null, "Orden");

				if (getNomCampos.moveToFirst()) {
					do {

						long id_campo = getNomCampos.getLong(0);
						String nombre_campo = getNomCampos.getString(1);
						int orden_campo = getNomCampos.getInt(2);
						String tipoDato = getNomCampos.getString(3);
						int requerido = getNomCampos.getInt(4);
						String tablaFuente = getNomCampos.getString(5);
						String idFuente = getNomCampos.getString(6);
						String textoFuente = getNomCampos.getString(7);

						Log.i("Datos de nom: ", id_campo + " " + nombre_campo
								+ " " + orden_campo + " " + tipoDato
								+ " requerido: " + requerido + " "
								+ tablaFuente + " " + idFuente + " "
								+ textoFuente + " consecutivo:"
								+ consecutivo_campo2

						);

						if (tipoDato.equals("entero") && tablaFuente.equals("")) { // Numerico
							Log.i("Datos de nom entero: ", id_campo + " "
									+ nombre_campo + " " + orden_campo + " "
									+ tipoDato + " requerido: " + requerido
									+ " " + tablaFuente + " " + idFuente + " "
									+ textoFuente + " consecutivo:"
									+ consecutivo_campo2);

							// Guardar respuesta
							EditText txt_nom_entero = (EditText) root
									.findViewById(consecutivo_campo2);
							String valor = txt_nom_entero.getText().toString();
							String valortrim = valor.trim();
							if ((valortrim.equals("") || valortrim == null)
									&& requerido == 1) {

								/*txt_nom_entero.setHint(nombre_campo
										+ " Campo requerido");*/
								txt_nom_entero.setHint(getResources().getString(R.string.nom_requerido) + " " + nombre_campo);
								txt_nom_entero.setHintTextColor(Color.RED);
								contadorRequerido++;
								Log.i("contador entero",
										contadorRequerido
												+ " ::::::::::::::::::::::::::::::::::::::::::::..");
								guardarRespuesta(id_nom, id_item, id_campo,
										valor);
								consecutivo_campo2++;
							} else {

								guardarRespuesta(id_nom, id_item, id_campo,
										valor);
								Log.i("Respuesta insertada:", " nom: " + id_nom
										+ " item: " + id_item + " id_campo: " + id_campo + " repuesta: "
										+ valor);
								consecutivo_campo2++;

							}

						} else if (tipoDato.equals("double")
								&& tablaFuente.equals("")) { // Double

							Log.i("Datos de double: ", id_campo + " "
									+ nombre_campo + " " + orden_campo + " "
									+ tipoDato + " requerido: " + requerido
									+ " " + tablaFuente + " " + idFuente + " "
									+ textoFuente + " consecutivo:"
									+ consecutivo_campo2

							);
							// Armar int
							EditText txt_nom_entero = (EditText) root
									.findViewById(consecutivo_campo2);
							String valor = txt_nom_entero.getText().toString();
							String valortrim = valor.trim();
							if ((valortrim.equals("") || valortrim == null)
									&& requerido == 1) {

								/*txt_nom_entero.setHint(nombre_campo
										+ " Campo requerido");*/
								txt_nom_entero.setHint(getResources().getString(R.string.nom_requerido) + " " + nombre_campo);
								txt_nom_entero.setHintTextColor(Color.RED);
								contadorRequerido++;
								Log.i("contador entero",
										contadorRequerido
												+ " ::::::::::::::::::::::::::::::::::::::::::::..");
								guardarRespuesta(id_nom, id_item, id_campo,
										valor);
								
								consecutivo_campo2++;
							} else {

								guardarRespuesta(id_nom, id_item, id_campo,
										valor);
								Log.i("Respuesta insertada:", " nom: " + id_nom
										+ " item: " + id_item + " id_campo: " + id_campo + " repuesta: "
										+ valor);
								consecutivo_campo2++;

							}

						} else if (tipoDato.equals("string")
								&& tablaFuente.equals("")) { // String
							Log.i("Datos de string: ", id_campo + " "
									+ nombre_campo + " " + orden_campo + " "
									+ tipoDato + " requerido: " + requerido
									+ " " + tablaFuente + " " + idFuente + " "
									+ textoFuente + " consecutivo:"
									+ consecutivo_campo2

							);

							EditText txt_nom_entero = (EditText) root
									.findViewById(consecutivo_campo2);
							String valor = txt_nom_entero.getText().toString();
							String valortrim = valor.trim();
							if ((valortrim.equals("") || valortrim == null)
									&& requerido == 1) {

								/*txt_nom_entero.setHint(nombre_campo
										+ " Campo requerido");*/
								txt_nom_entero.setHint(getResources().getString(R.string.nom_requerido) + " " + nombre_campo);
								txt_nom_entero.setHintTextColor(Color.RED);
								contadorRequerido++;
								Log.i("contador entero",
										contadorRequerido
												+ " ::::::::::::::::::::::::::::::::::::::::::::..");
								guardarRespuesta(id_nom, id_item, id_campo,
										valor);
								consecutivo_campo2++;
							} else {

								guardarRespuesta(id_nom, id_item, id_campo,
										valor);
								Log.i("Respuesta insertada:", " nom: " + id_nom
										+ " item: " + id_item + " id_campo: " + id_campo + " repuesta: "
										+ valor);
								consecutivo_campo2++;

							}

						}

						if (tablaFuente != null
								&& tablaFuente.equals("catSiNo")) {
							Log.i("Datos de catSINo: ", id_campo + " "
									+ nombre_campo + " " + orden_campo + " "
									+ tipoDato + " requerido: " + requerido
									+ " " + tablaFuente + " " + idFuente + " "
									+ textoFuente + " consecutivo:"
									+ consecutivo_campo2

							);
							Spinner spinner1 = (Spinner) root
									.findViewById(consecutivo_campo2);
							String valor = spinner1.getSelectedItem()
									.toString();
							 int idValor = spinner1.getSelectedItemPosition() + 1;
						       guardarRespuesta(id_nom, id_item, id_campo, String.valueOf(idValor));// 
							Log.i("Respuesta insertada:", " nom: " + id_nom
									+ " item: " + id_item + " repuesta: "
									+ valor);
							consecutivo_campo2++;

						} else if (tablaFuente != null
								&& tablaFuente.equals("catUso")) {
							Log.i("Datos de nom cat uso: ", id_campo + " "
									+ nombre_campo + " " + orden_campo + " "
									+ tipoDato + " requerido: " + requerido
									+ " " + tablaFuente + " " + idFuente + " "
									+ textoFuente + " consecutivo:"
									+ consecutivo_campo2

							);
							Spinner spinner1 = (Spinner) root
									.findViewById(consecutivo_campo2);
							String valor = spinner1.getSelectedItem()
									.toString();
							 int idValor = spinner1.getSelectedItemPosition() + 1;
						       guardarRespuesta(id_nom, id_item, id_campo, String.valueOf(idValor));// 
							Log.i("Respuesta insertada:", " nom: " + id_nom
									+ " item: " + id_item + " id_campo: " + id_campo + " repuesta: "
									+ valor);
							consecutivo_campo2++;

						} else if (tablaFuente != null
								&& tablaFuente.equals("catEstatusAparatos")) {
							Log.i("Datos de nom estatus aparato: ", id_campo
									+ " " + nombre_campo + " " + orden_campo
									+ " " + tipoDato + " requerido: "
									+ requerido + " " + tablaFuente + " "
									+ idFuente + " " + textoFuente
									+ " consecutivo:" + consecutivo_campo2

							);
							Spinner spinner1 = (Spinner) root
									.findViewById(consecutivo_campo2);
							String valor = spinner1.getSelectedItem()
									.toString();
							 int idValor = spinner1.getSelectedItemPosition() + 1;
						       guardarRespuesta(id_nom, id_item, id_campo, String.valueOf(idValor));// 
							Log.i("Respuesta insertada:", " nom: " + id_nom
									+ " item: " + id_item + " id_campo: " + id_campo + " repuesta: "
									+ valor);
							consecutivo_campo2++;

						} else {
							Log.i("Guardando la NOM",
									"Algo paso tablaFuente Null es un campo abierto: "
											+ nombre_campo);
						}

					} while (getNomCampos.moveToNext());
					getNomCampos.close();
				}

			} while (getNoms.moveToNext());
			getNoms.close();
		}

		if (contadorRequerido > 0) {

			Toast.makeText(app_context,  app_context.getText(R.string.nom_faltan_requeridos),
					Toast.LENGTH_SHORT).show();
			setNomEstatus(id_nom, id_item,Constantes.ESTATUS_SIN_REVISAR, app_context);
			Toast.makeText(app_context, app_context.getText(R.string.nom_cambiar_estatus),
					Toast.LENGTH_SHORT).show();
			Log.i("Guardar Nom", "Se cambio el estatus a En Revisión");
			contadorRequerido = 0;
			consecutivo_campo2 = 1;
			consecutivo_campo = 1;

		} else {
			// Cambiar estatus a Revisado
			setNomEstatus(id_nom, id_item, Constantes.ESTATUS_REVISADO, app_context);
			Toast.makeText(app_context, app_context.getText(R.string.nom_estatus_revisada),
					Toast.LENGTH_SHORT).show();
			Log.i("Guardar Nom", "Se cambio el estatus a Revisado");
			contadorRequerido = 0;
			consecutivo_campo2 = 1;
			consecutivo_campo = 1;

			valida_estatus();
			
		}

	}
	
	public void guardarRespuesta(long idNom, long idItem, long idCampo,
			String valor) {

		if (valor == null || valor.equals("")){
			valor = "NA";
		}
		String[] colRespuestaA = { "IdResp", "IdNom", "IdItem", "IdCampo",
				"Valor" };
		ContentValues colRespuestaInsert = new ContentValues();

		// colRespuesta.put("IdResp", Nombre);
		colRespuestaInsert.put("IdNom", idNom);
		colRespuestaInsert.put("IdItem", idItem);
		colRespuestaInsert.put("IdCampo", idCampo);
		colRespuestaInsert.put("Valor", valor);
		colRespuestaInsert.put("Estatus", Constantes.ESTATUS_REVISADO);

		ContentValues colRespuestaUpdate = new ContentValues();

		// colRespuesta.put("IdResp", Nombre);
		colRespuestaUpdate.put("Valor", valor);

		String query = "IdNom=" + idNom + " and IdItem =" + idItem
				+ " and IdCampo=" + idCampo;
		

		Cursor getNomCampos = app_context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Respuesta"), colRespuestaA,
				query, null, null);

		if (getNomCampos.getCount() > 0) {
			
			app_context.getContentResolver().update(
					Uri.parse(MyContentProvider.URL + "Respuesta"),
					colRespuestaUpdate, query, null);
			// Toast.makeText(app_context, , Toast.LENGTH_SHORT).show();
			
			Log.i("Guardar Nom", "Todo se actualizo bien en Respuesta Nom :)");

		} else {

			uri = app_context.getContentResolver().insert(
					Uri.parse(MyContentProvider.URL + "Respuesta"),
					colRespuestaInsert);
			// Toast.makeText(app_context,
			// "Todo se guardo bien en Respuesta Nom :)",
			// Toast.LENGTH_SHORT).show();
			Log.i("Guardar Nom", "Todo se guardo bien en Respuesta Nom :)");
		}

	}

	public Boolean setNomEstatus(long idNom, long idItem, int estatus_nom, Context context) {

		try {
			ContentValues updateValues = new ContentValues();
			updateValues.put("Estatus", estatus_nom);

			String statement = "IdNom = " + idNom + " and IdItem=" + idItem;

			int updateBoolean = context.getContentResolver().update(
					Uri.parse(MyContentProvider.URL + "NomItem"), updateValues,
					statement, null);

			if (updateBoolean == 1) // Si no hubo errores
				return true;
			else
				return false;

		} catch (Exception e) {
			Log.e("Frag Nom Revision", "CambiarNom a Revisado", e);
			e.printStackTrace();
			return false;
		}

	}

	public String getEstatusNom() {

		String estatusNom = "IdNom =" + id_nom + " and IdItem =" + id_item;
		String estatus = "";

		Cursor getNomsEstatus = app_context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "NomItem"), colNomItem,
				estatusNom, null, null);

		if (getNomsEstatus.moveToFirst()) {

			estatus = getNomsEstatus.getInt(3) + "";

		}

		return estatus;
	}

	public String getRespuestaCampoVerify(long idCampo) {

		String estatusNom = "IdNom =" + id_nom + " and IdItem =" + id_item
				+ " and IdCampo =" + idCampo;
		String respuesta = "";

		Cursor getRespuesta = app_context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Respuesta"), colRespuesta,
				estatusNom, null, null);

		if (getRespuesta.moveToFirst()) {

			respuesta = getRespuesta.getString(4);
			Log.i("Respuesta: ", respuesta
					+ ":::::::::::::::::::::::::::::::::");

		}
		getRespuesta.close();

		String estatusNom2 = "IdNom =" + id_nom + " and IdItem =" + id_item;
		Log.i("Imprimiento respuestas asociadas", id_nom + "idItem: " +id_item);
		String respuesta2 = "";

		Cursor getRespuesta2 = app_context.getContentResolver().query(
				Uri.parse(MyContentProvider.URL + "Respuesta"), colRespuesta,
				estatusNom2, null, null);

		if (getRespuesta2.moveToFirst()) {
			int i = 1;
			do {
				respuesta2 = getRespuesta2.getString(4);
				Log.i("Lista Respuesta2: ", respuesta2
						+ ":::::::::::::::::::::::::::::::::" + i);
				i++;
			} while (getRespuesta2.moveToNext());
			getRespuesta2.close();

		}

		return respuesta;
	}
	
	
	public void eliminarRespuestasNom(){
		
		String estatusNom2 = "IdNom =" + id_nom + " and IdItem =" + id_item;
		
		//uri = app_context.getContentResolver().delete(Uri.parse(MyContentProvider.URL + "Respuesta"), estatusNom2, null);
		app_context.getContentResolver().delete(
				Uri.parse(MyContentProvider.URL + "Respuesta"), estatusNom2,
				null);		
				
				
		 Toast.makeText(app_context,
				 app_context.getText(R.string.nom_cambiar_estatus),
		 Toast.LENGTH_SHORT).show();
		Log.i("Eliminar Nom", "Todo se elimino bien en Respuesta Nom :)");
		
		
		
	}
	
	public void dialogo(String tag, String titulo, String mensaje) {
		editNameDialog = Utl_CustomDialog.newInstance();

		Bundle bdl = new Bundle();

		bdl.putString("elementos",
				"layout,clase_name,boton1,boton2,title,mensage");

		bdl.putInt("layout", R.layout.customdialog);
		bdl.putString("Tag", tag);

		bdl.putString("nombre_clase", getClass().getName());

		bdl.putString("boton1", "button" + "," + R.id.button_accept + ","
				+ getResources().getString(R.string.str_main_btn_aceptarUPC));

		bdl.putString("boton2", "button" + "," + R.id.button_cancel + ","
				+ getResources().getString(R.string.str_main_btn_cancelarUPC));

		bdl.putString("title", "textview" + "," + R.id.title + ","
				+ titulo);

		bdl.putString("mensage", "textview" + "," + R.id.message_text + ","
				+ mensaje );

		editNameDialog.setArguments(bdl);

		editNameDialog.show(Act_Main.fm, "fragment_edit_name");

	}

	@Override
	public void onclick(int id, String Tag) {
		// TODO Auto-generated method stub
		if(Tag.equals("Todos")){
			switch (id) {
			case R.id.button_accept:
				eliminarRespuestasNom();
				editNameDialog.dismiss();
				setNomEstatus(id_nom, id_item, Constantes.ESTATUS_SIN_REVISAR, app_context);
				valida_estatus();
				break;
			case R.id.button_cancel:
				editNameDialog.dismiss();
				break;
			default:
				break;
			}	
		}
	}
	
	public void valida_estatus(){
		boolean revisado = false;
		
		revisado = buss_revision.terminar_revision(id_item, app_context);
		//Log.v("Frag Revision", "item_act "+ item_act+"");
		Log.v("Frag Revision", " Revision "+revisado+"");
		//if(item_act > 0 && revisado == true){
		if(revisado == true){
			Log.v("Frag revision", "Item Revisado");
			buss_revision.actualiza_estatus_item(id_item, Constantes.ESTATUS_REVISADO, app_context);
			
			buss_revision.valida_factura(id_fact, app_context);
			
			Toast.makeText(app_context,
					app_context.getText(R.string.str_revisionItem_toast_itemRevisado),
					Toast.LENGTH_LONG).show();			
			
		}
		else{
			buss_revision.actualiza_estatus_item(id_item, Constantes.ESTATUS_EN_REVISION, app_context);
			buss_revision.valida_factura(id_fact, app_context);
		}
	}
}
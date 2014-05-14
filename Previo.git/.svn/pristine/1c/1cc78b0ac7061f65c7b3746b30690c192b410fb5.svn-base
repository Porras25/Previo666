package com.adquem.grupologistics.controllers;

import java.util.ArrayList;

import com.adquem.grupologistics.bussines.Buss_FragListadoFact_RevisionReferencia;
import com.adquem.grupologistics.bussines.Buss_Frag_AltaItem;
import com.adquem.grupologistics.model.RazonCierre;
import com.adquem.grupologistics.model.Referencia;
import com.adquem.grupologistics.utilities.Constantes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Controlador entre la vista y la capa de negocio para el forzado de revisión
 * 
 * @author Adquem S.C
 * @version 1.0
 */
public class Frag_Forzar_Revision extends Fragment implements
		OnItemSelectedListener {

	private long id_refRev;
	private Context app_context;
	private Spinner razones;
	private EditText comentarios;
	private Button forzar;
	ArrayList<RazonCierre> valores;
	private int Tipo;
	private int Estatus;
	private Buss_Frag_AltaItem bus_alta_item;

	public static Fragment newInstance(Context context) {
		Frag_AltaItem f = new Frag_AltaItem();

		return f;
	}

	/**
	 * Crea la vista para forzar la revisión
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.lyt_fragment_forzar_revision, null);

		app_context = getActivity();

		this.razones = (Spinner) root.findViewById(R.id.spn_razonesCierre);
		this.comentarios = (EditText) root.findViewById(R.id.txtComentario);
		this.forzar = (Button) root.findViewById(R.id.btnForzarRevision);

		forzar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				int estatus = 0;
				bus_alta_item = new Buss_Frag_AltaItem();

				estatus = bus_alta_item.obtiene_estatus_referencia(id_refRev,
						app_context);

				if (estatus != Constantes.ESTATUS_SINCRONIZADO) {
					forzar_revision();
				} else {
					Toast.makeText(
							app_context,
							getText(R.string.str_forzarRevision_toast_referenciaSincronizada),
							Toast.LENGTH_LONG).show();
				}

			}
		});

		return root;
	}

	/**
	 * Crea la actividad para forzar la revisión
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		Bundle args = getArguments();
		this.id_refRev = args.getLong("Padre");
		this.Tipo = args.getInt("TipoListado");
		this.Estatus = args.getInt("Estatus");

		loadSpinnerRazones();

		pinta_vista();

	}

	/**
	 * Carga el spinner con el catálogo de razones de cierre
	 */
	private void loadSpinnerRazones() {

		Buss_FragListadoFact_RevisionReferencia bus_forzarCierre = new Buss_FragListadoFact_RevisionReferencia();

		valores = bus_forzarCierre.obtiene_razones(app_context);

		ArrayAdapter<RazonCierre> spinArrayAdapter = new ArrayAdapter<RazonCierre>(
				app_context, android.R.layout.simple_spinner_item, valores);

		spinArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		razones.setAdapter(spinArrayAdapter);
		razones.setOnItemSelectedListener(this);

	}

	/**
	 * Forza la revisión de la referencia, validando campos necesarios y
	 * fotografías
	 */
	public void forzar_revision() {
		Buss_FragListadoFact_RevisionReferencia bus_forzar = new Buss_FragListadoFact_RevisionReferencia();
		boolean forzado = false;
		boolean fotos = false;

		if (comentarios.getText() != null
				&& !comentarios.getText().toString().equals("")
				&& razones.getSelectedItem() != null) {

			fotos = bus_forzar.validacionReferencia(id_refRev,
					app_context);

			if (fotos) {

				forzado = bus_forzar.forzarRevision(id_refRev, comentarios
						.getText().toString(), (int) ((RazonCierre) razones
						.getSelectedItem()).getIdRazon(), app_context);

				if (forzado) {
					Toast.makeText(
							app_context,
							getText(R.string.str_forzarRevision_toast_confirmacion),
							Toast.LENGTH_LONG).show();
					Log.v("Frag_Forzar_Revision",
							getText(R.string.str_forzarRevision_toast_confirmacion)
									+ " IDReferencia: " + id_refRev);

					Bundle argsRefDesg = new Bundle();
				} else {
					Toast.makeText(
							app_context,
							getText(R.string.str_forzarRevision_toast_errorForzar),
							Toast.LENGTH_LONG).show();
					Log.v("Frag_Forzar_Revision",
							getText(R.string.str_forzarRevision_toast_errorForzar)
									+ " IDReferencia: " + id_refRev);
				}
			} else {
				Toast.makeText(app_context,
						getText(R.string.str_forzarRevision_toast_faltanFotos),
						Toast.LENGTH_LONG).show();
				Log.v("Frag_Forzar_Revision",
						getText(R.string.str_forzarRevision_toast_faltanFotos)
								+ " IDReferencia: " + id_refRev);
			}
		} else
			Toast.makeText(app_context,
					getText(R.string.str_forzarRevision_toast_faltanDatos),
					Toast.LENGTH_LONG).show();
		Log.v("Frag_Forzar_Revision",
				getText(R.string.str_forzarRevision_toast_faltanDatos)
						+ " IDReferencia: " + id_refRev);
	}

	/**
	 * Llena la vista con los valores de la base de datos
	 */
	public void pinta_vista() {
		Buss_FragListadoFact_RevisionReferencia bus_forzar = new Buss_FragListadoFact_RevisionReferencia();
		Referencia referencia = new Referencia();
		referencia = bus_forzar.getReferencia(id_refRev, app_context);
		int posicion = 0;

		Log.v("Frag_Forzar_Revision", "Pinta vista id referencia: "
				+ referencia);

		for (int i = 0; i < valores.size(); i++) {
			if (valores.get(i).getIdRazon() == referencia.getIdRazonCierre()) {
				posicion = i;
				break;
			}
		}

		comentarios.setText(referencia.getComentarios_razon_Cierre());
		razones.setSelection(posicion);
	}

	/**
	 * Detecta el item seleccionado
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		long alimentoId = 0;

		if (parent.getId() == R.id.spn_razonesCierre) {
			alimentoId = ((RazonCierre) parent.getItemAtPosition(position))
					.getIdRazon();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}

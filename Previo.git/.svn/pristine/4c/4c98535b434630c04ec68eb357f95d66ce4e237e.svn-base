package com.adquem.grupologistics.controllers;

import com.adquem.grupologistics.bussines.Buss_Frag_RevisionItem;
import com.adquem.grupologistics.bussines.Buss_Frag_RevisionNom;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.utilities.Constantes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Clase Controlador entre la vista y la capa de negocio para la revisión de un
 * item
 * 
 * @author Adquem S.C
 * @version 1.0
 */
public class Frag_item_revision extends Fragment {

	// /Variables globales
	String[] values;
	TextView referencia, razon_social, cantidad, unidad_medida, pais_orig,
			noParte, desc_lg;
	EditText cant_rec, comentarios;
	LinearLayout layout, layout_noms;
	Buss_Frag_RevisionItem buss_revision = new Buss_Frag_RevisionItem();
	Context app_context;
	long id_itemRev;
	Item item = new Item();

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		SharedPreferences prefs = this.getActivity().getSharedPreferences(
				"PreferenciasPrevioApp", Context.MODE_PRIVATE);
		// this.id_itemRev = prefs.getInt("Padre", 0);

		Log.v("onStart", "id_itemRev" + id_itemRev);
	}
	
	/**
	 * Crea la vista para la revisión del item
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.lyt_fragment_revisionitem, null);

		referencia = (TextView) root.findViewById(R.id.txv_numReferenciaValor);
		razon_social = (TextView) root.findViewById(R.id.txv_razonSocialValor);
		cantidad = (TextView) root.findViewById(R.id.txv_cantidadValor);
		unidad_medida = (TextView) root.findViewById(R.id.txv_unidadValor);
		pais_orig = (TextView) root.findViewById(R.id.txv_paisOrigenValor);
		desc_lg = (TextView) root.findViewById(R.id.txv_descripcionGL);
		layout = (LinearLayout) root.findViewById(R.id.lyt_paisOrigen);
		cant_rec = (EditText) root.findViewById(R.id.txt_cantidadRecibida);
		comentarios = (EditText) root.findViewById(R.id.txt_comentarios);
		layout_noms = (LinearLayout) root.findViewById(R.id.lyt_noms);
		noParte = (TextView) root.findViewById(R.id.txv_revisionItemTitulo);

		app_context = getActivity();

		return root;
	}

	/**
	 * Crea la actividad para revisión de ítem
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		Bundle args = getArguments();
		this.id_itemRev = args.getLong("Padre");

		Log.v("Frag_item_revision", "Id Item recibido: " + id_itemRev);

		pintaVista();

		ImageButton revision = (ImageButton) getView().findViewById(
				R.id.imgb_desglose);
		ImageButton galeria = (ImageButton) getView().findViewById(
				R.id.imgb_revisionCamara);
		Button actualiza = (Button) getView().findViewById(
				R.id.btnActualizaItem);

		actualiza.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int estatus = 0;

				estatus = buss_revision.obtiene_estatus_item(id_itemRev,
						app_context);

				if (estatus != Constantes.ESTATUS_SINCRONIZADO) {
					actualiza_revision();
				} else {
					Toast.makeText(
							app_context,
							getText(R.string.str_revisionItem_toast_itemSincronizado),
							Toast.LENGTH_LONG).show();
				}

			}
		});

		revision.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle argsItemDesg = new Bundle();
				FragmentTransaction tx = getFragmentManager()
						.beginTransaction();
				tx.setCustomAnimations(R.animator.enter, R.animator.exit,
						R.animator.pop_enter, R.animator.pop_exit);
				Frag_DesgloseCantidad remplazo = new Frag_DesgloseCantidad();

				tx.replace(R.id.frm_lyt_mainMenu,
						Act_Main.mFragmentStack.push(remplazo),
						Constantes.TIPOlISTADO_REFERENCIA + ":"
								+ Constantes.ESTATUS_TODOS);
				argsItemDesg.putLong("id_Item", id_itemRev);
				argsItemDesg.putLong("idFact", item.getIdFactura());
				argsItemDesg.putString("item_valor", item.getNoParte());
				remplazo.setArguments(argsItemDesg);
				tx.addToBackStack(null);
				tx.commit();
			}
		});

		galeria.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle argsItemDesg = new Bundle();
				// TODO Auto-generated method stub
				FragmentTransaction tx = getFragmentManager()
						.beginTransaction();
				tx.setCustomAnimations(R.animator.enter, R.animator.exit,
						R.animator.pop_enter, R.animator.pop_exit);
				Frag_PhotoIntentItem remplazo = new Frag_PhotoIntentItem();

				tx.replace(R.id.frm_lyt_mainMenu,
						Act_Main.mFragmentStack.push(remplazo),
						Constantes.TIPOlISTADO_REFERENCIA + ":"
								+ Constantes.ESTATUS_TODOS);
				Log.i("Frag_item_revision", "Id Item pasado en el bundle: "
						+ id_itemRev);
				argsItemDesg.putLong("Padre", id_itemRev);
				argsItemDesg.putLong("idFact", item.getIdFactura());
				remplazo.setArguments(argsItemDesg);
				tx.addToBackStack(null);
				// tx.remove(Frag_item_revision.this);
				tx.commit();
			}
		});

	}

	/**
	 * Método que obtiene la información de un ítem específico y la muestra en
	 * la vista
	 */
	public void pintaVista() {
		String[] valoresRef = null;
		String unidad;
		String pais;
		Buss_Frag_RevisionNom bus_nom = new Buss_Frag_RevisionNom(getActivity()
				.getBaseContext());
		String[][] noms;

		item = buss_revision.obtiene_item(id_itemRev, app_context);

		valoresRef = buss_revision.obtiene_referencia(
				item.getIdFactura(), app_context);

		unidad = buss_revision.obtiene_unidadMedida(
				item.getIdUnidadMedida(), app_context);

		pais = buss_revision.obtiene_pais_origen(id_itemRev, app_context);

		Log.v("Frag_item_revision", "Primer pais: " + pais);

		if (pais.equals("")) {
			layout.setVisibility(View.GONE);
		} else
			layout.setVisibility(View.VISIBLE);

		referencia.setText(valoresRef[0]);
		razon_social.setText(valoresRef[1]);
		cantidad.setText(item.getCantidadEsp() + "");
		unidad_medida.setText(unidad);
		pais_orig.setText(pais);
		desc_lg.setText(item.getDescripcion());
		
		
		if(item.getCantidadRec() != 0){
			cant_rec.setText(item.getCantidadRec() + "");
		}else if (item.getCantidadRec() == 0 ){
			cant_rec.setHintTextColor(Color.RED);
		}
		
		comentarios.setText(item.getComentarios());
		noParte.setText(getString(R.string.str_revisionitem_txv_titulo) + " "
				+ item.getNoParte().toString());

		noms = bus_nom.getNomsItem(id_itemRev, app_context);

		if (noms != null && noms.length > 0) {
			for (int i = 0; i < noms.length; i++) {

				Button btn = new Button(app_context);

				btn.setId(Integer.parseInt(noms[i][0]));
				btn.setText(noms[i][1]);

				layout_noms.addView(btn);

				btn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						FragmentTransaction tx = getFragmentManager()
								.beginTransaction();
						tx.setCustomAnimations(R.animator.enter,
								R.animator.exit, R.animator.pop_enter,
								R.animator.pop_exit);
						Frag_nom_revision remplazo = new Frag_nom_revision();

						tx.replace(R.id.frm_lyt_mainMenu,
								Act_Main.mFragmentStack.push(remplazo),
								Constantes.TIPOlISTADO_REFERENCIA + ":"
										+ Constantes.ESTATUS_TODOS);
						Act_Main.fragmentos.push(remplazo);
						Act_Main.elementos.push("Frag_nom_revision");
						Bundle argsItem = new Bundle();
						argsItem.putLong("id_item", id_itemRev);
						argsItem.putLong("id_nom", v.getId());
						argsItem.putLong("idFact", item.getIdFactura());
						remplazo.setArguments(argsItem);
						tx.addToBackStack(null);
						// tx.remove(Frag_item_revision.this);
						tx.commit();
					}
				});

			}
		} else
			Log.v("Frag_item_revision", "No hay noms para id item: "
					+ id_itemRev);

	}

	/**
	 * Método que actualiza el ítem especificado
	 */
	public void actualiza_revision() {
		int item_act = 0;
		boolean revisado = false;

		if (cant_rec.getText().toString() != null
				&& comentarios.getText().toString() != null) {

			if(Float.parseFloat(cant_rec.getText().toString()) > 0f){
				String coment;

				if (comentarios.getText().toString().equals("")) {
					coment = Constantes.VALUE_DEFAULT_STRING;
				} else {
					coment = comentarios.getText().toString();
				}

				item_act = buss_revision.actualiza_revision(id_itemRev,
						Float.parseFloat(cant_rec.getText().toString()), coment, app_context);

				revisado = buss_revision.terminar_revision(id_itemRev, app_context);

				if (revisado == true) {
					Log.v("Frag revision", "Item Revisado");
					buss_revision.actualiza_estatus_item(id_itemRev,
							Constantes.ESTATUS_REVISADO, app_context);

					buss_revision.valida_factura(item.getIdFactura(),
							app_context);

					Toast.makeText(app_context,
							getText(R.string.str_revisionItem_toast_itemRevisado),
							Toast.LENGTH_LONG).show();
					Log.v("Frag_item_revision",
							getText(R.string.str_revisionItem_toast_itemRevisado)
									+ " id Item: " + id_itemRev);
				} else if (item_act > 0) {
					buss_revision.actualiza_estatus_item(id_itemRev,
							Constantes.ESTATUS_EN_REVISION, app_context);

					buss_revision.valida_factura(item.getIdFactura(),
							app_context);

					Log.v("Frag_item_revision",
							getText(R.string.str_revisionItem_toast_itemActualizado)
									+ " Id Item: " + id_itemRev);
					Toast.makeText(
							app_context,
							getText(R.string.str_revisionItem_toast_itemActualizado),
							Toast.LENGTH_LONG).show();
				}	
			}
			else{
				Toast.makeText(app_context,
						getText(R.string.str_desgloses_toast_validaCantidad),
						Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(app_context,
					getText(R.string.str_revisionItem_toast_noDatos),
					Toast.LENGTH_LONG).show();
		}

	}

}

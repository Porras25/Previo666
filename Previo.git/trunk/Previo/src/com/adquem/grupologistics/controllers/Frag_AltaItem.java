package com.adquem.grupologistics.controllers;

import java.util.ArrayList;
import java.util.List;

import com.adquem.grupologistics.bussines.Buss_Frag_AltaItem;
import com.adquem.grupologistics.bussines.Buss_Frag_RevisionItem;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.model.Nom;
import com.adquem.grupologistics.model.UnidadMedida;
import com.adquem.grupologistics.utilities.Constantes;
import com.adquem.grupologistics.utilities.Dialogs;
import com.adquem.grupologistics.utilities.Utl_CustomDialog;

import android.os.Bundle;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Controlador entre la vista y la capa de negocio para el alta de un excedente
 * 
 * @author Adquem S.C
 * @version 1.0
 */
public class Frag_AltaItem extends Fragment implements OnItemSelectedListener,
		Dialogs {
	private Spinner noms;
	private Spinner cat_unidad_medida;
	private Button but1;
	private Button but2;
	private ImageButton but3;
	public static LinearLayout layout;
	public static EditText nParte, descripcion, cantidad;
	// private EditText unidad_med;
	private Context app_context;
	private long id_Ref;
	public static ArrayList<Long> noms_ids = new ArrayList<Long>();
	public static View vista;
	public static Utl_CustomDialog editNameDialog;
	private Buss_Frag_AltaItem bus_alta_item;

	public static Fragment newInstance(Context context) {
		Frag_AltaItem f = new Frag_AltaItem();

		return f;
	}

	/**
	 * Crea la vista del alta del ítem
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.lyt_fragment_alta_item, null);
		this.noms = (Spinner) root.findViewById(R.id.spn_noms);
		this.cat_unidad_medida = (Spinner) root
				.findViewById(R.id.spn_unidad_medida);
		this.but1 = (Button) root.findViewById(R.id.btn_altaNom);
		this.but2 = (Button) root.findViewById(R.id.btn_saveitem);
		this.but3 = (ImageButton) root.findViewById(R.id.imgb_cancelitem);
		this.nParte = (EditText) root.findViewById(R.id.txt_noparte);
		this.descripcion = (EditText) root.findViewById(R.id.txt_descripcion);
		this.cantidad = (EditText) root.findViewById(R.id.txt_cantidad);
		this.layout = (LinearLayout) root.findViewById(R.id.layout_noms);

		app_context = getActivity();

		noms_ids.removeAll(noms_ids);

		loadSpinnerNoms();
		loadSpinnerUnidadMedida();

		but1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addNoms(v);
			}
		});

		but2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int estatus = 0;

				estatus = bus_alta_item.obtiene_estatus_referencia(id_Ref,
						app_context);

				if (estatus != Constantes.ESTATUS_SINCRONIZADO) {
					guardar_item();
				} else {
					Toast.makeText(
							app_context,
							getText(R.string.str_alta_toast_referenciaSincronizada),
							Toast.LENGTH_LONG).show();
				}

			}
		});

		but3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String titulo = getText(
						R.string.str_alta_dialog_titulo_eliminacion).toString();
				String mensaje = getText(
						R.string.str_alta_dialog_msg_eliminacion).toString();
				String tag = "Todos";
				dialogo(tag, titulo, mensaje);
			}
		});

		return root;
	}

	/**
	 * Actividad creada para el alta del ítem
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		Bundle args = getArguments();
		this.id_Ref = args.getLong("Referencia");
		Log.v("Frag_AltaItem",
				"Id Referencia recibido: " + args.getLong("Referencia") + "");
	}

	/**
	 * Carga la lista de noms disponibles
	 */
	private void loadSpinnerNoms() {

		bus_alta_item = new Buss_Frag_AltaItem();

		List<Nom> valores = bus_alta_item.obtiene_noms(app_context);

		ArrayAdapter<Nom> spinArrayAdapter = new ArrayAdapter<Nom>(app_context,
				android.R.layout.simple_spinner_item, valores);

		spinArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		noms.setAdapter(spinArrayAdapter);
		noms.setOnItemSelectedListener(this);
	}

	/**
	 * Carga la lista de unidades de medida
	 */
	private void loadSpinnerUnidadMedida() {

		bus_alta_item = new Buss_Frag_AltaItem();

		List<UnidadMedida> valores = bus_alta_item
				.obtiene_unidad_medida(app_context);

		ArrayAdapter<UnidadMedida> spinArrayAdapter = new ArrayAdapter<UnidadMedida>(
				app_context, android.R.layout.simple_spinner_item, valores);

		spinArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		cat_unidad_medida.setAdapter(spinArrayAdapter);
		cat_unidad_medida.setOnItemSelectedListener(this);
	}

	/**
	 * Método asociado a la selección de elemento del spinner de paises
	 */
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		long alimentoId = 0;

		if (parent.getId() == R.id.spn_unidad_medida) {
			alimentoId = ((UnidadMedida) parent.getItemAtPosition(position))
					.getIdUnidadMedida();
		} else if (parent.getId() == R.id.spn_noms) {
			alimentoId = ((Nom) parent.getItemAtPosition(position)).getIdNom();
		}

	}

	/**
	 * Valida que no haya noms repetidas
	 * 
	 * @param button
	 *            Vista botón
	 */
	private void addNoms(View button) {
		// Do click handling here
		Log.v("Frag_AltaItem", "noms_ids.size() antes: " + noms_ids.size());
		if (noms_ids.size() == 0) {
			add_nom_proceso();
		} else {
			boolean flag = false;

			for (int i = 0; i < noms_ids.size(); i++) {
				if (noms_ids.get(i) == ((Nom) noms.getSelectedItem())
						.getIdNom()) {
					flag = true;
					break;
				}
			}

			if (flag) {
				Toast.makeText(app_context,
						getText(R.string.str_alta_toast_nomRepetida),
						Toast.LENGTH_LONG).show();
				Log.v("Frag_AltaItem", "addNoms, entré if bandera");
			} else {
				Log.v("Frag_AltaItem", "addNoms, entré else bandera");
				add_nom_proceso();
			}
		}

	}

	/**
	 * Agrega la nom a la lista de seleccionadas
	 */
	private void add_nom_proceso() {
		String nom_name = ((Nom) noms.getSelectedItem()).getNom();

		Button btn = new Button(app_context);
		btn.setText("" + nom_name);

		btn.setId((int) ((Nom) noms.getSelectedItem()).getIdNom());

		Log.v("Frag_AltaItem", "nom_name: " + nom_name);
		Log.v("Frag_AltaItem", "btn.getId(): " + btn.getId() + "");

		noms_ids.add(((Nom) noms.getSelectedItem()).getIdNom());
		Log.v("Frag_AltaItem", "noms_ids.size() despues " + noms_ids.size());
		layout.addView(btn);

		btn.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(final View v) {

				layout.post(new Runnable() {
					@Override
					public void run() {

						String tag = "UnoPantalla";
						String mensaje = getText(
								R.string.str_alta_dialog_msg_eliminaNOM)
								.toString();
						String titulo = getText(
								R.string.str_alta_dialog_titulo_eliminacion)
								.toString();
						vista = v;
						dialogo(tag, titulo, mensaje);

					}
				});

				return false;
			}
		});
	}

	/**
	 * Crea diálogo personalizado
	 * 
	 * @param tag
	 *            Identificador de tipo de diálogo
	 * @param titulo
	 *            Título del diálogo
	 * @param mensaje
	 *            Mensaje del diálogo
	 */
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

		bdl.putString("title", "textview" + "," + R.id.title + "," + titulo);

		bdl.putString("mensage", "textview" + "," + R.id.message_text + ","
				+ mensaje);

		editNameDialog.setArguments(bdl);

		editNameDialog.show(Act_Main.fm, "fragment_edit_name");

	}

	/**
	 * Elimina las noms de la vista
	 * 
	 * @param v
	 *            Vista botón que representa una nom
	 */
	public void removeViewPantalla(View v) {

		for (int i = 0; i < noms_ids.size(); i++) {
			if (noms_ids.get(i) == v.getId()) {
				noms_ids.remove(i);
				break;
			}
		}

		layout.removeView(v);

		Log.v("Frag_AltaItem", "id_boton: " + v.getId());
		Log.v("Frag_AltaItem", "tamanio arreglo noms: " + noms_ids.size());
	}

	/**
	 * Almacena el nuevo ítem
	 */
	private void guardar_item() {

		long item_guardado;

		bus_alta_item = new Buss_Frag_AltaItem();

		if ((nParte.getText() != null && !nParte.getText().toString()
				.equals(""))
				&& descripcion.getText() != null
				&& !descripcion.getText().toString().equals("")
				&& cantidad.getText() != null
				&& !cantidad.getText().toString().equals("")
				&& cat_unidad_medida.getSelectedItem() != null
				&& noms_ids != null && noms_ids.size() != 0) {

			item_guardado = bus_alta_item
					.alta(nParte.getText().toString(), descripcion.getText()
							.toString(), Float.parseFloat(cantidad.getText()
							.toString()), ((UnidadMedida) cat_unidad_medida
							.getSelectedItem()).getIdUnidadMedida(), noms_ids,
							app_context, id_Ref);

			if (item_guardado != 0) {
				Toast.makeText(app_context,
						getText(R.string.str_alta_toast_confirmacion),
						Toast.LENGTH_LONG).show();
				Log.v("Frag_AltItem",
						getText(R.string.str_alta_toast_confirmacion) + " ID: "
								+ item_guardado + " Nombre: "
								+ nParte.getText().toString());
				Buss_Frag_RevisionItem bus_revision = new Buss_Frag_RevisionItem();

				bus_revision.actualiza_estatus_referencia(id_Ref,
						Constantes.ESTATUS_EN_REVISION, app_context);

				long id_fact = bus_revision.getId_factura(id_Ref, app_context);
				
				Log.v("id_fact", "id_fact"+ id_fact);
				
				bus_revision.actualiza_estatus_factura(id_fact, Constantes.ESTATUS_EN_REVISION, app_context);
				
				FragmentTransaction tx = getFragmentManager()
						.beginTransaction();
				tx.setCustomAnimations(R.animator.enter, R.animator.exit,
						R.animator.pop_enter, R.animator.pop_exit);
				Frag_item_revision remplazo = new Frag_item_revision();
				tx.replace(R.id.frm_lyt_mainMenu, remplazo);
				Act_Main.fragmentos.push(remplazo);
				Act_Main.elementos.push("Frag_item_revision");
				// ////Referencia para alta item
				Bundle argsItem = new Bundle();
				argsItem.putLong("Padre", item_guardado);
				remplazo.setArguments(argsItem);
				tx.addToBackStack(null);
				tx.commit();
			} else {
				Toast.makeText(app_context,
						getText(R.string.str_alta_toast_errorAlta),
						Toast.LENGTH_LONG).show();
				Log.v("Frag_AltItem",
						getText(R.string.str_alta_toast_errorAlta)
								+ " Nombre: " + nParte.getText().toString());
			}

		} else {
			Toast.makeText(app_context,
					getText(R.string.str_alta_toast_faltaInfo),
					Toast.LENGTH_LONG).show();
			Log.v("Frag_AltItem", getText(R.string.str_alta_toast_faltaInfo)
					.toString());
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		// Callback method to be invoked when the selection disappears from this
		// view. The selection can disappear for instance when touch is
		// activated or when the adapter becomes empty.
	}

	/**
	 * Limpia los campos en la pantalla y la lista de noms seleccionadas
	 */
	public void limpia_pantalla() {
		nParte.setText("");
		descripcion.setText("");
		cantidad.setText("");

		layout.removeAllViews();

		noms_ids.removeAll(noms_ids);

	}

	/**
	 * Contiene la lógica de los botones según el tipo de diálogo
	 */
	@Override
	public void onclick(int id, String Tag) {
		// TODO Auto-generated method stub

		if (Tag.equals("Todos")) {
			switch (id) {
			case R.id.button_accept:
				editNameDialog.dismiss();
				limpia_pantalla();

				break;
			case R.id.button_cancel:
				editNameDialog.dismiss();
				break;
			default:
				break;
			}
		} else if (Tag.equals("UnoPantalla")) {
			switch (id) {
			case R.id.button_accept:
				editNameDialog.dismiss();

				removeViewPantalla(vista);
				noms_ids.remove(vista);

				break;
			case R.id.button_cancel:
				editNameDialog.dismiss();
				break;
			default:
				break;
			}
		}
	}

}

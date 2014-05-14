package com.adquem.grupologistics.controllers;

import java.util.ArrayList;
import java.util.LinkedList;

import com.adquem.grupologistics.bussines.Buss_Frag_DesgloseCantidad;
import com.adquem.grupologistics.bussines.Buss_Frag_RevisionItem;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.model.Desglose;
import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.model.Pais;
import com.adquem.grupologistics.utilities.Constantes;
import com.adquem.grupologistics.utilities.Dialogs;
import com.adquem.grupologistics.utilities.Utl_CustomDialog;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Clase controlador entre la vista y la capa de negocio de desglose
 * 
 * @author Adquem S.C
 * @version 1.0
 */
public class Frag_DesgloseCantidad extends Fragment implements
		OnItemSelectedListener, Dialogs {
	private Spinner pais;
	public static TextView cantidad, descripcion, item_revisar, no_parte;
	public static LinearLayout layout;
	public static Context appContext;
	private Button but_agregar;
	private ImageButton but_eliminar;
	private Button but_guardar;
	public static ArrayList<Desglose> desglose_pantalla = new ArrayList<Desglose>();
	public static ArrayList<Desglose> desglose_bd = new ArrayList<Desglose>();
	public static ArrayList<Desglose> desgloses_guardado = new ArrayList<Desglose>();
	public static int contador_desglose = 0;
	public static long id_item; 
	public static long id_fact;
	private int desglose_eliminado = 0;
	public static Utl_CustomDialog editNameDialog;
	public static View vista;
	private ArrayList<Pais> valores;
	public static Buss_Frag_RevisionItem buss_revision;
	public static Item item;
	private String item_valor;
	private int sincronizado = 0;

	LinkedList<Pais> valores_pais = new LinkedList<Pais>();
	Cursor c;

	/**
	 * Crea la vista de Desglose
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.lyt_fragment_desglose_cantidad, null);

		this.pais = (Spinner) root.findViewById(R.id.spn_paises);
		this.cantidad = (TextView) root.findViewById(R.id.txt_cantidad);
		this.descripcion = (TextView) root.findViewById(R.id.txt_descripcion);
		this.item_revisar = (TextView) root
				.findViewById(R.id.txv_titulo_alta_item);
		this.no_parte = (TextView) root.findViewById(R.id.txvItem);
		this.but_agregar = (Button) root.findViewById(R.id.imgb_adddesglose);
		this.but_eliminar = (ImageButton) root
				.findViewById(R.id.img_removedesgloses);
		this.but_guardar = (Button) root
				.findViewById(R.id.imgb_guardarDesglose);
		layout = (LinearLayout) root.findViewById(R.id.layout_desglose);

		appContext = getActivity();

		System.out.println("Contextooo " + appContext);

		but_eliminar.setOnClickListener(new OnClickListener() {
			//
			@Override
			public void onClick(View v) {
				// // TODO Auto-generated method stub

				if (sincronizado != Constantes.ESTATUS_SINCRONIZADO) {

					if (desglose_bd.size() > 0 || desglose_pantalla.size() > 0) {
						String titulo = getText(
								R.string.str_desgloses_dialog_titulo_limpia)
								.toString();
						String mensaje = getText(
								R.string.str_desgloses_dialog_msg_limpia)
								.toString();
						String tag = "Todos";
						dialogo(tag, titulo, mensaje);
					} else
						limpia_pantalla();

				} else {
					Toast.makeText(
							appContext,
							getText(R.string.str_desgloses_toast_itemSincronizadoElimina),
							Toast.LENGTH_LONG).show();
					Log.v("Frag_DesgloseCantidad",
							getText(R.string.str_desgloses_toast_itemSincronizadoElimina)
									+ " ID Item: " + id_item);
				}

			}
		});

		but_agregar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addPais(v);
			}
		});

		but_guardar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (sincronizado != Constantes.ESTATUS_SINCRONIZADO) {
					guarda_desgloses();
					cantidad.setText("");
					descripcion.setText("");
				} else {
					Toast.makeText(
							appContext,
							getText(R.string.str_desgloses_toast_itemSincronizadoGuarda),
							Toast.LENGTH_LONG).show();
					Log.v("Frag_DesgloseCantidad",
							getText(R.string.str_desgloses_toast_itemSincronizadoGuarda)
									+ " ID Item: " + id_item);
				}
			}
		});

		return root;
	}

	/**
	 * Crea la actividad para Desgloses
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		Bundle args = getArguments();
		this.id_item = args.getLong("id_Item");
		this.id_fact = args.getLong("idFact");
		this.item_valor = args.getString("item_valor");

		Log.v("Frag_DesgloseCantidad", "Id Item recibido: " + id_item);

		no_parte.setText(getText(R.string.str_desglose_cantidad_txv_desglose_cantidad2)
				+ " " + item_valor);
		loadSpinnerPaises();

		desglose_pantalla.removeAll(desglose_pantalla);
		desgloses_guardado.removeAll(desgloses_guardado);

		lista_Desgloses();

		item = new Item();
		buss_revision = new Buss_Frag_RevisionItem();

		item = buss_revision.obtiene_item(id_item, appContext);

		sincronizado = buss_revision.obtiene_estatus_item(id_item, appContext);

	}

	/**
	 * Crea el diálogo personalizado
	 * 
	 * @param tag
	 *            Identificador del diálogo
	 * @param titulo
	 *            Titulo del diálogo
	 * @param mensaje
	 *            Mensaje en el diálogo
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
	 * Carga el elemento spinner de la vista asociado al catálogo de países
	 */
	private void loadSpinnerPaises() {

		Buss_Frag_DesgloseCantidad bus_desglose = new Buss_Frag_DesgloseCantidad();

		valores = bus_desglose.obtiene_paises(appContext);

		ArrayAdapter<Pais> spinArrayAdapter = new ArrayAdapter<Pais>(
				appContext, android.R.layout.simple_spinner_item, valores);

		spinArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		pais.setAdapter(spinArrayAdapter);
		pais.setOnItemSelectedListener(this);

	}

	/**
	 * Método asociado a la selección de elemento del spinner de países
	 */
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		long alimentoId = 0;

		if (parent.getId() == R.id.spn_paises) {
			alimentoId = ((Pais) parent.getItemAtPosition(position))
					.getIdPais();
		}

	}

	/**
	 * Método que inicia lógica de agregar Desgloses en pantalla
	 * 
	 * @param button
	 */
	public void addPais(View button) {

		if ((cantidad.getText() != null && !cantidad.getText().toString()
				.equals(""))
				&& (descripcion.getText() != null && !descripcion.getText()
						.toString().equals(""))
				&& pais.getSelectedItem() != null) {
			
			if(Integer.parseInt(cantidad.getText().toString()) > 0){
				Desglose desglose = new Desglose();

				contador_desglose = contador_desglose + 1;
				
				Log.v("Contador desglose", "contador_desglose "+ contador_desglose);
				Log.v("Contador desglose", "desgloses_guardado.size() "+ desgloses_guardado.size());

				desglose.setCantidad(Integer
						.parseInt(cantidad.getText().toString()));
				desglose.setDescripcion(descripcion.getText().toString());
				desglose.setIdPais(((Pais) pais.getSelectedItem()).getIdPais());
				desglose.setIdItem(id_item);
				desglose.setIdDesglose(contador_desglose);

				Log.v("Frag_DesgloseCantidad",
						"Tamanio arreglo nuevos desgloses antes: "
								+ desglose_pantalla.size());
				if (desglose_pantalla.size() == 0 && desglose_bd.size() == 0)
					add_desglose(desglose);

				else {

					boolean flag = false;

					for (int i = 0; i < desglose_pantalla.size(); i++) {
						if (desglose_pantalla.get(i).getCantidad() == desglose
								.getCantidad()
								&& desglose_pantalla.get(i).getDescripcion()
										.equals(desglose.getDescripcion())
								&& desglose_pantalla.get(i).getIdPais() == desglose
										.getIdPais()) {
							flag = true;
							break;

						}
					}
//////////Agregado
					for (int j = 0; j < desglose_bd.size(); j++) {
						if (desglose_bd.get(j).getCantidad() == desglose
								.getCantidad()
								&& desglose_bd.get(j).getDescripcion()
										.equals(desglose.getDescripcion())
								&& desglose_bd.get(j).getIdPais() == desglose
										.getIdPais()) {
							flag = true;
							break;

						}
					}
////////					
					if (flag)
						Toast.makeText(
								appContext,
								getText(R.string.str_desgloses_toast_desgloseRepetido),
								Toast.LENGTH_LONG).show();
					else {
						add_desglose(desglose);
					}

				}
				Log.v("Frag_DesgloseCantidad",
						"Contador total desgloses bd + nuevos = "
								+ contador_desglose);
				Log.v("Frag_DesgloseCantidad",
						"Tamanio arreglo nuevos desgloses después: "
								+ desglose_pantalla.size() + "");
			}
			else{
				Toast.makeText(appContext,
						getText(R.string.str_desgloses_toast_validaCantidad),
						Toast.LENGTH_LONG).show();
			}
		} else
			Toast.makeText(appContext,
					getText(R.string.str_desgloses_toast_faltanDatos),
					Toast.LENGTH_LONG).show();
		Log.v("Frag_DesgloseCantidad",
				getText(R.string.str_desgloses_toast_faltanDatos).toString());
	}

	/**
	 * Método que agrega desglose a la vista y a lista de desgloses a insertar
	 * en la base de datos
	 * 
	 * @param desglose
	 *            Desglose a agregar
	 */
	public void add_desglose(Desglose desglose) {
		String pais_name = ((Pais) pais.getSelectedItem()).getPais();

		Button btn = new Button(appContext);
		btn.setText(cantidad.getText().toString() + " "
				+ descripcion.getText().toString() + " " + pais_name);

		btn.setId((int) desglose.getIdDesglose());

		layout = (LinearLayout) getView().findViewById(R.id.layout_desglose);

		layout.addView(btn);

		desglose_pantalla.add(desglose);
		desgloses_guardado.add(desglose);
		
		Log.v("Agrega desglose","btn.getId()"+btn.getId());
		
		descripcion.setText("");
		cantidad.setText("");

		btn.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(final View v) {

				layout.post(new Runnable() {
					@Override
					public void run() {
						Log.v("Elimina desglose","btn.getId()"+v.getId());
						String tag = "UnoPantalla";
						String mensaje = appContext.getResources().getText(R.string.str_desgloses_dialog_msg_eliminacion1).toString();
						String titulo = appContext.getResources().getText(R.string.str_desgloses_dialog_titulo_eliminacion).toString();
						vista = v;
						dialogo(tag, titulo, mensaje);

					}
				});

				return false;
			}
		});
	}

	/**
	 * Elimina todos los desgloses nuevos en la pantalla
	 * 
	 * @param v
	 *            Botón en la vista que representa un Desglose
	 */
	public void removeViewPantalla(View v) {
		Log.v("RemovePantalla","RemovePantalla");
		Log.v("RemovePantalla","v.getId() "+ v.getId());
		boolean flag = false;
		
		Log.v("Frag_DesgloseCantidad",
				"Tamanio arreglo desgloses nuevos a eliminar de pantalla"
						+ desglose_pantalla.size());
		boolean bandera = true;
		for (int i = 0; i < desgloses_guardado.size(); i++) {
			Log.v("Frag_DesgloseCantidad",
					"i " + i);
			//if (desglose_pantalla.get(i).getIdDesglose() == v.getId()) {
			if (desgloses_guardado.get(i).getIdDesglose() == v.getId()) {
				Log.v("RemovePantalla","Entre if");
				//desgloses_guardado.remove(i);
				for(int k = 0; k<desglose_pantalla.size();k++){
					if(desglose_pantalla.get(k).getIdDesglose() == v.getId()){
						desglose_pantalla.remove(k);	
					}						
				}
				layout.removeView(v);
				bandera = false;
				break;				
			}
				
		}
		if(bandera){
			removeViewBase(v);
		}
		obtiene_desglose();
		/*for(int j = 0; j <= desgloses_guardado.size(); j++){
			if(j == v.getId()){
				Log.v("RemovePantalla","Entre segundo if");				
				removeViewBase(v);
				
			}
		}*/
		
		
		Log.v("Frag_DesgloseCantidad",
				"Contador desgloses total (bd + nuevos) después de eliminación: "
						+ contador_desglose);
		Log.v("Frag_DesgloseCantidad",
				"Tamanio arreglo nuevos desgloses después de eliminación: "
						+ desglose_pantalla.size());

	}

	/**
	 * Elimina todos los desgloses de la base de datos mostrados en la pantalla
	 * 
	 * @param v
	 *            Botón en la vista que representa un Desglose
	 */
	public void removeViewBase(View v) {
		Buss_Frag_DesgloseCantidad bus_desglose = new Buss_Frag_DesgloseCantidad();

		Log.v("tamanio remove","Antes desglose_bd.size() "+desglose_bd.size());
		Log.v("tamanio remove","Antes desgloses_guardado.size() "+desgloses_guardado.size());
		Log.v("tamanio remove","v.getId() "+v.getId());
		
		//for (int i = 0; i < desglose_bd.size(); i++) {
		for (int i = 0; i < desgloses_guardado.size(); i++) {
			Log.v("For","i "+ i);
			//if (desglose_bd.get(i).getIdDesglose() == v.getId()) {
			if (i == v.getId()-1) {
				//desglose_eliminado = bus_desglose.elimina_desglose(v.getId(),
					//	appContext);
				//Log.v("tamanio remove","Antes desglose_bd.size() "+desglose_bd.get(i).getIdDesglose());
				//desglose_eliminado = bus_desglose.elimina_desglose(desglose_bd.get(i).getIdDesglose(),
				Log.v("tamanio remove","desgloses_guardado.get(i).getIdDesglose() "+desgloses_guardado.get(i).getIdDesglose());
				desglose_eliminado = bus_desglose.elimina_desglose(desgloses_guardado.get(i).getIdDesglose(),
							appContext);

				if (desglose_eliminado > 0) {
					//desglose_bd.remove(i);

					layout.removeView(v);

					Toast.makeText(
							appContext,
							appContext.getResources().getText(R.string.str_desgloses_toast_desgloseEliminado),
							Toast.LENGTH_LONG).show();
					Log.v("Frag_DesgloseCantidad",
							appContext.getResources().getText(R.string.str_desgloses_toast_desgloseEliminado)
									+ " Id Desglose: " + v.getId());
				} else{
					Toast.makeText(
							appContext,
							appContext.getResources().getText(R.string.str_desgloses_toast_errorEliminarDesglose),
							Toast.LENGTH_LONG).show();
				Log.v("Frag_DesgloseCantidad",
						appContext.getResources().getText(R.string.str_desgloses_toast_errorEliminarDesglose)
								+ " Id Desglose: " + v.getId());
				}
				Log.v("Frag_DesgloseCantidad",
						"Desgloses de basede datos después de eliminación: "
								+ desglose_bd.size());
				break;
			}
		}
	}

	/**
	 * Método para la no selección de un elemento del spinner de paises
	 */
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * Muestra en la vista los desgloses del item en la base de datos
	 */
	public void lista_Desgloses() {

		Buss_Frag_DesgloseCantidad bus_desglose = new Buss_Frag_DesgloseCantidad();
/*
		desglose_bd = bus_desglose.obtinene_degloses(id_item, appContext);
		Log.v("Frag_DesgloseCantidad", "Desgloses en la base de datos Id item "
				+ id_item + ": " + desglose_bd.size());*/

		obtiene_desglose();
		
		if (desglose_bd.size() != 0) {
			int cantidad;
			String descripcion2;
			long pais_id;
			String pais = "";

			contador_desglose = desglose_bd.size();

			for (int i = 0; i < desglose_bd.size(); i++) {
				cantidad = desglose_bd.get(i).getCantidad();
				descripcion2 = desglose_bd.get(i).getDescripcion();
				pais_id = desglose_bd.get(i).getIdPais();

				pais = bus_desglose.getPais(pais_id, appContext);

				Button btn = new Button(appContext);
				btn.setText(cantidad + " " + descripcion2 + " " + pais);

				//btn.setId((int) desglose_bd.get(i).getIdDesglose());
				btn.setId(i+1);

				layout.addView(btn);
//////							
				desgloses_guardado.add(desglose_bd.get(i));

				btn.setOnLongClickListener(new View.OnLongClickListener() {

					@Override
					public boolean onLongClick(final View v) {
						layout.post(new Runnable() {
							@Override
							public void run() {

								if (sincronizado != Constantes.ESTATUS_SINCRONIZADO) {

									String tag = "UnoBase";
									String mensaje = getText(
											R.string.str_desgloses_dialog_msg_eliminacion1)
											.toString();
									String titulo = getText(
											R.string.str_desgloses_dialog_titulo_eliminacion)
											.toString();

									vista = v;
									
									Log.v("vista", "vista.getId() " + vista.getId());

									dialogo(tag, titulo, mensaje);

								} else {
									Toast.makeText(
											appContext,
											getText(R.string.str_desgloses_toast_itemSincronizadoElimina1),
											Toast.LENGTH_LONG).show();
									Log.v("Frag_DesgloseCantidad",
											getText(
													R.string.str_desgloses_toast_itemSincronizadoElimina1)
													.toString());
								}

							}
						});

						return false;
					}
				});

			}
		}

	}

	/**
	 * Realiza la inserción en la base de datos de los nuevos desgloses y
	 * retorna a la vista de la revisión del item
	 */
	public void guarda_desgloses() {
		Buss_Frag_DesgloseCantidad buss_desglose = new Buss_Frag_DesgloseCantidad();

		long [] desgloses_guardados;
		Log.v("Frag_DesgloseCantidad", "Cantidad de desgloses a guardar: "
				+ desglose_pantalla.size());
		if (desglose_pantalla.size() != 0) {

			desgloses_guardados = buss_desglose.crea_desgloses(id_item,
					desglose_pantalla, appContext);

			Toast.makeText(
					appContext,
					desgloses_guardados.length + " "
							+ getText(
									R.string.str_desgloses_toast_desglosesGuardados)
									.toString(), Toast.LENGTH_LONG).show();
			Log.v("Frag_DesgloseCantidad",
					desgloses_guardados.length + " "
							+ getText(
									R.string.str_desgloses_toast_desglosesGuardados)
									.toString() + " Id Item: " + id_item);

			valida_estatus();
			
			for(int i = 0; i<desglose_pantalla.size(); i++){
				for(int j = 0 ; j<desgloses_guardado.size();j++){
					if(desglose_pantalla.get(i).getIdDesglose() == desgloses_guardado.get(j).getIdDesglose()){
						desgloses_guardado.get(j).setIdDesglose(desgloses_guardados[i]);
					}
				}
						
			}

			desglose_pantalla.removeAll(desglose_pantalla);
			
			obtiene_desglose();

		} else {
			Toast.makeText(appContext,
					getText(R.string.str_desgloses_toast_noHayNuevos),
					Toast.LENGTH_LONG).show();
		}

	}

	/**
	 * Realiza la cambios en el estatus del ítem, la factura y la referencia
	 * asociadas
	 */
	public void valida_estatus() {
		boolean revisado = false;
		revisado = buss_revision.terminar_revision(id_item, appContext);

		if (revisado == true) {
			buss_revision.actualiza_estatus_item(id_item,
					Constantes.ESTATUS_REVISADO, appContext);

			buss_revision.valida_factura(id_fact, appContext);

			Toast.makeText(appContext,
					appContext.getResources().getText(R.string.str_revisionItem_toast_itemRevisado),
					Toast.LENGTH_LONG).show();
			Log.v("Frag_DesgloseCantidad",
					appContext.getResources().getText(R.string.str_revisionItem_toast_itemRevisado)
							.toString() + "Id Item: " + id_item);
		} else {
			buss_revision.actualiza_estatus_item(id_item,
					Constantes.ESTATUS_EN_REVISION, appContext);
			buss_revision.valida_factura(item.getIdFactura(), appContext);
		}
	}
	
	public void obtiene_desglose(){
		Buss_Frag_DesgloseCantidad bus_desglose = new Buss_Frag_DesgloseCantidad();

		desglose_bd = bus_desglose.obtinene_degloses(id_item, appContext);
		Log.v("Frag_DesgloseCantidad", "Desgloses en la base de datos Id item "
				+ id_item + ": " + desglose_bd.size());
	}

	/**
	 * Limpia la pantalla, elimina de la vista y de la base los desgloses
	 */
	public void limpia_pantalla() {
		Buss_Frag_DesgloseCantidad buss_desglose = new Buss_Frag_DesgloseCantidad();
		int desg_elim = 0;

		descripcion.setText("");
		cantidad.setText("");
		Log.v("Frag_DesgloseCantidad",
				"Desgloses a eliminar: " + desglose_bd.size());
		
		/*final Buss_Frag_DesgloseCantidad bus_desglose = new Buss_Frag_DesgloseCantidad();

		desglose_bd = bus_desglose.obtinene_degloses(id_item, appContext);
		Log.v("Frag_DesgloseCantidad", "Desgloses en la base de datos Id item "
				+ id_item + ": " + desglose_bd.size());
		*/
		obtiene_desglose();
		Log.v("SIZE", "desglose_bd "+ desglose_bd.size());
		if (desglose_bd.size() != 0) {
			
			try{
			desg_elim = buss_desglose
					.elimina_desgloses(desglose_bd, appContext);
			Log.v("Frag_DesgloseCantidad", "Degloses eliminados: " + desg_elim);
			//if (desg_elim > 0) {
				desglose_bd.removeAll(desglose_bd);
				Toast.makeText(
						appContext,
						desg_elim + " "
								+ appContext.getResources().getText(
										R.string.str_desgloses_toast_desglosesEliminados)
										.toString(), Toast.LENGTH_LONG).show();
				Log.v("Frag Desglose",
						"Desgloses en la base de datos después de eliminación "
								+ desglose_bd.size());
			}
			catch(Exception e){
				Toast.makeText(
						appContext,
						appContext.getResources().getText(R.string.str_desgloses_toast_errorEliminarDesgloses),
						Toast.LENGTH_LONG).show();
				Log.v("Frag Desglose",
						appContext.getResources().getText(R.string.str_desgloses_toast_errorEliminarDesgloses)
								+ " Id Item: " + id_item+"\n"+e);				
			}

		}
		layout.removeAllViews();

		desglose_pantalla.removeAll(desglose_pantalla);
		desgloses_guardado.removeAll(desgloses_guardado);
		contador_desglose = 0;

	}

	/**
	 * Método que tiene las acciones de los botones en el diálogo
	 */
	@Override
	public void onclick(int id, String Tag) {
		// TODO Auto-generated method stub

		if (Tag.equals("Todos")) {
			switch (id) {
			case R.id.button_accept:
				editNameDialog.dismiss();
				limpia_pantalla();

				valida_estatus();

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

				break;
			case R.id.button_cancel:
				editNameDialog.dismiss();
				break;
			default:
				break;
			}
		} else {
			switch (id) {
			case R.id.button_accept:
				editNameDialog.dismiss();
				
				removeViewBase(vista);

				obtiene_desglose();
				if (desglose_bd.size() == 0)
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

}

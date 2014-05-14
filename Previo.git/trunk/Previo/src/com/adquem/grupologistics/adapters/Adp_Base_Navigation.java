package com.adquem.grupologistics.adapters;

import java.util.ArrayList;

import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.utilities.Utl_ItemMenu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Clase Adaptador entre la Vista y la Capa de Negocio para las Opciones del
 * Menú
 * 
 * @author Adquem
 * @version 1.0
 */
public class Adp_Base_Navigation extends BaseAdapter {
	private Activity activity;
	ArrayList<Utl_ItemMenu> arrayitms;

	/**
	 * Constructor de la Clase para declarar la Actividad y la Lista de Ítems  
	 * @param activity		Generada para mostrar las Opciones del Menú
	 * @param listarray		Lista de Ítems para las Opciones del Menú
	 */
	public Adp_Base_Navigation(Activity activity,
			ArrayList<Utl_ItemMenu> listarray) {
		super();
		this.activity = activity;
		this.arrayitms = listarray;
	}

	/**
	 * Regresa los Ítem's que contendrá la lista
	 */
	@Override
	public Object getItem(int position) {
		return arrayitms.get(position);
	}

	/**
	 * Regresa el número de Ítems en la Lista
	 */
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayitms.size();
	}

	/**
	 * Regresa la posición del Ítem de la Lista
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	// Declaramos clase estatica la cual representa a la fila
	/**
	 * Pone el Título e Icono a los Ítem's del Menú
	 */
	public static class Fila {
		TextView titulo_itm;
		ImageView icono;
	}

	/**
	 *  Crea la Vista que contendrá las Opciones del Menú
	 *  
	 *  @param position			Obtiene la Posición de la opción del Menú
	 *  @param convertView		Obtiene la Vista con las opciones del Menú 
	 *  @param parent			
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Fila view;
		LayoutInflater inflator = activity.getLayoutInflater();
		if (convertView == null) {
			view = new Fila();

			Utl_ItemMenu itm = arrayitms.get(position);
			convertView = inflator
					.inflate(R.layout.lyt_fragment_itemmenu, null);

			view.titulo_itm = (TextView) convertView
					.findViewById(R.id.txv_titleItem);

			view.titulo_itm.setText(itm.getTitulo());

			view.icono = (ImageView) convertView.findViewById(R.id.imgv_item);

			view.icono.setImageResource(itm.getIcono());
			convertView.setTag(view);
		} else {
			view = (Fila) convertView.getTag();
		}
		return convertView;
	}
}

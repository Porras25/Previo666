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
 * Men�
 * 
 * @author Adquem
 * @version 1.0
 */
public class Adp_Base_Navigation extends BaseAdapter {
	private Activity activity;
	ArrayList<Utl_ItemMenu> arrayitms;

	/**
	 * Constructor de la Clase para declarar la Actividad y la Lista de �tems  
	 * @param activity		Generada para mostrar las Opciones del Men�
	 * @param listarray		Lista de �tems para las Opciones del Men�
	 */
	public Adp_Base_Navigation(Activity activity,
			ArrayList<Utl_ItemMenu> listarray) {
		super();
		this.activity = activity;
		this.arrayitms = listarray;
	}

	/**
	 * Regresa los �tem's que contendr� la lista
	 */
	@Override
	public Object getItem(int position) {
		return arrayitms.get(position);
	}

	/**
	 * Regresa el n�mero de �tems en la Lista
	 */
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayitms.size();
	}

	/**
	 * Regresa la posici�n del �tem de la Lista
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	// Declaramos clase estatica la cual representa a la fila
	/**
	 * Pone el T�tulo e Icono a los �tem's del Men�
	 */
	public static class Fila {
		TextView titulo_itm;
		ImageView icono;
	}

	/**
	 *  Crea la Vista que contendr� las Opciones del Men�
	 *  
	 *  @param position			Obtiene la Posici�n de la opci�n del Men�
	 *  @param convertView		Obtiene la Vista con las opciones del Men� 
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

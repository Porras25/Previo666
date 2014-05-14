package com.adquem.grupologistics.adapters;

import com.adquem.grupologistics.controllers.Frag_Listado;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.controllers.Frag_listado_factura;
import com.adquem.grupologistics.controllers.Frag_listado_item;
import com.adquem.grupologistics.utilities.Constantes;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class Adp_StatePager_PagerReferencia extends FragmentStatePagerAdapter {

	final int PAGE_COUNT = 4;
	int Tipo;
	long Padre;
	int Estatus;
	String criterio;
	Context Contexto;

	/** Constructor of the class */
	public Adp_StatePager_PagerReferencia(FragmentManager fm, Context contexto,
			int tipo, long padre, int estatus, String criterio) {

		super(fm);
		this.Contexto = contexto;
		this.Tipo = tipo;
		this.Padre = padre;
		this.Estatus = estatus;
		this.criterio = criterio;
		
		
		Log.v("Previo app", "en adpstatepager tipo es" + Tipo);
		Log.v("Previo app", "en adpstatepager padre es" + Padre);
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int position) {
		Bundle bundle = new Bundle();
		Fragment retorna = null;
		int estado = Constantes.ESTATUS_TODOS;
		switch(position){	
			case 0:
				estado = Constantes.ESTATUS_TODOS;
				break;
			case 1:
				estado = Constantes.ESTATUS_REVISADO;
				break;
			case 2:
				estado = Constantes.ESTATUS_EN_REVISION;
				break;
			case 3:
				estado = Constantes.ESTATUS_SIN_REVISAR;
				break;
		}
		
		switch (Tipo) {
		case Constantes.TIPOlISTADO_REFERENCIA:
			//Frag_Listados lista = new Frag_Listados();
			Frag_Listado lista = new Frag_Listado();
			bundle.putInt("TipoListado", Tipo);
			bundle.putLong("Padre", Padre);
			bundle.putInt("Estatus", estado);
			bundle.putString("Criterio", criterio);
			lista.setArguments(bundle);
			retorna = lista;
			break;
		case Constantes.TIPOlISTADO_FACTURA:
			Frag_listado_factura listafac = new Frag_listado_factura();
			bundle.putInt("TipoListado", Tipo);
			bundle.putLong("Padre", Padre);
			bundle.putInt("Estatus", estado);
			bundle.putString("Criterio", criterio);
			listafac.setArguments(bundle);
			retorna = listafac;
			break;
		case Constantes.TIPOlISTADO_ITEM:
			Frag_listado_item listaItem = new Frag_listado_item();
			bundle.putInt("TipoListado", Tipo);
			bundle.putLong("Padre", Padre);
			bundle.putInt("Estatus", estado);
			bundle.putString("Criterio", criterio);
			listaItem.setArguments(bundle);
			retorna = listaItem;
			break;
		default:
			break;
		}

		return retorna;

	}

	/** Returns the number of pages */
	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		CharSequence Titulo = null;
		switch (position) {
		case 0:
			switch (Tipo) {
			case 1:
				Titulo = Contexto.getString(R.string.str_Listados_tittle_Referencias);
				break;
			case 2:
				Titulo = Contexto.getString(R.string.str_Listados_tittle_Facturas);
				break;
			case 3:
				Titulo = Contexto.getString(R.string.str_Listados_tittle_Item);
				break;
			default:
				break;
			}
			break;
		case 1:
			Titulo = Contexto.getString(R.string.str_Listados_tab_Revisado);
			break;
		case 2:
			Titulo = Contexto.getString(R.string.str_Listados_tab_EnRevision);
			break;
		case 3:
			Titulo = Contexto.getString(R.string.str_Listados_tab_SinRevision);
			break;
		}			

		return Titulo;
	}
	

}

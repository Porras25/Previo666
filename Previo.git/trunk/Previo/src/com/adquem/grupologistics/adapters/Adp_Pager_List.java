/**
 * 
 */
package com.adquem.grupologistics.adapters;

import com.adquem.grupologistics.controllers.Frag_Listado;
import com.adquem.grupologistics.utilities.Constantes;

import com.adquem.grupologistics.controllers.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * @author Usuario
 *
 */
public class Adp_Pager_List extends FragmentStatePagerAdapter{
	
	final int PAGE_COUNT = 4;
	final int PAGE_COUNT_SEARCH = 1;
	int estatus;
	int tipoListado;
	long padre;
	String criterio;
     Context Contexto;
	/** Constructor of the class */
	public Adp_Pager_List(FragmentManager fm, Context contexto,int estatus,int tipo, long padre, String criterio) {
	
		super(fm);
		this.Contexto= contexto;
		this.estatus=estatus;
		this.tipoListado=tipo;
		this.padre = padre;
		this.criterio = criterio;
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int position) {
		 Frag_Listado lista = new Frag_Listado();
		 Bundle bundle = new Bundle();
		 
		 bundle.putInt("TipoListado", tipoListado);
		 bundle.putInt("CurrentPage", position);
		 bundle.putLong("Padre", padre);
		 bundle.putString("Criterio", criterio);
		 
		 if(estatus == Constantes.BUSQUEDA){
			 bundle.putInt("Estatus", estatus);
			 Log.v("PrevioApp","Sigo siendo busqueda.." + estatus);
		 }
		 else{
			 switch(position){	
				case 0:
					bundle.putInt("Estatus",Constantes.ESTATUS_TODOS );
					break;
				case 1:
					bundle.putInt("Estatus",Constantes.ESTATUS_REVISADO );
					break;
				case 2:
					bundle.putInt("Estatus",Constantes.ESTATUS_EN_REVISION );
					break;
				case 3:
					bundle.putInt("Estatus",Constantes.ESTATUS_SIN_REVISAR );
					break;
			}
		 }
		 lista.setArguments(bundle);
		return lista;
	}

	/** Returns the number of pages */
	@Override
	public int getCount() {		
		if(estatus == Constantes.BUSQUEDA)
			return PAGE_COUNT_SEARCH;
		else
			return PAGE_COUNT;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		CharSequence Titulo = null;
		if(estatus == Constantes.BUSQUEDA){
			Titulo = Contexto.getString(R.string.str_Listados_tittle_Busqueda);
		}
		else{
			switch(position){	
			case 0:
				switch(tipoListado){
					case Constantes.TIPOlISTADO_REFERENCIA:
						Titulo=Contexto.getString(R.string.str_Listados_tittle_Referencias);
						break;
					case Constantes.TIPOlISTADO_FACTURA:
						Titulo=Contexto.getString(R.string.str_Listados_tittle_Facturas);
						break;
					case Constantes.TIPOlISTADO_ITEM:
						Titulo=Contexto.getString(R.string.str_Listados_tittle_Item);
						break;
					default:
						Titulo=Contexto.getString(R.string.str_Listados_tittle_Referencias);
						break;
				}
				break;
			case 1:
				Titulo=Contexto.getString(R.string.str_Listados_tab_Revisado);
				break;
			case 2:
				Titulo=Contexto.getString(R.string.str_Listados_tab_EnRevision);
				break;
			case 3:
				Titulo=Contexto.getString(R.string.str_Listados_tab_SinRevision);
				break;
			}
		}
		return Titulo;
	}
	
}

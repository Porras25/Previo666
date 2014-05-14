/**
 * 
 */
package com.adquem.grupologistics.controllers;

import com.adquem.grupologistics.adapters.Adp_Pager_List;
import com.adquem.grupologistics.utilities.Constantes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Usuario
 *
 */
public class Frag_Elemento extends Fragment {

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		SharedPreferences prefs = this.getActivity().getSharedPreferences("PreferenciasPrevioApp",Context.MODE_PRIVATE);	 
		this.tipoListado = prefs.getInt("TipoListado", Constantes.TIPOlISTADO_REFERENCIA);
		this.estatus = prefs.getInt("Estatus", Constantes.ESTATUS_TODOS);
		//this.padre = prefs.getLong("Padre", 0);
		Log.v("STACK", "Start fraglistado Item");
	}

	static Context Contexto;
    ViewPager pager;
    private int tipoListado;
    private int estatus;
    private long padre;
    private String criterio;

    public static Fragment newInstance(Context context) {
        Contexto=context;
        return new Frag_Elemento();
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
     
    	Bundle args = getArguments();
		this.tipoListado = args.getInt("TipoListado");
		this.estatus = args.getInt("Estatus");
		this.padre = args.getLong("Padre");
		this.criterio = args.getString("Criterio");
     
		pager = (ViewPager) getView().findViewById(R.id.vpag_referencia);
        pager.setOffscreenPageLimit(4);
		/** Getting fragment manager */
		final FragmentManager fm = getFragmentManager();

		/** Instantiating FragmentPagerAdapter */
		Adp_Pager_List pagerAdapter = new Adp_Pager_List(fm, getActivity().getBaseContext(),
				estatus, tipoListado, padre, criterio);	

		/** Setting the pagerAdapter to the pager object */
		pager.setAdapter(pagerAdapter);
		setRetainInstance(true);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return (ViewGroup) inflater.inflate(R.layout.lyt_fragment_referencia, null);
    }
    
}

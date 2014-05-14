package com.adquem.grupologistics.controllers;

import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.utilities.Constantes;
import com.adquem.grupologistics.adapters.Adp_Pager_List;
import com.adquem.grupologistics.adapters.Adp_StatePager_PagerReferencia;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Frag_Item extends Fragment {
	private static final int TIPO_LISTADO_FACTURAS = 2;
	private static final int TIPO_LISTADO_ITEMS = 3;
	private static final int TIPO_LISTADO_TODAS = 0;
	static Context Contexto;

	ViewPager pager;
	
	
	private int tipoListado;
    private int estatus;
    private long padre;
    private String criterio;

	public static Fragment newInstance(Context context) {
		Frag_Referencia f = new Frag_Referencia();
		Contexto = context;
		return f;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
		Bundle args = getArguments();
		this.tipoListado = args.getInt("TipoListado");
		this.estatus = args.getInt("Estatus");
		this.padre = args.getLong("Padre");
		this.criterio = args.getString("Criterio");

		Log.v("Previo App", "En fragment item el padre es" + padre);
		
		pager = (ViewPager) getView().findViewById(R.id.vpag_referencia);

		/** Getting fragment manager */
		final FragmentManager fm = getFragmentManager();

		/** Instantiating FragmentPagerAdapter */
		Adp_StatePager_PagerReferencia pagerAdapter = new Adp_StatePager_PagerReferencia(fm, getActivity()
				.getBaseContext(), Constantes.TIPOlISTADO_ITEM, padre, Constantes.ESTATUS_TODOS, criterio);
		
//		Adp_Pager_List pagerAdapter = new Adp_Pager_List(fm, getActivity().getBaseContext(),
//				estatus, tipoListado, padre, criterio);	
		
		/** Setting the pagerAdapter to the pager object */
		pager.setAdapter(pagerAdapter);
		setRetainInstance(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.lyt_fragment_referencia, null);

		return root;
	}

}

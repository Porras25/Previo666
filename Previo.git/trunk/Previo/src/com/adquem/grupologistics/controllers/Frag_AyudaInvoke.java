package com.adquem.grupologistics.controllers;



//import android.app.Fragment;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.adapters.Adp_Page_Ayuda;
import com.adquem.grupologistics.model.Referencia;
import com.adquem.grupologistics.utilities.Utl_ZoomOutPageTransformer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Frag_AyudaInvoke extends Fragment {
	
	private static final int SLIDE1 = 0;
	private static final int SLIDE2 = 1;
	private static final int SLIDE3 = 2;
	private static final int SLIDE4 = 3;

	static Context Contexto;
    private Bundle savedState = null;
    ViewPager pager;

    public static Fragment newInstance(Context context) {
        Frag_Ayuda f = new Frag_Ayuda();
        Contexto=context;
        return f;
    }
    
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lyt_fragment_viewpager_ayuda, null);
		 pager = (ViewPager) root.findViewById(R.id.pager);

		/** Getting fragment manager */
		final FragmentManager fm = getFragmentManager();

		/** Instantiating FragmentPagerAdapter */
	
		Adp_Page_Ayuda pagerAdapter = new Adp_Page_Ayuda(fm, getActivity().getBaseContext(),
				SLIDE1, SLIDE2);

		/** Setting the pagerAdapter to the pager object */
		pager.setAdapter(pagerAdapter);
		pager.setPageTransformer(true, new Utl_ZoomOutPageTransformer());
        return root;
    }
	}


package com.adquem.grupologistics.adapters;

import com.adquem.grupologistics.controllers.Frag_Ayuda;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Adp_Page_Ayuda extends FragmentPagerAdapter{
	
	final int PAGE_COUNT = 8;
	int Tipo;
	int Padre;
     Context Contexto;
	/** Constructor of the class */
	public Adp_Page_Ayuda(FragmentManager fm, Context contexto,int tipo,int padre) {
	
		super(fm);
		this.Contexto= contexto;
		this.Tipo=tipo;
		this.Padre=padre;
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int arg0) {
		 Frag_Ayuda ayuda= new Frag_Ayuda();
		 Bundle bundle = new Bundle();
		 
		 bundle.putInt("TipoListado",Tipo );
		 bundle.putInt("Padre", Padre);
		 bundle.putInt("CurrentPage", arg0);
		
		 
		 ayuda.setArguments(bundle);
		 
		return ayuda;
	}

	/** Returns the number of pages */
	@Override
	public int getCount() {		
		return PAGE_COUNT;
	}
	
	
}

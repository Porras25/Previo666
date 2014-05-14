package com.adquem.grupologistics.controllers;

import com.adquem.grupologistics.controllers.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Frag_Ayuda  extends Fragment{
	ImageView total;
	int position;
	public static Fragment newInstance(Context context) {
		Frag_Ayuda f = new Frag_Ayuda();
	
     return f;
	 } 
     
     
     @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lyt_fragment_ayuda, null);
	        total = (ImageView) root.findViewById(R.id.imgv_ayuda);	
	        Bundle args = getArguments();
			this.position = args.getInt("CurrentPage");
	        switch(position){	
			case 0:
				//Cambiar imagen 
				total.setImageResource(R.drawable.amenu);
				break;
			case 1:
				//Cambiar imagen 
				total.setImageResource(R.drawable.bbusquedas);
				break;
			case 2:
				//Cambiar imagen 
				total.setImageResource(R.drawable.cfiltrado);
				break;
			case 3:
				//Cambiar imagen 
				total.setImageResource(R.drawable.dreferencia);
				break;
			case 4:
				//Cambiar imagen 
				total.setImageResource(R.drawable.efacturas);
				break;
			case 5:
				//Cambiar imagen 
				total.setImageResource(R.drawable.fitem);
				break;
			case 6:
				//Cambiar imagen 
				total.setImageResource(R.drawable.gadjuntos);
				break;
			case 7:
				//Cambiar imagen 
				total.setImageResource(R.drawable.hcb);
				break;
			}
	        
		return root;
	    }
}

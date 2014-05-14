package com.adquem.grupologistics.utilities;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.R.integer;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
//...
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public  class Utl_CustomDialog extends DialogFragment implements OnClickListener{

 
	public static final int CONSTANTE_BOTON = 0;
	public static final int CONSTANTE_TEXTVIEW=1;
	private EditText mEditText;
	public static  View v;
	int layout;

	
	ClassLoader clase;
Class prueba;
Dialogs variable;
public HashSet<Button> botonesHash;

Map<Boton,String> m;
String className;
HashSet textos_botones;
static HashSet botones;
static HashSet textviews;
static HashSet images;
static String tag;


public static Utl_CustomDialog newInstance() {
    Utl_CustomDialog d = new Utl_CustomDialog();
    botones = new HashSet();
    textviews = new HashSet();
    images = new HashSet();
    return d;
}


 public Utl_CustomDialog() {
     // Empty constructor required for DialogFragment

botones = new HashSet();
textviews = new HashSet();
images = new HashSet();



 }
 
 void insertaimagen(int id,int Resource){
	 
	 Imagens img = new Imagens();
		img.id=id;
		img.resource=Resource;
		images.add(img); 
	 
 }
 void insertBoton(int id,String text){
	Boton boti = new Boton();
	boti.id=id;
	boti.texto=text;
	botones.add(boti);
	
	 
 }
 void setlayout(int layout){
	 this.layout=layout;
	 
 }
 
 void insertaTextView(int id, String text){
	 TextViews tv = new TextViews();
		tv.id=id;
		tv.texto=text;
		textviews.add(tv);
	 
	 
 }
 void setClassname(String classname){
	 this.className=classname;
	 
	 
 }
public static void setV(View v) {
	Utl_CustomDialog.v = v;
}
@Override
public void onDestroyView() {
  if (getDialog() != null && getRetainInstance())
    getDialog().setDismissMessage(null);
  super.onDestroyView();
}
@Override
public void onPause() {
    if (getDialog() != null )
        getDialog() .dismiss();
    super.onPause();
}
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {
    
	 	layout = getArguments().getInt("layout");
	 	tag=getArguments().getString("Tag");
	 	
	 	className = getArguments().getString("nombre_clase");
	 	String valores = getArguments().getString("elementos");
	 	String[] elementos = valores.split(",");
	 	for(int t=0; t<elementos.length;t++){
	 		
	 		String todos = getArguments().getString(elementos[t]);
	 		if(todos!=null){
	 			String []componentes = todos.split(",");
	 				if(componentes[0].equals("button")){
	 					
	 					Boton boti = new Boton();
	 					boti.id=Integer.parseInt(componentes[1]);
	 					boti.texto=componentes[2];
	 					boti.tag=tag;
	 					botones.add(boti);
	 					
	 				}
	 				else if(componentes[0].equals("textview")){
	 					 TextViews tv = new TextViews();
	 					tv.id=Integer.parseInt(componentes[1]);
	 					tv.texto=componentes[2];
	 					textviews.add(tv);
	 				}
	 				else if(componentes[0].equals("image")){
	 					
	 					 Imagens img = new Imagens();
	 					img.id=Integer.parseInt(componentes[1]);
	 					img.resource=Integer.parseInt(componentes[2]);
	 					images.add(img); 
	 					
	 				}
	 			
	 		}
	 		
	 		
	 		
	 	}
	 	
//	 	
	 String par = getArguments().getString("string");
	 	//Class<?> clazz 
	 	try {
			Object object= Class.forName(className).getConstructor().newInstance();
		 variable = (Dialogs) object;
			
		
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	//Constructor<?> ctor = clazz.getConstructor(String.class);
	 	//Object object = ctor.newInstance(new Object[] {  });
	 
	 
	 
	 	View view = inflater.inflate(layout, container);
	 setCancelable(false);

    
     
  

    // String valor = getArguments().getString("elementos");
    // String[] numerosComoArray = valor.split(",");
     //int tamanio=numerosComoArray.length;
     //for(int elements=0;elements<tamanio;elements++){	
   
    //		botones.add(getArguments().getInt(numerosComoArray[elements]));

    //	System.out.println();
    	
    	 
   //  }
     
    getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    

	return view;  
 }
 
 
 
 
@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);

//		Iterator it = botones.iterator();
//		
//		
//        
//        while(it.hasNext()) {
//              //  System.out.println("los valores de los id's" + it.next() +"");
//               // Log.i("valores hash ", it.next()+"");
//                int valor = ((Integer) it.next()).intValue();
//                Log.i("valor ", valor+"");
//                Button name = (Button)getView().findViewById(valor);
//                name.setOnClickListener(this);
//        	
//        }
		Iterator botones = this.botones.iterator();
		
		
        
        while(botones.hasNext()) {
          
        
                Boton value = (Boton) botones.next();
                Button bot = (Button)getView().findViewById(value.id);
                bot.setText(value.texto);
                bot.setTag(value.tag);
                bot.setOnClickListener(this);
        }
        
        
       
        Iterator textviews = this.textviews.iterator();
        while(textviews.hasNext()) {
          
        
                TextViews value = (TextViews) textviews.next();
                TextView tv = (TextView)getView().findViewById(value.id);
                tv.setText(value.texto);
                //bot.setOnClickListener(this);
        }
        
        Iterator imgs = this.images.iterator();
        while(imgs.hasNext()) {
          
        
                Imagens value = (Imagens) imgs.next();
                ImageView Imv = (ImageView)getView().findViewById(value.id);
                Imv.setImageResource(value.resource);
   
        }
        
        
        	

	      
		
	}


@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	variable.onclick(v.getId(),v.getTag().toString());
}
	class Boton{
		int id;
		String texto;	
		String tag;
		}
	class TextViews{
		int id;
		String texto;
	}
	public void checabotones() {
		// TODO Auto-generated method stub
		Log.e("tama��o del map", m.keySet()+"");
	Log.e("tama��o del map", m.size()+"");	
	}

}
class Boton{
	int id;
	String texto;
	
}
class TextViews{
	int id;
	String texto;
	
}
class Imagens{
	int id;
	int resource;
	
}
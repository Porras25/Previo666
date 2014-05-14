package com.adquem.grupologistics.adapters;

//import com.adquem.grupologistics.model.CatNormas;
import com.adquem.grupologistics.model.Pais;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpinerAdapter extends ArrayAdapter{

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private Object[] values;
    //private List values;

	public SpinerAdapter(Context context, int resource, Object[] objects) {
		//public SpinerAdapter(Context context, int resource, List objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
        this.context = context;
        this.values = objects;
        
		for(int i=0;i<objects.length;i++)
		Log.v("AdapterConstructor", ((Pais)values[i]).getPais()+"");
        
	}
	
    public int getCount(){
       return values.length;
    	//return values.size();
    }

    public Object getItem(int position){
       return values[position];
    	//return values.get(position);
    }

    public long getItemId(int position){
       return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
    	TextView label = new TextView(context);
    	Log.v("Adapter", "Posicion "+position);
    	
        /*if(values[position].getClass().getName() == UnidadMedida.class.getName()){
    	//if(values.get(position).getClass().getName() == UnidadMedida.class.getName()){
        	label.setText( ((UnidadMedida)values[position]).getUnidad());
    		//label.setText( ((UnidadMedida)values.get(position)).getUnidad());
        }*/
/*
    	else if(values.get(position).getClass().getName() == CatNormas.class.getName()){
        	//label.setText( ((UnidadMedida)values[position]).getUnidad());
    		label.setText( ((CatNormas)values.get(position)).getNombre());
        }*/
    	
    	//else if(values.get(position).getClass().getName() == Pais.class.getName()){
    	if(values[position].getClass().getName() == Pais.class.getName()){
        	label.setText( ((Pais)values[position]).getPais());
    		//label.setText( ((Pais)values.get(position)).getPais());
    		label.setText( ((Pais)values[position]).getPais());
    		for(int i=0;i<values.length;i++)
    		Log.v("Adapter", ((Pais)values[i]).getPais()+"");
        }
    	/*
    	else if(values.get(position).getClass().getName() == CatNormas.class.getName()){
        	//label.setText( ((UnidadMedida)values[position]).getUnidad());
    		label.setText( ((CatNormas)values.get(position)).getNombre());
        }*/
        //label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
            ViewGroup parent) {
        TextView label = new TextView(context);
        //label.setTextColor(Color.BLACK);
      //if(values[position].getClass().getName() == UnidadMedida.class.getName()){
    	/*if(values.get(position).getClass().getName() == UnidadMedida.class.getName()){
        	//label.setText( ((UnidadMedida)values[position]).getUnidad());
    		label.setText( ((UnidadMedida)values.get(position)).getUnidad());
        }

    	else if(values.get(position).getClass().getName() == CatNormas.class.getName()){
        	//label.setText( ((UnidadMedida)values[position]).getUnidad());
    		label.setText( ((CatNormas)values.get(position)).getNombre());
        }
    	
    	if(values.get(position).getClass().getName() == Pais.class.getName()){
        	//label.setText( ((UnidadMedida)values[position]).getUnidad());
    		label.setText( ((Pais)values.get(position)).getPais());
        }*/
    	if(values[position].getClass().getName() == Pais.class.getName()){
        	label.setText( ((Pais)values[position]).getPais());
    		//label.setText( ((Pais)values.get(position)).getPais());
    		label.setText( ((Pais)values[position]).getPais());
    		for(int i=0;i<values.length;i++)
    		Log.v("Adapter", ((Pais)values[i]).getPais()+"");
        }
    	/*
    	else if(values.get(position).getClass().getName() == CatNormas.class.getName()){
        	//label.setText( ((UnidadMedida)values[position]).getUnidad());
    		label.setText( ((CatNormas)values.get(position)).getNombre());
        }*/
    	
        return label;
    }
	
	

}

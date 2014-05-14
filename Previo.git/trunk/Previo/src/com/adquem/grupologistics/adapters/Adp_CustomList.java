/**
 * 
 */
package com.adquem.grupologistics.adapters;

import java.util.List;

import com.adquem.grupologistics.bussines.Buss_FragListadoFact_RevisionReferencia;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.model.Factura;
import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.model.Referencia;
import com.adquem.grupologistics.utilities.Constantes;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Usuario
 *
 */
public class Adp_CustomList extends ArrayAdapter<Object>{

	private final List lista;
	private final Context contexto;
	private final int listado;
	private int posSelected = 0;
	List<List<String>> childs;

	
	Buss_FragListadoFact_RevisionReferencia revision = new Buss_FragListadoFact_RevisionReferencia();
	
	public Adp_CustomList(Context context, int textViewResourceId,
			List objects, int listado, List<List<String>> hijos) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.contexto = context;
		this.listado = listado;
		this.lista = objects;
		this.childs = hijos;
	}
	
	@Override
	  public View getView(final int position, View convertView, ViewGroup parent) {
		posSelected = position;
		LayoutInflater inflater = (LayoutInflater) contexto
	        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.lyt_fragment_childrow, null);
	    TextView name = (TextView)rowView.findViewById(R.id.txv_itemTitle);
	    TextView description = (TextView)rowView.findViewById(R.id.txv_itemDescr);
	    TextView otro = (TextView) rowView.findViewById(R.id.txv_itemInfo);
	    TextView num_orden = (TextView) rowView.findViewById(R.id.txv_orden);
	    final ImageButton button = (ImageButton)rowView.findViewById(R.id.btn_itemGo);
	    
	    final TextView total = (TextView)rowView.findViewById(R.id.child_total);
	    final TextView enrevision = (TextView)rowView.findViewById(R.id.child_enrevision);
	    final TextView revisadas= (TextView)rowView.findViewById(R.id.childrevisadas);
	    final TextView nsinrevisar = (TextView)rowView.findViewById(R.id.child_sinrevisar);
	    final LinearLayout ocultar = (LinearLayout)rowView.findViewById(R.id.lyt_ocultar);
	  
	    
	    Animation animation = AnimationUtils.loadAnimation(contexto, R.animator.push_left_in);

	    rowView.startAnimation(animation);
	    
	    button.setTag(position+"");
	    TextView estado = (TextView)rowView.findViewById(R.id.txv_itemEstatus);
	    TextView faltan = (TextView)rowView.findViewById(R.id.txv_itemFaltanFotos);

	    int idEstatus = 0; 
	    String nombre = ""; 
	    String descripcion = "";
	    String extra = "";
	    String orden = "";
	    String ordenCompra = "";
	    int tipoDato;
	    if(listado == Constantes.BUSQUEDA){
	    	String tipoClase = lista.get(position).getClass().getName();
	    	if(tipoClase.equals(Referencia.class.getName())){
	    		tipoDato = Constantes.TIPOlISTADO_REFERENCIA;
	    	}
	    	else{
	    		if(tipoClase.equals(Factura.class.getName())){
	    			tipoDato = Constantes.TIPOlISTADO_FACTURA;
	    		}
	    		else{
	    			tipoDato = Constantes.TIPOlISTADO_ITEM;
	    		}
	    	}
	    }
	    else{
	    	tipoDato = listado;
	    }
	    
	    switch(tipoDato){
    	case Constantes.TIPOlISTADO_REFERENCIA:
    		idEstatus = ((Referencia) lista.get(position)).getEstatus();
    		nombre = ((Referencia) lista.get(position)).getNoReferencia();
    		descripcion = ((Referencia) lista.get(position)).getCliente();
    		extra = ((Referencia) lista.get(position)).getContenedor();
    		orden = ((Referencia) lista.get(position)).getOrdenCompra();
    		
    		Log.v("Previo App", "Falta foto: " + revision.validacionFotos(((Referencia) lista.get(position)).getIdReferencia(), contexto));
    		if(revision.validacionFotos(((Referencia) lista.get(position)).getIdReferencia(), contexto)){
    			faltan.setText("");
    		}
    		else{
    			faltan.setText(R.string.str_Listados_faltaFoto); 
    		}
    		break;
    	case Constantes.TIPOlISTADO_FACTURA:
    		idEstatus = ((Factura) lista.get(position)).getEstatus();
    		nombre = ((Factura) lista.get(position)).getFactura();
    		orden = ((Factura) lista.get(position)).getOrdenCompra();
    		Log.v("ADAPTER", "Orden: "+orden);
    		Log.v("ADAPTER", "Orden Lista: "+((Factura) lista.get(position)).getOrdenCompra());
    		
    		//TODO obtener el no referencia en base al id de referencia
   		 	String[] colNoRef = {"NoReferencia"};
   		 	String[] argsNoRef = { String.valueOf(((Factura) lista.get(position)).getIdReferencia()) };
    		Cursor cursorNoRef = contexto.getContentResolver().query(Uri.parse(MyContentProvider.URL+"Referencia"), colNoRef, "IdReferencia = ?", argsNoRef, null);
    		if(cursorNoRef.moveToFirst()){
    			descripcion = cursorNoRef.getString(0);
    		}
    		cursorNoRef.close();
    		extra = ((Factura) lista.get(position)).getProveedor();
    		break;
    	case Constantes.TIPOlISTADO_ITEM:
    		idEstatus = ((Item) lista.get(position)).getEstatus();
    		nombre = ((Item) lista.get(position)).getNoParte();
    		descripcion = ((Item) lista.get(position)).getDescripcion();
    		
    		//TODO Obtener el pais del item, obtener el desglose y luego el pais
    		String[] colIdPais = {"IdPais"};
    		String[] argsIdPais = {String.valueOf(((Item)lista.get(position)).getIdItem())};
    		Cursor cursorIdPais = contexto.getContentResolver().query(Uri.parse(MyContentProvider.URL+"Desglose"), colIdPais,"IdItem = ?", argsIdPais, null);
    		if(cursorIdPais.moveToFirst()){
    			String[] colPais = {"Pais"};
    			String[] argsPais = {String.valueOf(cursorIdPais.getInt(0))};
    			cursorIdPais.close();
    			Cursor cursorPais = contexto.getContentResolver().query(Uri.parse(MyContentProvider.URL+"Pais"), colPais, "IdPais = ?", argsPais, null);
    			if(cursorPais.moveToFirst()){
    				extra = cursorPais.getString(0);
    			}
    			cursorPais.close();
    		}
    		break;
    }

    switch (idEstatus) {
	case Constantes.ESTATUS_EN_REVISION:
		//estatus.setBackgroundResource(R.drawable.ic_en_revision);// .bg_estatus_enrevision);
		estado.setText(R.string.str_Listados_tab_EnRevision);
		rowView.setBackgroundResource(R.drawable.bg_elemento);
	
		break;
	case Constantes.ESTATUS_REVISADO:
		//estatus.setBackgroundResource(R.drawable.ic_revisado);// .bg_estatus_revisado);
		rowView.setBackgroundResource(R.drawable.bg_elemento);
	estado.setText(R.string.str_Listados_tab_Revisado);
		break;
	case Constantes.ESTATUS_SIN_REVISAR:
		//estatus.setBackgroundResource(R.drawable.ic_sin_revisar);// .bg_estatus_sinrevision);
		rowView.setBackgroundResource(R.drawable.bg_elemento);
	estado.setText(R.string.str_Listados_tab_SinRevision);
		break;
	default:
		break;
	}

    name.setText(nombre);
    
    /*if ( descripcion.length() > 20 ) {
        description.setText(descripcion.substring(0, 18) + "\n" + descripcion.substring(18)); 
        description.setText(Html.fromHtml(descripcion.substring(0, 18)+"\n"+descripcion.substring(18)));
    }
    else 
    	description.setText(descripcion);
*/
    description.setText(descripcion);
    otro.setText(extra);
    num_orden.setText(orden);
    //Log.v("ADAPTER", "Orden: "+orden);
    
    button.setImageResource(R.drawable.ic_drawer);
    
    button.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//int position = posSelected;
			if(ocultar.getVisibility()==View.GONE)ocultar.setVisibility(View.VISIBLE);
            else ocultar.setVisibility(View.GONE);
			Log.v("Previo App", "selected es:" + posSelected);
			long idSelected = 0;
			int pos = 0;
			Log.v("Previo app", v.getTag().toString());
			pos = Integer.parseInt(v.getTag().toString());
			switch(listado){
	    		case Constantes.TIPOlISTADO_REFERENCIA:
	    			idSelected = ((Referencia)lista.get(pos)).getIdReferencia();
	    			break;
	    		case Constantes.TIPOlISTADO_FACTURA:
	    			idSelected = ((Factura)lista.get(pos)).getIdFactura();
	    			break;
	    		case Constantes.TIPOlISTADO_ITEM:
	    			idSelected = ((Item)lista.get(pos)).getIdItem();
	    			break;
			}
			Log.v("Previo App", "valor seleccionado boton:" + idSelected);
//			Toast t = Toast.makeText(contexto, "Se presiono boton" + idSelected + " Contadores: " + childs.get(pos), Toast.LENGTH_SHORT);
//			t.show();
			
			//Creating the instance of PopupMenu  
	          PopupMenu popup = new PopupMenu(contexto, button);
	          MenuItem item1, item2, item3, item4; 
	          
	          //Inflating the Popup using xml file  
	          popup.getMenuInflater().inflate(R.menu.menuitem, popup.getMenu());  
	          Menu menu = popup.getMenu();
	          
	        //  String data = childs.get(position).toString();
	          String data = childs.get(position).toString().substring(1, childs.get(position).toString().length() - 1);
		         
	          String[] lista = data.split(",");
	          Log.v(data, lista.length + "");
	          
	          //total.setText("Total: "+lista[0]);
	          total.setText(lista[0]);
	          revisadas.setText(lista[1]);
	          enrevision.setText(lista[2]);
	          nsinrevisar.setText(lista[3]);	   
	          item1 = (MenuItem) menu.findItem(R.id.one);
	          item1.setTitle(lista[0]);
	          item2 = (MenuItem) menu.findItem(R.id.two);
	          item2.setTitle(lista[1]);
	          item3 = (MenuItem) menu.findItem(R.id.three);
	          item3.setTitle(lista[2]);
	          item4 = (MenuItem) menu.findItem(R.id.four);
	          item4.setTitle(lista[3]);
	          
	          //registering popup with OnMenuItemClickListener  
	          popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
	           public boolean onMenuItemClick(MenuItem item) {  
	            Toast.makeText(contexto,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();  
	            return true;  
	           }  
	          });

	        //  popup.show();//showing popup menu  
	         }  
		
	});
	    return rowView;
	  }

	
}

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
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;


/**
 * @author Usuario
 *
 */
public class Adp_Expandable_List extends BaseExpandableListAdapter {

	private static final String TAG = "Listado Previo App";
	
	List lista;
	Context Contexto;
	int listado;
	LayoutInflater inflater=null;
	int posSelected;
	List<List<String>> childs;
	
	Buss_FragListadoFact_RevisionReferencia revision = new Buss_FragListadoFact_RevisionReferencia();
	
	public Adp_Expandable_List(List lista, Context contexto, int listado, List<List<String>> hijos) {
		super();
		this.lista = lista;
		this.Contexto=contexto;
		this.listado = listado;
		this.childs = hijos;
	}
	
	
	@Override
	public Object getChild(int parentPos, int childPos) {
		return this.childs.get(parentPos).get(childPos);
	}

	@Override
	public long getChildId(int parentPos, int childPos) {
		return childPos;
	}

	@Override
	public View getChildView(int parentPos, int childPos, boolean isLastChild, View convertView,
			ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) Contexto.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.lyt_fragment_contador, null);
		}
		
		TextView total = (TextView) convertView.findViewById(R.id.txv_itemCont);
		total.setText(childs.get(parentPos).get(childPos));
		return convertView;
	}

	@Override
	public int getChildrenCount(int parentPos) {
/*		if(listado == Constantes.BUSQUEDA){
			if(this.lista.get(parentPos).getClass().getName().equals(Item.class.getName())){
				return 0;
			}
			else{
				return this.childs.get(parentPos).size();
			}
		}
		else{
			if(listado == Constantes.TIPOlISTADO_ITEM){
				return 0;
			}
			else{
				return this.childs.get(parentPos).size();
			}
		}
*/
		return this.childs.get(parentPos).size();
	}

	@Override
	public Object getGroup(int parentPos) {
		return this.lista.get(parentPos);
	}

	@Override
	public int getGroupCount() {
		return lista.size();
	}

	@Override
	public long getGroupId(int parentPos) {
		return parentPos;
	}

	@Override
	public View getGroupView(int parentPos, boolean isExpanded, View convertView, ViewGroup parent) {
		posSelected = parentPos;
		View vi=convertView;
		
	    if(convertView==null)
       inflater= (LayoutInflater) Contexto.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	    vi = inflater.inflate(R.layout.lyt_fragment_childrow, null);
	    
	    final ExpandableListView elv = (ExpandableListView)vi.findViewById(R.id.lv_lyt_listados_items);

//	    ImageView estatus=(ImageView)vi.findViewById(R.id.imgv_itemRow);
	    TextView name = (TextView)vi.findViewById(R.id.txv_itemTitle);
	    TextView description = (TextView)vi.findViewById(R.id.txv_itemDescr);
	    TextView otro = (TextView) vi.findViewById(R.id.txv_itemInfo);
	    //ImageButton button = (ImageButton)vi.findViewById(R.id.btn_itemGo);

	    TextView estado = (TextView)vi.findViewById(R.id.txv_itemEstatus);
	    TextView faltan = (TextView)vi.findViewById(R.id.txv_itemFaltanFotos);
	    
	    int idEstatus = 0; 
	    String nombre = ""; 
	    String descripcion = "";
	    String extra = "";
	    
	    int tipoDato;
	    if(listado == Constantes.BUSQUEDA){
	    	String tipoClase = lista.get(parentPos).getClass().getName();
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
	    		idEstatus = ((Referencia) lista.get(parentPos)).getEstatus();
	    		nombre = String.valueOf(((Referencia) lista.get(parentPos)).getNoReferencia());
	    		descripcion = ((Referencia) lista.get(parentPos)).getCliente();
	    		extra = ((Referencia) lista.get(parentPos)).getContenedor();
	    		Log.v("Previo App", "Falta foto: " + revision.validacionReferencia(((Referencia) lista.get(parentPos)).getIdReferencia(), Contexto));
	    		if(revision.validacionReferencia(((Referencia) lista.get(parentPos)).getIdReferencia(), Contexto)){
	    			faltan.setText("");
	    		}
	    		else{
	    			faltan.setText(R.string.str_Listados_faltaFoto);
	    		}
	    		break;
	    	case Constantes.TIPOlISTADO_FACTURA:
	    		idEstatus = ((Factura) lista.get(parentPos)).getEstatus();
	    		nombre = String.valueOf(((Factura) lista.get(parentPos)).getFactura());
	    		//TODO obtener el no referencia en base al id de referencia
	   		 	String[] colNoRef = {"NoReferencia"};
	   		 	String[] argsNoRef = { String.valueOf(((Factura) lista.get(parentPos)).getIdReferencia()) };
	    		Cursor cursorNoRef = Contexto.getContentResolver().query(Uri.parse(MyContentProvider.URL+"Referencia"), colNoRef, "IdReferencia = ?", argsNoRef, null);
	    		if(cursorNoRef.moveToFirst()){
	    			descripcion = cursorNoRef.getString(0);
	    		}
	    		cursorNoRef.close();
	    		extra = ((Factura) lista.get(parentPos)).getProveedor();
	    		break;
	    	case Constantes.TIPOlISTADO_ITEM:
	    		idEstatus = ((Item) lista.get(parentPos)).getEstatus();
	    		nombre = String.valueOf(((Item) lista.get(parentPos)).getNoParte());
	    		descripcion = ((Item) lista.get(parentPos)).getDescripcion();
	    		//TODO Obtener el pais del item, obtener el desglose y luego el pais
	    		String[] colIdPais = {"IdPais"};
	    		String[] argsIdPais = {String.valueOf(((Item)lista.get(parentPos)).getIdItem())};
	    		Cursor cursorIdPais = Contexto.getContentResolver().query(Uri.parse(MyContentProvider.URL+"Desglose"), colIdPais,"IdItem = ?", argsIdPais, null);
	    		if(cursorIdPais.moveToFirst()){
	    			String[] colPais = {"Pais"};
	    			String[] argsPais = {String.valueOf(cursorIdPais.getInt(0))};
	    			cursorIdPais.close();
	    			Cursor cursorPais = Contexto.getContentResolver().query(Uri.parse(MyContentProvider.URL+"Pais"), colPais, "IdPais = ?", argsPais, null);
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
			estado.setTextColor(Color.parseColor("#F7C028"));
	vi.setBackgroundResource(R.drawable.bg_elemento);
		
			break;
		case Constantes.ESTATUS_REVISADO:
			//estatus.setBackgroundResource(R.drawable.ic_revisado);// .bg_estatus_revisado);
			vi.setBackgroundResource(R.drawable.bg_elemento);
		estado.setText(R.string.str_Listados_tab_Revisado);
		estado.setTextColor(Color.parseColor("#AEC253"));
			break;
		case Constantes.ESTATUS_SIN_REVISAR:
			//estatus.setBackgroundResource(R.drawable.ic_sin_revisar);// .bg_estatus_sinrevision);
				vi.setBackgroundResource(R.drawable.bg_elemento);
		estado.setText(R.string.str_Listados_tab_SinRevision);
		estado.setTextColor(Color.RED);
			break;
		default:
			break;
		}

	    name.setText(nombre);
	    description.setText(descripcion);
	    otro.setText(extra);
	  /*  button.setImageResource(R.drawable.ic_drawer);
	    
	    button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int position = posSelected;
				Log.v("Previo App", "selected es:" + position);
				long idSelected = 0;
				long idElemento = 0;
				switch(listado){
		    		case Constantes.TIPOlISTADO_REFERENCIA:
		    			idSelected = ((Referencia)lista.get(position)).getIdReferencia();
		    			break;
		    		case Constantes.TIPOlISTADO_FACTURA:
		    			idSelected = ((Factura)lista.get(position)).getIdFactura();
		    			break;
		    		case Constantes.TIPOlISTADO_ITEM:
		    			idSelected = ((Item)lista.get(position)).getIdItem();
		    			break;
				}
				Log.v(TAG, "valor seleccionado boton:" + idSelected);
				Toast t = Toast.makeText(Contexto, "Se presiono boton" + idSelected,
						Toast.LENGTH_SHORT);
				t.show();
			}
		});
	    */
	    return vi;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int parentPos, int childPos) {
		return true;
	}
}

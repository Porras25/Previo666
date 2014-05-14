/**
 * 
 */
package com.adquem.grupologistics.model;

import com.google.gson.annotations.SerializedName;


/**
 * @author Usuario
 *
 */

public class NomItem {

    @SerializedName("idNom_Item")
	private long idNom_Item;
    @SerializedName("idNom")
	private long idNom;
    @SerializedName("idItem")
	private long idItem;
    public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	private int estatus;
	
	public long getIdNom_Item() {
		return idNom_Item;
	}
	public void setIdNom_Item(long idNom_Item) {
		this.idNom_Item = idNom_Item;
	}
	public long getIdNom() {
		return idNom;
	}
	public void setIdNom(long idNom) {
		this.idNom = idNom;
	}
	public long getIdItem() {
		return idItem;
	}
	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}
	
}

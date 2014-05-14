/**
 * 
 */
package com.adquem.grupologistics.model;

import com.google.gson.annotations.SerializedName;


/**
 * @author Usuario
 *
 */

public class Nom {
    private long id_Nom_campo;
	@SerializedName("idNom")
	private long idNom;
	@SerializedName("idCampo")
	private long idCampo;
	@SerializedName("nom")
	private String Nom;
	
	public long getId_Nom_campo() {
		return id_Nom_campo;
	}
	public void setId_Nom_campo(long id_Nom_campo) {
		this.id_Nom_campo = id_Nom_campo;
	}
	public long getIdNom() {
		return idNom;
	}
	public void setIdNom(long idNom) {
		this.idNom = idNom;
	}
	public long getIdCampo() {
		return idCampo;
	}
	public void setIdCampo(long idCampo) {
		this.idCampo = idCampo;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}

	@Override
	public String toString(){
		return this.Nom;
	}
	
}

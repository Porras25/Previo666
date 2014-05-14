/**
 * 
 */
package com.adquem.grupologistics.model;


/**
 * @author Usuario
 *
 */

public class Adjunto {

	private long idAdj;
	private String nombre;
	private String Descripcion;
	private int concepto;
	private long idForaneo;
	private int estatus;
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	public long getIdForaneo() {
		return idForaneo;
	}
	public void setIdForaneo(long idForaneo) {
		this.idForaneo = idForaneo;
	}
	
	public long getIdAdj() {
		return idAdj;
	}
	public void setIdAdj(long idAdj) {
		this.idAdj = idAdj;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public int getConcepto() {
		return concepto;
	}
	public void setConcepto(int concepto) {
		this.concepto = concepto;
	}
	
	
		
}

/**
 * 
 */
package com.adquem.grupologistics.model;


/**
 * @author Usuario
 *
 */

public class Usuario {
	
	
	private long idUsuario;
	private String nombre;
	private String apPaterno;
	private String apMaterno;
	private String nombreUsuario;
	private long iDIdioma;
	
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public long getiDIdioma() {
		return iDIdioma;
	}
	public void setiDIdioma(long iDIdioma) {
		this.iDIdioma = iDIdioma;
	}

}

/**
 * 
 */
package com.adquem.grupologistics.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Usuario
 * 
 */

public class Referencia {

	@SerializedName("idReferencia")
	private long idReferencia;
	@SerializedName("idCliente")
	private String cliente;
	@SerializedName("idContenedor")
	private String contenedor;
	@SerializedName("rfc")
	private String rfc;
	@SerializedName("noReferencia")
	private String noReferencia;
	@SerializedName("fecha_arribo")
	private String fecha_arribo;
	@SerializedName("ejecutivo")
	private String ejecutivo;
	@SerializedName("clasificador")
	private String clasificador;
	@SerializedName("plaza")
	private String plaza;
	@SerializedName("sello_arribo")
	private String sello_arribo;
	@SerializedName("sello_asignado")
	private String sello_asignado;
	@SerializedName("noOperacion")
	private String noOperacion;
	@SerializedName("fecha_fin_previo")
	private String fecha_fin_previo;
	@SerializedName("idRazonCierre")
	private int idRazonCierre;
	@SerializedName("comentarios_razon_cierre")
	private String comentarios_razon_Cierre;

	@SerializedName("ordenCompra")
	private String OrdenCompra;

	@SerializedName("estatus")
	private int Estatus;

	public String getOrdenCompra() {
		return OrdenCompra;
	}

	public void setOrdenCompra(String ordenCompra) {
		OrdenCompra = ordenCompra;
	}

	public long getIdReferencia() {
		return idReferencia;
	}

	public void setIdReferencia(long idReferencia) {
		this.idReferencia = idReferencia;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getContenedor() {
		return contenedor;
	}

	public void setContenedor(String contenedor) {
		this.contenedor = contenedor;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNoReferencia() {
		return noReferencia;
	}

	public void setNoReferencia(String noReferencia) {
		this.noReferencia = noReferencia;
	}

	public String getFecha_arribo() {
		return fecha_arribo;
	}

	public void setFecha_arribo(String fecha_arribo) {
		this.fecha_arribo = fecha_arribo;
	}

	public String getEjecutivo() {
		return ejecutivo;
	}

	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}

	public String getClasificador() {
		return clasificador;
	}

	public void setClasificador(String clasificador) {
		this.clasificador = clasificador;
	}

	public String getPlaza() {
		return plaza;
	}

	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}

	public String getSello_arribo() {
		return sello_arribo;
	}

	public void setSello_arribo(String sello_arribo) {
		this.sello_arribo = sello_arribo;
	}

	public String getSello_asignado() {
		return sello_asignado;
	}

	public void setSello_asignado(String sello_asignado) {
		this.sello_asignado = sello_asignado;
	}

	public String getNoOperacion() {
		return noOperacion;
	}

	public void setNoOperacion(String noOperacion) {
		this.noOperacion = noOperacion;
	}

	public String getFecha_fin_previo() {
		return fecha_fin_previo;
	}

	public void setFecha_fin_previo(String fecha_fin_previo) {
		this.fecha_fin_previo = fecha_fin_previo;
	}

	public int getIdRazonCierre() {
		return idRazonCierre;
	}

	public int getEstatus() {
		return Estatus;
	}

	public void setEstatus(int estatus) {
		Estatus = estatus;
	}

	public void setIdRazonCierre(int idRazonCierre) {
		this.idRazonCierre = idRazonCierre;
	}

	public String getComentarios_razon_Cierre() {
		return comentarios_razon_Cierre;
	}

	public void setComentarios_razon_Cierre(String comentarios_razon_Cierre) {
		this.comentarios_razon_Cierre = comentarios_razon_Cierre;
	}

}

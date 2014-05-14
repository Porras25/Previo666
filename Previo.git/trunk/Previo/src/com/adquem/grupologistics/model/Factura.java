/**
 * 
 */
package com.adquem.grupologistics.model;

import com.google.gson.annotations.SerializedName;


/**
 * @author Usuario
 *
 */
public class Factura {

    @SerializedName("idFactura")
	private long idFactura;
    @SerializedName("idReferencia")
	private long idReferencia;
    @SerializedName("factura")
	private String factura;
    @SerializedName("cantidad")
	private int cantidad;
    @SerializedName("fechaFactura")
	private String fechaFactura;
    @SerializedName("estatus")
	private int Estatus;
    @SerializedName("proveedor")
	private String proveedor;
    @SerializedName("ordenCompra")
	private String ordenCompra;
	
	private long referencia_Idreferencia;
	public long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}
	public long getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(long idReferencia) {
		this.idReferencia = idReferencia;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getOrdenCompra() {
		return ordenCompra;
	}
	public void setOrdenCompra(String ordenCompra) {
		this.ordenCompra = ordenCompra;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	public int getEstatus() {
		return Estatus;
	}
	public void setEstatus(int estatus) {
		Estatus = estatus;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
	public long getReferencia_Idreferencia() {
		return referencia_Idreferencia;
	}
	public void setReferencia_Idreferencia(long referencia_Idreferencia) {
		this.referencia_Idreferencia = referencia_Idreferencia;
	}
	

}
package com.adquem.grupologistics.model;

public class Respuestas {

	private long idResp;
	private long idNom;
	private long idItem;
	private long idCampo;
	private String Valor;
	private int estatus;
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	public String getValor() {
		return Valor;
	}
	public void setValor(String valor) {
		Valor = valor;
	}
	public long getIdResp() {
		return idResp;
	}
	public void setIdResp(long idResp) {
		this.idResp = idResp;
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
	public long getIdCampo() {
		return idCampo;
	}
	public void setIdCampo(long idCampo) {
		this.idCampo = idCampo;
	}
//	public long getValor() {
//		return valor;
//	}
//	public void setValor(long valor) {
//		this.valor = valor;
//	}
//	private long valor;
}

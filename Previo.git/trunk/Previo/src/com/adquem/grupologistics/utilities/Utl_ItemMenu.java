package com.adquem.grupologistics.utilities;

public class Utl_ItemMenu {
	private String titulo;
	private int icono;

	public Utl_ItemMenu(String title, int icon) {
		this.titulo = title;
		this.icono = icon;
	}

	public Utl_ItemMenu(String string) {
	
		this.titulo = string;
		// TODO Auto-generated constructor stub
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getIcono() {
		return icono;
	}

	public void setIcono(int icono) {
		this.icono = icono;
	}
}
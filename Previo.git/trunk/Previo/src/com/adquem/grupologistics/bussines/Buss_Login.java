package com.adquem.grupologistics.bussines;

public class Buss_Login {
	
	public boolean DatosValidos(String user, String pass){
		
		if(user.equalsIgnoreCase("")&&pass.equalsIgnoreCase(""))return false;
		if(user.length()<3&&pass.length()<3)return false;
			
		return true;
		
	}
	public boolean insertUser(String token, String fechacadu, String idioma){
			
		
		return false;
	}

}

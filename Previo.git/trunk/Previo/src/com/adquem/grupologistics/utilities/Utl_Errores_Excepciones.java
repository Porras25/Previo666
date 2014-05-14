/**
 * 
 */
package com.adquem.grupologistics.utilities;

import com.adquem.grupologistics.controllers.Act_Main;
import com.adquem.grupologistics.controllers.Login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Usuario
 * 
 */
public class Utl_Errores_Excepciones {

	public void manejoError(Context ctx, String idError) {
		int id = Integer.valueOf(idError.substring(1));
		Log.v("Previo App", "ErrorExcepcion id: " + id);
		switch (id) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 18:
		case 19:
		case 20:
		case 21:
		case 22:
		case 33:
		case 34:
		case 35:
		case 36:
		case 37:
		case 38:
		case 39:
			// TODO Despues de tres intentos de comunicación cerrara la sesión
			break;
		case 7:
			// TODO Despues de tres intentos de comunicación cerrara la sesión
			Toast.makeText(ctx, "Contacta al administrador", Toast.LENGTH_SHORT)
					.show();
			break;
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
			Toast.makeText(ctx, "Timeout, contacta con el Administrador",
					Toast.LENGTH_SHORT).show();
			break;
		case 13:
		case 15:
			// TODO el Previo App intentara n veces enviar la información
			Toast.makeText(ctx,
					"Error al insertar, contacta con el Administrador",
					Toast.LENGTH_SHORT).show();
			break;
		case 14:
		case 24:
		case 25:
			// TODO reconstruir el cliente del API
			Toast.makeText(ctx,
					"Error Critico, el canal de comunicación sufrio cambios",
					Toast.LENGTH_SHORT).show();
			break;
		case 16:
		case 32:
			// N/A
			break;
		case 17:
			// TODO Se tendra que sincronizar manualmente
			Toast.makeText(ctx, "Error Referencia Revisada", Toast.LENGTH_SHORT)
					.show();
			break;
		case 23:
		case 28:
		case 29:
		case 30:
		case 31:
			// TODO Se dejara el estatus de todas las entidades en "Revisado"
			break;
		case 26:
			// TODO verificar la integridad
			Toast.makeText(ctx, "Error al insertar, problemas con Previo App",
					Toast.LENGTH_SHORT).show();
			break;
		case 27:
			// TODO Se tendra que sincronizar manualmente
			Toast.makeText(ctx,
					"Error de sincronización, contacta con el Administrador",
					Toast.LENGTH_SHORT).show();
			break;
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
		case 45:
			Toast.makeText(ctx, "Error al iniciar sesión, verifica tus datos",
					Toast.LENGTH_SHORT).show();
			break;
		}
	}

}

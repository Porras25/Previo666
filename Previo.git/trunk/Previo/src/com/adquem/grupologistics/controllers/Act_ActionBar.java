package com.adquem.grupologistics.controllers;

import com.adquem.grupologistics.controllers.R;

import android.os.Bundle;
import android.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Act_ActionBar extends Act_Main {
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//

		// --Activaremos el boton Home
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// --Creamos un metodo para crear cada item
		CrearMenu(menu);
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		Act_Main.NavDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description */
		R.string.drawer_close /* "close drawer" description */
		) {
			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle("Drawer Abierto");
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle("Drawer Cerrado");
			}

		};
		return true;
	}

	// --Sobre escribimos el metodo para saber que item fue pulsado
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// --Llamamos al metodo que sabe que itema fue seleccionado
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return MenuSelecciona(item);
	}

	private boolean MenuSelecciona(MenuItem item) {
		switch (item.getItemId()) {

		case 0:
			Toast.makeText(this, "Has pulsado el Item 1 del Action Bar",
					Toast.LENGTH_SHORT).show();
			return true;

		case 1:
			Toast.makeText(this, "Has pulsado el Item 2 del Action Bar",
					Toast.LENGTH_SHORT).show();

			return true;

			// --A�adimos el caso para cuando se pulse el boton home

		case android.R.id.home:
			Toast.makeText(this, "Has pulsado el Home del Action Bar",
					Toast.LENGTH_SHORT).show();

			return true;

		}
		return false;
	}

	private void CrearMenu(Menu menu) {
		MenuItem item1 = menu.add(0, 0, 0, "Item 1");
		{
			// --Copio las imagenes que van en cada item
			item1.setIcon(R.drawable.search);
			item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}

		MenuItem item2 = menu.add(0, 1, 1, "Item 2");
		{
			item2.setIcon(R.drawable.tigerbarcode);
			item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}

	}

}

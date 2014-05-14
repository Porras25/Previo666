package com.adquem.grupologistics.controllers;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;

import com.adquem.grupologistics.bussines.Buss_Frag_Busqueda_Upc;
import com.adquem.grupologistics.controllers.R;
import com.adquem.grupologistics.adapters.Adp_Base_Navigation;
import com.adquem.grupologistics.utilities.Constantes;
import com.adquem.grupologistics.utilities.Utl_CustomDialog;
import com.adquem.grupologistics.utilities.Utl_ItemMenu;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * Clase Controlador entre la Vista y la Capa de Negocio para el Menú, Búsquedas
 * y Lectura de Código de Barras
 * 
 * @author Adquem
 * @version 1.0
 */
public class Act_Main extends FragmentActivity {
	private String[] titulos;
	public static MenuItem item1;
	public static MenuItem item2;
	protected static DrawerLayout NavDrawerLayout;
	private ListView NavList;
	private ArrayList<Utl_ItemMenu> NavItms;
	private TypedArray NavIcons;
	Adp_Base_Navigation NavAdapter;
	private ActionBarDrawerToggle mDrawerToggle;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;
	final String[] data = { "one, two" };
	public static Utl_CustomDialog editNameDialog;
	public static FragmentManager fm;
	public static String UPC;
	public static Stack<String> elementos;
	public static Stack<Fragment> fragmentos;
	public static String valor;
	public static Context contexto;
	Frag_Elemento referencias1;
	Buss_Frag_Busqueda_Upc busquedaUPC = new Buss_Frag_Busqueda_Upc();
	 

	final String[] fragments = {
			"com.adquem.grupologistics.controllers.Frag_Elemento",
			"com.adquem.grupologistics.controllers.Frag_Elemento",
			"com.adquem.grupologistics.controllers.Frag_Elemento",
			"com.adquem.grupologistics.controllers.Frag_configuracion",
			"com.adquem.grupologistics.controllers.Frag_AyudaInvoke", "" };

	public static Stack<Fragment> mFragmentStack;
	public SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Cambiar idioma
		Intent intent = getIntent();
		String userName = intent.getStringExtra("UserName");

		fm = getSupportFragmentManager();
		elementos = new Stack<String>();
		mFragmentStack = new Stack<Fragment>();
		fragmentos = new Stack<Fragment>();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.lyt_main);
		// --Activaremos el boton Home
		contexto = getApplicationContext();
		ActionBar actionBar = getActionBar();

		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#3262A2")));
		actionBar.setDisplayHomeAsUpEnabled(true);

		prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
		editor = prefs.edit();
		String idioma = null;
		// Cambiar idioma
		switch (prefs.getInt(Constantes.TITTLE_USER_LENGUAGE, 0)) {
		case 0:
			idioma = "es";
			break;
		case 1:
			idioma = "en";
			break;
		default:
			break;
		}

		Resources res = this.getResources();
		// Change locale settings in the app.
		DisplayMetrics dm = res.getDisplayMetrics();
		android.content.res.Configuration conf = res.getConfiguration();
		conf.locale = new Locale(idioma.toLowerCase());
		res.updateConfiguration(conf, dm);
		// Lista para el Menú
		NavList = (ListView) findViewById(R.id.lstv_listaMenu);
		View header = getLayoutInflater().inflate(R.layout.lyt_fragment_header,
				null);
		TextView usuario = (TextView) header
				.findViewById(R.id.txv_usuraioPrevio);
		usuario.setText(userName);
		// Establecemos header
		NavList.addHeaderView(header);
		NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
		titulos = getResources().getStringArray(R.array.nav_options);
		NavItms = new ArrayList<Utl_ItemMenu>();
		NavItms.add(new Utl_ItemMenu(titulos[0]));
		NavItms.add(new Utl_ItemMenu(titulos[1]));
		NavItms.add(new Utl_ItemMenu(titulos[2]));
		NavItms.add(new Utl_ItemMenu(titulos[3], NavIcons.getResourceId(3, -1)));
		NavItms.add(new Utl_ItemMenu(titulos[4], NavIcons.getResourceId(4, -1)));
		NavItms.add(new Utl_ItemMenu(titulos[5], NavIcons.getResourceId(5, -1)));
		NavAdapter = new Adp_Base_Navigation(this, NavItms);

		NavDrawerLayout = (DrawerLayout) findViewById(R.id.drw_mainMenu);
		final ListView navList = (ListView) findViewById(R.id.lstv_listaMenu);
		navList.setAdapter(NavAdapter);
		navList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int pos, long id) {
				FragmentTransaction tx = getSupportFragmentManager()
						.beginTransaction();
				tx.setCustomAnimations(R.animator.enter, R.animator.exit,
						R.animator.pop_enter, R.animator.pop_exit);

				switch (pos) {
				case 0:

					break;

				case 1:
					Frag_Elemento referencias = new Frag_Elemento();
					elementos.push("Frag_Elemento");
					tx.replace(R.id.frm_lyt_mainMenu,
							mFragmentStack.push(referencias),
							Constantes.TIPOlISTADO_REFERENCIA + ":"
									+ Constantes.ESTATUS_TODOS);
					fragmentos.push(referencias);

					Bundle argsref = new Bundle();
					argsref.putInt("TipoListado",
							Constantes.TIPOlISTADO_REFERENCIA);
					argsref.putInt("Estatus", Constantes.ESTATUS_TODOS);
					referencias.setArguments(argsref);
					// Back
//					tx.addToBackStack(null);
					tx.commit();
					break;
				case 2:
					Frag_Elemento facturas = new Frag_Elemento();
					elementos.push("Frag_Elemento");
					//tx.replace(R.id.frm_lyt_mainMenu, facturas);
					  tx.replace(R.id.frm_lyt_mainMenu,
						       mFragmentStack.push(facturas),
						       Constantes.TIPOlISTADO_FACTURA + ":"
						         + Constantes.ESTATUS_TODOS);
					  fragmentos.push(facturas);
					Bundle argsfac = new Bundle();
					argsfac.putInt("TipoListado",
							Constantes.TIPOlISTADO_FACTURA);
					argsfac.putInt("Estatus", Constantes.ESTATUS_TODOS);
					facturas.setArguments(argsfac);
					// Back
//					tx.addToBackStack(null);
					tx.commit();
					break;
				case 3:
					Frag_Elemento items = new Frag_Elemento();
					elementos.push("Frag_Elemento");

					//tx.replace(R.id.frm_lyt_mainMenu, items);
					  tx.replace(R.id.frm_lyt_mainMenu,
						       mFragmentStack.push(items),
						       Constantes.TIPOlISTADO_ITEM + ":"
						         + Constantes.ESTATUS_TODOS);
						     fragmentos.push(items);

					Bundle argsitm = new Bundle();
					argsitm.putInt("TipoListado", Constantes.TIPOlISTADO_ITEM);
					argsitm.putInt("Estatus", Constantes.ESTATUS_TODOS);
					items.setArguments(argsitm);
					// Back
//					tx.addToBackStack(null);
					tx.commit();
					break;
				case 4:
					tx.replace(R.id.frm_lyt_mainMenu, mFragmentStack
							.push(Fragment.instantiate(Act_Main.this,
									fragments[pos - 1])));
					fragmentos.push(Fragment.instantiate(Act_Main.this,
							fragments[pos - 1]));

					if (pos - 1 == 0 || pos - 1 == 1 || pos - 1 == 2)
						valor = "Frag_Referencia";
					else if (pos - 1 == 3)
						valor = "Frag_configuracion";
					else if (pos - 1 == 4)
						valor = "Frag_AyudaInvoke";
					elementos.push(valor);
					// Back
//					tx.addToBackStack(null);
//					 tx.remove(referencias1);
					tx.commit();
					break;

				case 5:
					tx.replace(R.id.frm_lyt_mainMenu, mFragmentStack
							.push(Fragment.instantiate(Act_Main.this,
									fragments[pos - 1])),
							Constantes.TIPOlISTADO_REFERENCIA + ":"
									+ Constantes.ESTATUS_TODOS);
					if (pos - 1 == 0 || pos - 1 == 1 || pos - 1 == 2)
						valor = "Frag_Referencia";
					else if (pos - 1 == 3)
						valor = "Frag_configuracion";
					else if (pos - 1 == 4)
						valor = "Frag_AyudaInvoke";
					elementos.push(valor);
					fragmentos.push(Fragment.instantiate(Act_Main.this,
							fragments[pos - 1]));
					// Back
//					tx.addToBackStack(null);
//					 tx.remove(referencias1);
					tx.commit();
					break;

				case 6:
					Intent intent = new Intent(Act_Main.this, Login.class);
					startActivity(intent);
					SharedPreferences preferences =  getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
					Editor edit = preferences.edit();
					edit.putBoolean(Constantes.TITTLE_PREFERENCE_LOGON, false);
					edit.commit();
					finish();
					break;

				default:
					break;
				}

				NavDrawerLayout
						.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
							@Override
							public void onDrawerClosed(View drawerView) {
								super.onDrawerClosed(drawerView);
							}
						});
				NavDrawerLayout.closeDrawer(navList);
			}
		});
		/** Getting a reference to the ViewPager defined the layout file */

		referencias1 = new Frag_Elemento();
		FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
		tx.setCustomAnimations(R.animator.enter, R.animator.exit,
				R.animator.pop_enter, R.animator.pop_exit);
		//tx.replace(R.id.frm_lyt_mainMenu, referencias1);
		//fragmentos.push(referencias1);
		tx.replace(R.id.frm_lyt_mainMenu,
			    mFragmentStack.push(referencias1),
			    Constantes.TIPOlISTADO_REFERENCIA + ":"
			      + Constantes.ESTATUS_TODOS);
			  fragmentos.push(referencias1);
		Bundle argsref = new Bundle();
		argsref.putInt("TipoListado", Constantes.TIPOlISTADO_REFERENCIA);
		argsref.putInt("Estatus", Constantes.ESTATUS_TODOS);
		referencias1.setArguments(argsref);
		elementos.push("Frag_Elemento");
		// Back
//		tx.addToBackStack(null);
		tx.commit();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		editor.putBoolean("primeraVez", true);
		editor.commit();
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		editor.putBoolean("primeraVez", true);
		editor.commit();
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		editor.putBoolean("primeraVez", true);
		editor.commit();
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Creamos un metodo para crear cada item
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

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		MenuItem busqueda = menu.getItem(0);

		searchView = (SearchView) busqueda.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		searchView.setOnQueryTextListener(searchListener);
		searchView.setSubmitButtonEnabled(true);
		searchView.setIconifiedByDefault(false);

		return true;

	}

	private OnQueryTextListener searchListener = new OnQueryTextListener() {

		@Override
		public boolean onQueryTextSubmit(String query) {
			Log.i("esto es del query", "Esto es del query");
			// //////////////////////////////////////BUSQUEDA
			Frag_Elemento busqueda = new Frag_Elemento();
			FragmentTransaction tx = getSupportFragmentManager()
					.beginTransaction();
			Bundle argsref = new Bundle();
			int posicionado;
			long padre;
			if (!mFragmentStack.empty()) {
				if (mFragmentStack.peek().getTag() != null) {
					String[] valores = mFragmentStack.peek().getTag()
							.split(":");
					posicionado = Integer.valueOf(valores[0]);
					padre = Long.valueOf(valores[1]);
				} else {
					posicionado = Constantes.TIPOlISTADO_REFERENCIA;
					padre = 0;
				}
				tx.replace(R.id.frm_lyt_mainMenu,
						mFragmentStack.push(busqueda), posicionado + ":"
								+ padre);
				fragmentos.push(busqueda);
				elementos.push("Frag_Elemento");
				// tx.replace(R.id.frm_lyt_mainMenu, busqueda);
				Log.v("Previo App", "mFragmentStack.peek().getTag() es:"
						+ posicionado + " y padre:" + padre);
				argsref.putInt("TipoListado", posicionado);
				argsref.putLong("Padre", padre);
			} else {
				tx.replace(R.id.frm_lyt_mainMenu,
						mFragmentStack.push(busqueda),
						Constantes.TIPOlISTADO_REFERENCIA + ":" + 0);
				fragmentos.push(busqueda);
				elementos.push("Frag_Elemento");
				argsref.putInt("TipoListado", Constantes.TIPOlISTADO_REFERENCIA);
				argsref.putLong("Padre", 0);
			}
			argsref.putInt("Estatus", Constantes.BUSQUEDA);
			argsref.putString("Criterio", query);
			busqueda.setArguments(argsref);
			elementos.push("Frag_Elemento");
			tx.addToBackStack(null);
			tx.commit();
			searchView.setQuery("", false);
			searchView.clearFocus();
			return true;
		}

		@Override
		public boolean onQueryTextChange(String newText) {
			// TODO Auto-generated method stub
			return false;
		}
	};

	// Sobre escribimos el metodo para saber que item fue pulsado
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Llamamos al metodo que sabe que itema fue seleccionado
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return MenuSelecciona(item);
	}

	/**
	 * Permite elegir las opciones del ActionBar como Búsquedas, Lector de
	 * Código de Barras o Home
	 * 
	 * @param item
	 *            Permite elegir entre las opciones del ActionBar
	 * @return
	 */
	private boolean MenuSelecciona(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			return true;

		case 1:
			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			startActivityForResult(intent, 0);
			return true;

		case android.R.id.home:
			return true;
		}
		return false;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
		editor = prefs.edit();
		String idioma = null;
		// Cambiar idioma
		switch (prefs.getInt(Constantes.TITTLE_USER_LENGUAGE, 0)) {
		case 0:
			idioma = "es";
			break;
		case 1:
			idioma = "en";
			break;
		default:
			break;
		}

		Resources res = this.getResources();
		// Change locale settings in the app.
		DisplayMetrics dm = res.getDisplayMetrics();
		android.content.res.Configuration conf = res.getConfiguration();
		conf.locale = new Locale(idioma.toLowerCase());
		res.updateConfiguration(conf, dm);
		
	}

	Handler handle = new Handler();

	/**
	 * Permite Iniciar el Intento de Lectura de Código de Barras
	 * 
	 * @param requestCode
	 *            Permite ver si se realizo la petición
	 * @param resultCode
	 *            Permite ver si la respuesta de la petición fue satisfactoria
	 * @param intent
	 *            Crea el Intento para realizar la Lectura del Código de Barras
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				UPC = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				Toast.makeText(this, UPC, Toast.LENGTH_LONG).show();

				final Runnable proceso = new Runnable() {
					/**
					 * Realiza la Búsqueda del Ítem por medio del Código de
					 * Barras leido
					 */
					@Override
					public void run() {
						// TODO Auto-generated method stub
						long idItemEscneado = 0;
						try {
							idItemEscneado = busquedaUPC.searchUpc(UPC,
									Act_Main.contexto);

							Log.i(getString(R.string.str_main_log_valorUPCTag),
									getString(R.string.str_main_log_valorUPCMsg)
											+ idItemEscneado);
						} catch (Exception e) {
							Log.e(getString(R.string.str_main_log_UPCInvalidoTag),
									getString(R.string.str_main_log_UPCInvalidoMsg)
											+ idItemEscneado);
							Toast.makeText(
									getApplicationContext(),
									getString(R.string.str_main_toast_UPCInvalido),
									Toast.LENGTH_LONG).show();
						}

						if (idItemEscneado != 0) {
							FragmentTransaction tx = getSupportFragmentManager()
									.beginTransaction();
							tx.setCustomAnimations(R.animator.enter,
									R.animator.exit, R.animator.pop_enter,
									R.animator.pop_exit);
							Frag_item_revision remplazo = new Frag_item_revision();

							tx.replace(R.id.frm_lyt_mainMenu,
									mFragmentStack.push(remplazo),
									Constantes.TIPOlISTADO_ITEM + ":"
											+ Constantes.ESTATUS_TODOS);
							Act_Main.fragmentos.push(remplazo);
							Act_Main.elementos.push("Frag_item_revision");

							Bundle argsItem = new Bundle();
							argsItem.putLong("Padre", idItemEscneado);
							remplazo.setArguments(argsItem);

							tx.addToBackStack(null);
							tx.commit();
						} else
							Toast.makeText(
									getApplicationContext(),
									getString(R.string.str_main_toast_itemNoEncontradoUPC),
									Toast.LENGTH_SHORT).show();
					}
				};

				handle.post(proceso);

			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}

	private void CrearMenu(Menu menu) {
		item1 = menu.add(0, 0, 0, "Item 1");
		{
			// Copio las imagenes que van en cada item
			SearchView sv = new SearchView(getActionBar().getThemedContext());
			item1.setIcon(R.drawable.ic_action_search);
			item1.setActionView(sv);
			// item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
					| MenuItem.SHOW_AS_ACTION_IF_ROOM);

			// android:actionViewClass="android.widget.SearchView"/>
		}

		item2 = menu.add(0, 1, 1, "Item 2");
		{
			item2.setIcon(R.drawable.codigobarras);
			item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}
	}
	
}

package com.adquem.grupologistics.controllers;

import static com.adquem.grupologistics.utilities.AccountGeneral.sServerAuthenticate;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONObject;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.adquem.grupologistics.bussines.Buss_Login;
import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.dao.SyncLT;
import com.adquem.grupologistics.model.CatEstatusAparatos;
import com.adquem.grupologistics.model.CatSiNo;
import com.adquem.grupologistics.model.CatUso;
import com.adquem.grupologistics.model.ColumnasNom;
import com.adquem.grupologistics.model.Desglose;
import com.adquem.grupologistics.model.Factura;
import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.model.Nom;
import com.adquem.grupologistics.model.NomItem;
import com.adquem.grupologistics.model.Pais;
import com.adquem.grupologistics.model.RazonCierre;
import com.adquem.grupologistics.model.Referencia;
import com.adquem.grupologistics.model.UnidadMedida;
import com.adquem.grupologistics.model.Upc;
import com.adquem.grupologistics.utilities.AccountGeneral;
import com.adquem.grupologistics.utilities.Constantes;
import com.adquem.grupologistics.utilities.Utl_RESTful_Client;
import com.adquem.grupologistics.utilities.Utl_SharedPreferences;

public class Login extends AccountAuthenticatorActivity {

	public final static String ARG_ACCOUNT_TYPE = "ACCOUNT_TYPE";
	public final static String ARG_AUTH_TYPE = "AUTH_TYPE";
	public final static String ARG_ACCOUNT_NAME = "ACCOUNT_NAME";
	public final static String ARG_IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";
	public static final long MILLISECONDS_PER_SECOND = 1000L;
	public static final long SECONDS_PER_MINUTE = 60L;
	public static final long SYNC_INTERVAL_IN_MINUTES = 60L;
	public static final long SYNC_INTERVAL_IN_HOURS = 24L;
	public static final long SYNC_INTERVAL = SYNC_INTERVAL_IN_MINUTES
			* SECONDS_PER_MINUTE * MILLISECONDS_PER_SECOND
			* SYNC_INTERVAL_IN_HOURS;
	public static final String KEY_ERROR_MESSAGE = "ERR_MSG";
	public static final String AUTHORITY = "com.adquem.grupologistics.dao.MyContentProvider";
	public final static String PARAM_USER_PASS = "USER_PASS";

	private final int REQ_SIGNUP = 1;
	String[] Tablas;
	Editor editor;
	String fechacadu;
	String idioma;
	private final String TAG = this.getClass().getSimpleName();

	private AccountManager mAccountManager;
	private String mAuthTokenType;
	String userName;
	String userPass;
	Buss_Login buss;
	Utl_SharedPreferences preferencias;
	SharedPreferences preferences;
	Editor edit;
	Context app_context;
	boolean insertCat;

	private boolean status = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		app_context = getBaseContext();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.lyt_fragment_login);
		Tablas = Constantes.nomMetodosGet;
		preferencias = new Utl_SharedPreferences(getBaseContext());
		preferences = getSharedPreferences("MisPreferencias",
				Context.MODE_PRIVATE);
		edit = preferences.edit();

		editor = preferencias.getEditorPreferencesSettings();

		mAccountManager = AccountManager.get(getBaseContext());

		String accountName = getIntent().getStringExtra(ARG_ACCOUNT_NAME);
		mAuthTokenType = getIntent().getStringExtra(ARG_AUTH_TYPE);

		if (mAuthTokenType == null)
			mAuthTokenType = AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS;

		if (accountName != null) {
			((TextView) findViewById(R.id.txt_usuario)).setText(accountName);
		}

		Button btn_entrar = (Button) findViewById(R.id.btn_entrar);

		btn_entrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// getToken();

				buss = new Buss_Login();
				if (buss.DatosValidos(
						((TextView) findViewById(R.id.txt_usuario)).getText()
								.toString(),
						((TextView) findViewById(R.id.txt_password)).getText()
								.toString())) {
					userName = ((TextView) findViewById(R.id.txt_usuario))
							.getText().toString();
					userPass = ((TextView) findViewById(R.id.txt_password))
							.getText().toString();
					if (userName.equals(Constantes.ADMINISTRADOR_USUARIO)
							&& userPass
									.equals(Constantes.ADMINISTRADOR_PASSWORD)) {

						Intent intent1 = new Intent(Login.this,
								Act_Configuracion.class);
						startActivity(intent1);
						finish();
					}
					// else {
					// Toast.makeText(getBaseContext(),
					// R.string.str_toast_errorlogin,
					// Toast.LENGTH_LONG).show();
					// ((TextView) findViewById(R.id.txt_usuario))
					// .setText("");
					//
					// ((TextView) findViewById(R.id.txt_password))
					// .setText("");
					// }
					else {

						Log.v("Previo App", "Campo de usuario en login es:<"
								+ userName + ">");

						AccountManager accountManager = AccountManager
								.get(getBaseContext());
						Account[] accounts = accountManager
								.getAccountsByType("Grupo_Logistics");
						Account account = null;

						if (preferencias.getPreferencesSettings().getBoolean(
								Constantes.TITTLE_PREFERENCE_FIRST, true)
								&& accounts.length == 0) {
							MyContentProvider conc = new MyContentProvider();
							insertCat = true;
							Log.v("Previo-App",
									"Constantes.TITTLE_PREFERENCE_FIRST Login.insert cat first es:"
											+ insertCat);

							removeAlbumStorageDir();

							submit();

							// Realizamos cualquier otra operaciï¿½ï¿½n
							// necesaria
							// Creamos una nueva instancia y llamamos al
							// mï¿½ï¿½todo
							// ejecutar
							// pasï¿½ï¿½ndole el string.

						} else {

							// AccountManager accountManager = AccountManager
							// .get(getBaseContext());
							// Account[] accounts = accountManager
							// .getAccountsByType("Grupo_Logistics");
							// Account account = null;

							if (accounts.length == 1) {
								for (int i = 0; i < accounts.length; i++) {
									if (accounts[i].name.equals(userName)) {
										account = accounts[i];
										status = true;
										break;
									}
								}
								Log.v("Previo App", "Usuario en login es:"
										+ account);
								if (status) {
									if (preferences.getBoolean(
											Constantes.TITTLE_PREFERENCE_LOGON,
											false)) {
										if (userPass
												.equalsIgnoreCase(accountManager
														.getPassword(account))) {

											if (preferencias
													.getPreferencesSettings()
													.getBoolean(
															Constantes.TITTLE_PREFERENCE_FIRST,
															true)) {
												MyContentProvider conc = new MyContentProvider();
												insertCat = true;
												submit();

											} else {
												 ContentResolver.addPeriodicSync(
											                account,
											                AUTHORITY,
											                null,
											                SYNC_INTERVAL);
												Intent intent1 = new Intent(
														Login.this,
														Act_Main.class);
												intent1.putExtra("UserName",
														userName);
												startActivity(intent1);
												edit.putBoolean(
														Constantes.TITTLE_PREFERENCE_LOGON,
														true);
												edit.commit();
												finish();
											}
										} else {
											Toast.makeText(
													getBaseContext(),
													R.string.str_toast_errorlogin,
													Toast.LENGTH_LONG).show();
										}
									} else {
										// submit();
										if (account != null) {
											if (userPass
													.equals(accountManager
															.getPassword(account))) {

												if (preferencias
														.getPreferencesSettings()
														.getBoolean(
																Constantes.TITTLE_PREFERENCE_FIRST,
																true)) {
													MyContentProvider conc = new MyContentProvider();
													insertCat = true;
													submit();
												} else {
													  ContentResolver.addPeriodicSync(
												                account,
												                AUTHORITY,
												                null,
												                SYNC_INTERVAL);
													Intent intent1 = new Intent(
															Login.this,
															Act_Main.class);
													intent1.putExtra(
															"UserName",
															userName);
													startActivity(intent1);
													edit.putBoolean(
															Constantes.TITTLE_PREFERENCE_LOGON,
															true);
													edit.commit();
													finish();
												}

											} else {
												Toast.makeText(
														getBaseContext(),
														R.string.str_toast_errorlogin,
														Toast.LENGTH_LONG)
														.show();
											}
										} else {
											Toast.makeText(
													getBaseContext(),
													R.string.str_toast_errorlogin,
													Toast.LENGTH_LONG).show();
										}
									}

								} else {
									account = null;
									insertCat = true;
									Log.v("Previo-App",
											"else de estatus Login.insert cat first es:"
													+ insertCat);
									Toast.makeText(
											getBaseContext(),
											getText(R.string.str_toast_cuenta_asociada),

											Toast.LENGTH_LONG).show();
									/*
									 * Toast.makeText(getBaseContext(),
									 * R.string.str_toast_errorAccount,
									 * Toast.LENGTH_LONG).show(); submit();
									 */

								}
							} else {
								Toast.makeText(
										getBaseContext(),
										getText(R.string.str_toast_cuenta_asociada),

										Toast.LENGTH_LONG).show();

							}

						}
					}

				} else {
					Toast.makeText(getBaseContext(),
							R.string.str_toast_errorlogin, Toast.LENGTH_LONG)
							.show();
					((TextView) findViewById(R.id.txt_usuario)).setText("");

					((TextView) findViewById(R.id.txt_password)).setText("");
				}

				// userName = "salcibar";
				// userPass = "Prueba*#1";
				setAlbumStorageDir();
			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		// ContentValues updateValues = new ContentValues();
		// updateValues.put("Estatus",3 );
		// getContentResolver().update(
		// Uri.parse(MyContentProvider.URL + "Item"),
		// updateValues, " Estatus =4", null);
		// getContentResolver().update(
		// Uri.parse(MyContentProvider.URL + "Factura"),
		// updateValues, " Estatus =4", null);
		// getContentResolver().update(
		// Uri.parse(MyContentProvider.URL + "NomItem"),
		// updateValues, " Estatus =4", null);
		// getContentResolver().update(
		// Uri.parse(MyContentProvider.URL + "Referencia"),
		// updateValues, " Estatus =4", null);
		if (!preferencias.getPreferencesSettings().getBoolean(
				Constantes.TITTLE_PREFERENCE_FIRST, true)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");
			Date strDate = null;
			try {
				strDate = sdf.parse(preferencias.getPreferencesSettings()
						.getString(Constantes.TITTLE_EXPIRATION_TOKEN,
								"00/00/00"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				Log.e("ERROR conversion de la expiracion del token--------",
						e.toString());
				e.printStackTrace();
			}
			Date now = new Date();
			Boolean fecha = now.after(strDate);
			Boolean cons = preferences.getBoolean(
					Constantes.TITTLE_PREFERENCE_LOGON, false);
			if (preferences.getBoolean(Constantes.TITTLE_PREFERENCE_LOGON,
					false) && now.after(strDate)) {
				Intent intent1 = new Intent(Login.this, Act_Main.class);
				intent1.putExtra("UserName",
						preferences.getString(Constantes.TITTLE_USER_NAME, ""));
				startActivity(intent1);
				finish();
			} else {
				edit.putBoolean(Constantes.TITTLE_PREFERENCE_LOGON, false);
				edit.commit();
			}
		}

		super.onStart();

	}

	private boolean Sync() {
		// TODO Auto-generated method stub

		new MiTarea().execute();
		return true;

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// The sign up activity returned that the user has successfully created
		// an account

		MyContentProvider con = new MyContentProvider();
		if (requestCode == REQ_SIGNUP && resultCode == RESULT_OK) {
			finishLogin(data);
		} else
			super.onActivityResult(requestCode, resultCode, data);
	}

	public void submit() {

		final String accountType = getIntent().getStringExtra(ARG_ACCOUNT_TYPE);

		new AsyncTask<String, Void, Intent>() {

			@Override
			protected Intent doInBackground(String... params) {

				Log.d("Grupo_Logistics", TAG + "> Started authenticating");
				String token = null;
				String authtoken = null;
				Bundle data = new Bundle();
				try {
					authtoken = sServerAuthenticate.userSignIn(userName,
							userPass, mAuthTokenType);
					JSONObject arrau = new JSONObject(authtoken);
					// for(int i = 0; i < arrau.length(); i++){
					// JSONObject c = arrau.getJSONObject(i);
					token = arrau.getString("access_token");
					fechacadu = arrau.getString("expires_in");
					idioma = arrau.getString("idIdioma");
					buss.insertUser(token, fechacadu, idioma);

					// }
					editor.putString(Constantes.TITTLE_EXPIRATION_TOKEN,
							fechacadu);
					edit.putInt(Constantes.TITTLE_USER_LENGUAGE,
							Integer.parseInt(idioma));
					edit.putBoolean(Constantes.TITTLE_PREFERENCE_LOGON, true);
					edit.putString(Constantes.TITTLE_USER_NAME, userName);
					editor.putBoolean(Constantes.TITTLE_PREFERENCE_FIRST, false);
					editor.putString(Constantes.VALUE_PREFERENCE_TOKEN, token);
					editor.commit();
					edit.commit();

					data.putString(AccountManager.KEY_ACCOUNT_NAME, userName);
					data.putString(AccountManager.KEY_ACCOUNT_TYPE, accountType);
					data.putString(AccountManager.KEY_AUTHTOKEN, token);
					data.putString(PARAM_USER_PASS, userPass);

				} catch (Exception e) {
					data.putString(KEY_ERROR_MESSAGE, e.getMessage());
				}

				final Intent res = new Intent();
				res.putExtras(data);
				return res;
			}

			@Override
			protected void onPostExecute(Intent intent) {
				if (intent.hasExtra(KEY_ERROR_MESSAGE)) {
					Toast.makeText(getBaseContext(),
							getString(R.string.str_toast_erroserver),
							Toast.LENGTH_SHORT).show();
					Log.i("Error Login---------------------",
							intent.getStringExtra(KEY_ERROR_MESSAGE));
				} else {
					if (Sync())
						;
					finishLogin(intent);
				}
			}
		}.execute();
	}

	private void finishLogin(Intent intent) {
		Log.d("Previo", TAG + "> finishLogin");

		String accountName = intent
				.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
		String accountPassword = intent.getStringExtra(PARAM_USER_PASS);
		final Account account = new Account(accountName,
				AccountGeneral.ACCOUNT_TYPE);

		if (getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, true)) {
			Log.d("Previo", TAG + "> finishLogin > addAccountExplicitly");
			String authtoken = intent
					.getStringExtra(AccountManager.KEY_AUTHTOKEN);
			String authtokenType = mAuthTokenType;

			// Creating the account on the device and setting the auth token we
			// got
			// (Not setting the auth token will cause another call to the server
			// to authenticate the user)
			mAccountManager
					.addAccountExplicitly(account, accountPassword, null);
			mAccountManager.setAuthToken(account, authtokenType, authtoken);
			 ContentResolver.addPeriodicSync(
		                account,
		                AUTHORITY,
		                null,
		                SYNC_INTERVAL);
		} else {
			Log.d("Previo", TAG + "> finishLogin > setPassword");
			mAccountManager.setPassword(account, accountPassword);
		}

		setAccountAuthenticatorResult(intent.getExtras());
		setResult(RESULT_OK, intent);

		editor.putString(Constantes.VALUE_PREFERENCE_TOKEN,
				intent.getStringExtra(AccountManager.KEY_AUTHTOKEN));
		editor.commit();
	}

	@SuppressWarnings("deprecation")
	private String getToken() {

		// AccountManager accountManager = AccountManager.get(getBaseContext());
		// Account[] accounts =
		// accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE);
		// Account account = null;
		//
		//
		// if (accounts.length > 0) {
		// for (int i = 0; i < accounts.length; i++) {
		// if (accounts[i].name.equalsIgnoreCase("salcibar")){
		// account = accounts[i];
		// break;
		// }
		// }

		// accountManager.getAuthToken(account, AccountGeneral.ACCOUNT_TYPE,
		// null, this, new OnTokenAcquired(), null);
		new obtenerToken().execute();

		// } else {
		// account = null;
		// Toast.makeText(getBaseContext(), R.string.str_toast_errorAccount,
		// Toast.LENGTH_LONG).show();
		// }

		return "";
	}

	public class obtenerToken extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public void obtenerToken() {

		new AsyncTask<Void, Void, Void>() {

			@SuppressWarnings("deprecation")
			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub

				AccountManager accountManager = AccountManager
						.get(getBaseContext());
				Account[] accounts = accountManager
						.getAccountsByType(AccountGeneral.ACCOUNT_TYPE);
				Account account = null;

				if (accounts.length > 0) {
					for (int i = 0; i < accounts.length; i++) {
						if (accounts[i].name.equalsIgnoreCase(userName)) {
							account = accounts[i];
							break;
						}
					}
				}

				String newToken = null;
				try {
					newToken = accountManager
							.getAuthToken(account, AccountGeneral.ACCOUNT_TYPE,
									true, null, null).getResult()
							.getString(AccountManager.KEY_AUTHTOKEN);
					Log.i("Este es el token", newToken);

				} catch (OperationCanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AuthenticatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.i("token", newToken);

				return null;
			}

		};

	}

	// Metodo que regresa el estado de conexion del dispositivo
	public boolean isConnected() {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
			return true;
		else
			return false;
	}

	private class MiTarea extends AsyncTask<String, String, Void> {

		private ProgressDialog dialog;
		private String token = "";

		protected void onPreExecute() {

			token = preferencias.getPreferencesSettings().getString(
					Constantes.VALUE_PREFERENCE_TOKEN, "NA");

			dialog = new ProgressDialog(Login.this);
			dialog.setMessage(getString(R.string.str_progressdialog_synctittle));
			dialog.setTitle(getString(R.string.str_progressdialog_syncmessage));
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setCancelable(false);
			dialog.show(); // Mostramos el diï¿½ï¿½logo antes de comenzar
		}

		@SuppressWarnings("static-access")
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub

			if (token != "NA") {

				/**
			 * 
			 */
				Utl_RESTful_Client cleinte = new Utl_RESTful_Client();
				SyncLT sync = new SyncLT();
				// try {
				for (int i = 0; Tablas.length > i; i++) {

					switch (i) {
					case 0:
						if (insertCat) {
							ArrayList<CatUso> Usos = cleinte
									.getUsos(Constantes.VALUE_PREFERENCE_URL
											+ Tablas[i] + "?token_user="
											+ token);

							// objetoReferencias.get("Referencia");
							publishProgress(Tablas[i]
									+ " "
									+ app_context
											.getText(R.string.sync_cargando));
							// Log.i("Se descargo de " + Tablas[i]
							// + "----------------",
							// objetoReferencias.toString());
							sync.GETSync(Usos, i, Constantes.nomTablas,
									getContentResolver(), false);
						}
						break;
					case 1:
						if (insertCat) {
							ArrayList<Pais> Paises = cleinte
									.getPaises(Constantes.VALUE_PREFERENCE_URL
											+ Tablas[i] + "?token_user="
											+ token);

							// objetoReferencias.get("Referencia");
							publishProgress(Tablas[i]
									+ " "
									+ app_context
											.getText(R.string.sync_cargando));
							// Log.i("Se descargo de " + Tablas[i]
							// + "----------------",
							// objetoReferencias.toString());

							sync.GETSync(Paises, i, Constantes.nomTablas,
									getContentResolver(), false);
						}
						break;
					case 2:
						if (insertCat) {
							ArrayList<UnidadMedida> UnidadMedida = cleinte
									.getUnidadMedida(Constantes.VALUE_PREFERENCE_URL
											+ Tablas[i]
											+ "?token_user="
											+ token);

							// objetoReferencias.get("Referencia");
							publishProgress(Tablas[i]
									+ " "
									+ app_context
											.getText(R.string.sync_cargando));
							// Log.i("Se descargo de " + Tablas[i]
							// + "----------------",
							// objetoReferencias.toString());
							sync.GETSync(UnidadMedida, i, Constantes.nomTablas,
									getContentResolver(), false);
						}
						break;
					case 3:
						if (insertCat) {
							ArrayList<RazonCierre> RazonCierre = cleinte
									.getRazonCierre(Constantes.VALUE_PREFERENCE_URL
											+ Tablas[i]
											+ "?token_user="
											+ token);

							// objetoReferencias.get("Referencia");
							publishProgress(Tablas[i]
									+ " "
									+ app_context
											.getText(R.string.sync_cargando));
							// Log.i("Se descargo de " + Tablas[i]
							// + "----------------",
							// objetoReferencias.toString());
							sync.GETSync(RazonCierre, i, Constantes.nomTablas,
									getContentResolver(), false);
						}
						break;

					case 4:
						if (insertCat) {
							ArrayList<ColumnasNom> ColumnasNoms = cleinte
									.getColumnasNoms(Constantes.VALUE_PREFERENCE_URL
											+ Tablas[i]
											+ "?token_user="
											+ token);

							// objetoReferencias.get("Referencia");
							publishProgress(Tablas[i]
									+ " "
									+ app_context
											.getText(R.string.sync_cargando));
							// Log.i("Se descargo de " + Tablas[i]
							// + "----------------",
							// objetoReferencias.toString());
							sync.GETSync(ColumnasNoms, i, Constantes.nomTablas,
									getContentResolver(), false);
						}
						break;
					case 5:
						if (insertCat) {
							ArrayList<CatSiNo> SiNO = cleinte
									.getSiNo(Constantes.VALUE_PREFERENCE_URL
											+ Tablas[i] + "?token_user="
											+ token);

							// objetoReferencias.get("Referencia");
							publishProgress(Tablas[i]
									+ " "
									+ app_context
											.getText(R.string.sync_cargando));
							// Log.i("Se descargo de " + Tablas[i]
							// + "----------------",
							// objetoReferencias.toString());
							sync.GETSync(SiNO, i, Constantes.nomTablas,
									getContentResolver(), false);
						}
						break;
					case 6:
						if (insertCat) {
							ArrayList<CatEstatusAparatos> EstatusAparatos = cleinte
									.getCatEstatusAparatos(Constantes.VALUE_PREFERENCE_URL
											+ Tablas[i]
											+ "?token_user="
											+ token);

							// objetoReferencias.get("Referencia");
							publishProgress(Tablas[i]
									+ " "
									+ app_context
											.getText(R.string.sync_cargando));
							// Log.i("Se descargo de " + Tablas[i]
							// + "----------------",
							// objetoReferencias.toString());
							sync.GETSync(EstatusAparatos, i,
									Constantes.nomTablas, getContentResolver(),
									false);
						}
						break;
					case 7:
						ArrayList<Nom> Noms = cleinte
								.getNom(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + token);

						// objetoReferencias.get("Referencia");
						publishProgress(Tablas[i] + " "
								+ app_context.getText(R.string.sync_cargando));
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Noms, i, Constantes.nomTablas,
								getContentResolver(), false);
						break;
					case 8:
						ArrayList<Upc> Upcs = cleinte
								.getUpc(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + token);

						// objetoReferencias.get("Referencia");
						publishProgress(Tablas[i] + " "
								+ app_context.getText(R.string.sync_cargando));
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Upcs, i, Constantes.nomTablas,
								getContentResolver(), false);
						break;
					case 9:
						ArrayList<Referencia> Referencias = cleinte
								.getReferencia(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + token);

						// objetoReferencias.get("Referencia");
						publishProgress(Tablas[i] + " "
								+ app_context.getText(R.string.sync_cargando));
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						// Log.v("Previo-App", "Login.idUser es:" + idIns +
						// " para actualizar numrefs>" + Referencias.size());
						// for(int r = 0; r < Referencias.size(); r++){
						// Referencias.get(r).setUserId(Integer.valueOf(idIns));
						// }
						sync.GETSync(Referencias, i, Constantes.nomTablas,
								getContentResolver(), false);
						break;
					case 10:
						ArrayList<Factura> Facturas = cleinte
								.getFactura(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + token);

						// objetoReferencias.get("Referencia");
						publishProgress(Tablas[i] + " "
								+ app_context.getText(R.string.sync_cargando));
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Facturas, i, Constantes.nomTablas,
								getContentResolver(), false);
						break;
					case 11:
						ArrayList<Item> Items = cleinte
								.getItem(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + token);

						// objetoReferencias.get("Referencia");
						publishProgress(Tablas[i] + " "
								+ app_context.getText(R.string.sync_cargando));
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Items, i, Constantes.nomTablas,
								getContentResolver(), false);
						break;
					case 12:
						ArrayList<NomItem> NomsItems = cleinte
								.getNomItem(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + token);

						// objetoReferencias.get("Referencia");
						publishProgress(Tablas[i] + " "
								+ app_context.getText(R.string.sync_cargando));
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(NomsItems, i, Constantes.nomTablas,
								getContentResolver(), false);
						break;
					case 13:
						ArrayList<Desglose> Desgloses = cleinte
								.getDesglose(Constantes.VALUE_PREFERENCE_URL
										+ Tablas[i] + "?token_user=" + token);

						// objetoReferencias.get("Referencia");
						publishProgress(Tablas[i] + " "
								+ app_context.getText(R.string.sync_cargando));
						// Log.i("Se descargo de " + Tablas[i]
						// + "----------------",
						// objetoReferencias.toString());
						sync.GETSync(Desgloses, i, Constantes.nomTablas,
								getContentResolver(), false);
						break;

					default:
						break;
					}
				}

			}

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			dialog.setMessage(values[0]);
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Log.i("Final", "Termino el AsyncTask");

			if (dialog.isShowing()) {
				dialog.dismiss();
			}
			Intent intent1 = new Intent(Login.this, Act_Main.class);
			intent1.putExtra("UserName", userName);
			startActivity(intent1);
			finish();
		}
	}

	// Generar Directorios Fotos y Videos de Referencia e Ítem
	public void setAlbumStorageDir() {
		File path = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

		File file_refFotoInicio = new File(path,
				Constantes.RUTA_REF_FOTO_INICIO);
		File file_refFotoFin = new File(path, Constantes.RUTA_REF_FOTO_FIN);
		File file_refVideo = new File(path, Constantes.RUTA_REF_VIDEO);
		File file_itemFoto = new File(path, Constantes.RUTA_ITEM_FOTO);
		File file_itemVideo = new File(path, Constantes.RUTA_ITEM_VIDEO);

		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			if (!file_refFotoInicio.exists()) {
				file_refFotoInicio.mkdirs();
			}

			if (!file_refFotoFin.exists()) {
				file_refFotoFin.mkdirs();
			}

			if (!file_refVideo.exists()) {
				file_refVideo.mkdirs();
			}

			if (!file_itemFoto.exists()) {
				file_itemFoto.mkdirs();
			}

			if (!file_itemVideo.exists()) {
				file_itemVideo.mkdirs();
			}
		} else {
			Log.d("RutasDirs ", "External storage is not mounted READ/WRITE.");
		}
	}

	// Eliminar Directorios Fotos y Videos de Referencia e Ítem
	public void removeAlbumStorageDir() {
		File path = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {

			File fileItem = new File(path + Constantes.RUTA_ELIMINA_ITEMS);
			File fileRef = new File(path + Constantes.RUTA_ELIMINA_REFERENCIAS);

			if (fileItem.exists()) {
				String deleteCmd = "rm -r " + fileItem;
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec(deleteCmd);
				} catch (IOException e) {
				}
			}

			if (fileRef.exists()) {
				String deleteCmd = "rm -r " + fileRef;
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec(deleteCmd);
				} catch (IOException e) {
				}
			}
		} else {
			Log.d("RutasDirs ", "External storage is not mounted READ/WRITE.");
		}
	}

}

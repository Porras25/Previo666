package com.adquem.grupologistics.utilities;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.adquem.grupologistics.dao.MyContentProvider;
import com.adquem.grupologistics.model.Adjunto;
import com.adquem.grupologistics.model.CatEstatusAparatos;
import com.adquem.grupologistics.model.CatSiNo;
import com.adquem.grupologistics.model.CatUso;
import com.adquem.grupologistics.model.ColumnasNom;
import com.adquem.grupologistics.model.Desglose;
import com.adquem.grupologistics.model.Excedentes;
import com.adquem.grupologistics.model.Factura;
import com.adquem.grupologistics.model.Item;
import com.adquem.grupologistics.model.Nom;
import com.adquem.grupologistics.model.NomItem;
import com.adquem.grupologistics.model.Pais;
import com.adquem.grupologistics.model.RazonCierre;
import com.adquem.grupologistics.model.Referencia;
import com.adquem.grupologistics.model.RespuestaJSON;
import com.adquem.grupologistics.model.Respuestas;
import com.adquem.grupologistics.model.UnidadMedida;
import com.adquem.grupologistics.model.Upc;
import com.adquem.grupologistics.model.iResultado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

public class Utl_RESTful_Client {
	Context mContext;
	ContentResolver mContentResolver;
	ArrayList<iResultado> data;

	public Utl_RESTful_Client(Context context) {
		mContext = context;
		mContentResolver = context.getContentResolver();
	}

	public static HttpClient HttpGetClient() {

		// TODO Auto-generated constructor stub
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		int timeoutConnection = 50000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 50000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		return httpclient;
	}

	public static String GET(String url) {
		InputStream inputStream = null;
		String result = "";
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
			inputStream = httpResponse.getEntity().getContent();
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Problemas en el GET!";
		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}
		return result;
	}

	public static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;
	}

	public static Boolean PUT(String Url, final HttpPut post) {
		final HttpClient httpClient = new DefaultHttpClient();
		final Boolean resul = false;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// Your implementation goes here
					try {
						HttpResponse resp = httpClient.execute(post);
						String respStr = EntityUtils.toString(resp.getEntity());

						if (!respStr.equals("true"))
							;
						Log.i("Repuesta PUT", respStr);

					} catch (Exception e) {

						Log.e("Desde el PUT", e.toString());

					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}).start();

		return resul;

	}

	public Boolean POST(String Url, final HttpPost post) {
		final HttpClient httpClient = new DefaultHttpClient();
		final Boolean resul = false;

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// Your implementation goes here
					try {
						HttpResponse resp = httpClient.execute(post);
						String respStr = EntityUtils.toString(resp.getEntity());

						if (!respStr.equals("true"))
							;
						Log.i("Repuesta POST", respStr);
						// resul = false;
					} catch (Exception e) {
						// TODO: handle exception
						// Log.i("Verifica la integridad de la entidad ",
						// String.valueOf(i));
						Log.e("Desde el Post", e.toString());
						// resul = false;

					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}).start();

		return resul;

	}

	public ArrayList<Item> getItem(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<Item>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<Item> data = new ArrayList<Item>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<CatUso> getUsos(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<CatUso>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<CatUso> data = new ArrayList<CatUso>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<Pais> getPaises(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<Pais>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<Pais> data = new ArrayList<Pais>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<UnidadMedida> getUnidadMedida(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<UnidadMedida>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<UnidadMedida> data = new ArrayList<UnidadMedida>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<RazonCierre> getRazonCierre(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<RazonCierre>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<RazonCierre> data = new ArrayList<RazonCierre>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<ColumnasNom> getColumnasNoms(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<ColumnasNom>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<ColumnasNom> data = new ArrayList<ColumnasNom>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<CatSiNo> getSiNo(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<CatSiNo>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<CatSiNo> data = new ArrayList<CatSiNo>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<CatEstatusAparatos> getCatEstatusAparatos(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<CatEstatusAparatos>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<CatEstatusAparatos> data = new ArrayList<CatEstatusAparatos>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<Nom> getNom(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<Nom>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<Nom> data = new ArrayList<Nom>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<Upc> getUpc(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<Upc>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<Upc> data = new ArrayList<Upc>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<Referencia> getReferencia(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<Referencia>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<Referencia> data = new ArrayList<Referencia>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<Factura> getFactura(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<Factura>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<Factura> data = new ArrayList<Factura>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<NomItem> getNomItem(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<NomItem>>() {
		}.getType();
		Gson gson = new Gson();
		ArrayList<NomItem> data = new ArrayList<NomItem>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public ArrayList<Desglose> getDesglose(String Url) {

		java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<Desglose>>() {
		}.getType();
		Gson gson = new GsonBuilder().serializeNulls().create();
		ArrayList<Desglose> data = new ArrayList<Desglose>();
		HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Url));
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			data = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			Log.i("json array",
					"While getting server response server generate error. ");
		}

		return data;

	}

	public boolean putAdjuntoRef(String Url, List<Adjunto> Adjuntos) {

		boolean result = false;
		int concepto;
		for (int u = 0; Adjuntos.size() > u; u++) {
			switch (Adjuntos.get(u).getConcepto()) {
			case 5:
				concepto = 4;
				break;

			default:
				concepto = Adjuntos.get(u).getConcepto();
				break;
			}

			try {
				uploadFile(Adjuntos.get(u).getDescripcion(), Url + concepto);

				result = true;

			} catch (Exception e) {
				// TODO: handle exception
				result = false;

			}
		}
		return result;

	}

	public boolean putAdjuntoItm(String Url, List<Adjunto> Adjuntos) {

		boolean result = false;
		int concepto;
		// HttpClient httpClient = Utl_RESTful_Client.HttpGetClient();
		for (int u = 0; Adjuntos.size() > u; u++) {
			switch (Adjuntos.get(u).getConcepto()) {
			case 4:
				concepto = 3;
				break;

			default:
				concepto = Adjuntos.get(u).getConcepto();
				break;
			}

			// convert java object to JSON format,
			// and returned as JSON formatted string
			try {

				uploadFile(Adjuntos.get(u).getDescripcion(), Url + concepto);
				// HttpResponse response = httpClient.execute(new
				// HttpPost(Url));
				// String json = gson.toJson(Adjuntos);
				// post.setEntity(new StringEntity(json));
				result = true;
				// Log.i("PUT Adjuntos REF------------------>","se insertaron correctamente"
				// );
			} catch (Exception e) {
				// TODO: handle exception
				result = false;
				// e.toString();
				// Log.i("PUT Adjuntos REF ERROR------------------>",e.toString());
			}
		}
		return result;
	}

	public boolean putReferencia(String Url, List<Referencia> refrencia) {

		Gson gson = new Gson();
		boolean result;
		// DefaultHttpClient client = new DefaultHttpClient();

		HttpPut post = new HttpPut(Url);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Accept", "application/json");
		// add name value pairs

		try {
			String json = gson.toJson(refrencia);

			Log.i("PUT Referencia JSON------------------>", json);

			result = true;
			StringEntity entidad = new StringEntity(json, HTTP.UTF_8);
			post.setEntity(entidad);
			PUT(Url, post);
			ContentValues updateValues = new ContentValues();
			updateValues.put("Estatus", 4);
			mContentResolver.update(
					Uri.parse(MyContentProvider.URL + "Referencia"),
					updateValues, " Estatus =3", null);
			// ContentValues updateValues1 = new ContentValues();
			// updateValues1.put("Estatus", 4);
			// mContentResolver.update(
			// Uri.parse(MyContentProvider.URL + "Referencia"),
			// updateValues1, " Estatus =3", null);
			Log.i("PUT Referencias------------------>" + "",
					"se insertaron correctamente");
		} catch (Exception e) {
			// TODO: handle exception

			result = false;
			e.toString();
			Log.i("PUT Referencias ERROR------------------>", e.toString());
		}

		return result;

	}

	public Utl_RESTful_Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean putItem(String Url, List<Item> item) {

		Gson gson = new Gson();
		boolean result;
		// DefaultHttpClient client = new DefaultHttpClient();
		final HttpClient httpClient = new DefaultHttpClient();
		final HttpPut post = new HttpPut(Url);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Accept", "application/json");
		// add name value pairs

		try {
			String json = gson.toJson(item);
			Log.i("PUT ITEM JSON------------------>", json);

			result = true;
			StringEntity entidad = new StringEntity(json, HTTP.UTF_8);
			post.setEntity(entidad);
			Log.i("Json", new StringEntity(json).toString());
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						// Your implementation goes here
						try {
							HttpResponse resp = httpClient.execute(post);
							String respStr = EntityUtils.toString(resp
									.getEntity());

							if (!respStr.equals("true"))
								;
							Log.i("REspuesta de Items--------------", respStr);

							// resul = false;
						} catch (Exception e) {
							// TODO: handle exception
							// Log.i("Verifica la integridad de la entidad ",
							// String.valueOf(i));
							Log.e("Desde el PUT", e.toString());
							// resul = false;
							Log.i("PUT Item ERROR------------------>",
									e.toString());

						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}).start();

		} catch (Exception e) {
			// TODO: handle exception

			result = false;
			e.toString();
			Log.i("PUT Items ERROR------------------>", e.toString());
		}

		return result;

	}

	public static boolean putDesglose(String Url, List<Desglose> desglose) {

		Gson gson = new Gson();
		boolean result;
		// DefaultHttpClient client = new DefaultHttpClient();

		HttpPut post = new HttpPut(Url);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Accept", "application/json");
		// add name value pairs

		try {
			String json = gson.toJson(desglose);
			result = true;
			Log.i("PUT Desglose JSON------------------>", json);

			StringEntity entidad = new StringEntity(json, HTTP.UTF_8);
			post.setEntity(entidad);
			PUT(Url, post);
			Log.i("PUT Desglose------------------>",
					"se actualizaron correctamente");
		} catch (Exception e) {
			// TODO: handle exception

			result = false;
			e.toString();
			Log.i("PUT Desglose ERROR------------------>", e.toString());
		}

		return result;

	}

	public boolean postDesglose(String Url, List<Desglose> desglose) {

		Gson gson = new Gson();
		boolean result;
		// DefaultHttpClient client = new DefaultHttpClient();

		HttpPost post = new HttpPost(Url);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Accept", "application/json");
		// add name value pairs

		try {
			String json = gson.toJson(desglose);
			Log.i("POST Desglose JSON------------------>", json);

			result = true;
			StringEntity entidad = new StringEntity(json, HTTP.UTF_8);
			post.setEntity(entidad);
			POST(Url, post);
			Log.i("POST Desglose------------------>",
					"se insertaron correctamente");
		} catch (Exception e) {
			// TODO: handle exception

			result = false;
			e.toString();
			Log.i("POST Desglose ERROR------------------>", e.toString());
		}

		return result;

	}

	public boolean postRespustas(String Url, List<Respuestas> respuestas) {

		if (respuestas.size() > 0) {
			Gson gson = new Gson();
			boolean result;
			// DefaultHttpClient client = new DefaultHttpClient();

			HttpPost post = new HttpPost(Url);
			post.setHeader("Content-type", "application/json");
			post.setHeader("Accept", "application/json");
			// add name value pairs

			try {
				String json = gson.toJson(respuestas);
				Log.i("POST Respuesta JSON------------------>", json);

				result = true;
				StringEntity entidad = new StringEntity(json, HTTP.UTF_8);
				post.setEntity(entidad);
				POST(Url, post);
				Log.i("PUT Respuestas------------------>",
						"se insertaron correctamente");
			} catch (Exception e) {
				// TODO: handle exception

				result = false;
				e.toString();
				Log.i("PUT Respuestas ERROR------------------>", e.toString());
			}

			return result;
		}
		return false;
	}

	public static String uploadFile(String sourceFileUri, String upLoadServerUri) {

		String fileName = sourceFileUri;
		// String fileName =
		// "/storage/emulated/0/Pictures/Referencias/FotosInicio/7385/IMG_20140415_113124_7385_2053895087.jpg";
		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1 * 1024 * 1024;
		File sourceFile = new File(sourceFileUri);
		String serverResponseMessage = null;
		int serverResponseCode = 0;

		try {

			// open a URL connection to the Servlet
			FileInputStream fileInputStream = new FileInputStream(sourceFile);
			URL url = new URL(upLoadServerUri);

			// Open a HTTP connection to the URL
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true); // Allow Inputs
			conn.setDoOutput(true); // Allow Outputs
			conn.setUseCaches(false); // Don't use a Cached Copy
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("ENCTYPE", "multipart/form-data");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			conn.setRequestProperty("uploaded_file", fileName);

			dos = new DataOutputStream(conn.getOutputStream());

			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
					+ fileName + "\"" + lineEnd);

			// dos.writeBytes("Content-Disposition: form-data; name="uploaded_file";filename=""
			// + fileName + """ + lineEnd);

			dos.writeBytes(lineEnd);

			// create a buffer of maximum size
			bytesAvailable = fileInputStream.available();

			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];

			// read file and write it into form...
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			while (bytesRead > 0) {

				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			}

			// send multipart form data necesssary after file data...
			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			// Responses from the server (code and message)
			serverResponseCode = conn.getResponseCode();
			serverResponseMessage = conn.getResponseMessage();

			Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage
					+ ": " + serverResponseCode);

			fileInputStream.close();
			dos.flush();
			dos.close();

		} catch (MalformedURLException ex) {

			ex.printStackTrace();

			Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
		} catch (Exception e) {

			e.printStackTrace();

			Log.e("Upload file to server Exception",
					"Exception : " + e.getMessage(), e);
		}

		return serverResponseMessage;

	}

	public ArrayList<iResultado> putExcedente(String Url, Excedentes existentes) {
		// TODO Auto-generated method stub
		data = new ArrayList<iResultado>();
		final Gson gson = new Gson();
		boolean result;
		// DefaultHttpClient client = new DefaultHttpClient();

		final HttpClient httpClient = new DefaultHttpClient();
		final HttpPost post = new HttpPost(Url);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Accept", "application/json");
		// add name value pairs

		try {
			if (existentes != null) {
				String json = gson.toJson(existentes, Excedentes.class);
				Log.i("Excedentes JSON que se envia----------------------------",
						json);
				result = true;
				post.setEntity(new StringEntity(json, HTTP.UTF_8));
				Log.i("Json", new StringEntity(json).toString());
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							// Your implementation goes here
							try {
								List<iResultado> resultado= new ArrayList<iResultado>();
								HttpResponse resp = httpClient.execute(post);
								String respStr = EntityUtils.toString(resp
										.getEntity());
								java.lang.reflect.Type arrayListType = new TypeToken<ArrayList<iResultado>>() {
								}.getType();

								HttpEntity entity = resp.getEntity();
//								Reader reader = new InputStreamReader(entity
//										.getContent());
								resultado = gson.fromJson(respStr, arrayListType);

								// if (!respStr.equals("true"));
								Log.i("REspuesta de Excedentes--------------",
										respStr);
								if(resultado.size()>0){
									for(int u=0;resultado.size()>u;u++){
										ContentValues updateValues = new ContentValues();
										updateValues.put("IdItem", resultado.get(u).getIdItem());
										updateValues.put("IdItemApp",resultado.get(u).getIdItemApp() );
										mContentResolver.insert(
												Uri.parse(MyContentProvider.URL + "ItemExcedente"),
												updateValues);
									}
								
								
								}
								// resul = false;
							 } catch (Exception e) {
								// TODO: handle exception
								// Log.i("Verifica la integridad de la entidad ",
								// String.valueOf(i));
								Log.e("Desde el PUT", e.toString());
								// resul = false;
								Log.i("PUT Excedentes ERROR------------------>",
										e.toString());

							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}).start();
			} else {
				result = false;
			}

		} catch (Exception e) {
			// TODO: handle exception

			result = false;
			e.toString();
			Log.i("PUT Exedentes ERROR------------------>", e.toString());
		}

		return data;

	}
}

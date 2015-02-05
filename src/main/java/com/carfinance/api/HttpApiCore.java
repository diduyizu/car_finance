package com.carfinance.api;

import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

public class HttpApiCore {

	private final static Logger logger = Logger.getLogger(HttpApiCore.class);
	
	private static ThreadLocal<String> transactions = new ThreadLocal<String>();
	private static String USER_AGENT = "SpaceApi/1.0";
//	static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	private static final Integer DEFAULT_GET_REQUEST_TIMEOUT = 15000;
	private static final Integer DEFAULT_POST_REQUEST_TIMEOUT = 20000;
	
	//***********************外部提供接口调用基类********************************
//	public static <T> T getJsonObject(String json, Class<T> beanClass) {
//		return gson.fromJson(json, beanClass);
//	}
	
	private static String retrieveInputStream(HttpEntity httpEntity) throws Exception {
		StringBuilder stringBuffer = new StringBuilder();
		InputStreamReader inputStreamReader = new InputStreamReader(httpEntity.getContent(), HTTP.UTF_8);
		char buffer[] = new char[1024 * 9];
		int count;
		while ((count = inputStreamReader.read(buffer, 0, buffer.length)) > 0) {
			stringBuffer.append(buffer, 0, count);
		}
		return stringBuffer.toString().trim();
	}
	
	private static int parseInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return -1;
		}
	}
	
	private static void parseStatusCode(int code, String content) throws ServerException {
		switch (code) {
		case 200:
			break;
		case 304:
		case 401:
		case 400:
			if (content.contains(":")) {
				int index = content.indexOf(":");
				code = parseInt(content.substring(0, index));
				content = content.substring(index + 1);
			}
			throw new ServerException(code, content);
		case 403:
		case 404:
		case 500:
		case 502:
		case 503:
			throw new ServerException(code, content);
		}
	}
	
	private static String getRequest(String url, HttpClient client) throws ServerException, NetworkException {
		logger.info("HttpApi>>GET请求的url=" + url);
		String result = null;
		int statusCode = 0;
		HttpGet getMethod = new HttpGet(url);
		try {
			getMethod.setHeader("User-Agent", USER_AGENT);
			client.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, DEFAULT_GET_REQUEST_TIMEOUT);
			client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, DEFAULT_GET_REQUEST_TIMEOUT);
			HttpResponse httpResponse = client.execute(getMethod);
			statusCode = httpResponse.getStatusLine().getStatusCode();
			result = retrieveInputStream(httpResponse.getEntity()).trim();
			logger.info("HttpApi>>GET请求的返回�?="+result);
		} catch (Exception e) {
			throw new NetworkException(e);
		} finally {
			client.getConnectionManager().shutdown();
		}
		parseStatusCode(statusCode, result);
		return result;
	}
	
	//提供给外部调�?get方式
	public static String getRequest(String url) throws ServerException, NetworkException {
		return getRequest(url, new DefaultHttpClient(new BasicHttpParams()));
	}
	
	public static void setTransactions() {
		transactions.set(String.valueOf(System.currentTimeMillis()));
	}
	
	//提供给外部调用，post方式，调用post方式之前，需要先调用setTransactions方法设置transactions
	public static String postRequest(String url) throws ServerException, NetworkException {
		return postRequest(url, new DefaultHttpClient(new BasicHttpParams()), null);
	}
	
	private static String postRequest(String url, UrlEncodedFormEntity formParams) throws ServerException, NetworkException {
		return postRequest(url, new DefaultHttpClient(new BasicHttpParams()), formParams);
	}
	
	private static String postRequest(String url, HttpClient client, UrlEncodedFormEntity formParams) throws ServerException, NetworkException {
		logger.info("HttpApi>>POST请求的url=" + url);
		String code = transactions.get();
		if (code == null) {
			throw new ServerException(500, "请先调用setTransactions设置流水批号");
		}
		String result = null;
		int statusCode = 0;
		HttpPost postMethod = new HttpPost(url);
		try {
			postMethod.setHeader("code", code);
			postMethod.setHeader("User-Agent", USER_AGENT);
			if (formParams != null) {
				postMethod.setEntity(formParams);
			}
			client.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, DEFAULT_POST_REQUEST_TIMEOUT);
			client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, DEFAULT_POST_REQUEST_TIMEOUT);
			HttpResponse httpResponse = client.execute(postMethod);
			statusCode = httpResponse.getStatusLine().getStatusCode();
			result = retrieveInputStream(httpResponse.getEntity());
			logger.info("HttpApi>>POST请求的返回�?="+result);
		} catch (Exception e) {
			throw new NetworkException(e);
		} finally {
			transactions.remove();
			client.getConnectionManager().shutdown();
		}
		parseStatusCode(statusCode, result);
		return result;
	}
	//***********************外部提供接口调用基类********************************
}

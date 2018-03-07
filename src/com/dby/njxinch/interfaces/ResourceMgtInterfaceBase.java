package com.dby.njxinch.interfaces;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class ResourceMgtInterfaceBase {

	protected static Log log = LogFactory
			.getLog(ResourceMgtInterfaceBase.class);


	public JSONObject getJsonResult(String jsonStr) {
		JSONObject jsonResult = null;
		String msg = null;
		try {
			jsonResult = JSONObject.fromObject(jsonStr);
			if (jsonResult == null || jsonResult.isNullObject()
					|| jsonResult.isArray()) {
				msg = "json result formate error";
			} else {
				if (!jsonResult.containsKey("rc")) {
					msg = "json result formate error, param rc is not exist.";
				} else if (jsonResult.containsKey("values")) {
					try {
						jsonResult.getJSONArray("values");
					} catch (Exception e) {
						msg = e.getMessage();
					}
				} else if (jsonResult.containsKey("value")) {
					// TODO value是否只允许为非数组
					try {
						// jsonResult.getJSONObject("value");
					} catch (Exception e) {
						msg = e.getMessage();
					}
				}else if(jsonResult.containsKey("result")){
					
					try {
						jsonResult.getJSONArray("result");
					} catch (Exception e) {
						msg = e.getMessage();
					}
					
				}
			}
			if (msg != null) {
				JSONObject originResult = jsonResult;
				jsonResult = new JSONObject();
				jsonResult.put("rc", 907);
				jsonResult.put("msg", msg);
				jsonResult.put("originResult", originResult);
				jsonResult.put("value", new JSONArray()); //bugfix
			}

			return jsonResult;
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JSONObject();
			jsonResult.put("rc", 908);
			msg = "not json formate," + e.getMessage();
			jsonResult.put("msg", msg);
			jsonResult.put("originResult", jsonStr);
			jsonResult.put("value", new JSONArray()); //bugfix


		}
		return jsonResult;
	}
    //iaas 返回结果 转为JSONArray
	public JSONArray getJsonValues(JSONObject jsonResult) {
		JSONArray jsonValues = null;
		int rc = jsonResult.getInt("rc");
		if (rc == 0) {
			if (jsonResult.containsKey("values")) {
				try {
					jsonValues = jsonResult.getJSONArray("values");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (jsonValues == null && jsonResult.containsKey("value")) {
				try {
					jsonValues = jsonResult.getJSONArray("value");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (jsonValues == null) {
			jsonValues = new JSONArray();
		}
		return jsonValues;
	}
    
	

	

	public JSONObject getJsonByPostData(String apiUri ,JSONObject jsonData){
		String url  = apiUri;
		JSONObject jsonResult = null;
		if (!apiUri.startsWith("http")) {
			url =  apiUri;
		}
		long t1 = System.currentTimeMillis();
		long time = 0;
		String result = null;
		HttpResponse response = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();

			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-type", "application/json");
			StringEntity _entity = new StringEntity(jsonData.toString(), "UTF-8");
			httpPost.setEntity(_entity);
			
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity);
			}
			System.out.println("<==========>"+result);
		} catch (Exception e) {
			//e.printStackTrace();
			result =  "{rc:999," + "msg:\""+e.getMessage()+"\"," + "value:[]}";
		} finally {
			time = System.currentTimeMillis() - t1;
			if (log.isDebugEnabled()) {
				log.debug("end...getStrByPutData,time=" + time + "ms\r\nurl=" + url + "\r\nresult=" + result + "\r\n");
			}
		}
		jsonResult = getJsonResult(result);
		return jsonResult;
	}
		
	public JSONObject getJsonByGetData(String apiUri, String params) {
		JSONObject jsonResult = null;
		// 1. 拼接请求地址
		String url = apiUri;
		// 1.1 检查是否已包含http来判断是否需要添加baseUrl
		if (!apiUri.startsWith("http")) {
			url = apiUri;
		}

		// 1.2 添加参数param
		if (params != null && !"".equals(params.trim())) {
			if (params.startsWith("/") || params.startsWith("?") || params.startsWith("&")) {
				url = url + params;
			} else {
				url = url + "?" + params;
			}
		}
		System.out.println("url="+url);
		long t1 = System.currentTimeMillis();
		long time = 0;
		if (log.isDebugEnabled()) {
			log.debug("start getJsonByGetData\r\nurl=" + url + "\r\n");
		}
		
		String result = null;
		String originResult = null;
		// 2. get方法请求
		HttpResponse response = null;
		try {
			HttpClient client = getHttpClient();			
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("Content-Type", "text/html;charset=utf-8");

			response = client.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				result = EntityUtils.toString(httpEntity);
				EntityUtils.consume(httpEntity);
			}
			System.out.println("result="+result);
		} catch (Exception e) {
			// e.printStackTrace();
			result = "{rc:909," + "msg:\"" + e.getMessage() + "\"," + "value:[]}";
		} finally {
			time = System.currentTimeMillis() - t1;
			if (log.isDebugEnabled()) {
				log.debug("end...getStrByGetData,time=" + time + "ms\r\nurl=" + url + "\r\nresult=" + result + "\r\n");
			}
		}
		jsonResult = getJsonResult(result);
		return jsonResult;
	}
	
	//put
	public String getStrByPutData(String apiUri, String jsonData) {
		return getJsonByPutData(apiUri, jsonData).toString();
	}
    
	public JSONObject getJsonByPutData(String apiUri, String params) {
		JSONObject jsonParams = null;
		if (params != null) {
			jsonParams = JSONObject.fromObject(params);
		}
		return getJsonByPutData(apiUri, jsonParams);
	}
	
	public JSONObject getJsonByPutData(String apiUri, JSONObject jsonData) {
		return getJsonByPutPostData(apiUri, jsonData, false);
	}
	
//	public JSONObject getJsonByPostData(String apiUri, JSONObject jsonData) {
//		return getJsonByPutPostData(apiUri, jsonData, true);
//	}
	

	
	private JSONObject getJsonByPutPostData(String apiUri, JSONObject jsonData, boolean isPost) {

		JSONObject jsonResult = null;

		String url = apiUri;
		if (!apiUri.startsWith("http")) {
			url =  apiUri;
		}
		
		
		long t1 = System.currentTimeMillis();
		long time = 0;
		if (log.isDebugEnabled()) {
			log.debug("start getStrByPutData\r\nurl=" + url + "\r\njson="
					+ jsonData + "\r\n");
		}

		String result = null;
		HttpResponse response = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();

			
			HttpEntityEnclosingRequestBase httpMethod = null;
			
			if(isPost) {
				httpMethod = new HttpPost(url);
			} else {
				httpMethod = new HttpPut(url);
			}
			
			// HttpPost httpMethod = new HttpPost(url);
			
			httpMethod.setHeader("Content-type", "application/json");
			StringEntity _entity = new StringEntity(jsonData.toString(),
					"UTF-8");
			httpMethod.setEntity(_entity);

			response = httpClient.execute(httpMethod);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			result = "{rc:909," + "msg:\"" + e.getMessage() + "\","
					+ "value:[]}";
		} finally {
			time = System.currentTimeMillis() - t1;
			if (log.isDebugEnabled()) {
				log.debug("end...getStrByPutData,time=" + time + "ms\r\nurl="
						+ url + "\r\nresult=" + result + "\r\n");
			}

		}
		jsonResult = getJsonResult(result);
		return jsonResult;
	}
    
	
	/**
	 * get 请求  url 参数不做接口认证
	 */
	public JSONObject getJsonByDeleteData(String apiUri, JSONObject jsonParams) {
		String strParams = null;
		if (jsonParams != null && !jsonParams.isNullObject()) {
			Iterator<String> it = jsonParams.keys();
			while (it.hasNext()) {
				String key = it.next();
				if (strParams == null) {
					strParams = "";
				} else {
					strParams += "&";
				}
				try{
				strParams += key + "="
						+ jsonParams.getString(key);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return getJsonByDeleteData(apiUri, strParams);
		
	}
	
	/**
	 * get 请求  url 参数不做接口认证
	 */
	public JSONObject getJsonByGetData(String apiUri, JSONObject jsonParams) {
		String strParams = null;
		if (jsonParams != null && !jsonParams.isNullObject()) {
			Iterator<String> it = jsonParams.keys();
			while (it.hasNext()) {
				String key = it.next();
				if (strParams == null) {
					strParams = "";
				} else {
					strParams += "&";
				}
				try{
				strParams += key + "="
						+ jsonParams.getString(key);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return getJsonByGetData(apiUri, strParams);
		
	}
     
	/**
	 * get 请求  url 参数需做接口认证
	 */
	public JSONObject getJsonByGetData1(String apiUri, JSONObject jsonParams) {
		String strParams = null;
		if (jsonParams != null && !jsonParams.isNullObject()) {
			Iterator<String> it = jsonParams.keys();
			while (it.hasNext()) {
				String key = it.next();
				if (strParams == null) {
					strParams = "/";
				} else {
					strParams += "/";
				}
				try{
				strParams +=  
						jsonParams.getString(key);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return getJsonByGetData(apiUri, strParams);
		
	}
	
	public JSONObject getJsonByDeleteData(String apiUri, String params) {

		JSONObject jsonResult = null;

		// 1. 拼接请求地址
		String url = apiUri;
		// 1.1 检查是否已包含http来判断是否需要添加baseUrl
		if (!apiUri.startsWith("http")) {
			url =  apiUri;
		}

		// 1.2 添加参数param
		if (params != null && !"".equals(params.trim())) {
			if (params.startsWith("/") || params.startsWith("?")
					|| params.startsWith("&")) {
				url = url + params;
				
			} else {
				url = url + "?" + params;
				
			}
		}
		System.out.println("url="+url);
		long t1 = System.currentTimeMillis();
		long time = 0;
		if (log.isDebugEnabled()) {
			log.debug("start getJsonBydeleteData\r\nurl=" + url + "\r\n");
		}

		String result = null;
		String originResult = null;
		// 2. get方法请求
		HttpResponse response = null;
		try {
			HttpClient client = getHttpClient();			
			HttpDelete httpGet = new HttpDelete(url);
			httpGet.addHeader("Content-Type", "text/html;charset=utf-8");

			response = client.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				result = EntityUtils.toString(httpEntity);
				EntityUtils.consume(httpEntity);
			}
			System.out.println("result="+result);
		} catch (Exception e) {
			// e.printStackTrace();
			result = "{rc:909," + "msg:\"" + e.getMessage() + "\","
					+ "value:[]}";
		} finally {

			time = System.currentTimeMillis() - t1;
			if (log.isDebugEnabled()) {
				log.debug("end...getStrBydeleteData,time=" + time + "ms\r\nurl="
						+ url + "\r\nresult=" + result + "\r\n");
			}
		}
		jsonResult = getJsonResult(result);
		return jsonResult;

	}
	
	public String getHtmlByGetData(String apiUri,String header) {


		System.out.println("url="+apiUri);
		long t1 = System.currentTimeMillis();
		long time = 0;
		if (log.isDebugEnabled()) {
			log.debug("start gethtmlByGetData\r\nurl=" + apiUri + "\r\n");
		}

		String result = null;
		String originResult = null;
		// 2. get方法请求
		HttpResponse response = null;
		try {
			HttpClient client = getHttpClient();			
			HttpGet httpGet = new HttpGet(apiUri);
			httpGet.addHeader("Content-Type", "text/html;charset=utf-8");
			httpGet.addHeader("textqx", header);
//			signature(httpGet, apiUri);

			response = client.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				result = EntityUtils.toString(httpEntity);
				EntityUtils.consume(httpEntity);
			}
			System.out.println("result="+result);
		} catch (Exception e) {
			// e.printStackTrace();
			result = "{rc:909," + "msg:\"" + e.getMessage() + "\","
					+ "value:[]}";
		} finally {

			time = System.currentTimeMillis() - t1;
			if (log.isDebugEnabled()) {
				log.debug("end...getStrByGetData,time=" + time + "ms\r\nurl="
						+ apiUri + "\r\nresult=" + result + "\r\n");
			}
		}
		return result;

	}
	
	
	
	
	public JSONObject getJsonByGetData1(String apiUri, String params) {

		JSONObject jsonResult = null;

		// 1. 拼接请求地址
		String url = apiUri;
		// 1.1 检查是否已包含http来判断是否需要添加baseUrl
		if (!apiUri.startsWith("http")) {
			url =  apiUri;
		}

		// 1.2 添加参数param
		if (params != null && !"".equals(params.trim())) {
			if (params.startsWith("/") || params.startsWith("?")
					|| params.startsWith("&")) {
				url = url + params;
				
			} else {
				url = url + "?" + params;
				
			}
		}
		System.out.println("url="+url);
		long t1 = System.currentTimeMillis();
		long time = 0;
		if (log.isDebugEnabled()) {
			log.debug("start getJsonByGetData\r\nurl=" + url + "\r\n");
		}

		String result = null;
		String originResult = null;
		// 2. get方法请求
		HttpResponse response = null;
		try {
			HttpClient client = getHttpClient();			
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("Content-Type", "application/json;charset=utf-8");

			response = client.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				result = EntityUtils.toString(httpEntity);
				EntityUtils.consume(httpEntity);
			}
			System.out.println("result="+result);
		} catch (Exception e) {
			// e.printStackTrace();
			result = "{rc:909," + "msg:\"" + e.getMessage() + "\","
					+ "value:[]}";
		} finally {

			time = System.currentTimeMillis() - t1;
			if (log.isDebugEnabled()) {
				log.debug("end...getStrByGetData,time=" + time + "ms\r\nurl="
						+ url + "\r\nresult=" + result + "\r\n");
			}
		}
		jsonResult = getJsonResult(result);
		return jsonResult;

	}
	
	public String getStrByGetData(String apiUri, String params) {
		return getJsonByGetData(apiUri, params).toString();
	}
	
	// 得到HttpClient
	private HttpClient getHttpClient() {
		HttpParams mHttpParams = new BasicHttpParams();
		// 设置网络链接超时
		// 即:Set the timeout in milliseconds until a connection is established.
		HttpConnectionParams.setConnectionTimeout(mHttpParams, 4 * 1000);
		// 设置socket响应超时
		// 即:in milliseconds which is the timeout for waiting for data.
		HttpConnectionParams.setSoTimeout(mHttpParams, 30 * 1000);
		// 设置socket缓存大小
		HttpConnectionParams.setSocketBufferSize(mHttpParams, 30 * 1024);
		// 设置是否可以重定向
		HttpClientParams.setRedirecting(mHttpParams, true);

		HttpClient httpClient = new DefaultHttpClient(mHttpParams);
		return httpClient;
	}
}

package com.piccus.tools;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.log4j.Logger;

public abstract class Spider {
	private static Logger log = Logger.getLogger(Spider.class);
	
	//链接页面源代码
	private String pageSourceCode = "";
	//页面头信息
	private Header[] responseHeaders = null;
	//最大连接超时时间
	private static int connectTimeout = 3500;
	//最大读取超时时间
	private static int readTimeout = 3500;
	//最大连接重试次数
	private static int maxConnectTimes = 3;
	//网页编码方式
	private static String charsetName = "utf-8";
	private static HttpClient httpClient = new DefaultHttpClient();
	
	static{
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeout);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, readTimeout);
	}
	
	public boolean readPage(String urlStr, String charsetName, String method, HashMap<String, String> params){
		if("post".equals(method) || "POST".equals(method)){
			return readPageByPost(urlStr, charsetName, params);
		}else{
			return readPageByGet(urlStr, charsetName, params);
		}
		
	}

	private boolean readPageByGet(String urlStr, String charsetName, HashMap<String, String> params) {
		HttpGet getMethod = createGetMethod(urlStr, params);
		return readPage(getMethod, charsetName, urlStr);
	}
	
	private boolean readPageByPost(String urlStr, String charsetName, HashMap<String, String> params) {
		HttpPost getPost = createPostMethod(urlStr, params);
		return readPage(getPost, charsetName, urlStr);
	}
	
	private boolean readPage(HttpRequestBase getMethod, String charsetName, String urlStr) {
		// TODO Auto-generated method stub
		return false;
	}
	

	private HttpGet createGetMethod(String urlStr, HashMap<String, String> params) {
		
		return null;
	}
	
	private HttpPost createPostMethod(String urlStr, HashMap<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean readPageByGet(String urlStr, String charsetName){
		return this.readPageByGet(urlStr, charsetName, null);
	}
	
	public boolean readPageByPost(String urlStr, String charsetName){
		return this.readPageByPost(urlStr, charsetName, null);
	}
	
	public Header[] getHeader(){
		return responseHeaders;
	}
	
	public void setConnectTimeout(int timeout){
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
	}
	
	public void setReadTimeout(int timeout){
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
	}
	
	public static void  setMaxConnectionTimes(int maxConnectionTimes){
		Spider.maxConnectTimes = maxConnectionTimes;
	}
	
	public void setTimeout(int connectTimeout, int readTimeout){
		setConnectTimeout(connectTimeout);
		setReadTimeout(readTimeout);
	}
	
	public static void setCharsetName(String charsetName){
		Spider.charsetName = charsetName;
	}
	
}

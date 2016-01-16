package com.piccus.tools;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Spider {
	
	private static HttpClient httpClient = new DefaultHttpClient();
	
	public static void download(String str)throws Exception{
		String path = "http://" + str;
		HttpGet method = new HttpGet(path);
		HttpResponse httpResponse = httpClient.execute(method);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if(statusCode == HttpStatus.SC_OK){
			InputStream in = httpResponse.getEntity().getContent();
			FileOutputStream out = null;
			byte[] buffer = new byte[512];
			try{
				out = new FileOutputStream("demo.html");
				while(in.read(buffer) != -1){
					out.write(buffer);
				}
			}finally{
				in.close();
				out.close();
			}
		}
		System.out.print("ok");
	}
	
	
	public static void main(String[] args) {
		try {
			Spider.download("www.baidu.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

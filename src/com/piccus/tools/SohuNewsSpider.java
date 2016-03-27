package com.piccus.tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class SohuNewsSpider {

	//HaspMap�洢���������
	private static HashMap<String, String> hm = new HashMap<String, String>();
	//��ȡ����
	private static final String url = "http://pinglun.sohu.com/";
	//agent
	private static final String agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.231.154 Safari/537.36 LBBROWSER";
			
	/*
	 * @Author: Piccus
	 * @Descriptiuon: ��ʼ�����沢��ʼ��ȡ
	 */
	public static void initSpider(){
		try {
			getHashData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * @Author: Piccus
	 * @Description: ������ȡ����
	 */
	public static HashMap<String, String> getData(){
		return hm;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: ������ȡ���ݵ�HashMap
	 */
	private static void getHashData() throws IOException{
		Document doc = Jsoup.connect(url).userAgent(agent).get();
		Element content = doc.select("div.tp-news").get(3);
		Elements links = content.getElementsByClass("txt");
		for(Element link : links){
			Elements linka = link.select("a");
			hm.put(linka.attr("title"), linka.attr("href"));
		}
	}
}

package com.piccus.tools;


import java.io.IOException;
import java.util.HashMap;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class BaiduNewsSpider{
	//HaspMap�洢���������
	private HashMap<String, String> hm = new HashMap<String, String>();
	//��ȡ�ؼ���
	private String keyword = "";
	//ʵ����ȡ����
	private String realUrl = "http://news.baidu.com/ns?";
	//Ĭ��ʱ��˳����ȡ
	private final int sortByTime = 0;
	//Ĭ����ȡ����	
	private int page = 0;
	private int listMax = 20;
	private String allCount = "";
	//����UserAgent
	private final String agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.231.154 Safari/537.36 LBBROWSER";
		
	/*
	 * @param keyword
	 * @param filename
	 * @Author: Piccus
	 * @Description: ��ʼ�����沢��ʼ��ȡ 
	 */
	public void initSpider(String key){
		keyword = key;
		int pn = page * listMax;
		realUrl = realUrl + "pn=" + pn + "&word=" + keyword + "&tn=news&from=news&cl=2&rn=" + listMax + "ct=" + sortByTime;
		try {
			getHashData(realUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * @Author:Piccus
	 * @Description:������ȡ����
	 */
	public HashMap<String, String> getData(){
		return  hm;
	}
	
	/*
	 * @param url
	 * @Author: Piccus
	 * @Description: ������ȡ���ݵ�HashMap
	 */
	private void getHashData(String url) throws IOException{
		Document doc = Jsoup.connect(url).userAgent(agent).get();
		allCount = doc.getElementsByClass("nums").text().substring(7);
		Elements links = doc.getElementsByClass("result");
		for(Element link : links){
			Element linka = link.getElementsByClass("c-title").first().select("a").first();
			String linkHref = linka.attr("href");
			String linkText = linka.text();
			Element linksource = link.getElementsByClass("c-author").first();
			hm.put(linkText + " " + linksource.text(), linkHref);
		}	
	}
	
	public String getAllCount(){
		return allCount;
	}
	
	/*
	 * @param listMAx
	 * @Author: Piccus
	 * @Description: ������ȡ����
	 */
	public void setListMax(int listMax){
		this.listMax = listMax;
	}
	
	public void setPage(int page){
		this.page = page;
	}
}
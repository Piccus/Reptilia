package com.piccus.tools;


import java.io.IOException;
import java.util.HashMap;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public abstract class BaiduNewsSpider{
	//HaspMap存储标题和链接
	private static HashMap<String, String> hm = new HashMap<String, String>();
	//爬取关键字
	private static String keyword = "";
	//实际爬取链接
	private static String realUrl = "http://news.baidu.com/ns?";
	//默认时间顺序爬取
	private static final int sortByTime = 0;
	//默认爬取数量	
	private static int listMax = 20;
	//爬虫UserAgent
	private static final String agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.231.154 Safari/537.36 LBBROWSER";
		
	/*
	 * @param keyword
	 * @param filename
	 * @Author: Piccus
	 * @Description: 初始化爬虫并开始爬取 
	 */
	public static void initSpider(String key){
		keyword = key;
		realUrl = realUrl + "word=" + keyword + "&tn=news&from=news&rn=" + listMax + "ct=" + sortByTime;
		try {
			getHashData(realUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * @Author:Piccus
	 * @Description:返回爬取数据
	 */
	public static HashMap<String, String> getData(){
		return  hm;
	}
	
	/*
	 * @param url
	 * @Author: Piccus
	 * @Description: 保存爬取数据到HashMap
	 */
	private static void getHashData(String url) throws IOException{
		Document doc = Jsoup.connect(url).userAgent(agent).get();
		Elements links = doc.getElementsByClass("result");
		for(Element link : links){
			Element linka = link.getElementsByClass("c-title").first();
			String linkHref = linka.attr("href");
			String linkText = linka.text();
			Element linksource = link.getElementsByClass("c-author").first();
			hm.put(linkText + " " + linksource.text(), linkHref);
		}	
	}
	
	/*
	 * @param listMAx
	 * @Author: Piccus
	 * @Description: 设置爬取数量
	 */
	public static void setListMax(int listMax){
		BaiduNewsSpider.listMax = listMax;
	}
}
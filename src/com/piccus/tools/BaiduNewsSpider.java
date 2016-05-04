package com.piccus.tools;


import java.io.IOException;
import java.util.HashMap;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class BaiduNewsSpider{
	//HaspMap存储标题和链接
	private HashMap<String, String> hm = new HashMap<String, String>();
	//爬取关键字
	private String keyword = "";
	//实际爬取链接
	private String realUrl = "http://news.baidu.com/ns?";
	//默认时间顺序爬取
	private final int sortByTime = 0;
	//默认爬取数量	
	private int page = 0;
	private int listMax = 20;
	private String allCount = "";
	//爬虫UserAgent
	private final String agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.231.154 Safari/537.36 LBBROWSER";
		
	/*
	 * @param keyword
	 * @param filename
	 * @Author: Piccus
	 * @Description: 初始化爬虫并开始爬取 
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
	 * @Description:返回爬取数据
	 */
	public HashMap<String, String> getData(){
		return  hm;
	}
	
	/*
	 * @param url
	 * @Author: Piccus
	 * @Description: 保存爬取数据到HashMap
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
	 * @Description: 设置爬取数量
	 */
	public void setListMax(int listMax){
		this.listMax = listMax;
	}
	
	public void setPage(int page){
		this.page = page;
	}
}
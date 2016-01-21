package com.piccus.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class BaiduNewsSpider {
	
	private static HashMap<String, String> hm = new HashMap<String, String>();
	
	
	public static void saveList(String filename) throws IOException{

		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		for(Entry<String, String> entry : hm.entrySet()){
			bw.write(entry.getKey());
			bw.write(entry.getValue());
			bw.newLine();
		}
		bw.flush();
		bw.close();
		fw.close();
	}
	
	public static void showList(String url) throws IOException{
		Document doc = Jsoup.connect(url).get();
		Element content = doc.getElementById("content_left");
		Elements links = content.getElementsByClass("c-title");
		for(Element link : links){
			Elements linka = link.getElementsByTag("a");
			String linkHref = linka.attr("href");
			String linkText = linka.text();
			hm.put(linkText, linkHref);
		}	
	}
	
	
	public static void main(String args[]) throws IOException{
		BaiduNewsSpider.showList("http://news.baidu.com/ns?word=%E8%82%A1%E5%B8%82&pn=0&cl=2&ct=0&tn=news&rn=20&ie=utf-8&bt=0&et=0");
		BaiduNewsSpider.saveList("demo.txt");
	}
	
}
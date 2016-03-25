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


public class BaiduNewsSpider implements Runnable{
	//HaspMap�洢���������
	private static HashMap<String, String> hm = new HashMap<String, String>();
	//��ȡ�ؼ���
	private static String keyword = "";
	//ʵ����ȡ����
	private static String realUrl = "http://news.baidu.com/ns?";
	//���ݴ洢λ��
	private static String filepath = "";
	//Ĭ��ʱ��˳����ȡ
	private static final int sortByTime = 0;
	//Ĭ����ȡ����	
	private static int listMax = 20;
	//����UserAgent
	private static final String agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.231.154 Safari/537.36 LBBROWSER";
	
	/*
	 * @param keyword
	 * @param filename
	 * @Authro: Piccus
	 * @Description: ʵ����BaiduNewsSpider
	 */
	public BaiduNewsSpider(String key, String filename){
		keyword = key;
		filepath = filename;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: �߳̿�ʼ
	 */
	public void run(){
		initSpider(keyword, filepath);
	}
	
	/*
	 * @param keyword
	 * @param filename
	 * @Author: Piccus
	 * @Description: ��ʼ�����沢��ʼ��ȡ 
	 */
	private static void initSpider(String key, String filename){
		keyword = key;
		realUrl = realUrl + "word=" + keyword + "&tn=news&from=news&rn=" + listMax + "ct=" + sortByTime;
		try {
			showList(realUrl);
			saveList(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("success get " + listMax + " websites and saving to " + filename);
	}
	
	/*
	 * @param filename
	 * @Author: Piccus
	 * @Description: ����HashMap���ļ�
	 */
	private static void saveList(String filename) throws IOException{

		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		for(Entry<String, String> entry : hm.entrySet()){
			bw.write(entry.getKey());
			bw.write(entry.getValue());
			bw.newLine();
		}
		bw.flush();
		fw.close();
		bw.close();
	}
	
	/*
	 * @param url
	 * @Author: Piccus
	 * @Description: ������ȡ���ݵ�HashMap
	 */
	private static void showList(String url) throws IOException{
		Document doc = Jsoup.connect(url).userAgent(agent).get();
		Element content = doc.getElementById("content_left");
		Elements links = content.getElementsByClass("result");
		for(Element link : links){
			Elements linka = link.select("a");
			String linkHref = linka.attr("href");
			String linkText = linka.text();
			hm.put(linkText, linkHref);
		}	
	}
	
	/*
	 * @param listMAx
	 * @Author: Piccus
	 * @Description: ������ȡ����
	 */
	public void setListMax(int listMax){
		BaiduNewsSpider.listMax = listMax;
	}
	
	/*
	 * @Author: Piccus
	 * @Desciption: BaiduNewsSpider thread usage Demo
	 */
	public static void main(String args[]) throws IOException{
		BaiduNewsSpider spider = new BaiduNewsSpider("��Ʊ", "demo.txt");
		spider.setListMax(30);
		Thread thread = new Thread(spider);
		thread.start();
	}
	
}
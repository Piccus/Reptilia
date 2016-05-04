package com.piccus.core;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import com.piccus.tools.BaiduNewsSpider;
import com.piccus.tools.DBControl;

public class BaiduNewsThread{
	
	//爬取关键字
	private String keyword = "";
	//爬取数量
	private int listMax = 20; 
	//当前新闻页码
	private int page = 0;
	//新闻总数
	private String allCount = "";
	/*
	 * @param keyword listMax
	 * @Author: Piccus
	 * @Description: 实例化本类
	 */
	public BaiduNewsThread(String keyword, int listMax, int page){
		this.keyword = keyword;
		this.listMax = listMax;
		this.page = page;
	}

	public Vector getData(){
		
		Vector tm = new Vector();
		BaiduNewsSpider sp = new BaiduNewsSpider();
		sp.setListMax(listMax);
		sp.setPage(page);
		sp.initSpider(keyword);
		allCount = sp.getAllCount();
		HashMap<String, String> hm = sp.getData();
		for(Entry<String, String> entry : hm.entrySet()){
			tm.addElement(entry.getKey() + "   "+ entry.getValue());
		}
		return tm;
	}

	public String getAllCount(){
		return allCount;
	}
}
package com.piccus.core;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import com.piccus.tools.BaiduNewsSpider;
import com.piccus.tools.DBControl;

public class BaiduNewsThread{
	
	//��ȡ�ؼ���
	private String keyword = "";
	//��ȡ����
	private int listMax = 20; 
	//��ǰ����ҳ��
	private int page = 0;
	//��������
	private String allCount = "";
	/*
	 * @param keyword listMax
	 * @Author: Piccus
	 * @Description: ʵ��������
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
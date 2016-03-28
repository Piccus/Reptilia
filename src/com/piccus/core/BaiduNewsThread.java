package com.piccus.core;

import com.piccus.tools.BaiduNewsSpider;
import com.piccus.tools.DBControl;

public class BaiduNewsThread implements Runnable {
	
	//��ȡ�ؼ���
	private String keyword = "";
	//��ȡ����
	private int listMax = 20; 
	
	
	/*
	 * @param keyword listMax
	 * @Author: Piccus
	 * @Description: ʵ��������
	 */
	
	public BaiduNewsThread(String keyword,int listMax){
		this.keyword = keyword;
		this.listMax = listMax;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ�߳�
	 */
	@Override
	public void run() {
		initBaiduNewsThread();
	}

	/*
	 * @Author: Piccus
	 * @Description: ��ʼ��BaiduNewsSpider������DBControl���浽SQlite
	 */
	private void initBaiduNewsThread() {
		BaiduNewsSpider.setListMax(listMax);
		BaiduNewsSpider.initSpider(keyword);
		DBControl.saveBaiduNews(BaiduNewsSpider.getData());
	}
}
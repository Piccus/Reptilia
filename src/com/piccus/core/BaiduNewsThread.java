package com.piccus.core;

import com.piccus.tools.BaiduNewsSpider;
import com.piccus.tools.DBControl;

public class BaiduNewsThread {
	
	//��ȡ�ؼ���
	private String keyword = "";
	//��ȡ����
	private int listMax = 20; 
	//���ݿ�����
	private String dbName = "";
	
	/*
	 * @param keyword listMax dbname
	 * @Author: Piccus
	 * @Description: ʵ��������
	 */
	
	public BaiduNewsThread(String keyword,int listMax, String dbName){
		this.keyword = keyword;
		this.listMax = listMax;
		this.dbName = dbName;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ
	 */

	public void start() {
		initBaiduNewsThread();
	}

	/*
	 * @Author: Piccus
	 * @Description: ��ʼ��BaiduNewsSpider������DBControl���浽SQlite
	 */
	private void initBaiduNewsThread() {
		BaiduNewsSpider.setListMax(listMax);
		BaiduNewsSpider.initSpider(keyword);
		DBControl.changeDBName(dbName);
		DBControl.saveBaiduNews(BaiduNewsSpider.getData());
	}
}
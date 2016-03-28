package com.piccus.core;

import com.piccus.tools.BaiduNewsSpider;
import com.piccus.tools.DBControl;

public class BaiduNewsThread implements Runnable {
	
	//爬取关键字
	private String keyword = "";
	//爬取数量
	private int listMax = 20; 
	
	
	/*
	 * @param keyword listMax
	 * @Author: Piccus
	 * @Description: 实例化本类
	 */
	
	public BaiduNewsThread(String keyword,int listMax){
		this.keyword = keyword;
		this.listMax = listMax;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 开始线程
	 */
	@Override
	public void run() {
		initBaiduNewsThread();
	}

	/*
	 * @Author: Piccus
	 * @Description: 初始化BaiduNewsSpider并调用DBControl保存到SQlite
	 */
	private void initBaiduNewsThread() {
		BaiduNewsSpider.setListMax(listMax);
		BaiduNewsSpider.initSpider(keyword);
		DBControl.saveBaiduNews(BaiduNewsSpider.getData());
	}
}
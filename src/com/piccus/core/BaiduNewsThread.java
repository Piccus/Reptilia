package com.piccus.core;

import com.piccus.tools.BaiduNewsSpider;
import com.piccus.tools.DBControl;

public class BaiduNewsThread {
	
	//爬取关键字
	private String keyword = "";
	//爬取数量
	private int listMax = 20; 
	//数据库名称
	private String dbName = "";
	
	/*
	 * @param keyword listMax dbname
	 * @Author: Piccus
	 * @Description: 实例化本类
	 */
	
	public BaiduNewsThread(String keyword,int listMax, String dbName){
		this.keyword = keyword;
		this.listMax = listMax;
		this.dbName = dbName;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 开始
	 */

	public void start() {
		initBaiduNewsThread();
	}

	/*
	 * @Author: Piccus
	 * @Description: 初始化BaiduNewsSpider并调用DBControl保存到SQlite
	 */
	private void initBaiduNewsThread() {
		BaiduNewsSpider.setListMax(listMax);
		BaiduNewsSpider.initSpider(keyword);
		DBControl.changeDBName(dbName);
		DBControl.saveBaiduNews(BaiduNewsSpider.getData());
	}
}
package com.piccus.core;

import com.piccus.tools.BaiduNewsSpider;
import com.piccus.tools.DBControl;

public class BaiduNewsThread implements Runnable {
	
	//爬取关键字
	private String keyword = "";
	
	/*
	 * @param keyword
	 * @Author: Piccus
	 * @Description: 实例化本类
	 */
	
	public BaiduNewsThread(String keyword){
		this.keyword = keyword;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 开始线程
	 */
	@Override
	public void run() {
		initBaiduNewsThreadThread();
	}

	/*
	 * @Author: Piccus
	 * @Description: 初始化BaiduNewsSpider并调用DBControl保存到SQlite
	 */
	private void initBaiduNewsThreadThread() {
		BaiduNewsSpider.initSpider(keyword);
		DBControl.Save(BaiduNewsSpider.getData());
	}	
}

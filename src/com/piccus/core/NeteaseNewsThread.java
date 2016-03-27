package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.NeteaseNewsSpider;

public class NeteaseNewsThread implements Runnable{
	//新闻来源类型
	private static final int Netease = 1;
	
	/*
	 * @Author: Piccus
	 * @Description: 开始线程
	 */
	@Override
	public void run() {
		initNeteaseNewsThread();
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 初始化NeteaseNewsSpider并调用DBControl保存到SQlite
	 */
	private void initNeteaseNewsThread() {
		NeteaseNewsSpider.initSpider();
		DBControl.saveHeadlineNews(NeteaseNewsSpider.getData(), Netease);
	}
}

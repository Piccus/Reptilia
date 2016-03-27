package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.SinaNewsSpider;

public class SinaNewsThread implements Runnable{
	//新闻来源类型
	private static final int Sina = 0;
	
	/*
	 * @Author: Piccus
	 * @Description: 开始线程
	 */
	@Override
	public void run() {
		initSinaNewsThread();
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 初始化SinaNewsSpider并调用DBControl保存到SQlite
	 */
	private void initSinaNewsThread() {
		SinaNewsSpider.initSpider();
		DBControl.saveHeadlineNews(SinaNewsSpider.getData(), Sina);
	}
}

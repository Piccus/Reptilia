package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.SohuNewsSpider;

public class SohuNewsThread implements Runnable{
	//新闻来源类型
	private static final int Sohu = 2;
	
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
	 * @Description: 初始化SohuNewsSpider并调用DBControl保存到SQlite
	 */
	private void initSinaNewsThread() {
		SohuNewsSpider.initSpider();
		DBControl.saveHeadlineNews(SohuNewsSpider.getData(), Sohu);
	}
}

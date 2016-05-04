package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.NeteaseNewsSpider;

public class NeteaseNewsThread {
	//新闻来源类型
	private static final int Netease = 1;

	
	/*
	 * @param dbname
	 * @Author: Piccus
	 * @Description: 实例化本类
	 */
	public NeteaseNewsThread(){

	}
	
	/*
	 * @Author: Piccus
	 * @Description: 开始
	 */
	public void start() {
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

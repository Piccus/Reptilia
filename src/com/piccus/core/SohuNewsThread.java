package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.SohuNewsSpider;

public class SohuNewsThread {
	//新闻来源类型
	private static final int Sohu = 2;
	//数据库名称
	private String dbName = "";
	
	/*
	 * @param dbname
	 * @Author: Piccus
	 * @Description: 实例化本类
	 */
	public SohuNewsThread(String dbName){
		this.dbName = dbName;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 开始线程
	 */
	public void start() {
		initSinaNewsThread();
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 初始化SohuNewsSpider并调用DBControl保存到SQlite
	 */
	private void initSinaNewsThread() {
		SohuNewsSpider.initSpider();
		DBControl.changeDBName(dbName);
		DBControl.saveHeadlineNews(SohuNewsSpider.getData(), Sohu);
	}
}

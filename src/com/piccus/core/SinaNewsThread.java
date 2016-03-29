package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.SinaNewsSpider;

public class SinaNewsThread{
	//新闻来源类型
	private static final int Sina = 0;
	//数据库名称
	private String dbName = "";
	
	/*
	 * @param dbname
	 * @Author: Piccus
	 * @Description: 实例化本类
	 */
	public SinaNewsThread(String dbName){
		this.dbName = dbName;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 开始
	 */
	public void start() {
		initSinaNewsThread();
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 初始化SinaNewsSpider并调用DBControl保存到SQlite
	 */
	private void initSinaNewsThread() {
		SinaNewsSpider.initSpider();
		DBControl.changeDBName(dbName);
		DBControl.saveHeadlineNews(SinaNewsSpider.getData(), Sina);
	}
}

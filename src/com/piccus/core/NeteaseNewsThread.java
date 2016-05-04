package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.NeteaseNewsSpider;

public class NeteaseNewsThread {
	//������Դ����
	private static final int Netease = 1;

	
	/*
	 * @param dbname
	 * @Author: Piccus
	 * @Description: ʵ��������
	 */
	public NeteaseNewsThread(){

	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ
	 */
	public void start() {
		initNeteaseNewsThread();
	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ��NeteaseNewsSpider������DBControl���浽SQlite
	 */
	private void initNeteaseNewsThread() {
		NeteaseNewsSpider.initSpider();

		DBControl.saveHeadlineNews(NeteaseNewsSpider.getData(), Netease);
	}
}

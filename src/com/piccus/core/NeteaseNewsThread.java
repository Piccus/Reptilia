package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.NeteaseNewsSpider;

public class NeteaseNewsThread implements Runnable{
	//������Դ����
	private static final int Netease = 1;
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ�߳�
	 */
	@Override
	public void run() {
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

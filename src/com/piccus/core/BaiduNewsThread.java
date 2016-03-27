package com.piccus.core;

import com.piccus.tools.BaiduNewsSpider;
import com.piccus.tools.DBControl;

public class BaiduNewsThread implements Runnable {
	
	//��ȡ�ؼ���
	private String keyword = "";
	
	/*
	 * @param keyword
	 * @Author: Piccus
	 * @Description: ʵ��������
	 */
	
	public BaiduNewsThread(String keyword){
		this.keyword = keyword;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ�߳�
	 */
	@Override
	public void run() {
		initBaiduNewsThread();
	}

	/*
	 * @Author: Piccus
	 * @Description: ��ʼ��BaiduNewsSpider������DBControl���浽SQlite
	 */
	private void initBaiduNewsThread() {
		BaiduNewsSpider.initSpider(keyword);
		DBControl.saveBaiduNews(BaiduNewsSpider.getData());
	}	
}

package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.SinaNewsSpider;

public class SinaNewsThread implements Runnable{
	//������Դ����
	private static final int Sina = 0;
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ�߳�
	 */
	@Override
	public void run() {
		initSinaNewsThread();
	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ��SinaNewsSpider������DBControl���浽SQlite
	 */
	private void initSinaNewsThread() {
		SinaNewsSpider.initSpider();
		DBControl.saveHeadlineNews(SinaNewsSpider.getData(), Sina);
	}
}

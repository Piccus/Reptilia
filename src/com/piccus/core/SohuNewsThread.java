package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.SohuNewsSpider;

public class SohuNewsThread implements Runnable{
	//������Դ����
	private static final int Sohu = 2;
	
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
	 * @Description: ��ʼ��SohuNewsSpider������DBControl���浽SQlite
	 */
	private void initSinaNewsThread() {
		SohuNewsSpider.initSpider();
		DBControl.saveHeadlineNews(SohuNewsSpider.getData(), Sohu);
	}
}

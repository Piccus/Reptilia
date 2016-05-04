package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.SohuNewsSpider;

public class SohuNewsThread {
	//������Դ����
	private static final int Sohu = 2;

	
	/*
	 * @param dbname
	 * @Author: Piccus
	 * @Description: ʵ��������
	 */
	public SohuNewsThread(){

	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ�߳�
	 */
	public void start() {
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

package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.SinaNewsSpider;

public class SinaNewsThread{
	//������Դ����
	private static final int Sina = 0;

	
	/*
	 * @param dbname
	 * @Author: Piccus
	 * @Description: ʵ��������
	 */
	public SinaNewsThread(){

	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ
	 */
	public void start() {
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

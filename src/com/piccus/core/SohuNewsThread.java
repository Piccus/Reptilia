package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.SohuNewsSpider;

public class SohuNewsThread {
	//������Դ����
	private static final int Sohu = 2;
	//���ݿ�����
	private String dbName = "";
	
	/*
	 * @param dbname
	 * @Author: Piccus
	 * @Description: ʵ��������
	 */
	public SohuNewsThread(String dbName){
		this.dbName = dbName;
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
		DBControl.changeDBName(dbName);
		DBControl.saveHeadlineNews(SohuNewsSpider.getData(), Sohu);
	}
}

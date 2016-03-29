package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.SinaNewsSpider;

public class SinaNewsThread{
	//������Դ����
	private static final int Sina = 0;
	//���ݿ�����
	private String dbName = "";
	
	/*
	 * @param dbname
	 * @Author: Piccus
	 * @Description: ʵ��������
	 */
	public SinaNewsThread(String dbName){
		this.dbName = dbName;
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
		DBControl.changeDBName(dbName);
		DBControl.saveHeadlineNews(SinaNewsSpider.getData(), Sina);
	}
}

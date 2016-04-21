package com.piccus.core;

public class HeadlineNewsThread implements Runnable{
	
	private int sinaThreadMark = 0;
	
	private int neteaseThreadMark = 0;
	
	private int sohuThreadMark = 0;
	
	private String dbName = "";
	
	/*
	 * @param sinaThreadMark neteaseThreadMark sohuThreadMark dbName
	 * @Author: Piccus
	 * @Description: ʵ��������
	 */
	public HeadlineNewsThread(int sinaThreadMark, int neteaseThreadMark, int sohuThreadMark, String dbName){
		this.sinaThreadMark = sinaThreadMark;
		this.neteaseThreadMark = neteaseThreadMark;
		this.sohuThreadMark = sohuThreadMark;
		this.dbName = dbName;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: ��ʼ�߳� 
	 */	@Override
	public void run() {
		initHeadlineNewsThread();
	}

	/*
	 * @Author: Piccus
	 * @Description: ��ʼ��3С���沢����DBControl���浽SQlite
	 */
	private void initHeadlineNewsThread() {
		if(sinaThreadMark == 1){
			SinaNewsThread sinaNewsThread = new SinaNewsThread(dbName);
			sinaNewsThread.start();
		}
		if(neteaseThreadMark == 1){
			NeteaseNewsThread neteaseNewsThread = new NeteaseNewsThread(dbName);
			neteaseNewsThread.start();
		}
		if(sohuThreadMark == 1){
			SohuNewsThread sohuNewsThread = new SohuNewsThread(dbName);
			sohuNewsThread.start();
		}
	}
}

package com.piccus.core;

public class HeadlineNewsThread implements Runnable{
	
	private int sinaThreadMark = 0;
	
	private int neteaseThreadMark = 0;
	
	private int sohuThreadMark = 0;
	
	
	/*
	 * @param sinaThreadMark neteaseThreadMark sohuThreadMark dbName
	 * @Author: Piccus
	 * @Description: 实例化本类
	 */
	public HeadlineNewsThread(int sinaThreadMark, int neteaseThreadMark, int sohuThreadMark){
		this.sinaThreadMark = sinaThreadMark;
		this.neteaseThreadMark = neteaseThreadMark;
		this.sohuThreadMark = sohuThreadMark;
	}
	
	/*
	 * @Author: Piccus
	 * @Description: 开始线程 
	 */	@Override
	public void run() {
		initHeadlineNewsThread();
	}

	/*
	 * @Author: Piccus
	 * @Description: 初始化3小爬虫并调用DBControl保存到SQlite
	 */
	private void initHeadlineNewsThread() {
		if(sinaThreadMark == 1){
			SinaNewsThread sinaNewsThread = new SinaNewsThread();
			sinaNewsThread.start();
		}
		if(neteaseThreadMark == 1){
			NeteaseNewsThread neteaseNewsThread = new NeteaseNewsThread();
			neteaseNewsThread.start();
		}
		if(sohuThreadMark == 1){
			SohuNewsThread sohuNewsThread = new SohuNewsThread();
			sohuNewsThread.start();
		}
	}
	
	public static void main(String args[]){
		HeadlineNewsThread headlineNewsThread = new HeadlineNewsThread(1, 1, 1);
		Thread thread = new Thread(headlineNewsThread);
		thread.start();
	}
}

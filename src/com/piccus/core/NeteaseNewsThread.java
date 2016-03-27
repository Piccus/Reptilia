package com.piccus.core;

import com.piccus.tools.DBControl;
import com.piccus.tools.NeteaseNewsSpider;

public class NeteaseNewsThread implements Runnable{

	private static final int Netease = 1;
	
	@Override
	public void run() {
		initNeteaseNewsThread();
	}

	private void initNeteaseNewsThread() {
		NeteaseNewsSpider.initSpider();
		DBControl.saveHeadlineNews(NeteaseNewsSpider.getData(), Netease);
	}
	
	public static void main(String args[]){
		NeteaseNewsThread td = new NeteaseNewsThread();
		Thread t = new Thread(td);
		t.start();
	}

}

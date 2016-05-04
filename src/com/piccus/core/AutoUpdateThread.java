package com.piccus.core;

import java.util.Calendar;

public class AutoUpdateThread implements Runnable{

	@Override
	public void run() {
		try {
			initAutoUpdateThread();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initAutoUpdateThread() throws Exception {
		while(true){
			if(checkSystemTime()){
				SinaNewsThread sinaNewsThread = new SinaNewsThread();
				sinaNewsThread.start();
				NeteaseNewsThread neteaseNewsThread = new NeteaseNewsThread();
				neteaseNewsThread.start();
				SohuNewsThread sohuNewsThread = new SohuNewsThread();
				sohuNewsThread.start();
				Thread.currentThread().sleep(100000);
			}
			Thread.currentThread().sleep(10000);
		}
	}

	private boolean checkSystemTime() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		int minite = calendar.get(Calendar.MINUTE);
		if((hour == 9 || hour == 15 || hour == 19) && minite == 0){
			return true;
		}

		return false;
	}
}

package com.javacore.wisdom.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NetConfigCache {
	
	//NETCONFIG load sign
	private static boolean INIT_NETCONFIG_FINISHED=false;

	
	public static Map<String, String> netcgc = new ConcurrentHashMap<String,String>();

	
			
	public static synchronized boolean getInitNetConfigFinished() {
		return INIT_NETCONFIG_FINISHED;
	}
	
	public static synchronized void setInitNetConfigFinished(boolean finished) {
		 INIT_NETCONFIG_FINISHED=finished;
	}
	
	
	public static synchronized void Clear() {
		netcgc.clear();
	}

}

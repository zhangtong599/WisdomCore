package com.javacore.wisdom.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataConfigCache {
	
private static boolean INIT_DATACONFIG_FINISHED=false;
	
	
	public static Map<String, String> datacgc = new ConcurrentHashMap<>();
	
	public static synchronized boolean getInitDataConfigFinished() {
		return INIT_DATACONFIG_FINISHED;
	}
	
	public static synchronized void setInitDataConfigFinished(boolean finished) {
		INIT_DATACONFIG_FINISHED=finished;
	}
	
	
	public static synchronized void Clear() {
		datacgc.clear();
	}

}

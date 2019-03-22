package com.javacore.wisdom.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NodeConfigCache {
	
	//NODECONFIG load sign
	private static boolean INIT_NODECONFIG_FINISHED=false;
	
	
	public static Map<String, String> nodecgc = new ConcurrentHashMap<>();
	
	public static synchronized boolean getInitNodeConfigFinished() {
		return INIT_NODECONFIG_FINISHED;
	}
	
	public static synchronized void setInitNodeConfigFinished(boolean finished) {
		INIT_NODECONFIG_FINISHED=finished;
	}
	
	
	public static synchronized void Clear() {
		nodecgc.clear();
	}
}

package com.javacore.wisdom.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CheckConfigCache {
	private static boolean INIT_CHECKCONFIG_FINISHED = false;

	public static Map<String, String> checkcgc = new ConcurrentHashMap<>();

	public static synchronized boolean getInitCheckConfigFinished() {
		return INIT_CHECKCONFIG_FINISHED;
	}

	public static synchronized void setInitCheckConfigFinished(boolean finished) {
		INIT_CHECKCONFIG_FINISHED = finished;
	}

	public static synchronized void Clear() {
		checkcgc.clear();
	}
}

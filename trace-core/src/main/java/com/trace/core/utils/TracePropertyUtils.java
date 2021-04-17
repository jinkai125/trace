package com.trace.core.utils;

import com.trace.core.Constants;

public class TracePropertyUtils {

	public static String getProperty(String name) {
		String env = System.getenv(name);
		if (env != null && !env.trim().isEmpty()) {
			return env;
		}
		return System.getProperty(name, Constants.DEFAULT_TRACE_KEY);
	}
}

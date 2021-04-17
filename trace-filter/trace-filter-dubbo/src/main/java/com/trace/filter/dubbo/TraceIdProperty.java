package com.trace.filter.dubbo;

import com.trace.core.utils.TracePropertyUtils;

class TraceIdProperty {

	private static final String PROPERTY;
	
	private static final String KEY = "trace.dubbo.property";
	static {
		PROPERTY = TracePropertyUtils.getProperty(KEY);
	}
	
	static String get() {
		return PROPERTY;
	}
}

package com.trace.core;

import org.slf4j.MDC;
import com.trace.core.utils.TracePropertyUtils;

public class MDCTraceId implements TraceId {

	private static final TraceId INSTANCE;

	public static final String PROPERTY_KEY = "trace.mdc.property";
	
	private static final String KEY;
	static {
		KEY = TracePropertyUtils.getProperty(PROPERTY_KEY);
		INSTANCE = new MDCTraceId();
	}

	private MDCTraceId() throws RuntimeException {

	}

	@Override
	public String get() {
		try {
		    return (String) MDC.get(KEY);
		} catch (Exception e) {
			throw new RuntimeException("调用 MDC.get(key)方法异常", e);
		}
	}

	@Override
	public void set(String traceId) {
		try {
		    MDC.put(KEY, traceId);
		} catch (Exception e) {
			throw new RuntimeException("调用 MDC.put(key, value)方法异常", e);
		}
	}

	@Override
	public void clean() {
		try {
		    MDC.remove(KEY);
		} catch (Exception e) {
			throw new RuntimeException("调用 MDC.remove(key)方法异常", e);
		}
	}
	
	static TraceId getInstance() {
		return INSTANCE;
	}
}

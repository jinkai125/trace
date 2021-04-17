package com.trace.core;

import java.lang.reflect.Method;

import com.trace.core.utils.TracePropertyUtils;

public class MDCTraceId implements TraceId {

	private static final TraceId INSTANCE;

	private static final String PROPERTY_KEY = "trace.mdc.property";
	private static final String KEY;
	static {
		KEY = TracePropertyUtils.getProperty(PROPERTY_KEY);
		INSTANCE = new MDCTraceId();
	}
	private static final String LOG4J = "org.apache.log4j.MDC";

	private static final String SL4J = "org.slf4j.MDC";

	private final Method get;

	private final Method put;

	private final Method remove;

	private MDCTraceId() throws RuntimeException {

		Class<?> mdc = null;
		Class<?>[] putArgs = new Class<?>[] { String.class, Object.class };
		try {
			mdc = Class.forName(SL4J, false, Thread.currentThread().getContextClassLoader());
		} catch (ClassNotFoundException e) {
			try {
				mdc = Class.forName(LOG4J, false, Thread.currentThread().getContextClassLoader());
			} catch (ClassNotFoundException e1) {
				throw new RuntimeException(LOG4J + " or " + SL4J + " class not found");
			}
			putArgs[1] = String.class;
		}
		try {
			get = mdc.getMethod("get", String.class);
			put = mdc.getMethod("put", putArgs);
			remove = mdc.getMethod("remove", String.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String get() {
		try {
			return (String) get.invoke(null, KEY);
		} catch (Exception e) {
			throw new RuntimeException("调用 MDC.get(key)方法异常", e);
		}
	}

	@Override
	public void set(String traceId) {
		try {
			put.invoke(null, KEY, traceId);
		} catch (Exception e) {
			throw new RuntimeException("调用 MDC.put(key, value)方法异常", e);
		}
	}

	@Override
	public void clean() {
		try {
			remove.invoke(null, KEY);
		} catch (Exception e) {
			throw new RuntimeException("调用 MDC.remove(key)方法异常", e);
		}
	}
	
	static TraceId getInstance() {
		return INSTANCE;
	}
}

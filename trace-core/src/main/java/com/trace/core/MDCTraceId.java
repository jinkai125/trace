package com.trace.core;

import java.lang.reflect.Method;

public class MDCTraceId implements TraceId {

    private static final String LOG4J = "org.apache.log4j.MDC";

    private static final String SL4J = "org.slf4j.MDC";

    private final Method get;

    private final Method put;

    private final Method remove;

    private final String key;

    public MDCTraceId() throws ClassNotFoundException {
        this(Constants.TRACE_ID_KEY);
    }
    
    public MDCTraceId(String key) throws ClassNotFoundException {
        
        Class<?> mdc = null;
        Class<?>[] putArgs = new Class<?>[] {String.class, Object.class};
        try {
            mdc = Class.forName(SL4J, false, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            mdc = Class.forName(LOG4J, false, Thread.currentThread().getContextClassLoader());
            putArgs[1] = String.class;
        }
        try {
            get = mdc.getMethod("get", String.class);
            put = mdc.getMethod("put", putArgs);
            remove = mdc.getMethod("remove", String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.key = key;
    }

    @Override
    public String get() {
        try {
            return (String) get.invoke(null, key);
        } catch (Exception e) {
            throw new RuntimeException("调用 MDC.get(key)方法异常", e);
        }
    }

    @Override
    public void set(String traceId) {
        try {
            put.invoke(null, key, traceId);
        } catch (Exception e) {
            throw new RuntimeException("调用 MDC.put(key, value)方法异常", e);
        }
    }

    @Override
    public void clean() {
        try {
            remove.invoke(null, key);
        } catch (Exception e) {
            throw new RuntimeException("调用 MDC.remove(key)方法异常", e);
        }
    }
}

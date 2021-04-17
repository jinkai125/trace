package com.trace.core;

public class NativeTraceId implements TraceId {

	private static final InheritableThreadLocal<String> TRACE_ID = new InheritableThreadLocal<>();

	private static final TraceId INSTANCE = new NativeTraceId();
	private NativeTraceId() {
	}

	@Override
	public String get() {
		return TRACE_ID.get();
	}

	@Override
	public void set(String traceId) {
		TRACE_ID.set(traceId);
	}

	@Override
	public void clean() {
		TRACE_ID.remove();
	}

	static TraceId getInstance() {
		return INSTANCE;
	}
}

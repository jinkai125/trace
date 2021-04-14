package com.trace.core;

/**
 * TraceId 全链路的唯一标识,在整个链路中保持正常传递
 * 
 * @author jinkai125@126.com
 *
 */
public interface TraceId {

    /**
     * 获取当前链路内的traceId
     * 
     * @return
     */
    String get();

    /**
     * 设置当前链路的traceId
     * 
     * @param traceId
     */
    void set(String traceId);

    /**
     * 清空链路traceId
     */
    void clean();

    /**
     * 本地的traceId,可通过该对象完成全局链路
     */
    TraceId NATIVE = new TraceId() {

        private final InheritableThreadLocal<String> TRACE_ID = new InheritableThreadLocal<>();

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
    };
    
    AdapterTraceId DEFAULT_ADAPTER = new AdapterTraceId();
}

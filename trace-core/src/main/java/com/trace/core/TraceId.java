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

    TraceId ADAPTER = new AdapterTraceId();
}

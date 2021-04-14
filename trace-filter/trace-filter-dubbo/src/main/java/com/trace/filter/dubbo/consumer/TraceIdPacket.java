package com.trace.filter.dubbo.consumer;

import com.trace.core.TraceId;

public interface TraceIdPacket {

    void packet(TraceId traceId);
}

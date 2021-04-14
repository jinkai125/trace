package com.trace.filter.dubbo.support;

import org.apache.dubbo.rpc.RpcContext;
import com.trace.core.TraceId;
import com.trace.filter.dubbo.config.DubboTraceIdConfig;
import com.trace.filter.dubbo.consumer.TraceIdPacket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultTraceIdPacket implements TraceIdPacket {

    private DubboTraceIdConfig config = new DubboTraceIdConfig();
    
    @Override
    public void packet(TraceId traceId) {
        RpcContext.getContext().setAttachment(config.getKey(), traceId.get());
    }
}

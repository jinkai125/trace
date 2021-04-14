package com.trace.filter.dubbo.support;

import java.util.UUID;
import org.apache.dubbo.rpc.RpcContext;
import com.trace.filter.dubbo.config.DubboTraceIdConfig;
import com.trace.filter.dubbo.provider.TraceIdReslover;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultTraceIdReslover implements TraceIdReslover {

    private DubboTraceIdConfig config = new DubboTraceIdConfig();
    
    @Override
    public String reslover() {
        String traceId = RpcContext.getContext().getAttachment(config.getKey());
        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString().replace("-", "");
            RpcContext.getContext().setAttachment(config.getKey(), traceId);
        }
        return traceId;
    }
}

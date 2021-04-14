package com.trace.filter.dubbo.consumer;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import com.trace.core.TraceId;
import com.trace.filter.dubbo.support.DefaultTraceIdPacket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Activate(group = {CommonConstants.CONSUMER})
public class DubboConsumerTraceFilter implements Filter {

    private TraceIdPacket packet = new DefaultTraceIdPacket();

    private TraceId traceId = TraceId.NATIVE;

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        packet.packet(traceId);
        return invoker.invoke(invocation);
    }
}

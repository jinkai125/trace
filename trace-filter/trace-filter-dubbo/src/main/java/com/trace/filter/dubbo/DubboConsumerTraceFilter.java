package com.trace.filter.dubbo;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

import com.trace.core.TraceId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Activate(group = {CommonConstants.CONSUMER})
public class DubboConsumerTraceFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String property = TraceIdProperty.get();
        RpcContext.getContext().setAttachment(property, TraceId.ADAPTER.get());
        return invoker.invoke(invocation);
    }
}

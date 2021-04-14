package com.trace.filter.dubbo.provider;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import com.trace.core.TraceId;
import com.trace.filter.dubbo.support.DefaultTraceIdReslover;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Activate(group = {CommonConstants.PROVIDER})
public class DubboProviderTraceFilter implements Filter {

    private TraceIdReslover reslover = new DefaultTraceIdReslover();

    private TraceId traceId = TraceId.NATIVE;

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            this.traceId.set(reslover.reslover());
            return invoker.invoke(invocation);
        } finally {
            traceId.clean();
        }
    }
}

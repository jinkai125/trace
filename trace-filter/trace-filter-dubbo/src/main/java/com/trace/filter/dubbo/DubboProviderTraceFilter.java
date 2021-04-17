package com.trace.filter.dubbo;

import java.util.UUID;

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
@Activate(group = { CommonConstants.PROVIDER })
public class DubboProviderTraceFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		TraceId.ADAPTER.set(getTraceId());
		try {
			return invoker.invoke(invocation);
		} finally {
			TraceId.ADAPTER.clean();
		}
	}

	private String getTraceId() {
		String property = TraceIdProperty.get();
		String traceId = RpcContext.getContext().getAttachment(property);
		if (traceId == null || traceId.isEmpty()) {
			traceId = UUID.randomUUID().toString().replace("-", "");
			RpcContext.getContext().setAttachment(property, traceId);
		}
		return traceId;
	}
}

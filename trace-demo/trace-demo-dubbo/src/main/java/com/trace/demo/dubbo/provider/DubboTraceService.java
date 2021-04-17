package com.trace.demo.dubbo.provider;

import com.trace.core.TraceId;

public interface DubboTraceService {

    default void helloWorld() {
        System.out.println(TraceId.ADAPTER.get());
        TraceId.ADAPTER.set("222");
    }
}

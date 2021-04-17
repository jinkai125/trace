package com.trace.demo.dubbo.consumer;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import com.trace.core.TraceId;
import com.trace.demo.dubbo.provider.DubboTraceService;

public class TraceConsumer {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        TraceId.ADAPTER.set("11111");
        ApplicationConfig application = new ApplicationConfig();
        application.setName("DubboTraceProvider");
        ReferenceConfig<DubboTraceService> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        RegistryConfig registry = new RegistryConfig("zookeeper://127.0.0.1:2181");
        reference.setRegistry(registry);
        reference.setInterface(DubboTraceService.class);
        reference.setVersion("1.0.0");
        reference.setTimeout(60000);

        DubboTraceService dubboTraceService = reference.get();
        dubboTraceService.helloWorld();
        System.out.println(TraceId.ADAPTER.get());
    }
}

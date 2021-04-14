package com.trace.demo.dubbo.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class TraceServer {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws InterruptedException {
        DubboTraceService downloadService = new DubboTraceService() {
        };
        ApplicationConfig application = new ApplicationConfig();
        application.setName("DubboTraceProvider");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setPort(12345);
        protocol.setId("dubbo");
        protocol.setName("dubbo");
        protocol.setThreads(28808);
        protocol.setDispatcher("message");

        RegistryConfig registry = new RegistryConfig("zookeeper://127.0.0.1:2181");
        registry.setId("DubboTraceProvider");

        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setFilter("dubboProviderTraceFilter");
        providerConfig.setTimeout(55000);
        providerConfig.setRetries(0);
        providerConfig.setPayload(83886080);

        ServiceConfig<DubboTraceService> service = new ServiceConfig<>();
        service.setApplication(application);
        service.setRegistry(registry);
        service.setProtocol(protocol);
        service.setProvider(providerConfig);
        service.setInterface(DubboTraceService.class);
        service.setRef(downloadService);
        service.setVersion("1.0.0");
        service.export();
        System.out.println("started");
        Thread.currentThread().join();
    }
}

package com.trace.filter.tomcat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletRequest;

public class DefaultTomcatTraceIdResolver implements TomcatTraceIdResolver {

    private TomcatTraceConfig config = new TomcatTraceConfig();

    private Map<String, InnerTraceIdFind> inners = Collections.synchronizedMap(new HashMap<>());

    @Override
    public String resolver(ServletRequest request) {
        String traceId = inners.getOrDefault(config.getSource(), new InnerTraceIdFind() {
        }).find(config.getKey(), request);
        if (traceId == null || traceId.isEmpty()) {
            return UUID.randomUUID().toString().replace("-", "");
        }
        return traceId;
    }

    public interface InnerTraceIdFind {

        default String find(String key, ServletRequest request) {
            return UUID.randomUUID().toString().replace("-", "");
        }
    }

    public class ParameterInnerTraceIdFind implements InnerTraceIdFind {
        @Override
        public String find(String key, ServletRequest request) {
            return request.getParameter(key);
        }
    }

    public class AttributeInnerTraceIdFind implements InnerTraceIdFind {
        @Override
        public String find(String key, ServletRequest request) {
            return (String) request.getAttribute(key);
        }
    }
}

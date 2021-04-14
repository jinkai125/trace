package com.trace.filter.tomcat;

import java.util.UUID;
import javax.servlet.ServletRequest;
import com.trace.core.Constants;

public interface TraceIdResolver {

    default String resolver(ServletRequest request) {
        String traceId = request.getParameter(Constants.TRACE_ID_KEY);
        if (traceId == null || traceId.isEmpty()) {
            return UUID.randomUUID().toString().replace("-", "");
        }
        return traceId;
    }
}

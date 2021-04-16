package com.trace.filter.tomcat;

import javax.servlet.ServletRequest;

public interface TomcatTraceIdResolver {

    String resolver(ServletRequest request);
}

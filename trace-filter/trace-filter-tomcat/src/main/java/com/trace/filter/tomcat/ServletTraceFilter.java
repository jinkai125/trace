package com.trace.filter.tomcat;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.trace.core.TraceId;
import com.trace.core.utils.TracePropertyUtils;

/**
 * 提供通过Servlet提供的filter进行拦截处理完成日志的链路id的出入口
 * @author jinkai125@126.com
 *
 */
public class ServletTraceFilter implements Filter {

	private String key;
	private static final String NAME = "trace.servlet.property";
	
	private TraceIdResolver resolver;
	public ServletTraceFilter() {
		this(null);
	}
	
	public ServletTraceFilter(TraceIdResolver resolver) {
		this.resolver = resolver;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if (resolver != null) {
			return;
		}
		key = filterConfig.getInitParameter(NAME);
		if (key == null || key.isEmpty()) {
			key = TracePropertyUtils.getProperty(NAME);
		}
		resolver = new TraceIdResolver() {
			@Override
			public String resolver(ServletRequest request) {
				return request.getParameter(key);
			}
		};
	}
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String traceId = resolver.resolver(request);
            TraceId.ADAPTER.set(traceId);
            chain.doFilter(request, response);
        } finally {
        	TraceId.ADAPTER.clean();
        }
    }
}

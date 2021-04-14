package com.trace.filter.tomcat;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.trace.core.TraceId;
import lombok.Getter;
import lombok.Setter;

/**
 * 提供通过Servlet提供的filter进行拦截处理完成日志的链路id的出入口
 * @author jinkai125@126.com
 *
 */
@Getter
@Setter
public class TomcatTraceFilter implements Filter {

    /** 链路id分解器,实现对请求链路id的提取,若不设置则使用默认的提取器 */
    private TraceIdResolver resolver = new TraceIdResolver() {
    };
    
    /** traceId,推荐自己重新设置,满足日志打印时的自定义处理 */
    private TraceId traceId = TraceId.NATIVE;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String traceId = resolver.resolver(request);
            this.traceId.set(traceId);
            chain.doFilter(request, response);
        } finally {
            this.traceId.clean();
        }
    }
}

package com.trace.demo.tomcat;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.trace.filter.tomcat.ServletTraceFilter;
import com.trace.filter.tomcat.TraceIdResolver;

@Configuration
public class FilterConfiguration {

    /**
     * 添加过滤器,过滤器内完成配置Filter
     * @param traceIdResolver
     * @return
     */
    @Bean
    public FilterRegistrationBean<Filter> traceIdFilter(TraceIdResolver traceIdResolver) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        // 配置Servlet Filter,过滤器内可完成提取TraceId,traceId可通过自定义解析完成提取也可以通过默认方式提取
        filterRegistrationBean.setFilter(new ServletTraceFilter(traceIdResolver));
        filterRegistrationBean.addUrlPatterns("*");
        // 设置初始化参数
        Map<String, String> init = new HashMap<>();
        init.put(ServletTraceFilter.NAME, "TraceId");
        filterRegistrationBean.setInitParameters(init);
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
    
    /**
     * 此处定义了自定义的TraceId提取器,通过parameter内提取TraceId完成提取
     * @return
     */
    @Bean
    public TraceIdResolver traceIdResolver() {
        return new TraceIdResolver() {};
    }
}

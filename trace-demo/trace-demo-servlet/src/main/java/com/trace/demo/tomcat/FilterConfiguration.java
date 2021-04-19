package com.trace.demo.tomcat;

import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.trace.filter.tomcat.ServletTraceFilter;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<Filter> traceIdFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new ServletTraceFilter());
        filterRegistrationBean.addUrlPatterns("*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}

package com.trace.filter.tomcat;

import java.util.UUID;
import javax.servlet.ServletRequest;

/**
 * TraceId提取,通过resolver解析除请求中的TraceId
 *
 * @author 金凯
 * @email jinkai125@126.com
 * @date 创建日期：2021年4月17日
 */
public interface TraceIdResolver {

    /**
     * 解析
     * 
     * @param name
     * @param request
     * @return
     */
    default String resolver(String name, ServletRequest request) {
        String traceId = request.getParameter(name);
        return traceId == null || traceId.isEmpty() ? UUID.randomUUID().toString().replace("-", "")
                : traceId;
    }
}

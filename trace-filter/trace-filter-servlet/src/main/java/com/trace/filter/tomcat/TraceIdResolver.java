package com.trace.filter.tomcat;

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
	 * @param request
	 * @return
	 */
    String resolver(ServletRequest request);
}

package com.trace.filter.tomcat;

import com.trace.core.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TomcatTraceConfig {
    /** 对应traceId的key */
    private String key = Constants.TRACE_ID_KEY;
    /** traceId提取源,默认支持attribute和parameter */
    private String source = "parameter";
}

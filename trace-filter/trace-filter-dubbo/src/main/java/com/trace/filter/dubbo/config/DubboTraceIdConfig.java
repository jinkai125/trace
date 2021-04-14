package com.trace.filter.dubbo.config;

import com.trace.core.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DubboTraceIdConfig {

    private String key = Constants.TRACE_ID_KEY;
}

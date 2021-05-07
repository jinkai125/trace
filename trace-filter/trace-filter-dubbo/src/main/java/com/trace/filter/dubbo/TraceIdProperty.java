package com.trace.filter.dubbo;

import com.trace.core.utils.TracePropertyUtils;

class TraceIdProperty {

    private static final String PROPERTY;

    static {
        PROPERTY = TracePropertyUtils.getProperty(DubboTraceIdConstants.PROPERTY_KEY);
    }

    static String get() {
        return PROPERTY;
    }
}

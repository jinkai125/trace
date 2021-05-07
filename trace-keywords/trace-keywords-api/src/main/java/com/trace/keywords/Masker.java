package com.trace.keywords;

/**
 * 脱敏器,对需要脱敏的数据进行特殊处理
 * @author jinkai125@126.com
 *
 */
public interface Masker {

    /**
     * 数据脱敏,默认使用非脱敏方式
     * @param name
     * @param value
     * @return
     */
    default String masking(String name, Object value) {
        return (String) value;
    }
}

package com.trace.keywords.masker;

import com.trace.keywords.Masker;

/**
 * 手机号脱敏器
 * 
 * @author jinkai125@126.com
 *
 */
public class TelephoneMasker implements Masker {

    @Override
    public String masking(String name, Object value) {
        if (value == null) {
            return null;
        }
        String stringValue = value.toString();
        int len = stringValue.length();
        if (len == 0) {
            return "";
        }
        if (len < 6) {
            return "*****";
        }
        if (len < 11) {
            return stringValue.substring(0, 2) + "****" + stringValue.substring(len - 2);
        }
        return stringValue.substring(0, 3) + "****" + stringValue.substring(10);
    }
}

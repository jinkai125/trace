package com.trace.keywords.masker;

import com.trace.keywords.Masker;

/**
 * 身份证脱敏器
 * 
 * @author jinkai125@126.com
 *
 */
public class IdentityMasker implements Masker {

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
        if (len < 15) {
            return stringValue.substring(0, 3) + "****" + stringValue.substring(len - 3);
        }
        if (len == 15) {
            return stringValue.substring(0, 6) + "****" + stringValue.substring(len - 3);
        }
        return stringValue.substring(0, 6) + "****" + stringValue.substring(len - 4);
    }
}

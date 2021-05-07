package com.trace.keywords.masker;

import com.trace.keywords.Masker;

/**
 * 密码脱敏器
 * 
 * @author jinkai125@126.com
 *
 */
public class PasswordMasker implements Masker {

    @Override
    public String masking(String name, Object value) {
        if (value == null) {
            return null;
        }
        return "******";
    }
}

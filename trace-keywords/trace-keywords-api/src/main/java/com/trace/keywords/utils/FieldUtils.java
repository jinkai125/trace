package com.trace.keywords.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FieldUtils {

    public static Object get(Field field, Object obj) throws Exception {
        String name = field.getName();
        String getter = name.substring(0, 1).toUpperCase() + name.substring(1);
        if (field.getType() == boolean.class) {
            getter = "is" + getter;
        } else {
            getter = "get" + getter;
        }
        Method method = null;
        try {
            method = obj.getClass().getMethod(getter);
            return method.invoke(obj);
        } catch (Exception e) {
            Boolean accessible = null;
            try {
                accessible = field.isAccessible();
                if (accessible) {
                    field.setAccessible(false);
                }
                return field.get(obj);
            } catch (Exception e1) {
                throw e1;
            } finally {
                if (accessible != null && accessible.booleanValue()) {
                    field.setAccessible(true);
                }
            }
        }
    }
}

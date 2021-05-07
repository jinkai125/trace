package com.trace.keywords;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Sub sub = new Sub();
        
        String method = "getName";
        
        Method method2 = sub.getClass().getMethod(method);
        
        Object invoke = method2.invoke(sub);
        System.out.println(invoke);
        
    }
}

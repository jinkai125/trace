package com.trace.keywords.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.trace.keywords.Masker;
import com.trace.keywords.masker.NoneMasker;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Keyword {

    /**
     * keyword的别名,同alias
     * 
     * @return
     */
    String alias() default "";

    /**
     * 脱敏器
     * 
     * @return
     */
    Class<? extends Masker> masker() default NoneMasker.class;
}

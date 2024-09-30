package com.hezy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitAccess {

    /**
     * 限制访问的key
     * @return
     */
    String key();

    /**
     * 限制访问次数
     * @return
     */
    int times();

    /**
     * 时间段
     * @return
     */
    int duration();
}

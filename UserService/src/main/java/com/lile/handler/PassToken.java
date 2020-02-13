package com.lile.handler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/*
 * @Author leli
        * @Description //跳过token验证 * @Date 13:22 2020/2/10
 * @Param
        * @return
        **/
public @interface PassToken {
    boolean required() default  true;
}

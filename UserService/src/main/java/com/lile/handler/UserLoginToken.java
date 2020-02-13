package com.lile.handler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/*
 * @Author leli
        * @Description //需要登录才可以操作 * @Date 13:23 2020/2/10
 * @Param
        * @return
        **/
@interface UserLoginToken {
    boolean required() default  true;
}

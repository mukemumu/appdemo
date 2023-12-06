package com.tang.appdemo.interceptor.annotation;

import java.lang.annotation.*;

/**
 * @description 自定义注解：登陆拦截
 *
 * @author tanghx
 * @date 2023/12/6 9:57
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
}

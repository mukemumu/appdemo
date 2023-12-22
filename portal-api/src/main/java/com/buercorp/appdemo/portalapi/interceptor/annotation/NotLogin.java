package com.buercorp.appdemo.portalapi.interceptor.annotation;

import java.lang.annotation.*;

/**
 * @description 关闭拦截注解
 *
 * @author tanghx
 * @date 2023/12/21 16:35
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotLogin {
}

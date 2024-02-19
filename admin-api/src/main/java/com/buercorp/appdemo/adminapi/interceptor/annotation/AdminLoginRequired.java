package com.buercorp.appdemo.adminapi.interceptor.annotation;

import java.lang.annotation.*;

/**
 * @description admin 登陆验证专用注解
 *
 * @author tanghx
 * @date 2023/12/21 14:20
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminLoginRequired {
}

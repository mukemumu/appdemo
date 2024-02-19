package com.buercorp.appdemo.adminapi.interceptor.annotation;

import java.lang.annotation.*;

/**
 * @description 用户语言环境拦截器
 *
 * @author tanghx
 * @date 2023/12/11 10:43
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LocaleRequired {
}

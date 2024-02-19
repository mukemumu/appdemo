package com.buercorp.appdemo.portalapi.config;

import com.buercorp.appdemo.portalapi.interceptor.LocaleInterceptor;
import com.buercorp.appdemo.portalapi.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description 注册拦截器
 *
 * @author tanghx
 * @date 2023/12/6 10:46
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;

    @Autowired
    private LocaleInterceptor localeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor);
        registry.addInterceptor(userLoginInterceptor);
    }
}

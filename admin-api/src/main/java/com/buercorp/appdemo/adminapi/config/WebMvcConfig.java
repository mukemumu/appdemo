package com.buercorp.appdemo.adminapi.config;

import com.buercorp.appdemo.adminapi.interceptor.AdminLoginInterceptor;
import com.buercorp.appdemo.adminapi.interceptor.LocaleInterceptor;
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
    private LocaleInterceptor localeInterceptor;

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor);
        registry.addInterceptor(adminLoginInterceptor);
    }
}

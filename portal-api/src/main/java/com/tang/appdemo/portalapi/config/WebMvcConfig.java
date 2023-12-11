package com.tang.appdemo.portalapi.config;

import com.tang.appdemo.portalapi.interceptor.I18nInterceptor;
import com.tang.appdemo.portalapi.interceptor.LoginInterceptor;
import com.tang.appdemo.portalapi.interceptor.annotation.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * @description 注册拦截器
 *
 * @author tanghx
 * @date 2023/12/6 10:46
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private I18nInterceptor i18nInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(i18nInterceptor);
        registry.addInterceptor(loginInterceptor);
    }
}

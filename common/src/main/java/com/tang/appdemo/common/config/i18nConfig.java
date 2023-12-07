package com.tang.appdemo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @description 国际化
 *
 * @author tanghx
 * @date 2023/12/6 15:48
 */

@Configuration
public class i18nConfig implements WebMvcConfigurer {

    /**
     * 加载 message.properties 文件
     * 创建 messageSources 对象
     * @return
     */
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * 访问 messageSources 对象
     * @param messageSource
     * @return
     */
    @Bean("messageSourceAccessor")
    public MessageSourceAccessor messageSourceAccessor(
            @Autowired @Qualifier("messageSource") MessageSource messageSource) {
        MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource);
        return messageSourceAccessor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("en");
        registry.addInterceptor(localeChangeInterceptor);
    }
}

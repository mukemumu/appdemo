package com.buercorp.appdemo.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @description 国际化
 *
 * @author tanghx
 * @date 2023/12/6 15:48
 */
@Slf4j
@Configuration
public class i18nConfig{

    @Value("${spring.messages.basename}")
    private String baseName;

    /**
     * 加载 message.properties 文件
     * 创建 messageSources 对象
     * @return
     */
    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        log.warn("messageSource baseName: " + baseName);
        messageSource.setBasename(baseName);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

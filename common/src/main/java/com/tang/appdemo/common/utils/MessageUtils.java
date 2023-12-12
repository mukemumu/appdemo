package com.tang.appdemo.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @description 消息工具类，获取多语言配置
 *
 * @author tanghx
 * @date 2023/12/8 10:04
 */


@Component
public class MessageUtils {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String i18nKey, Locale locale, Object... params){
        return messageSource.getMessage(i18nKey,params,locale);
    }
}

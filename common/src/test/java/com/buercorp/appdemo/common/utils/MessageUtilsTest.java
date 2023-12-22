package com.buercorp.appdemo.common.utils;

import com.buercorp.appdemo.common.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author tanghx
 * @description
 * @date 2023/12/13 10:43
 */

@Slf4j
@Component
public class MessageUtilsTest {

   @Autowired
   private static MessageSource messageSource;

   @BeforeAll
   public static void setUp(){
       ReloadableResourceBundleMessageSource me = new ReloadableResourceBundleMessageSource();
       me.setBasename("classpath:i18n/message");
       me.setDefaultEncoding("UTF-8");
       messageSource = me;
   }

    @Test
    public void getMessageTest(){
        String message_1 = messageSource.getMessage("error.code.1010", null, Locale.CHINA);
        Assertions.assertEquals(message_1, "新密码长度未在指定范围 [8, 30]");

        String message_2 = messageSource.getMessage("error.code.1010", null, Locale.US);
        Assertions.assertEquals(message_2, "User new password length not in [8, 30]!");
    }
}

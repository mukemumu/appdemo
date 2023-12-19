package com.buercorp.appdmeo.common.utils;

import com.buercorp.appdemo.common.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

/**
 * @author tanghx
 * @description
 * @date 2023/12/13 10:43
 */

@Slf4j
@SpringBootTest(classes = {MessageUtils.class})
public class MessageUtilsTest {

    @Autowired
    private MessageUtils messageUtils;

    @Test
    public void  getMessageTest(){
        String message = messageUtils.getMessage("error.code.1011", Locale.CHINA, null);
        log.info("message：{}", message);
        Assertions.assertEquals(message, "验证码错误");
    }
}

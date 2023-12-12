package com.tang.appdemo.message;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author tanghx
 * @description
 * @date 2023/12/8 11:30
 */
@SpringBootTest
public class MessageTest {

    @Test
    public void messageTest(){
        ResourceBundle bundle = ResourceBundle.getBundle("i18n/message", Locale.SIMPLIFIED_CHINESE);
        System.out.println(bundle.getString("error.code.1010"));
        Assertions.assertEquals(bundle.getString("error.code.1010"), "新密码长度未在指定范围 [8, 30]");

        ResourceBundle usBundle = ResourceBundle.getBundle("i18n/message", Locale.US);
        System.out.println(usBundle.getString("error.code.1010"));
    }
}

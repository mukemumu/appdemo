package com.tang.appdemo.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

/**
 * @description 加密功能测试
 *
 * @author tanghx
 * @date 2023/12/4 10:16
 */
@SpringBootTest
public class MD5Test {

    @Test
    public void md5Test(){
        String str = "96e79218965eb72c92a549dd5a330112";
        String password = "111111";
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(str.equals(md5Password));
    }
}

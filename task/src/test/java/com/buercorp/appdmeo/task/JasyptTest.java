package com.buercorp.appdmeo.task;


import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tanghx
 * @description
 * @date 2023/12/16 14:42
 */
@Slf4j
@SpringBootTest
public class JasyptTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void encrypt(){
        String username = stringEncryptor.encrypt("java-Jasypt-task");
        String password = stringEncryptor.encrypt("java-Jasypt-task-com");

        log.info("test username {}",username);
        log.info("test password {}", password);
    }
}

package com.buercorp.appdemo.repository.manager;

import com.buercorp.appdemo.repository.manager.impl.UserManagerImpl;
import com.buercorp.appdemo.repository.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tanghx
 * @description
 * @date 2023/12/11 18:50
 */
@Slf4j
@SpringBootTest
class UserManagerTest {

    @Autowired
    private UserManager userManager;

    private static User user;

    @BeforeAll
    public static void bf(@Autowired UserManager manager) {
        user = manager.getUser(1L);
    }

    @Test
    void getUser() {
        User userTest = userManager.getUser(1L);
        log.info("user info {}", user);
        Assertions.assertEquals(user, userTest);
    }

    @Test
    void testGetUser() {
        User userTest = userManager.getUser("admin");
        log.info("user info {}", user);
        Assertions.assertEquals(user, userTest);
    }
}
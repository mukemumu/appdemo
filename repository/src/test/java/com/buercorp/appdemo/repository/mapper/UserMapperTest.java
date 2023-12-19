package com.buercorp.appdemo.repository.mapper;

import com.buercorp.appdemo.repository.manager.UserManager;
import com.buercorp.appdemo.repository.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.Assert;

/**
 * @author tanghx
 * @description
 * @date 2023/12/5 14:30
 */

@Slf4j
@SpringBootTest
@ComponentScan("com.buercorp.addemo")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private static User user;

    private static User userTest;

    @BeforeAll
    public static void setUp(@Autowired UserManager manager){
        user = manager.getUser(1L);
        userTest = new User();
        userTest.setUserName("ceshi");
        userTest.setPassword("1245");
        userTest.setName("软件测试");
    }

    @AfterAll
    public static void deData(@Autowired UserMapper mapper){
        User user = mapper.findUserById(userTest.getId());
        if (user != null){
            mapper.deleteById(userTest.getId());
        }
        Assertions.assertNull(user);
    }

    @Test
    public void findUserByUsernameTest(){
        String username = "admin";
        User userByName = userMapper.findUserByUsername(username);
        Assertions.assertEquals(userByName, user);
    }

    @Test
    void findUserById() {
        User userById = userMapper.findUserById(1L);
        log.info("user info {}", user);
        Assertions.assertEquals(userById, user);
    }

    @Test
    void saveUser() {
        userMapper.saveUser(userTest);
        User ceShi = userMapper.findUserByUsername("ceshi");
        Assertions.assertNotNull(ceShi);
    }

    @Test
    void updateUserById() {
        User userT = userTest;
        userT.setPassword("1212112112");
        userMapper.updateUserById(userT);
        User userByName = userMapper.findUserByUsername(userTest.getUserName());
        Assertions.assertNotEquals(userByName, userTest);
    }

    @Test
    void deleteById() {
        userMapper.deleteById(userTest.getId());
        User user = userMapper.findUserById(userTest.getId());
        Assertions.assertNull(user);
    }
}

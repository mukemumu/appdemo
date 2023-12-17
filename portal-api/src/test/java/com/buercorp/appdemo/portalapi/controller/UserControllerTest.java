package com.buercorp.appdemo.portalapi.controller;

import com.buercorp.appdemo.repository.model.po.User;
import com.buercorp.appdemo.repository.model.vo.LoginVo;
import com.buercorp.appdemo.service.user.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;


/**
 * @author tanghx
 * @description
 * @date 2023/12/11 18:51
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserService userService;

    private User user;
    /**
     * 数据准备
     */
    @BeforeAll
    static void createUser(){
        User user = new User();
        user.setUserName("admin");
        user.setPassword("111111");
    }

    @Test
    void login() {
        ResponseEntity<LoginVo> response = testRestTemplate.postForEntity("/user/login", user, LoginVo.class);
        String token = response.getBody().getToken();
        System.out.println("token 是否为空：" + "".equals(token));
    }

    @Test
    void getUserInfo() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void updateSysUser() {
    }

    @Test
    void deleteUserById() {
    }
}
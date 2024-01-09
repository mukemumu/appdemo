package com.buercorp.appdemo.service.user;

import com.buercorp.appdemo.repository.model.dto.LoginDto;
import com.buercorp.appdemo.repository.model.vo.LoginVo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tanghx
 * @description
 * @date 2023/12/19 16:13
 */
@Data
@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    private static StringBuffer requestUrl;

    private static LoginDto loginDto;

    private String token;

    @BeforeAll
    public static void setUp(){

        loginDto = new LoginDto();
        loginDto.setUsername("admin");
        loginDto.setPassword("111111");

        requestUrl = new StringBuffer("http://localhost:8503/user/login");


    }

    @Test
    void login() {
        LoginVo login = userService.login(loginDto, requestUrl);
        setToken(login.getToken());
        Assertions.assertNotNull(login);
        log.info("token:{}", token);
    }

    @Test
    void getUserInfo() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteById() {
    }
}
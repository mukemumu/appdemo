package com.tang.appdemo.service.user;

import com.tang.appdemo.common.constants.AppConstants;
import com.tang.appdemo.repository.model.dto.LoginDto;
import com.tang.appdemo.repository.model.vo.LoginVo;
import com.tang.appdemo.repository.model.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tanghx
 * @description
 * @date 2023/12/11 18:30
 */

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    private String token;

    @Test
    void login() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("admin");
        loginDto.setPassword("111111");
        LoginVo vo = userService.login(loginDto);
        log.info("user token:" + vo.getToken());
        token = vo.getToken();
    }

    @Test
    void getUserInfo() {
//        UserInfoVo userInfo = userService.getUserInfo("9f5e6e8fa57b42e395988eccddeace31");
//        log.info("user info:" + userInfo);
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
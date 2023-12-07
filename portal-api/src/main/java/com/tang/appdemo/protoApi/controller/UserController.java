package com.tang.appdemo.protoApi.controller;

import com.alibaba.fastjson2.JSON;
import com.tang.appdemo.common.config.i18nConfig;
import com.tang.appdemo.common.constants.AppConstants;
import com.tang.appdemo.common.utils.RequestComponent;
import com.tang.appdemo.protoApi.interceptor.annotation.LoginRequired;
import com.tang.appdemo.repository.model.dto.LoginDto;
import com.tang.appdemo.repository.model.po.User;
import com.tang.appdemo.repository.model.vo.LoginVo;
import com.tang.appdemo.repository.model.vo.UserInfoVo;
import com.tang.appdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @description 登陆请求处理
 *
 * @author tanghx
 * @date 2023/12/4 10:06
 */

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RequestComponent requestComponent;

    /**
     * 用户登陆
     * @param loginDto
     * @return
     */
    @PostMapping(value = "/login")
    public LoginVo login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = userService.login(loginDto);
        return loginVo;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @LoginRequired
    @GetMapping(value = "/getUserInfo")
    public UserInfoVo getUserInfo(@RequestHeader(name = "token") String token){
        UserInfoVo userInfo = userService.getUserInfo(token);
        return userInfo;
    }
}
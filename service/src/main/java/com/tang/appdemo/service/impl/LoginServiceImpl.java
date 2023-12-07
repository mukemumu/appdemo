package com.tang.appdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.tang.appdemo.common.exception.ErrorCode;
import com.tang.appdemo.common.exception.LoginException;
import com.tang.appdemo.repository.mapper.UserMapper;
import com.tang.appdemo.repository.model.dto.LoginDto;
import com.tang.appdemo.repository.model.po.User;
import com.tang.appdemo.repository.model.vo.LoginVo;
import com.tang.appdemo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author tanghx
 * @description
 * @date 2023/12/4 15:23
 */

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {
        User user = userMapper.findUserByUsername(loginDto.getUsername());
        if (user == null){
            throw new LoginException(ErrorCode.LOGIN_ERROR_USERNAME);
        }

        String password = loginDto.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!md5Password.equals(user.getPassword())){
            throw new LoginException(ErrorCode.LOGIN_ERROR_PASSWORD);
        }

        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user.login.token:"+token, JSON.toJSONString(user),10, TimeUnit.MINUTES);

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }
}


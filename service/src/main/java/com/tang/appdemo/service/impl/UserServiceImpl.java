package com.tang.appdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.tang.appdemo.common.constants.AppConstants;
import com.tang.appdemo.common.exception.ErrorCode;
import com.tang.appdemo.common.exception.LoginException;
import com.tang.appdemo.common.utils.MessageUtils;
import com.tang.appdemo.repository.mapper.UserMapper;
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
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author tanghx
 * @description
 * @date 2023/12/4 15:23
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MessageUtils messageUtils;

    /**
     * 用户登陆
     *
     * @param loginDto
     * @return
     */
    @Override
    public LoginVo login(LoginDto loginDto) {
        User user = userMapper.findUserByUsername(loginDto.getUsername());

        if (user == null) {
            throw new LoginException(ErrorCode.LOGIN_ERROR_USERNAME);
        }

        String password = loginDto.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!md5Password.equals(user.getPassword())) {
            throw new LoginException(ErrorCode.LOGIN_ERROR_PASSWORD);
        }

        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(AppConstants.REDIS_TOKEN_PREFIX + token, JSON.toJSONString(user), 15, TimeUnit.MINUTES);

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    /**
     * 获取登陆用户信息
     *
     * @param token
     * @return
     */
    @Override
    public UserInfoVo getUserInfo(String token) {
        String userInfo = (String) RequestContextHolder
                .getRequestAttributes()
                .getAttribute(AppConstants.RESULT_USER, RequestAttributes.SCOPE_REQUEST);

        if ("".equals(userInfo)) {
            throw new LoginException(ErrorCode.LOGIN_AUTH);
        }

        User user = JSON.parseObject(userInfo, User.class);

//        String message = messageSource.getMessage("error.code.1007", null, LocaleContextHolder.getLocale());
//        log.info("提示信息：");

        String message = messageUtils.getMessage("error.code.1007", LocaleContextHolder.getLocale(), null);
        System.out.println(message);

        return new UserInfoVo(user);
    }
}


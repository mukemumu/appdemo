package com.buercorp.appdemo.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import com.buercorp.appdemo.common.constants.AppConstants;
import com.buercorp.appdemo.common.exception.ErrorCode;
import com.buercorp.appdemo.common.exception.LoginException;
import com.buercorp.appdemo.common.utils.MessageUtils;
import com.buercorp.appdemo.repository.mapper.UserMapper;
import com.buercorp.appdemo.repository.model.dto.LoginDto;
import com.buercorp.appdemo.repository.model.po.User;
import com.buercorp.appdemo.repository.model.vo.LoginVo;
import com.buercorp.appdemo.repository.model.vo.UserInfoVo;
import com.buercorp.appdemo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        if (StringUtils.isNullOrEmpty(userInfo)) {
            throw new LoginException(ErrorCode.LOGIN_AUTH);
        }

        User user = JSON.parseObject(userInfo, User.class);

        return new UserInfoVo(user);
    }

    /**
     * 新增用户
     * @param user
     */
    @Override
    @Transactional
    public void saveUser(User user) {

        // 查询用户是否已经存在
        User userExist = userMapper.findUserById(user.getId());

        if (userExist != null){
            throw new LoginException(ErrorCode.USER_REPEAT);
        }

        // 密码加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        userMapper.saveUser(user);
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        userMapper.updateUserById(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }
}


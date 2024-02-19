package com.buercorp.appdemo.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.buercorp.appdemo.common.exception.AppException;
import com.buercorp.appdemo.repository.mapper.LoginTokenMapper;
import com.buercorp.appdemo.repository.model.po.LoginToken;
import com.mysql.cj.util.StringUtils;
import com.buercorp.appdemo.common.constants.AppConstants;
import com.buercorp.appdemo.common.exception.ErrorCode;
import com.buercorp.appdemo.common.exception.LoginException;
import com.buercorp.appdemo.repository.mapper.UserMapper;
import com.buercorp.appdemo.repository.model.dto.LoginDto;
import com.buercorp.appdemo.repository.model.po.User;
import com.buercorp.appdemo.repository.model.vo.LoginVo;
import com.buercorp.appdemo.repository.model.vo.UserInfoVo;
import com.buercorp.appdemo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.UUID;

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
    private LoginTokenMapper loginTokenMapper;

//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;

//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 用户登陆
     * @param loginDto
     * @return
     */
    @Override
    @Transactional
    public LoginVo login(LoginDto loginDto, StringBuffer requestURL) {

        // 根据用户名查询用户
        User user = userMapper.findUserByUsername(loginDto.getUsername());

        if (user == null) {
            // 用户名不存在
            throw new LoginException(ErrorCode.LOGIN_ERROR_USERNAME);
        }

        // 进行 md5 加密
        String password = loginDto.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!md5Password.equals(user.getPassword())) {
            // 密码错误
            throw new LoginException(ErrorCode.LOGIN_ERROR_PASSWORD);
        }

//        int index = requestURL.indexOf("admin");

        // 生成 login_token 信息
        String login_token = UUID.randomUUID().toString().replace("-", "");

        // 将 login_token 信息写入数据库
        loginTokenMapper.insert(new LoginToken(login_token,user.getId()));


        // 将 login_token 写入缓存
//        if (index == -1) {
//            redisTemplate.opsForValue().set(AppConstants.REDIS_TOKEN_PREFIX + token, JSON.toJSONString(user), 15, TimeUnit.MINUTES);
//        } else {
//            redisTemplate.opsForValue().set(AppConstants.ADMIN_TOKEN_PREFIX + token, JSON.toJSONString(user), 15, TimeUnit.MINUTES);
//        }

        LoginVo loginVo = new LoginVo();
        loginVo.setLogin_token(login_token);
        return loginVo;
    }

    /**
     * 获取登陆用户信息
     *
     * @param token
     * @return
     */
    @Override
    public UserInfoVo getUserInfo(String token, StringBuffer requestUrl) {

        int index = requestUrl.indexOf("admin");

        String userInfo = (String) RequestContextHolder
                .getRequestAttributes()
                .getAttribute(AppConstants.LOGIN_USER, RequestAttributes.SCOPE_REQUEST);

        if (StringUtils.isNullOrEmpty(userInfo)) {
            throw new LoginException(ErrorCode.LOGIN_AUTH);
        }

        User user = JSON.parseObject(userInfo, User.class);

        return new UserInfoVo(user);
    }

    /**
     * 新增用户
     *
     * @param user
     */
    @Override
    @Transactional
    public void saveUser(User user) {

        // 查询用户是否已经存在
        User userExist = userMapper.findUserById(user.getId());

        if (userExist != null) {
            throw new LoginException(ErrorCode.USER_REPEAT);
        }

        // 密码加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        userMapper.saveUser(user);
    }

    /**
     * 修改用户信息
     *
     * @param user
     */
    @Override
    @Transactional
    public void updateUser(User user) {

        // 判断用户是否存在
        User userExist = userMapper.findUserByUsername(user.getUserName());
        if (userExist == null) {
            throw new AppException(ErrorCode.USER_DOSE_NOT_EXIST);
        }

        userMapper.updateUserById(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        // 判断用户是否存在
        User user = userMapper.findUserById(id);
        if (user == null) {
            throw new AppException(ErrorCode.USER_DOSE_NOT_EXIST);
        }

        // 删除
        userMapper.deleteById(id);
    }

    /**
     * 向 Elasticsearch 添加信息
     * @param user
     * @return
     */
//    @Override
//    public User insert(User user) {
//
//        IndexCoordinates indexCoordinates = IndexCoordinates.of();
//
//        User save = elasticsearchTemplate.save(user, indexCoordinates);
//        return null;
//    }

    /**
     * 获取所有用户信息
     * @return
     */
    @Override
    public List<User> getAll() {
        List<User> userList = userMapper.getAll();
        return userList;
    }
}

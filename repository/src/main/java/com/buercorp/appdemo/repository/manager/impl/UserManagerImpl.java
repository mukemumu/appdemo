package com.buercorp.appdemo.repository.manager.impl;

import com.buercorp.appdemo.repository.manager.UserManager;
import com.buercorp.appdemo.repository.mapper.UserMapper;
import com.buercorp.appdemo.repository.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description User 通用功能实现
 *
 * @author tanghx
 * @date 2023/12/4 10:07
 */
@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Long userId) {
        User user = userMapper.findUserById(userId);
        return user;
    }

    @Override
    public User getUser(String username) {
        User user = userMapper.findUserByUsername(username);
        return user;
    }
}

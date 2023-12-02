package com.tang.appdemo.repository.manager.impl;

import com.tang.appdemo.common.exception.ErrorCode;
import com.tang.appdemo.repository.manager.UserManager;
import com.tang.appdemo.repository.mapper.UserMapper;
import com.tang.appdemo.repository.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
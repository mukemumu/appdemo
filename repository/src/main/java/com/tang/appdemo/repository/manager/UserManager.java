package com.tang.appdemo.repository.manager;

import com.tang.appdemo.repository.model.po.User;

public interface UserManager {

    User getUser(Long userId);

    User getUser(String username);
}
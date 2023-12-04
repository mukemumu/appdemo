package com.tang.appdemo.repository.manager;

import com.tang.appdemo.repository.model.po.User;

/**
 * @description User 通用接口
 *
 * @author tanghx
 * @date 2023/12/4 10:08
 */
public interface UserManager {

    User getUser(Long userId);

    User getUser(String username);
}
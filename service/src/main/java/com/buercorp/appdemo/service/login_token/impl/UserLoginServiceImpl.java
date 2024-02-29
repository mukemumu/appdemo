package com.buercorp.appdemo.service.login_token.impl;

import com.alibaba.fastjson.JSON;
import com.buercorp.appdemo.repository.manager.UserManager;
import com.buercorp.appdemo.repository.mapper.UserLoginMapper;
import com.buercorp.appdemo.repository.model.po.User;
import com.buercorp.appdemo.repository.model.po.UserLogin;
import com.buercorp.appdemo.service.login_token.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * @author tanghx
 * @description
 * @date 2024/2/18 11:25
 */
@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private UserManager userManager;

    /**
     * 获取 login_token 表中的一行数据
     * @param userLoginToken
     * @return
     */
    @Override
    public UserLogin getLoginToken(String userLoginToken) {
        UserLogin info = userLoginMapper.getLoginToken(userLoginToken);
        return info;
    }

    /**
     * 获取 login_token 对应的用户信息
     * @param userLoginToken
     * @return
     */
    @Override
    public String getUserInfo(String userLoginToken) {
        User user = userManager.getUser(userLoginMapper.getUserId(userLoginToken));
        return JSON.toJSONString(user);
    }

    /**
     * 更新过期时间
     */
    @Override
    @Transactional
    public void updateExpirationTime(String userLoginToken) {
        // 确认更新对象
        UserLogin lt = userLoginMapper.getLoginToken(userLoginToken);

        // 更新时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 30);
        lt.setExpireTime(calendar.getTime());

        userLoginMapper.updateByPrimaryKeySelective(lt);
    }

    /**
     * 根据 login_token 判断是否失效
     * @param userLoginToken
     * @return
     */
    @Override
    public boolean isInvalid(String userLoginToken) {
        UserLogin info = getLoginToken(userLoginToken);

        // 当前时间
        Date currentTime = new Date();
        if (currentTime.after(info.getExpireTime())){
            return true;
        }
        return false;
    }
}

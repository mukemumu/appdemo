package com.buercorp.appdemo.service.login_token.impl;

import com.buercorp.appdemo.repository.manager.UserManager;
import com.buercorp.appdemo.repository.mapper.LoginTokenMapper;
import com.buercorp.appdemo.repository.model.po.LoginToken;
import com.buercorp.appdemo.repository.model.po.User;
import com.buercorp.appdemo.service.login_token.LoginTokenService;
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
public class LoginTokenServiceImpl implements LoginTokenService {

    @Autowired
    private LoginTokenMapper loginTokenMapper;

    @Autowired
    private UserManager userManager;

    /**
     * 获取 login_token 表中的一行数据
     * @param loginToken
     * @return
     */
    @Override
    public LoginToken getLoginToken(String loginToken) {
        LoginToken info = loginTokenMapper.getLoginToken(loginToken);
        return info;
    }

    /**
     * 获取 login_token 对应的用户信息
     * @param loginToken
     * @return
     */
    @Override
    public String getUserInfo(String loginToken) {
        User user = userManager.getUser(userManager.getUserID(loginToken));
        log.info(user.toString());
        return user.toString();
    }

    /**
     * 更新过期时间
     */
    @Override
    @Transactional
    public void updateExpirationTime(String loginToken) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(30, Calendar.MINUTE);
        loginTokenMapper.updateExpirationTime(loginToken, calendar.getTime());
    }

    /**
     * 根据 login_token 判断是否失效
     * @param loginToken
     * @return
     */
    @Override
    public boolean isInvalid(String loginToken) {
        LoginToken info = getLoginToken(loginToken);

        // 当前时间
        Date currentTime = new Date();
        if (currentTime.after(info.getExpireTime())){
            return true;
        }
        return false;
    }
}

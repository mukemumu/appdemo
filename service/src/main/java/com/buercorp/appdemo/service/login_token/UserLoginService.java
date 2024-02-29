package com.buercorp.appdemo.service.login_token;

import com.buercorp.appdemo.repository.model.po.UserLogin;

/**
 * @author tanghx
 * @description
 * @date 2024/2/18 11:25
 */
public interface UserLoginService {

    UserLogin getLoginToken(String userLoginToken);

    String getUserInfo(String userLoginToken);

    void updateExpirationTime(String userLoginToken);

    /**
     * true：失效
     * false：有效
     * @param loginToken
     * @return
     */
    boolean isInvalid(String loginToken);
}

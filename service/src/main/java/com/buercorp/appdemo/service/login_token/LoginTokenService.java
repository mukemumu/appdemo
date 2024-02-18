package com.buercorp.appdemo.service.login_token;

import com.buercorp.appdemo.repository.model.po.LoginToken;
import com.buercorp.appdemo.repository.model.po.User;

/**
 * @author tanghx
 * @description
 * @date 2024/2/18 11:25
 */
public interface LoginTokenService {

    LoginToken getLoginToken(String loginToken);

    String getUserInfo(String loginToken);

    void updateExpirationTime(String loginToken);

    /**
     * true：失效
     * false：有效
     * @param loginToken
     * @return
     */
    boolean isInvalid(String loginToken);
}

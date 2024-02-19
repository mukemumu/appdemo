package com.buercorp.appdemo.repository.mapper;

import com.buercorp.appdemo.repository.model.po.LoginToken;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * @author tanghx
 * @description
 * @date 2024/2/18 10:15
 */
@Mapper
public interface LoginTokenMapper {
    void saveLoginToken(LoginToken loginToken);

    long getUserId(String loginToken);

    LoginToken getLoginToken(String loginToken);

    void updateExpirationTime(String loginToken, Date newExpirationTime);
}

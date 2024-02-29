package com.buercorp.appdemo.repository.mapper;

import com.buercorp.appdemo.repository.model.po.UserLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginMapper {

    long getUserId(String userLoginToken);

    UserLogin getLoginToken(String userLoginToken);

    int deleteByPrimaryKey(Long id);

    int insert(UserLogin row);

    int insertSelective(UserLogin row);

    UserLogin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLogin row);

    int updateByPrimaryKey(UserLogin row);
}
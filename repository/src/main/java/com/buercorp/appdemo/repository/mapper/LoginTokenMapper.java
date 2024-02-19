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

    long getUserId(String loginToken);

    LoginToken getLoginToken(String loginToken);

    int deleteByPrimaryKey(Long id);

    int insert(LoginToken row);

    /**
     * 选择插入
     * @param row
     * @return
     */
    int insertSelective(LoginToken row);

    LoginToken selectByPrimaryKey(Long id);

    /**
     * 根据主键选择性更新
     * @param row
     * @return
     */
    int updateByPrimaryKeySelective(LoginToken row);

    int updateByPrimaryKey(LoginToken row);
}

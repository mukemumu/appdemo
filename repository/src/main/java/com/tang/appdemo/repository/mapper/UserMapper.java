package com.tang.appdemo.repository.mapper;

import com.tang.appdemo.repository.model.po.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    User findUserByUsername(String username);

    User findUserById(Long userId);
}

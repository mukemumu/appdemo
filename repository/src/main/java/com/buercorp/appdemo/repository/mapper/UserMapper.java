package com.buercorp.appdemo.repository.mapper;

import com.buercorp.appdemo.repository.model.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User findUserByUsername(String username);

    User findUserById(Long userId);

    int insert(User row);

    int insertSelective(User row);

    void updateUserById(User user);

    void deleteById(Long id);

    List<User> getAll();
}
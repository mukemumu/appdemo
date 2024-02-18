package com.buercorp.appdemo.repository.mapper;

import com.buercorp.appdemo.repository.model.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description User mapper 接口
 *
 * @author tanghx
 * @date 2023/12/4 10:08
 */
@Mapper
public interface UserMapper {
    User findUserByUsername(String username);

    User findUserById(Long userId);

    void saveUser(User user);

    void updateUserById(User user);

    void deleteById(Long id);

    List<User> getAll();

}

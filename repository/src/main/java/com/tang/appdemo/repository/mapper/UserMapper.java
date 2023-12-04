package com.tang.appdemo.repository.mapper;

import com.tang.appdemo.repository.model.po.User;
import org.apache.ibatis.annotations.Mapper;

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
}

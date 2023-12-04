package com.tang.appdemo.repository.mapper;

import com.tang.appdemo.repository.model.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description 基础功能测试
 *
 * @author tanghx
 * @date 2023/12/4 10:16
 */
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findUserByUsernameTest(){
        User user = userMapper.findUserByUsername("admin");
        System.out.println(user.getUserName());
    }
}

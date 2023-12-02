package com.tang.appdemo.repository.mapper;

import com.tang.appdemo.repository.model.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

package com.buercorp.appdemo.repository.mapper;

import com.buercorp.appdemo.repository.model.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tanghx
 * @description
 * @date 2023/12/5 14:30
 */

@SpringBootTest
@ComponentScan("com.tang.addemo.repository")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findUserByUsernameTest(){
        String username = "admin";
        User userById = userMapper.findUserByUsername(username);
    }

    @Test
    void findUserById() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void updateUserById() {
    }

    @Test
    void deleteById() {
    }
}

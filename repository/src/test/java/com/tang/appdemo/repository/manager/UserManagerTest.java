package com.tang.appdemo.repository.manager;

import com.tang.appdemo.repository.mapper.UserMapper;
import com.tang.appdemo.repository.model.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserManagerTest {

    @Autowired
    private UserManager userManager;

    @Test
    public void findUserByUsernameTest(){
        User user = userManager.getUser("admin");
        System.out.println(user.getUserName());
    }
}

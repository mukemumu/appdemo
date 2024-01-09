package com.buercorp.appdemo.repository.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.buercorp.appdemo.repository.manager.impl.UserManagerImpl;
import com.buercorp.appdemo.repository.mapper.UserMapper;
import com.buercorp.appdemo.repository.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @description
 *
 * @author tanghx
 * @date 2023/12/26 14:41
 */
@Nested
@Slf4j
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserManagerImpl.class)
class UserManagerTest {

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private UserManager userManager;

    @Test
    void testGetUserById() {
        User user = new User();
        user.setUserName("admin");

//        userMapper = mock(UserMapper.class);

        // 模拟调用
        when(userMapper.findUserById(any())).thenReturn(user);

        // 真实调用
        User user_test = userManager.getUser(1L);

        assertEquals(user.getUserName(), user_test.getUserName());
    }
}

package com.buercorp.appdemo.repository.mapper;

import com.buercorp.appdemo.repository.manager.UserManager;
import com.buercorp.appdemo.repository.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

/**
 * @author tanghx
 * @description
 * @date 2023/12/5 14:30
 */

@Slf4j
@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
public class UserMapperTest {

    @MockBean
    private UserMapper userMapper;

    @Test
    @Sql(scripts = "")
    public void findUserByUsernameTest(){
        String username = "admin";
        User userByName = userMapper.findUserByUsername(username);
    }

    @Test
    @Sql(scripts = "")
    void findUserById() {
        User userById = userMapper.findUserById(1L);
    }

    @Test
    @Sql(scripts = "")
    void saveUser() {
    }

    @Test
    @Sql(scripts = "")
    void updateUserById() {
    }

    @Test
    @Sql(scripts = "")
    void deleteById() {
    }
}

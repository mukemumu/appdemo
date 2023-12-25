package com.buercorp.appdemo.repository.manager;

import com.buercorp.appdemo.repository.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tanghx
 * @description
 * @date 2023/12/11 18:50
 */
@Slf4j
@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
class UserManagerTest {

    @MockBean
    private UserManager userManager;

    @Test
    @Sql(scripts = "")          // 数据准备脚本
    void getUser() {
        User userTest = userManager.getUser(1L);
    }

    @Test
    @Sql(scripts = "")          // 数据准备脚本
    void testGetUser() {
        User userTest = userManager.getUser("admin");
    }
}
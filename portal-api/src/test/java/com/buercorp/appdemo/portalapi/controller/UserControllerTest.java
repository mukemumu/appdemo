package com.buercorp.appdemo.portalapi.controller;


import com.buercorp.appdemo.repository.model.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.head;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author tanghx
 * @description
 * @date 2023/12/11 18:51
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void login() {
        // 创建请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // 创建请求体
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("username", "admin");
//        body.add("password", "111111");
//
//        // 创建请求实体
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
//
//        // 发送POST请求
//        String url = "/user/login";
//        String response = testRestTemplate.postForEntity(url, requestEntity, String.class).getBody();
//
//        log.info("token index {}", response.indexOf("token"));
//
//        // 断言响应内容
//        Assertions.assertNotEquals(response.indexOf("token"), -1);
    }

    @Test
    void getUserInfo() {

    }

    @Test
    void saveUser() {
    }

    @Test
    void updateSysUser() {
    }

    @Test
    void deleteUserById() {
    }
}
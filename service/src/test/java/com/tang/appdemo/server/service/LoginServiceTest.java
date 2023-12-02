package com.tang.appdemo.server.service;


import com.tang.appdemo.repository.model.dto.LoginDto;
import com.tang.appdemo.repository.model.vo.LoginVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void loginTest(){
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("admin");
        loginDto.setPassword("12345");

        LoginVo vo = loginService.login(loginDto);
        System.out.println(vo.getToken());
    }
}
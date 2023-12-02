package com.tang.appdemo.proto_api.controller;

import com.tang.appdemo.common.result.Result;
import com.tang.appdemo.common.result.ResultCodeEnum;
import com.tang.appdemo.repository.model.dto.LoginDto;
import com.tang.appdemo.repository.model.vo.LoginVo;
import com.tang.appdemo.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto){
        LoginVo login = loginService.login(loginDto);
        return Result.builder(login, ResultCodeEnum.SUCCESS);
    }
}
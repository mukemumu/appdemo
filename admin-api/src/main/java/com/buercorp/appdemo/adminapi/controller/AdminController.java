package com.buercorp.appdemo.adminapi.controller;

import com.buercorp.appdemo.repository.model.dto.LoginDto;
import com.buercorp.appdemo.repository.model.vo.LoginVo;
import com.buercorp.appdemo.service.common.RequestComponent;
import com.buercorp.appdemo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanghx
 * @description admin 登陆控制器
 * @date 2023/12/21 14:34
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestComponent requestComponent;

    @PostMapping(value = "/login")
    public LoginVo login(@RequestBody LoginDto loginDto) {
        StringBuffer requestURL = requestComponent.getRequest().getRequestURL();
        LoginVo loginVo = userService.login(loginDto, requestURL);
        return loginVo;
    }
}

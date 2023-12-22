package com.buercorp.appdemo.adminapi.controller;

import com.buercorp.appdemo.common.exception.LoginException;
import com.buercorp.appdemo.portalapi.utils.RequestComponent;
import com.buercorp.appdemo.repository.manager.UserManager;
import com.buercorp.appdemo.repository.model.dto.LoginDto;
import com.buercorp.appdemo.repository.model.po.User;
import com.buercorp.appdemo.repository.model.vo.LoginVo;
import com.buercorp.appdemo.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description admin 登陆控制器
 *
 * @author tanghx
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
    public LoginVo login(@RequestBody LoginDto loginDto){
        StringBuffer requestURL = requestComponent.getRequest().getRequestURL();
        LoginVo loginVo = userService.login(loginDto, requestURL);
        return loginVo;
    }
}

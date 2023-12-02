package com.tang.appdemo.server.service;

import com.tang.appdemo.repository.model.dto.LoginDto;
import com.tang.appdemo.repository.model.vo.LoginVo;

public interface LoginService {
    LoginVo login(LoginDto loginDto);
}

package com.tang.appdemo.server.service;

import com.tang.appdemo.repository.model.dto.LoginDto;
import com.tang.appdemo.repository.model.vo.LoginVo;

/**
 * @description user 接口
 *
 * @author tanghx
 * @date 2023/12/4 10:12
 */
public interface LoginService {
    LoginVo login(LoginDto loginDto);
}

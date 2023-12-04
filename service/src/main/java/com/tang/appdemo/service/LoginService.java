package com.tang.appdemo.service;

import com.tang.appdemo.repository.model.dto.LoginDto;
import com.tang.appdemo.repository.model.vo.LoginVo;

/**
 * @description
 *
 * @author tanghx
 * @date 2023/12/4 15:23
 */
public interface LoginService {

    LoginVo login(LoginDto loginDto);
}

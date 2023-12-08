package com.tang.appdemo.service.user;

import com.tang.appdemo.repository.model.dto.LoginDto;
import com.tang.appdemo.repository.model.po.User;
import com.tang.appdemo.repository.model.vo.LoginVo;
import com.tang.appdemo.repository.model.vo.UserInfoVo;

/**
 * @description
 *
 * @author tanghx
 * @date 2023/12/4 15:23
 */
public interface UserService {

    LoginVo login(LoginDto loginDto);

    UserInfoVo getUserInfo(String token);
}

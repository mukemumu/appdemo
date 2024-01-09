package com.buercorp.appdemo.service.user;

import com.buercorp.appdemo.repository.model.dto.LoginDto;
import com.buercorp.appdemo.repository.model.po.User;
import com.buercorp.appdemo.repository.model.vo.LoginVo;
import com.buercorp.appdemo.repository.model.vo.UserInfoVo;

/**
 * @description
 *
 * @author tanghx
 * @date 2023/12/4 15:23
 */
public interface UserService {

    LoginVo login(LoginDto loginDto, StringBuffer requestURL);

    UserInfoVo getUserInfo(String token, StringBuffer requestURL);

    void saveUser(User user);

    void updateUser(User user);

    void deleteById(Long id);
}

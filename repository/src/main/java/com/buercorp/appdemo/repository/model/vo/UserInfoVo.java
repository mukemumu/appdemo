package com.buercorp.appdemo.repository.model.vo;

import com.buercorp.appdemo.repository.model.po.User;
import lombok.Data;

import java.util.Date;

/**
 * @author tanghx
 * @description
 * @date 2023/12/7 11:22
 */
@Data
public class UserInfoVo{
    private String username;

    private String birthday;

    private String phone;

    public UserInfoVo(User user){
        username = user.getUsername();
        phone = user.getPhone();
        birthday = user.getBirthday();
    }
}

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

    private String name;

    private String phone;

    private String avatar;

    private Date createTime;

    private Date updateTime;

    public UserInfoVo(User user){
        username = user.getUserName();
        name = user.getName();
        phone = user.getPhone();
        avatar = user.getAvatar();
        createTime = user.getCreateTime();
        updateTime = user.getUpdateTime();
    }
}

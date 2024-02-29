package com.buercorp.appdemo.repository.model.po;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* Created by MyBatis Generator 2024/02/29
* https://github.com/greatwqs/mybatis-generator-plugin
*/
@Getter
@Setter
@ToString
public class User {
    private Long userId;

    private String username;

    private String loginId;

    private String password;

    private String birthday;

    private Byte sex;

    private String city;

    private String phone;

    private String mail;

    private String avatar;

    private Byte identity;

    private Date firstWorkingTime;

    private Byte isValid;

    private Date createTime;

    private Date updateTime;
}
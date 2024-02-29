package com.buercorp.appdemo.repository.model.po;

import java.util.Calendar;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* Created by MyBatis Generator 2024/02/29
* https://github.com/greatwqs/mybatis-generator-plugin
*/
@Data
@ToString
@NoArgsConstructor
public class UserLogin {
    private Long id;

    private Long userId;

    private String userLoginToken;

    private Date expireTime;

    private Byte isValid;

    private Date createTime;

    private Date updateTime;

    public UserLogin(String userLoginToken, Long userId){
        this.userId = userId;
        this.userLoginToken = userLoginToken;

        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        this.createTime = calendar.getTime();

        // 设置过期时间，默认过期时间为 30 分钟
        calendar.add(Calendar.MINUTE, 30);
        this.expireTime = calendar.getTime();

        this.isValid = 1;
    }
}
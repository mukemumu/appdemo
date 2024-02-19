package com.buercorp.appdemo.repository.model.po;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * @author tanghx
 * @description
 * @date 2024/2/18 10:09
 */
@Data
public class LoginToken {

    private Long id;

    private String loginToken;

    private Long userId;

    private Date expireTime;                    // 过期时间

    private Date createTime;

    private Date updateTime;

    public LoginToken(String loginToken, Long userId) {
        this.loginToken = loginToken;
        this.userId = userId;

        // 设置过期时间，默认过期时间为 30 分钟
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 30);

        this.expireTime = calendar.getTime();
    }
}

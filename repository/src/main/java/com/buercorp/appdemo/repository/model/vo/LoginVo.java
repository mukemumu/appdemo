package com.buercorp.appdemo.repository.model.vo;

import lombok.Data;

/**
 * @description 传递 token
 *
 * @author tanghx
 * @date 2023/12/4 10:11
 */
@Data
public class LoginVo{
    private String login_token;
    private String refresh_token;
}

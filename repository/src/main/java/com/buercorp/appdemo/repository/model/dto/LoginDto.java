package com.buercorp.appdemo.repository.model.dto;

import lombok.Data;

/**
 * @description 接收 User 信息
 *
 * @author tanghx
 * @date 2023/12/4 10:09
 */
@Data
public class LoginDto {

    private String username;
    private String password;
    private String codeKey;
    private String captcha;
}

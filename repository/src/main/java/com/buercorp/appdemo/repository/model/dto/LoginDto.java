package com.buercorp.appdemo.repository.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @description 接收 User 信息
 *
 * @author tanghx
 * @date 2023/12/4 10:09
 */
@Data
public class LoginDto {

    @NotNull(message = "账号不能为空")
    private String username;

    @Size(min = 6, max = 12, message = "密码长度为 6 ~ 12！")
    private String password;


    private String codeKey;


    private String captcha;
}

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

    @Size(message = "{LoginDto.username.Size}")
    @NotNull(message = "{LoginDto.username.NotNull}")
    private String username;

    @Size(min = 4, max = 12, message = "{Password.size}")
    private String password;


    private String codeKey;


    private String captcha;
}

package com.tang.appdemo.repository.model.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
    private String codeKey;
    private String captcha;
}

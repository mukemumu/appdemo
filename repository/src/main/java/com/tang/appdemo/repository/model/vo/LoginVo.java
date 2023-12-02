package com.tang.appdemo.repository.model.vo;

import com.tang.appdemo.common.result.Result;
import lombok.Data;

@Data
public class LoginVo{
    private String token;
    private String refresh_token;
}

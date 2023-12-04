package com.tang.appdemo.repository.model.vo;

import com.tang.appdemo.common.result.Result;
import lombok.Data;

/**
 * @description 传递 token
 *
 * @author tanghx
 * @date 2023/12/4 10:11
 */
@Data
public class LoginVo{
    private String token;
    private String refresh_token;
}

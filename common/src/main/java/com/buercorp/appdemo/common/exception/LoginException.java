package com.buercorp.appdemo.common.exception;

import lombok.Data;

/**
 * @description 登陆异常
 *
 * @author tanghx
 * @date 2023/12/4 10:01
 */
public class LoginException extends AppException {

    public LoginException(ErrorCode errorCode){
        super(errorCode);
    }
}

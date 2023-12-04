package com.tang.appdemo.common.exception;

/**
 * @description 登陆异常
 *
 * @author tanghx
 * @date 2023/12/4 10:01
 */
public class LoginException extends AppException {

    public LoginException(ErrorCode errorCode){
        setErrorCode(errorCode);
    }
}

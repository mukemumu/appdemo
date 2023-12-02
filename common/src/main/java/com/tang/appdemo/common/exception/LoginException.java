package com.tang.appdemo.common.exception;

public class LoginException extends AppException {

    public LoginException(ErrorCode errorCode){
        setErrorCode(errorCode);
    }
}

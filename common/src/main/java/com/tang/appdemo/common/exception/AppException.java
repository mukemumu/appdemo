package com.tang.appdemo.common.exception;

import lombok.Data;

@Data
public class AppException extends RuntimeException{

    private ErrorCode errorCode;

}

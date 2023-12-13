package com.buercorp.appdemo.common.exception;

import lombok.Data;

/**
 * @description 全局异常基类
 *
 * @author tanghx
 * @date 2023/12/4 9:59
 */
@Data
public class AppException extends RuntimeException{

    private ErrorCode errorCode;

}

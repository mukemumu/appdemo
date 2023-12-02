package com.tang.appdemo.common.result;

import com.tang.appdemo.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200 , "操作成功") ;

    private Integer code ;      // 业务状态码
    private String message ;    // 响应消息

    private ResultCodeEnum(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }
}
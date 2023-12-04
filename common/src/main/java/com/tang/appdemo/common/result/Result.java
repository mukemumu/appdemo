package com.tang.appdemo.common.result;

import com.tang.appdemo.common.exception.AppException;
import com.tang.appdemo.common.exception.ErrorCode;
import lombok.Data;

/**
 * @description
 *
 * @author tanghx
 * @date 2023/12/4 10:05
 */
@Data
public class Result<T> {
    private Integer code;

    private String message;

    private T data;

    private Result(){}

    public static <T> Result<T> builder(T data, String message, Integer code){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> builder(T data, ResultCodeEnum codeEnum){
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(codeEnum.getCode());
        result.setMessage(codeEnum.getMessage());
        return result;
    }

    public static <T> Result<T> builder(T data, ErrorCode errorCode){
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(errorCode.getAppCode());
        result.setMessage(errorCode.getI18nKey());
        return result;
    }
}

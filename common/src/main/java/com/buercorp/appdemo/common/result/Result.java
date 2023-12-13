package com.buercorp.appdemo.common.result;

import lombok.Data;

/**
 * @description 异常响应类
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

    public static <T> Result<T> builder(T data, String message){
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> builder(T data, String message, Integer code){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}

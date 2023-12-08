package com.tang.appdemo.common.result;

import com.tang.appdemo.common.constants.AppConstants;
import com.tang.appdemo.common.exception.AppException;
import com.tang.appdemo.common.exception.ErrorCode;
import com.tang.appdemo.common.utils.MessageUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @description 异常响应类
 *
 * @author tanghx
 * @date 2023/12/4 10:05
 */
@Data
@Component
public class Result<T> {

    @Autowired
    private static MessageUtils messageUtils;

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

    public static <T> Result<T> builder(T data, ErrorCode errorCode){
        Result<T> result = new Result<>();
        result.setData(data);

        result.setCode(errorCode.getAppCode());

        result.setMessage(messageUtils.getMessage(
                AppConstants.ERROR_CODE_PREFIX + errorCode.getAppCode(),
                LocaleContextHolder.getLocale(),
                null));

        return result;
    }
}

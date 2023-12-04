package com.tang.appdemo.common.exception;


import com.tang.appdemo.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description 全局异常处理
 *
 * @author tanghx
 * @date 2023/12/4 10:03
 */
@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Exception> exception(Exception e){
        log.error(ErrorCode.SYSTEM_ERROR.getI18nKey());
        return Result.builder(e,e.getMessage(),500);
    }

    @ResponseBody
    @ExceptionHandler(LoginException.class)
    public Result loginException(LoginException e){
        log.error(e.getErrorCode().getI18nKey());
        return Result.builder(e, e.getErrorCode());
    }
}

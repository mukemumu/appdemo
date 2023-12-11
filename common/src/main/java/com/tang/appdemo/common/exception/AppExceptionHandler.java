package com.tang.appdemo.common.exception;


import com.tang.appdemo.common.constants.AppConstants;
import com.tang.appdemo.common.result.Result;
import com.tang.appdemo.common.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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

    @Autowired
    private MessageUtils messageUtils;

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Exception> exception(Exception e){
        log.error("AppExceptionHandler, exception", e);
        return Result.builder(e, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(LoginException.class)
    public Result loginException(LoginException e){
        log.error("AppExceptionHandler, loginException", e);

        String message = messageUtils.getMessage(
                AppConstants.ERROR_CODE_PREFIX + e.getErrorCode().getAppCode(),
                LocaleContextHolder.getLocale(),
                null);

        log.info("异常信息：" + message);

        Integer code = e.getErrorCode().getAppCode();

        return Result.builder(e, message, code);
    }
}

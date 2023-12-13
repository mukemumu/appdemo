package com.buercorp.appdemo.common.exception;


import com.buercorp.appdemo.common.result.Result;
import com.buercorp.appdemo.common.utils.MessageUtils;
import com.buercorp.appdemo.common.constants.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.*;

/**
 * @author tanghx
 * @description 全局异常处理
 * @date 2023/12/4 10:03
 */
@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

    @Autowired
    private MessageUtils messageUtils;

    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Exception> exception(Exception e) {
        log.error("AppExceptionHandler, exception", e);
        return Result.builder(e, e.getMessage());
    }

    /**
     * 登陆异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(LoginException.class)
    public Result loginException(LoginException e) {
        log.error("AppExceptionHandler, loginException", e);

        Locale locale = (Locale) RequestContextHolder.getRequestAttributes().getAttribute(AppConstants.LOCALE, RequestAttributes.SCOPE_REQUEST);

        String message = messageUtils.getMessage(
                AppConstants.ERROR_CODE_PREFIX + e.getErrorCode().getAppCode(),
                locale,
                null);

        log.info("异常信息：{}", message);
        Integer code = e.getErrorCode().getAppCode();
        return Result.builder(e, message, code);
    }

    /**
     * 参数验证失败异常处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringJoiner msgJoiner = new StringJoiner(",");
            Set<String> errors = new HashSet<>(objectErrors.size() / 2);
            for (ObjectError objectError : objectErrors) {
                errors.add(objectError.getDefaultMessage());
            }
            for (String error : errors) {
                msgJoiner.add(error);
            }
            String errorMessage = msgJoiner.toString();
            return Result.builder(errorMessage,AppConstants.PARAM_VALID + errorMessage);
        }
        return Result.builder(null, AppConstants.PARAM_VALID + ex.getMessage());
    }
}

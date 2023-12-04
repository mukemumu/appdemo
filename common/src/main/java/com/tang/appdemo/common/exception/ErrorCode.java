package com.tang.appdemo.common.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description 异常信息枚举
 *
 * @author tanghx
 * @date 2023/12/4 10:00
 */
@Getter
@NoArgsConstructor
public enum ErrorCode {
    LOGIN_ERROR_PASSWORD(200,201 , "密码错误"),
    LOGIN_ERROR_USERNAME(200,200,"用户名不存在"),
    VALIDATE_CODE_ERROR(200,202 , "验证码错误") ,
    LOGIN_AUTH(200, 208 , "用户未登录"),
    USER_NAME_IS_EXISTS(200, 209 , "用户名已经存在"),
    SYSTEM_ERROR(400, 9999 , "您的网络有问题请稍后重试"),
    NODE_ERROR(200, 217, "该节点下有子节点，不可以删除"),
    DATA_ERROR(200, 204, "数据异常"),
    ACCOUNT_STOP(200, 216, "账号已停用"),
    STOCK_LESS(200, 219, "库存不足");

    private int httpStatus;

    private int appCode;

    // message key
    private String i18nKey;

    private ErrorCode(int httpStatus, int appCode, String i18nKey) {
        this.httpStatus = httpStatus;
        this.appCode = appCode;
        this.i18nKey = i18nKey;
    }
}

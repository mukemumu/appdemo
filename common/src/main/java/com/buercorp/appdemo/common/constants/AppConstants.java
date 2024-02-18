package com.buercorp.appdemo.common.constants;

import java.util.Locale;

/**
 * @author tanghx
 * @description
 * @date 2023/12/7 10:38
 */
public class AppConstants {

    // Admin 用户的 token 前缀
    public static final String ADMIN_TOKEN_PREFIX = "admin.login.token";

    // redis 中普通用户的 token 前缀
    public static final String REDIS_TOKEN_PREFIX = "user.login.token";

    // RequestContextHolder 中登陆的普通用户信息属性名
    public static final String LOGIN_USER = "Login_user";

    // RequestContextHolder 中登陆的 admin 用户信息属性名
    public static final String ADMIN_LOGIN_USER = "Admin_user";

    // 异常信息码前缀
    public static final String ERROR_CODE_PREFIX = "error.code.";

    // 语言环境 header 参数
    public static final String ACCEPT_LANGUAGE = "language";

    // RequestContextHolder 中的语言环境信息
    public static final String LOCALE = "locale";

    public static final String PARAM_VALID = "参数不合法：";
}

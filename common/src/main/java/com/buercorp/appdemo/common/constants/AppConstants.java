package com.buercorp.appdemo.common.constants;

import java.util.Locale;

/**
 * @author tanghx
 * @description
 * @date 2023/12/7 10:38
 */
public class AppConstants {

    // redis 中的 token 前缀
    public static final String REDIS_TOKEN_PREFIX = "user.login.token:";

    // RequestContextHolder 中登陆用户信息属性名
    public static final String RESULT_USER = "Result_user:";

    // 异常信息码前缀
    public static final String ERROR_CODE_PREFIX = "error.code.";

    public static final String ACCEPT_LANGUAGE = "language";

    public static final String LOCALE = "locale";

    public static final String PARAM_VALID = "参数不合法：";
}

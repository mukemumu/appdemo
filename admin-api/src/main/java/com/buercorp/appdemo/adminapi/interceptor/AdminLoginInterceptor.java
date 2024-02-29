package com.buercorp.appdemo.adminapi.interceptor;

import com.buercorp.appdemo.adminapi.interceptor.annotation.AdminLoginRequired;
import com.buercorp.appdemo.common.constants.AppConstants;
import com.buercorp.appdemo.common.exception.ErrorCode;
import com.buercorp.appdemo.common.exception.LoginException;

import com.buercorp.appdemo.common.utils.ReflexUtil;
import com.buercorp.appdemo.service.login_token.UserLoginService;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.buercorp.appdemo.common.constants.AppHeaders.ADMIN_LOGIN_TOKEN;

/**
 * @description admin 登陆验证
 *
 * @author tanghx
 * @date 2023/12/21 14:21
 */
@Slf4j
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserLoginService userLoginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AdminLoginRequired adminLoginRequired = ReflexUtil.getClazzOrMethodAnnotation(handlerMethod, AdminLoginRequired.class);

        if (adminLoginRequired == null){
            return true;
        }

        // login_token 不能为空
        String login_token = request.getHeader(ADMIN_LOGIN_TOKEN);
        if (StringUtils.isNullOrEmpty(login_token)){
            log.warn("AdminLoginInterceptor login_token is empty, url: " + request.getRequestURL().toString());
            // 登录令牌不存在或者已经过期
            throw new LoginException(ErrorCode.LOGIN_AUTH);
        }

        // 根据 login_token 获取用户信息，判断是否失效
        if (userLoginService.isInvalid(login_token)){
            // 登录令牌不存在或者已经过期
            log.warn("AdminLoginInterceptor login_token is not valid, url: " + request.getRequestURL().toString());
            throw new LoginException(ErrorCode.LOGIN_AUTH);
        }

        // 获取用户信息
        String userInfo = userLoginService.getUserInfo(login_token);

        // 将完成登陆的用户信息放入 RequestContextHolder 中
        RequestContextHolder.currentRequestAttributes().setAttribute(AppConstants.ADMIN_LOGIN_USER, userInfo, RequestAttributes.SCOPE_REQUEST);

        // 更新过期时间
        userLoginService.updateExpirationTime(login_token);

        return true;
    }
}

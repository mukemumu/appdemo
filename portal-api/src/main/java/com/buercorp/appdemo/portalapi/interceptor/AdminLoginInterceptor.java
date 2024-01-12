package com.buercorp.appdemo.portalapi.interceptor;

import com.buercorp.appdemo.common.constants.AppConstants;
import com.buercorp.appdemo.common.exception.ErrorCode;
import com.buercorp.appdemo.common.exception.LoginException;
import com.buercorp.appdemo.portalapi.interceptor.annotation.AdminLoginRequired;
import com.buercorp.appdemo.common.utils.ReflexUtil;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;


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
    private RedisTemplate<String, String> redisTemplate;

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

        String token = request.getHeader("token");
        String userInfo = redisTemplate.opsForValue().get(AppConstants.ADMIN_TOKEN_PREFIX + token);

        if (StringUtils.isNullOrEmpty(userInfo)){
            throw new LoginException(ErrorCode.LOGIN_AUTH);
        }

        RequestContextHolder.currentRequestAttributes().setAttribute(AppConstants.ADMIN_LOGIN_USER, userInfo, RequestAttributes.SCOPE_REQUEST);

        redisTemplate.expire(AppConstants.ADMIN_TOKEN_PREFIX + token, 15, TimeUnit.MINUTES);

        return true;
    }
}

package com.buercorp.appdemo.portalapi.interceptor;

import com.buercorp.appdemo.portalapi.interceptor.annotation.NotLogin;
import com.mysql.cj.util.StringUtils;
import com.buercorp.appdemo.common.constants.AppConstants;
import com.buercorp.appdemo.common.exception.ErrorCode;
import com.buercorp.appdemo.common.exception.LoginException;
import com.buercorp.appdemo.common.utils.LoginContextUtil;
import com.buercorp.appdemo.common.utils.ReflexUtil;
import com.buercorp.appdemo.portalapi.interceptor.annotation.LoginRequired;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * @description protal-api 登陆拦截器
 *
 * @author tanghx
 * @date 2023/12/21 14:17
 */

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {          // 表示当前拦截器只拦截处理器方法
            return true;
        }

        // 获取注解信息
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        NotLogin notLogin = ReflexUtil.getClazzOrMethodAnnotation(handlerMethod, NotLogin.class);

        if (notLogin != null) {     // 不拦截具有特定注解的处理器方法
            return true;
        }

        LoginRequired loginRequired = ReflexUtil.getClazzOrMethodAnnotation(handlerMethod, LoginRequired.class);

        if (loginRequired == null) {     // 没有特定注解，不拦截请求
            return true;
        }

        String token = request.getHeader("token");
        String userInfo = redisTemplate.opsForValue().get(AppConstants.REDIS_TOKEN_PREFIX + token);

        if (StringUtils.isNullOrEmpty(userInfo)){
            throw new LoginException(ErrorCode.LOGIN_AUTH);
        }

        // 将完成登陆的用户放入 RequestContextHolder 中
        RequestContextHolder.currentRequestAttributes().setAttribute(AppConstants.LOGIN_USER, userInfo, RequestAttributes.SCOPE_REQUEST);

        // 更新 token 的过期时间
        redisTemplate.expire(AppConstants.REDIS_TOKEN_PREFIX + token, 15, TimeUnit.MINUTES);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 删除用户信息
        LoginContextUtil.remove();
    }
}
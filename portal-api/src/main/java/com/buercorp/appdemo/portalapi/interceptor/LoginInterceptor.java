package com.buercorp.appdemo.portalapi.interceptor;

import com.buercorp.appdemo.repository.manager.UserManager;
import com.buercorp.appdemo.repository.mapper.LoginTokenMapper;
import com.buercorp.appdemo.repository.model.po.LoginToken;
import com.buercorp.appdemo.repository.model.po.User;
import com.buercorp.appdemo.repository.model.vo.UserInfoVo;
import com.buercorp.appdemo.service.login_token.LoginTokenService;
import com.buercorp.appdemo.service.user.UserService;
import com.mysql.cj.util.StringUtils;
import com.buercorp.appdemo.common.constants.AppConstants;
import com.buercorp.appdemo.common.exception.ErrorCode;
import com.buercorp.appdemo.common.exception.LoginException;
import com.buercorp.appdemo.common.utils.LoginContextUtil;
import com.buercorp.appdemo.common.utils.ReflexUtil;
import com.buercorp.appdemo.portalapi.interceptor.annotation.LoginRequired;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;
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
    private LoginTokenService loginTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {          // 表示当前拦截器只拦截处理器方法
            return true;
        }

        // 获取注解信息
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        LoginRequired loginRequired = ReflexUtil.getClazzOrMethodAnnotation(handlerMethod, LoginRequired.class);

        if (loginRequired == null) {     // 没有特定注解，不拦截请求
            return true;
        }

        // 从请求中获取 login_token 信息
        String login_token = request.getHeader("login_token");

        // login_token 不能为空
        if (StringUtils.isNullOrEmpty(login_token)){
            // 登录令牌不存在或者已经过期
            throw new LoginException(ErrorCode.LOGIN_AUTH);
        }

        // 判断用户信息是否失效
        if (loginTokenService.isInvalid(login_token)){
            // 登录令牌不存在或者已经过期
            throw new LoginException(ErrorCode.LOGIN_AUTH);
        }

        // 从 login_token 表中获取用户信息
        String userInfo = loginTokenService.getUserInfo(login_token);

        // 将完成登陆的用户信息放入 RequestContextHolder 中
        RequestContextHolder.currentRequestAttributes().setAttribute(AppConstants.LOGIN_USER, userInfo, RequestAttributes.SCOPE_REQUEST);

        // 更新 login_token 的过期时间
        loginTokenService.updateExpirationTime(login_token);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 删除用户信息
        LoginContextUtil.remove();
    }
}
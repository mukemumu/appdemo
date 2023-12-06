package com.tang.appdemo.interceptor;

import com.alibaba.fastjson.JSON;
import com.tang.appdemo.common.exception.ErrorCode;
import com.tang.appdemo.common.exception.LoginException;
import com.tang.appdemo.common.utils.LoginContextUtil;
import com.tang.appdemo.repository.model.po.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * @description 登陆拦截器
 *
 * @author tanghx
 * @date 2023/12/5 17:25
 */

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String method = request.getMethod();
        if ("OPTIONS".equals(method)){
            return true;
        }

        // 从请求中获取 token
        String token = request.getHeader("token");

        if ("".equals(token)){
            // 用户没有完成登陆
            log.error(ErrorCode.LOGIN_AUTH.getI18nKey());
            throw new LoginException(ErrorCode.LOGIN_AUTH);

        }
        // 通过 token 从 redis 中获取用户信息
        String userInfo = redisTemplate.opsForValue().get("");
        if ("".equals(userInfo)){
            // 用户没有完成登陆
            log.error(ErrorCode.LOGIN_AUTH.getI18nKey());
            throw new LoginException(ErrorCode.LOGIN_AUTH);
        }

        //
        LoginContextUtil.set((org.apache.catalina.User) JSON.parseObject(userInfo, User.class));

        // 更新 token 的过期时间
        redisTemplate.expire("", 15, TimeUnit.MINUTES);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 删除用户信息
        LoginContextUtil.remove();
    }
}
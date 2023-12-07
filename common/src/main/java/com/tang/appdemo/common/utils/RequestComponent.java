package com.tang.appdemo.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @description 请求工具类
 *
 * @author tanghx
 * @date 2023/12/7 10:57
 */
@Slf4j
@Component
public class RequestComponent {

    public HttpServletRequest getRequest(){
        if (getRequestAttributes() != null){
            return getRequestAttributes().getRequest();
        }

        return null;
    }

    private ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
    }
}

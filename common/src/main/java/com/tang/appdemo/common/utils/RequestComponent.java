package com.tang.appdemo.common.utils;

import com.tang.appdemo.common.CommonApplication;
import com.tang.appdemo.common.constants.AppConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Locale;

/**
 * @description 请求工具类
 *
 * @author tanghx
 * @date 2023/12/7 10:57
 */
@Slf4j
@Component
public class RequestComponent {

    /**
     * 获取 Request 请求对象
     * @return
     */
    public HttpServletRequest getRequest(){
        if (getRequestAttributes() != null){
            return getRequestAttributes().getRequest();
        }

        return null;
    }

    /**
     * 获取请求头信息
     * @return
     */
    private ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取 Locale
     * @return
     */
    public Locale getLocale() {
        return AppConstants.LOCALE;
    }
}

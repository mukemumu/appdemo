package com.tang.appdemo.common.utils;

import org.apache.catalina.User;

/**
 * @description ThreadLocal 工具类
 *
 * @author tanghx
 * @date 2023/12/6 10:01
 */
public class LoginContextUtil {

    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void set(User user){
        threadLocal.set(user);
    }

    public static User get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}

package com.buercorp.appdemo.portalapi.utils;

import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;

/**
 * @description 反射工具类
 *
 * @author tanghx
 * @date 2023/12/7 9:43
 */
public class ReflexUtil {


    /**
     * 通过 HandlerMethod 获取类或方法上是否存在指定的注解类型
     * @param handlerMethod
     * @param annotationClass
     * @return
     * @param <T>
     */
    public static <T extends Annotation> T getClazzOrMethodAnnotation(HandlerMethod handlerMethod,
            Class<T> annotationClass){

        Class<?> clazz = handlerMethod.getBeanType();
        T annotation = clazz.getAnnotation(annotationClass);
        if (annotation != null){
            return annotation;
        }

        return handlerMethod.getMethod().getAnnotation(annotationClass);
    }
}

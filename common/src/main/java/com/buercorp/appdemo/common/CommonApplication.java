package com.buercorp.appdemo.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description
 *
 * @author tanghx
 * @date 2023/12/4 10:06
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.buercorp.appdemo")
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }
}
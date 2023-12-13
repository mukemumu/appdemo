package com.buercorp.appdemo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description
 *
 * @author tanghx
 * @date 2023/12/6 10:46
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.buercorp.appdemo")
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}

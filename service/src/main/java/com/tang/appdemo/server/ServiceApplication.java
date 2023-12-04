package com.tang.appdemo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description 扫描
 *
 * @author tanghx
 * @date 2023/12/4 10:12
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.tang.appdemo")
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}

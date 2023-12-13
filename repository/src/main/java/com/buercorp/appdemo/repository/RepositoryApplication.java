package com.buercorp.appdemo.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description
 *
 * @author tanghx
 * @date 2023/12/4 10:13
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.buercorp.appdemo")
public class RepositoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(RepositoryApplication.class, args);
    }
}

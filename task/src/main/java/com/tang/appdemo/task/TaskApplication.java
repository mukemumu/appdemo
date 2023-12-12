package com.tang.appdemo.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tanghx
 * @description
 * @date 2023/12/11 19:18
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.tang.appdemo")
public class TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}

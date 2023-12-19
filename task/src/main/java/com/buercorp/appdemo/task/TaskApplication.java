package com.buercorp.appdemo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author tanghx
 * @description
 * @date 2023/12/11 19:18
 */

@Slf4j
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = "com.buercorp.appdemo")
public class TaskApplication{
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}

package com.buercorp.appdemo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author tanghx
 * @description
 * @date 2023/12/11 19:18
 */

@Slf4j
@EnableAsync
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = "com.buercorp.appdemo")
public class TaskApplication{
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}

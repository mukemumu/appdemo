package com.tang.appdemo.protoApi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description 全局启动类
 *
 * @author tanghx
 * @date 2023/12/4 10:07
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.tang.appdemo")
public class ProtoApiApplication {
    public static void main(String[] args) {
        log.info("启动···");
        SpringApplication.run(ProtoApiApplication.class, args);
    }
}

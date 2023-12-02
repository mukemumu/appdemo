package com.tang.appdemo.proto_api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.tang.appdemo")
public class ProtoApiApplication {
    public static void main(String[] args) {
        log.info("启动···");
        SpringApplication.run(ProtoApiApplication.class, args);
    }
}

package com.buercorp.appdemo.adminapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanghx
 * @description
 * @date 2023/12/6 16:41
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenApi(){
        return new OpenAPI().info(new Info()
                .title("appdemo 接口文档")
                .description("appdemo 接口文档")
                .version("1.0.0"));
    }
}

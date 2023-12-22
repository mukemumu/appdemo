package com.buercorp.appdemo.task.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author tanghx
 * @description
 * @date 2023/12/19 17:07
 */
@Slf4j
@Component
public class LogsDeleteTask{

    @Scheduled(cron = "0 0 * * * *")
    public void deleteLogs() {

    }
}

package com.buercorp.appdemo.task.config;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description 自定义任务执行线程池
 *
 * @author tanghx
 * @date 2023/12/19 18:11
 */

@Configuration
public class TaskConfig {

    @Lazy
    @Bean
    public ThreadPoolTaskExecutor taskExecutor(TaskExecutionProperties properties){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        // 配置参数集合
        PropertyMapper mapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        TaskExecutionProperties.Pool pool = properties.getPool();       // 获取线程池对象

        // 配置线程池
        mapper.from(pool::getCoreSize).to(taskExecutor::setCorePoolSize);
        mapper.from(pool::getMaxSize).to(taskExecutor::setMaxPoolSize);
        mapper.from(pool::getQueueCapacity).to(taskExecutor::setQueueCapacity);
        mapper.from(pool::getKeepAlive).asInt(Duration::getSeconds).to(taskExecutor::setKeepAliveSeconds);
        mapper.from(pool::isAllowCoreThreadTimeout).to(taskExecutor::setAllowCoreThreadTimeOut);
        mapper.from("my-task-").whenHasText().to(taskExecutor::setThreadNamePrefix);

        // 设置任务拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        return taskExecutor;
    }
}

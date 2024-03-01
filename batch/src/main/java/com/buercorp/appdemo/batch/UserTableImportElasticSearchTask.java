package com.buercorp.appdemo.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.io.IOException;


/**
 * @Author ldd
 * @Date 2024/2/22
 * 测试数据库中的User表中的十万条数据导入ElasticSearch
 */
@Slf4j
@Component
public class UserTableImportElasticSearchTask {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("userTableToEsJob")
    private Job job;


    int n=1;

    @Scheduled(cron = "0 0/5 * * * *")//每五分钟执行一次
    public void task() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, IOException {
        log.warn("执行了第"+n+"次数");
        n++;

       jobLauncher.run(job,new JobParameters());
    }
}

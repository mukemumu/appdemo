package com.buercorp.appdemo.batch.service;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import java.io.IOException;
import java.text.ParseException;

/**
 * @Author ldd
 * @Date 2024/3/1
 */
public interface UserTableData {
    /**
     * 为User表进行初始化数据
     * @param n 需要生成的数据的量
     */
    void dataInit(int n) throws IOException, ParseException;

    void csvToDb() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException;
}

package com.buercorp.appdemo.batch;

import com.buercorp.appdemo.batch.service.UserTableData;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.ParseException;

/**
 * @Author ldd
 * @Date 2024/3/1
 */
@SpringBootTest
public class DaTest {

    @Autowired
    private UserTableData userTableData;

    @Test
    void dataTest() throws IOException, ParseException {
        userTableData.dataInit(100);
    }

    @Test
    void csvToDb() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        userTableData.csvToDb();
    }
}

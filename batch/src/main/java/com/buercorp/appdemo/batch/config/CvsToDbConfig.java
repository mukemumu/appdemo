package com.buercorp.appdemo.batch.config;

import com.buercorp.appdemo.repository.model.po.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;

/**
 * @Author ldd
 * @Date 2024/2/27
 */
@Configuration
public class CvsToDbConfig {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private final String path=System.getProperty("user.dir");

    //多线程读-读文件，使用FlatFileItemReader
    @Bean
    public FlatFileItemReader<User> cvsToDBItemReader(){
        FlatFileItemReader<User> reader = new FlatFileItemReaderBuilder<User>()
                .name("CSVItemReader")
                .saveState(false) //防止状态被覆盖
                .resource(new PathResource(new File(path, "user.csv").getAbsolutePath()))
                .delimited()
                .names("userId", "username", "loginId", "password","birthday","sex","city","phone","mail","avatar","identity","firstWorkingTime","isValid","createTime","updateTime")
                .targetType(User.class)
                .build();

        return reader;
    }

    //数据库写-使用mybatis提供批处理读入
    @Bean
    public MyBatisBatchItemWriter<User> cvsToDBItemWriter(){
        MyBatisBatchItemWriter<User> itemWriter = new MyBatisBatchItemWriter<>();
        itemWriter.setSqlSessionFactory(sqlSessionFactory); //需要指定sqlsession工厂
        //指定要操作sql语句，路径id为：EmployeeMapper.xml定义的sql语句id
        itemWriter.setStatementId("com.buercorp.appdemo.repository.mapper.UserMapper.save");  //操作sql
        return itemWriter;
    }

    @Bean
    public Step cvsToDBStep(JobRepository jobRepository, PlatformTransactionManager transactionManage){
        return new StepBuilder("cvsToDbStep", jobRepository)
                .<User, User>chunk(1000, transactionManage)
                .reader(cvsToDBItemReader())
                .writer(cvsToDBItemWriter())
                .allowStartIfComplete(true)//允许步骤重启
                //.taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }


    @Bean
    public Job csvToDBJob(JobRepository jobRepository,
                           @Qualifier("cvsToDBStep") Step stepTest) {
        return new JobBuilder("cvs-to-db-job2", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(stepTest)
                .build();
    }

}

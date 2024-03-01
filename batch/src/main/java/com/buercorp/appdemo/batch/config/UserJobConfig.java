package com.buercorp.appdemo.batch.config;

import com.buercorp.appdemo.repository.mapper.UserRowMapper;
import com.buercorp.appdemo.repository.model.po.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author ldd
 * @Date 2024/2/22
 */
@Configuration
public class UserJobConfig {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //注入
    @Bean
    public UserRowMapper userRowMapper() {
        return new UserRowMapper();
    }

    @Bean
    public JdbcCursorItemReader<User> userItemReader() {
        return new JdbcCursorItemReaderBuilder<User>()
                .name("UserItemReader")
                .dataSource(dataSource)
                .sql("select * from user")
                .rowMapper(userRowMapper())
                .build();
    }


    @Bean
    public ItemWriter<User> userItemWriter() {
        return new ItemWriter<User>() {
            @Override
            public void write(Chunk<? extends User> chunk) throws Exception {
                List<? extends User> items = chunk.getItems();
                for (User item : items) {
                    System.out.println(item);
                }
                elasticsearchTemplate.save(items);
            }
        };
    }


    @Bean
    public Step userTableToEsStep(JobRepository jobRepository, PlatformTransactionManager transactionManage) {
        return new StepBuilder("userTableToEs", jobRepository)
                .<User, User>chunk(500, transactionManage)
                .reader(userItemReader())
                .writer(userItemWriter())
                .allowStartIfComplete(true)//允许步骤重启
                .build();

    }


    @Bean
    public Job userTableToEsJob(JobRepository jobRepository,
                       @Qualifier("userTableToEsStep") Step stepTest) {
        return new JobBuilder("mysql-in-es-job-user-table", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(stepTest)
                .build();
    }
}

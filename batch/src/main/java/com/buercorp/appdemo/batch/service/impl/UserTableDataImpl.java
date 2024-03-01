package com.buercorp.appdemo.batch.service.impl;

import com.buercorp.appdemo.batch.service.UserTableData;
import com.buercorp.appdemo.batch.utils.AreaGenerate;
import com.buercorp.appdemo.batch.utils.BirthdayGenerate;
import com.buercorp.appdemo.batch.utils.EmailGenerate;
import com.buercorp.appdemo.batch.utils.PhoneGenerate;
import com.buercorp.appdemo.repository.mapper.AreaMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Author ldd
 * @Date 2024/3/1
 */
@Service
public class UserTableDataImpl implements UserTableData {
    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("csvToDBJob")
    private Job job;

    @Override
    public void dataInit(int n) throws IOException, ParseException {
        String path = System.getProperty("user.dir");
        File file = new File(path, "user.csv");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        String txt = "";

        Random numberR = new Random();
        Random boolR = new Random();
        // 给文件中生产10万条数据
        long beginTime = System.currentTimeMillis();
        System.out.println("开始时间：【 " + beginTime + " 】");
        //生成手机号：
        Set<String> randomPhoneNumbers = PhoneGenerate.generateRandomPhoneNumbers(n);
        List<String> phoneNumberList = new ArrayList<>(randomPhoneNumbers);
        Collections.shuffle(phoneNumberList);

        //生成邮箱：
        Set<String> randomEmailNumbers = EmailGenerate.generateRandomEmailNumbers(n);
        List<String> emailNumberList = new ArrayList<>(randomEmailNumbers);
        Collections.shuffle(emailNumberList);

        //生成出生日期：
        List<String> birthdayNumbers = BirthdayGenerate.generateRandomBirthdayNumbers(n);
        List<String> birthdayNumberList = new ArrayList<>(birthdayNumbers);
        Collections.shuffle(birthdayNumberList);

        //生产随机地址
        List<String> areaNumbers = AreaGenerate.generateRandomAreaNumbers(1000,areaMapper);

        //生成第一次工作时间,创建时间和更新时间：
        String firstWorkingTime = "2020-8-20";
        String createTime = "2024-2-20";
        String updateTime = "2024-2-28";
        // 将字符串日期,转成Date对象
        // 创建SimpleDateFormat对象,写日期模式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 调用方法parse,字符串转成日期对象
        Date date = sdf.parse(firstWorkingTime);
        Date date2 = sdf.parse(createTime);
        Date date3 = sdf.parse(updateTime);


        for (int i = 0; i <= (n-1); i++) {
            if (i == (n-1)) {
                txt = i+1 + ",user_" + i + "," + (boolR.nextBoolean() ? phoneNumberList.get(i) : emailNumberList.get(i)) + "," + numberR.nextInt(1000,1000000) + "," + birthdayNumberList.get(i) + "," + (boolR.nextBoolean() ? 0 : 1) + "," + areaNumbers.get(numberR.nextInt(1000)) + "," + phoneNumberList.get(i) + "," + emailNumberList.get(i) + "," + "avatar/" + i +".img"+ "," + (boolR.nextBoolean() ? 0 : 1) + "," + date + "," + (boolR.nextBoolean() ? 0 : 1) + "," + date2 + "," + date3;
            } else {
                txt = i+1 + ",user_" + i + "," + (boolR.nextBoolean() ? phoneNumberList.get(i) : emailNumberList.get(i)) + "," + numberR.nextInt(1000,1000000) + "," + birthdayNumberList.get(i) + "," + (boolR.nextBoolean() ? 0 : 1) + "," + areaNumbers.get(numberR.nextInt(1000)) + "," + phoneNumberList.get(i) + "," + emailNumberList.get(i) + "," + "avatar/" + i + ".img"+"," + (boolR.nextBoolean() ? 0 : 1) + "," + date + "," + (boolR.nextBoolean() ? 0 : 1) + "," + date2 + "," + date3 + "\n";
            }

            out.write(txt.getBytes());
            out.flush();
        }
        out.close();
        System.out.println("总共耗时：【 " + (System.currentTimeMillis() - beginTime)/1000 + " 】秒");
    }

    @Override
    public void csvToDb() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        long time = new Date().getTime();
        jobLauncher.run(job,new JobParameters());
    }


}

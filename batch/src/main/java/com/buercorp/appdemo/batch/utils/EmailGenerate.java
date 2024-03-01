package com.buercorp.appdemo.batch.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author ldd
 * @Date 2024/2/26
 */
public class EmailGenerate {

    public static Set<String> generateRandomEmailNumbers(int count) {
        Set<String> EmailNumbers = new HashSet<>();

        while (EmailNumbers.size() < count) {
            String emailNumber = generateRandomEmailNumber();
            EmailNumbers.add(emailNumber);
        }

        return EmailNumbers;
    }

    public static String generateRandomEmailNumber() {
        Random random=new Random();
        String number="";
        String suffix="";
        for(int i=0;i<10;i++){
            if (i==0){
                number+=1+random.nextInt(9);
                continue;
            }
            number+=random.nextInt(10);
        }
        int i=random.nextInt(3);
        if(i==0){
            suffix="@qq.com";
        }
        else if(i==1){
            suffix="@gmail.com";
        }
        else{
            suffix="@163.com";
        }
        return number+suffix;
    }
}

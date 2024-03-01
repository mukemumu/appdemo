package com.buercorp.appdemo.batch.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author ldd
 * @Date 2024/2/26
 */
public class PhoneGenerate {
    public static Set<String> generateRandomPhoneNumbers(int count) {
        Set<String> phoneNumbers = new HashSet<>();

        while (phoneNumbers.size() < count) {
            String phoneNumber = generateRandomPhoneNumber();
            phoneNumbers.add(phoneNumber);
        }

        return phoneNumbers;
    }

    public static String generateRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // 生成手机号码的前三位（例如：138）
        sb.append("1").append(random.nextInt(10)).append(random.nextInt(10));

        // 生成手机号码的后八位（例如：12345678）
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}

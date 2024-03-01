package com.buercorp.appdemo.batch.utils;

import java.util.*;

/**
 * @Author ldd
 * @Date 2024/2/26
 */
public class BirthdayGenerate {


    public static List<String> generateRandomBirthdayNumbers(int count) {
        List<String> BirthdayNumbers = new ArrayList<>();

        while (BirthdayNumbers.size() < count) {
            String birthdayNumber = randomBirthday();
            BirthdayNumbers.add(birthdayNumber);
        }

        return BirthdayNumbers;
    }


    public static String randomBirthday() {

        Random numberR = new Random();

        int yyyy = numberR.nextInt(1900, 2024);
        int mm = numberR.nextInt(1, 12);
        int dd = 0; // will set it later depending on year and month

        switch(mm) {
            case 2:
                if (isLeapYear(yyyy)) {
                    dd = numberR.nextInt(1, 29);
                } else {
                    dd = numberR.nextInt(1, 28);
                }
                break;

            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dd = numberR.nextInt(1, 31);
                break;

            default:
                dd = numberR.nextInt(1, 30);
                break;
        }

        String year = Integer.toString(yyyy);
        String month = Integer.toString(mm);
        String day = Integer.toString(dd);

        if (mm < 10) {
            month ="0" + mm;
        }

        if (dd < 10) {
            day ="0" + dd;
        }

        return year + '-' + month + '-' + day;
    }

    public static boolean isLeapYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        int noOfDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        if (noOfDays > 365) {
            return true;
        }
        return false;
    }
}

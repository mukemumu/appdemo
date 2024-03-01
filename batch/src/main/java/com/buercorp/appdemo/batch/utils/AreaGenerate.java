package com.buercorp.appdemo.batch.utils;

import com.buercorp.appdemo.repository.mapper.AreaMapper;
import com.buercorp.appdemo.repository.model.po.Area;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author ldd
 * @Date 2024/2/27
 */
public class AreaGenerate {

    public static List<String> generateRandomAreaNumbers(int count, AreaMapper areaMapper) {
        List<String> AreaNumbers = new ArrayList<>();
        List<Area> provinces = areaMapper.findAreaByParent("86");
        while (AreaNumbers.size() < count) {
            String area = randomArea(areaMapper,provinces);
            AreaNumbers.add(area);
        }
        return AreaNumbers;
    }

    public static String randomArea(AreaMapper areaMapper,List<Area> provinces) {

        Random numberR = new Random();
        int provinceIndex = numberR.nextInt(provinces.size());
        Area province = provinces.get(provinceIndex);


        List<Area> cities = areaMapper.findAreaByParent(province.getCode());
        if (cities.size()==0){
            return province.getName();
        }
        int cityIndex = numberR.nextInt(cities.size());
        Area city = cities.get(cityIndex);


        List<Area> counties = areaMapper.findAreaByParent(city.getCode());
        if (counties.size()==0){
            return province.getName()+city.getName();
        }
        int countyIndex = numberR.nextInt(counties.size());
        Area county = counties.get(countyIndex);

        return province.getName() + city.getName() + county.getName();

    }
}

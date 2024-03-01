package com.buercorp.appdemo.repository.mapper;

import com.buercorp.appdemo.repository.model.po.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author ldd
 * @Date 2024/2/27
 */
@Mapper
public interface AreaMapper {
    List<Area> findAreaByParent(String parent);
}

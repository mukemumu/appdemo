package com.buercorp.appdemo.repository.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ldd
 * @Date 2024/2/27
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Area {
    private Integer id;
    private String parent;
    private String code;
    private String name;
}

package com.buercorp.appdemo.repository.model.po;

import lombok.Data;
import java.util.Date;

/**
 * @description 全局实体基类
 *
 * @author tanghx
 * @date 2023/12/4 10:09
 */
@Data
public class BaseEntity {

    private Long id;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;
}

package com.tang.appdemo.repository.model.po;

import lombok.Data;
import java.util.Date;

/**
 *
 *
 *
 * @author tanghx
 * Create on 2023/11/29
 */
@Data
public class BaseEntity {

    private Long id;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;
}

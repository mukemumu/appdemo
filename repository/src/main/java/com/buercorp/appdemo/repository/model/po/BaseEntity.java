package com.buercorp.appdemo.repository.model.po;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

/**
 * @description 全局实体基类
 *
 * @author tanghx
 * @date 2023/12/4 10:09
 */
@Data
public class BaseEntity {

    @NotNull(message = "用户 ID 不能为空")
    private Long id;

    @PastOrPresent(message = "输入正确的时间！")
    private Date createTime;

    @PastOrPresent(message = "输入正确的时间！")
    private Date updateTime;

    @Range(min = 0, max = 1)
    private Integer isDeleted;
}

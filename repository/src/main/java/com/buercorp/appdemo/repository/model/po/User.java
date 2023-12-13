package com.buercorp.appdemo.repository.model.po;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

/**
 * @description user 表
 *
 * @author tanghx
 * @date 2023/12/4 10:10
 */
@Data
public class User extends BaseEntity {

    @NotNull(message = "账号不能为空")
    private String userName;

    @Size(min = 6, max = 12, message = "密码长度为 6 ~ 12！")
    private String password;

    @NotNull(message = "用户名不能为空")
    private String name;

    @Length(min = 11, max = 11, message = "输入正确的手机号！")
    private String phone;

    @URL
    private String avatar;

    private String description;

    /**
     * 1：表示可用，0：表示禁用
     */
    @Range(min = 0, max = 1)
    private Integer status;

}

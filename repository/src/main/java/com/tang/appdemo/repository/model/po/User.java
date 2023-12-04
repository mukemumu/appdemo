package com.tang.appdemo.repository.model.po;

import lombok.Data;

/**
 * @description user 表
 *
 * @author tanghx
 * @date 2023/12/4 10:10
 */
@Data
public class User extends BaseEntity {
    private String userName;

    private String password;

    private String name;

    private String phone;

    private String avatar;

    private String description;

    /**
     * @see UserStatus
     */
    private Integer status;

}

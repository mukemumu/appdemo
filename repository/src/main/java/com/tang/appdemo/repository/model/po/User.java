package com.tang.appdemo.repository.model.po;

import lombok.Data;

/**
 * table: user
 * @author tanghx
 * Create on 2023/11/29
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

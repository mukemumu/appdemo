package com.buercorp.appdemo.repository.model.po;

import java.util.Date;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

/**
* Created by MyBatis Generator 2024/02/29
* https://github.com/greatwqs/mybatis-generator-plugin
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "user")
public class User {
    private Long userId;

    private String username;

    private String loginId;

    private String password;

    private String birthday;

    private Byte sex;

    private String city;

    private String phone;

    private String mail;

    private String avatar;

    private Byte identity;

    private Date firstWorkingTime;

    private Byte isValid;

    private Date createTime;

    private Date updateTime;
}
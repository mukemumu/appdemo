//package com.buercorp.appdemo.repository.model.es;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.io.Serializable;
//
///**
// * @author tanghx
// * @description 描述 ES 索引结构的实体类
// * @date 2024/1/31 10:59
// */
//@JsonIgnoreProperties(ignoreUnknown = true)
//@Document(indexName = "user_index", createIndex = false)
//public class UserIndex implements Serializable {
//
//    private static final long serialVersionID = 1L;         // 序列版本 ID
//
//    @Field(type = FieldType.Integer)
//    private Integer id;
//
//    @Field(type = FieldType.Keyword)
//    private String userName;
//
//    @Field(type = FieldType.Keyword)
//    private String password;
//
//    @Field(type = FieldType.Keyword)
//    private String name;
//
//    @Field(type = FieldType.Keyword)
//    private String phone;
//
//    @Field(type = FieldType.Keyword)
//    private String avatar;
//
//    @Field(type = FieldType.Keyword)
//    private String description;
//
//    @Field(type = FieldType.Integer)
//    private Integer status;
//}

//package com.buercorp.appdemo.portalapi.controller;
//
//import com.buercorp.appdemo.repository.model.po.User;
//import jakarta.validation.constraints.NotNull;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author tanghx
// * @description elasticsearch test controller
// * @date 2024/1/30 17:07
// */
//@RestController
//@RequestMapping(value = "/user")
//public class ElasticsearchController {
//
//    private ElasticsearchOperations elasticsearchOperations;
//
//    public ElasticsearchController(ElasticsearchOperations elasticsearchOperations) {
//        this.elasticsearchOperations = elasticsearchOperations;
//    }
//
//    @PostMapping("/user")
//    public @NotNull(message = "用户 ID 不能为空") Long save(@RequestBody User user) {
//        User savedEntity = elasticsearchOperations.save(user);
//        return savedEntity.getId();
//    }
//
//    @GetMapping("/user/{id}")
//    public User findById(@PathVariable("id")  Long id) {
//        User user = elasticsearchOperations.get(id.toString(), User.class);
//        return user;
//    }
//}

//package com.buercorp.appdemo.service.es.impl;
//
//
//import com.buercorp.appdemo.repository.mapper.UserMapper;
//import com.buercorp.appdemo.repository.model.es.UserIndex;
//import com.buercorp.appdemo.repository.model.po.User;
//import com.buercorp.appdemo.service.es.UserIndexService;
//import com.buercorp.appdemo.service.user.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.IndexOperations;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author tanghx
// * @description 初始化索引结构，同步 sys_user 表中内容
// * @date 2024/2/5 15:04
// */
//@Slf4j
//@Service
//public class UserIndexServiceImpl implements UserIndexService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    /**
//     * 索引结构初始化
//     */
//    public void initIndex(){
//        // 索引初始化
//        IndexOperations indexOps = elasticsearchTemplate.indexOps(UserIndex.class);
//        if (indexOps.exists()){
//            boolean delFlag = indexOps.delete();
//            log.info("contents_index exists, delete:{}", delFlag);
//            indexOps.createMapping(UserIndex.class);
//        }else {
//            log.info("Contents_index not exists");
//            indexOps.createMapping(UserIndex.class);
//        }
//
//        // 数据同步
//        List<User> userList = userMapper.getAll();
//        if (userList.size() > 0){
//            List<UserIndex> userIndexList = new ArrayList<>();
//            userIndexList.forEach(user -> {
//                UserIndex userIndex = new UserIndex();
//                BeanUtils.copyProperties(user, userIndex);
//                userIndexList.add(userIndex);
//            });
//            elasticsearchTemplate.save(userIndexList);
//
//            // 根据 ID 查询
////            UserIndex userIndex = elasticsearchTemplate.get("2", UserIndex.class);
////            log.info("user_index - 2:{}", userIndex);
//        }
//    }
//}

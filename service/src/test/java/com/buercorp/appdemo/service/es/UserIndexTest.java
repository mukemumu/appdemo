//package com.buercorp.appdemo.service.es;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.TransportUtils;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.elasticsearch.client.RestClient;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.net.ssl.SSLContext;
//import java.util.Optional;
//
///**
// * @author tanghx
// * @description
// * @date 2024/1/31 17:50
// */
//@SpringBootTest
//public class UserIndexTest {
//
//    @Autowired
//    private UserIndexService userIndexService;
//
//    @Test
//    public void testInit(){
//        userIndexService.initIndex();
//    }
//}

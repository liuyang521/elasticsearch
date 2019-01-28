package com.springboot.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ElasticsearchApplication {
    /**
     * SpringBoot默认支持两种技术来和ES交互
     * 1、Jest （默认不生效）
     *      需要导入jest的工具包（io.searchbox.client.JestClient）
     * 2、SpringData ElasticSearch [连接超时有可能版本不适配]    https://docs.spring.io/spring-data/elasticsearch/docs/3.1.4.RELEASE/reference/html/
     *      1)、Client 节点信息clusterNodes、clusterName
     *      2)、ElasticsearchTemplate
     *      3)、编写ElasticsearchRepository的子接口操作es
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }
    @RequestMapping(value = "/",produces = "text/plain;charset=UTF-8")
    String index(){
        return "Hello Spring Boot!";
    }
}


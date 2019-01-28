package com.springboot.elasticsearch;

import com.springboot.elasticsearch.entity.Person;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot单元测试
 *
 * 可以在测试期间很方便的类似编码一眼进行自动注入等容器的功能
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void contextLoads() {
        System.out.println(person);

    }

    @Test
    public void testHelloService() {
        //此处名称要和MyAppConfig中方法名称一致
        boolean b = ioc.containsBean("helloService");
        System.out.println(b); // true
    }

}


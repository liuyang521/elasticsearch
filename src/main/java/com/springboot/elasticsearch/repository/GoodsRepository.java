package com.springboot.elasticsearch.repository;


import com.springboot.elasticsearch.entity.GoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface GoodsRepository extends ElasticsearchRepository<GoodsInfo,Long> {

}

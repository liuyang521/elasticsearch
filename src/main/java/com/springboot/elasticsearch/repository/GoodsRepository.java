package com.springboot.elasticsearch.repository;


import com.springboot.elasticsearch.entity.GoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodsRepository extends ElasticsearchRepository<GoodsInfo,Long> {
    //严格按照命名规范 findByGoodNameLike 会报错，No property goodName found for type GoodsInfo
    //没有goodName属性
    List<GoodsInfo> findByNameLike(String goodName);

}

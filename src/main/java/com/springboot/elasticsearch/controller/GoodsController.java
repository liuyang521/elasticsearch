package com.springboot.elasticsearch.controller;

import com.springboot.elasticsearch.entity.GoodsInfo;
import com.springboot.elasticsearch.repository.GoodsRepository;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("save")
    public String save() {
        GoodsInfo goods = new GoodsInfo(System.currentTimeMillis(),
                "商品"+System.currentTimeMillis(),"测试商品");
        goodsRepository.save(goods);
        return "success";
    }

    @GetMapping("delete")
    public String delete(long id){
        goodsRepository.deleteById(id);
        return "success";
    }

    @GetMapping("update")
    public String update(long id,String name,String description){
        GoodsInfo goods = new GoodsInfo(id,name,description);
        goodsRepository.save(goods);
        return "success";
    }

    @GetMapping("/select/{q}")
    public List<GoodsInfo> testSearch(@PathVariable String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);//根据name/id/description模糊查询
        Iterable<GoodsInfo> searchResult = goodsRepository.search(builder);

        Iterator<GoodsInfo> iterator = searchResult.iterator();
        List<GoodsInfo> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     *
     * @param page 当前页
     * @param size 每页条数
     * @param q 查询条件
     * @return list
     */
    @GetMapping("/{page}/{size}")
    public List<GoodsInfo> searchCity(@PathVariable Integer page, @PathVariable Integer size, String q) {

        // 分页参数
        Pageable pageable = new PageRequest(page, size);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if(null == q){
            MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();//查询全部

            nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withPageable(pageable)
                    .withQuery(matchAllQueryBuilder);
        }else{
            QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
            // 分数，并自动按分排序
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builder);//条件查询

            nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withPageable(pageable)
                    .withQuery(functionScoreQueryBuilder);
        }
        // 分数、分页
        SearchQuery searchQuery = nativeSearchQueryBuilder.build();
        List<GoodsInfo> list = goodsRepository.search(searchQuery).getContent();
        return list;

    }

}
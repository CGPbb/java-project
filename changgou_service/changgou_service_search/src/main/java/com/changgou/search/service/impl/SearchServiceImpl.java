package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Override
    public Map search(Map<String, String> searchMap) throws Exception {
        Map<String, Object> resultMap = new HashMap();
        //有条件才查询ES
        if (null != searchMap) {
            //组合条件对象
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            //keywords的参数值不为空
            if (!StringUtils.isEmpty(searchMap.get("keywords"))) {
                //name:表示按照name域进行查询（请在elasticsearch-head插件中查看，name域对应到SkuInfo类的name字段）
                //matchQuery:模糊查询
                boolQuery.must(QueryBuilders.matchQuery("name", searchMap.get("keywords")).operator(Operator.AND));
            }
            //按照品牌过滤查询
            if (!StringUtils.isEmpty(searchMap.get("brand"))) {
                //brandName:表示按照brandName域进行查询（请在elasticsearch-head插件中查看，brandName域对应到SkuInfo类的brandName字段）
                //termQuery:精确查询
                boolQuery.must(QueryBuilders.termQuery("brandName", searchMap.get("brand")));
            }
            //按照规格进行过滤查询
            for (String key : searchMap.keySet()) {
                if (key.startsWith("spec_")) {
                    String value = searchMap.get(key).replace("%2B", "+");
                    boolQuery.filter(QueryBuilders.termQuery("specMap." + key.substring(5) + ".keyword", value));
                }
            }
            //按照价格区间进行过滤查询
            if(!StringUtils.isEmpty(searchMap.get("price"))){
                String[] p = searchMap.get("price").split("-");
                if(p.length==2){
                   boolQuery.filter(QueryBuilders.rangeQuery("price").lte(p[1]));
                }
                boolQuery.filter(QueryBuilders.rangeQuery("price").gte(p[0]));
            }
            //原生搜索实现类
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(boolQuery);
            //高亮
            HighlightBuilder.Field field = new HighlightBuilder.Field("name").preTags("<span style='color:red'>").postTags("</span>");
            nativeSearchQueryBuilder.withHighlightFields(field);
            //品牌聚合（分组）查询
            String skuBrand = "skuBrand";
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(skuBrand).field("brandName"));

            //规格集合（分组）查询
            String skuSpec = "skuSpec";
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(skuSpec).field("spec.keyword"));
            //-----分页-----
            //获取当前页
           String pageNum =searchMap.get("pageNum");
           String pageSize = searchMap.get("pageSize");
           if(StringUtils.isEmpty(pageNum)){
               pageNum = "1";
           }
           if(StringUtils.isEmpty(pageSize)){
               //默认设置为每页显示为30条记录
               pageSize="30";
           }
           nativeSearchQueryBuilder.withPageable(PageRequest.of(Integer.valueOf(pageNum)-1,Integer.valueOf(pageSize)));
            //-----排序-----
            //排序字段不为空时
            if(!StringUtils.isEmpty(searchMap.get("sortField"))){
                //再判断是那种排序方式：升序（ASC）或降序（DESC）
                if("ASC".equals(searchMap.get("sortRule"))){
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(searchMap.get("sortField")).order(SortOrder.ASC));
                }else{
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(searchMap.get("sortField")).order(SortOrder.DESC));
                }
            }
            //执行查询，返回结果对象
            /**
             *  第一个参数:条件构建对象
             *  第二个参数:查询实体类
             *  第三个参数:查询结果操作对象
             */

            AggregatedPage<SkuInfo> aggregatedPage = esTemplate.queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class, new SearchResultMapper() {
                @Override
                public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                    List<T> list = new ArrayList<>();
                    SearchHits hits = searchResponse.getHits();
                    if (null != hits) {
                        for (SearchHit hit : hits) {
                            SkuInfo skuInfo = JSON.parseObject(hit.getSourceAsString(), SkuInfo.class);
                            //添加高亮域代码实现
                            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                            if(null !=highlightFields && highlightFields.size()>0){
                                skuInfo.setName(highlightFields.get("name").getFragments()[0].toString());
                            }
                            list.add((T) skuInfo);
                        }
                    }
                    return new AggregatedPageImpl<T>(list, pageable, hits.getTotalHits(), searchResponse.getAggregations());
                }
            });
            //总条数
            resultMap.put("total", aggregatedPage.getTotalElements());
            //总页数
            resultMap.put("totalPages", aggregatedPage.getTotalPages());
            //查询结果集合
            resultMap.put("rows", aggregatedPage.getContent());
            //获取品牌聚合结果
            StringTerms brandTerms = (StringTerms) aggregatedPage.getAggregation(skuBrand);
            List<String> brandList = brandTerms.getBuckets().stream().map(bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
            resultMap.put("brandList",brandList);
            //获取规格聚合结果
            StringTerms specTerms = (StringTerms) aggregatedPage.getAggregation(skuSpec);
            List<String> specList = specTerms.getBuckets().stream().map(bucket -> bucket.getKeyAsString()).collect(Collectors.toList());
            resultMap.put("specList",specList(specList));
            //将当前页放入resutlMap中，并返回给前端
            resultMap.put("pageNum",pageNum);
            return resultMap;
        }
        return null;
    }

    //处理规格集合  源码中的方法为formartSpec
    public Map<String, Set<String>> specList(List<String> specList) {
        Map<String, Set<String>> specMap = new HashMap<>();
        if (null != specList && specList.size() > 0) {
            for (String spec : specList) {
                Map<String, String> map = JSON.parseObject(spec, Map.class);
                Set<Map.Entry<String, String>> entries = map.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Set<String> specValues = specMap.get(key);
                    if (null == specValues) {
                        specValues = new HashSet<>();
                    }
                    specValues.add(value);
                    specMap.put(key, specValues);
                }
            }
        }
        return specMap;
    }
}

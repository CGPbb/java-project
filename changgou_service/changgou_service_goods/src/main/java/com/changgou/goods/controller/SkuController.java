package com.changgou.goods.controller;

import com.changgou.goods.pojo.Sku;
import com.changgou.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SkuController {

    @Autowired
    private SkuService skuService;

    @ResponseBody
    @GetMapping("/sku/spu/{spuId}")
    public List<Sku> findSkuListBySpuId(@PathVariable("spuId") String spuId){
        Map<String,Object> searchMap = new HashMap<>();

        if (!"all".equals(spuId)){
            searchMap.put("spuId",spuId);
        }
        searchMap.put("status","1");
        List<Sku> skuList = skuService.findList(searchMap);

        return skuList;
    }
}

package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SpecController {
    @Autowired
    private SpecService specService;

    /**
     * 根据商品分类名称查询规格列表
     *
     * @param category
     * @return
     */
    @GetMapping("/spec/category/{category}")
    public Result findListByCategoryName(@PathVariable String category) {
        List<Map> specList = specService.findListByCategoryName(category);
        return new Result(StatusCode.OK, "", true, specList);
    }
}

package com.changgou.goods.service;

import java.util.List;
import java.util.Map;

public interface SpecService {
    /**
     * 根据商品分类名称查询规格列表
     * @param categoryName
     * @return
     */
    List<Map> findListByCategoryName(String categoryName);
}

package com.changgou.goods.service;

import com.changgou.goods.pojo.Sku;

import java.util.List;
import java.util.Map;

public interface SkuService {
    /**
     * 根据 id查询
     *
     * @param id
     * @return
     */
    Sku findById(int id);

    /**
     * 查询所有
     *
     * @return
     */
    List<Sku> findAll();

    /**
     * 添加数据
     *
     * @param sku
     * @return
     */
    boolean add(Sku sku);

    /**
     * 更新数据
     *
     * @param sku
     * @return
     */
    boolean update(Sku sku);

    /**
     * 删除
     *
     * @param id 用户 id
     * @return
     */
    boolean delete(int id);

    /***
     * 多条件搜索
     * @param searchMap
     * @return
     */
    List<Sku> findList(Map<String, Object> searchMap);
}

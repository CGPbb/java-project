package com.changgou.goods.service;

import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;

import java.util.List;

public interface SpuService {

    /**
     * 添加
     * @param goods
     */
    void add(Goods goods);

    /**
     * 根据 id查找
     * @param id
     * @return
     */
    Goods findGoodsById(String id);

    /**
     * 查询所有
     * @return
     */
    List<Spu> findAll();

    /**
     * 修改
     * @param goods
     */
    void update(Goods goods,String id);

    /**
     * 审核
     *
     * @param id
     */
    void audit(String id);

    /**
     * 下架商品
     *
     * @param id
     */
    void pull(String id);

    /**
     * 上架商品
     *
     * @param id
     */
    void put(String id);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 恢复数据
     *
     * @param id
     */
    void restore(String id);

    /**
     * 物理删除
     *
     * @param id
     */
    void realDelete(String id);

    Spu findById(String id);
}

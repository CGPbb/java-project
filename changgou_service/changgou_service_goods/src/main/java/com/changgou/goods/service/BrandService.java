package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {

    /**
     * 根据 id 查询品牌资料
     *
     * @param id
     * @return
     */
    Brand findById(int id);

    /***
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();

    /**
     * 添加品牌
     *
     * @param brand
     * @return
     */
    boolean add(Brand brand);

    /**
     * 修改品牌信息
     *
     * @param brand
     * @return
     */
    boolean update(Brand brand);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 多条件搜索品牌方法
     *
     * @param searchMap
     * @return
     */
    List<Brand> findList(Map<String, Object> searchMap);

    /**
     * 分页查询
     *
     * @param page 当前页码
     * @param size 显示条数
     * @return
     */
    Page<Brand> findPage(int page, int size);

    /**
     * 带条件的分页查询
     *
     * @param searchMap 条件
     * @param page
     * @param size
     * @return
     */
    Page<Brand> findPage(Map<String, Object> searchMap, int page, int size);

    /**
     * 根据商品分类名称查询品牌列表
     * @param categoryName
     * @return
     */
    public List<Map> findListByCategoryName(String categoryName);
}

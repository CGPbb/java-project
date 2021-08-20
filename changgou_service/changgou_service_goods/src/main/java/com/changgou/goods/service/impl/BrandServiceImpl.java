package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 根据 id 查询品牌资料
     *
     * @param id
     * @return
     */
    @Override
    public Brand findById(int id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /***
     * 查询所有品牌
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 添加品牌
     *
     * @param brand
     * @return
     */
    @Override
    public boolean add(Brand brand) {
        return brandMapper.insertSelective(brand) > 0 ? true : false;
    }

    /**
     * 修改品牌信息
     *
     * @param brand
     * @return
     */
    @Override
    public boolean update(Brand brand) {
        return brandMapper.updateByPrimaryKeySelective(brand) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return brandMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * 多条件搜索品牌方法
     *
     * @param searchMap
     * @return
     */
    @Override
    public List<Brand> findList(Map<String, Object> searchMap) {
        Example example = new Example(Brand.class);
        //创建条件对象
        Example.Criteria criteria = example.createCriteria();
        //品牌名称
        if (searchMap.get("name") != null && !searchMap.get("name").equals("")) {
            criteria.andLike("name", "%" + searchMap.get("name") + "%");
        }
        //品牌首字母
        if (searchMap.get("letter") != null && !"".equals(searchMap.get("letter"))) {
            criteria.andEqualTo("letter", searchMap.get("letter"));
        }
        return brandMapper.selectByExample(example);
    }

    /**
     * 分页查询
     *
     * @param page 当前页码
     * @param size 显示条数
     * @return
     */
    @Override
    public Page<Brand> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        return (Page<Brand>) brandMapper.selectAll();
    }

    /**
     * 带条件的分页查询
     *
     * @param searchMap 条件
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = new Example(Brand.class);
        //创建条件对象
        Example.Criteria criteria = example.createCriteria();
        //品牌名称
        if (searchMap.get("name") != null && !searchMap.get("name").equals("")) {
            criteria.andLike("name", "%" + searchMap.get("name") + "%");
        }
        //品牌首字母
        if (searchMap.get("letter") != null && !"".equals(searchMap.get("letter"))) {
            criteria.andEqualTo("letter", searchMap.get("letter"));
        }
        return (Page<Brand>) brandMapper.selectByExample(example);
    }

    /**
     * 根据商品分类名称查询品牌列表
     *
     * @param categoryName
     * @return
     */
    @Override
    public List<Map> findListByCategoryName(String categoryName) {
        return brandMapper.findListByCategoryName(categoryName);
    }


}

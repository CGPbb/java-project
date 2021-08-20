package com.changgou.goods.dao;

import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


@Repository
public interface BrandMapper extends Mapper<Brand> {

    @Select("select name,image from tb_brand where id in (select brand_id FROM tb_category_brand WHERE  category_id IN (SELECT id FROM tb_category WHERE NAME=#{name}) )order by seq")
    List<Map> findListByCategoryName(@Param("name") String categoryName);

}

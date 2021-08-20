package com.changgou.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 根据 id 查询品牌资料
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") int id) {
        Brand brand = brandService.findById(id);
        if (brand == null) {
            throw new RuntimeException();
        }
        return new Result(StatusCode.OK, "查找成功", true, brand);

    }

    /***
     * 查询所有品牌
     * @return
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> brandList = brandService.findAll();
        return new Result<>(StatusCode.OK, "查询成功", true, brandList);
    }

    /**
     * 添加品牌
     *
     * @param brand
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        boolean add = brandService.add(brand);
        if (!add) {
            throw new RuntimeException();
        }
        return new Result(StatusCode.OK, "添加成功", true);
    }

    /**
     * 修改品牌信息
     *
     * @param brand
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Brand brand, @PathVariable("id") Integer id) {
        brand.setId(id);
        boolean update = brandService.update(brand);
        if (!update) {
            throw new RuntimeException();
        }
        return new Result(StatusCode.OK, "修改成功", true);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        boolean delete = brandService.delete(id);
        if (!delete) {
            throw new RuntimeException();
        }
        return new Result(StatusCode.OK, "删除成功", true);
    }

    /**
     * 带条件查询
     *
     * @param map
     * @return
     */
    @GetMapping("/search")
    public Result findList(@RequestParam Map<String, Object> map) {
        List<Brand> brandList = brandService.findList(map);
        return new Result(StatusCode.OK, "查询成功", true, brandList);
    }

    @GetMapping("/search/{page}/{size}")
    public Result findPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Brand> pageInfo = brandService.findPage(page, size);
        PageResult<Brand> pageResult = new PageResult<Brand>(pageInfo.getTotal(), pageInfo.getResult());
        return new Result(StatusCode.OK, "查询成功", true, pageResult);
    }

    @GetMapping("/searchPage/{page}/{size}")
    public Result findPage(@RequestParam Map<String, Object> searchMap, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Brand> pageInfo = brandService.findPage(searchMap, page, size);
        PageResult<Brand> pageResult = new PageResult<Brand>(pageInfo.getTotal(), pageInfo.getResult());
        return new Result(StatusCode.OK, "查询成功", true, pageResult);
    }

    @GetMapping("/category/{category}")
    public Result findByCategoryName(@PathVariable String category) {
        System.out.println(category);
        List<Map> brandList = brandService.findListByCategoryName(category);
        return new Result(StatusCode.OK, "查询成功", true, brandList);
    }
}

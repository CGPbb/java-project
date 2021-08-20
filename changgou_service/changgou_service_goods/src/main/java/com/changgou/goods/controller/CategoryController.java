package com.changgou.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Category> categoryList = categoryService.findAll();
        return new Result(StatusCode.OK, "查询成功", true, categoryList);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        return new Result(StatusCode.OK, "查询成功", true, category);
    }


    /***
     * 新增数据
     * @param category
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Category category) {
        categoryService.add(category);
        return new Result(StatusCode.OK, "添加成功", true);
    }


    /***
     * 修改数据
     * @param category
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Category category, @PathVariable Integer id) {
        category.setId(id);
        categoryService.update(category);
        return new Result(StatusCode.OK, "修改成功", true);
    }


    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return new Result(StatusCode.OK, "删除成功", true);
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Category> list = categoryService.findList(searchMap);
        return new Result(StatusCode.OK, "查询成功", true, list);
    }


    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Category> pageList = categoryService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return new Result(StatusCode.OK, "查询成功", true, pageResult);
    }


}

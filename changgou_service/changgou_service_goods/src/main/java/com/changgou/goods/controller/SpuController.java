package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    @GetMapping
    public Result findAll() {
        List<Spu> spuList = spuService.findAll();
        return new Result(StatusCode.OK, "查询成功", true, spuList);
    }

    /**
     * 添加数据
     *
     * @param goods
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Goods goods) {
        spuService.add(goods);
        return new Result(StatusCode.OK, "添加成功", true);
    }


    /**
     * 根据 id进行查找
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Result findById(@PathVariable("id") String id) {
        Goods goods = spuService.findGoodsById(id);
        return new Result(StatusCode.OK, "查询成功", true, goods);
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Goods goods, @PathVariable String id) {
        spuService.update(goods, id);
        return new Result(StatusCode.OK, "修改成功", true);
    }

    @PutMapping("/audit/{id}")
    public Result audit(@PathVariable String id) {
        spuService.audit(id);
        return new Result(StatusCode.OK, "审核通过", true);
    }

    @PutMapping("/pull/{id}")
    public Result pull(@PathVariable String id) {
        spuService.pull(id);
        return new Result(StatusCode.OK, "下架商品成功", true);
    }

    @PutMapping("/put/{id}")
    public Result put(@PathVariable String id) {
        spuService.put(id);
        return new Result(StatusCode.OK, "上架商品成功", true);
    }

    @PutMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        spuService.delete(id);
        return new Result(StatusCode.OK, "删除成功，可还原", true);
    }

    @PutMapping("/restore/{id}")
    public Result restore(@PathVariable String id) {
        spuService.restore(id);
        return new Result(StatusCode.OK, "还原成功", true);
    }

    @DeleteMapping("/realDelete/{id}")
    public Result realDelete(@PathVariable String id) {
        spuService.realDelete(id);
        return new Result(StatusCode.OK, "彻底删除成功", true);
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/findSpuById/{id}")
    public Result<Spu> findSpuById(@PathVariable String id){
        Spu spu = spuService.findById(id);
        return new Result(StatusCode.OK,"查询成功",true,spu);
    }
}

package com.changgou.system.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.system.pojo.Admin;
import com.changgou.system.service.AdminService;
import com.changgou.system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") int id) {
        Admin admin = adminService.findById(id);
        if (admin == null) {
            throw new RuntimeException();
        }
        return new Result(StatusCode.OK, "查找成功", true, admin);
    }

    @GetMapping
    public Result<List<Admin>> findAll() {
        List<Admin> adminList = adminService.findAll();
        return new Result<>(StatusCode.OK, "查询成功", true, adminList);
    }

    @PostMapping
    public Result add(@RequestBody Admin admin) {
        boolean add = adminService.add(admin);
        if (!add) {
            throw new RuntimeException();
        }
        return new Result(StatusCode.OK, "添加成功", true);
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Admin admin, @PathVariable("id") Integer id) {
        admin.setId(id);
        boolean update = adminService.update(admin);
        if (!update) {
            throw new RuntimeException();
        }
        return new Result(StatusCode.OK, "修改成功", true);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        boolean delete = adminService.delete(id);
        if (!delete) {
            throw new RuntimeException();
        }
        return new Result(StatusCode.OK, "删除成功", true);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        boolean login = adminService.login(admin);
        if (login) {
            Map<String, String> info = new HashMap<>();
            info.put("username", admin.getLoginName());
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), admin.getLoginName(), null);
            info.put("token", token);
            return new Result(StatusCode.OK, "登录成功", true, info);
        } else {
            return new Result(StatusCode.LOGINERROR, "用户名或密码错误", false);
        }
    }
}

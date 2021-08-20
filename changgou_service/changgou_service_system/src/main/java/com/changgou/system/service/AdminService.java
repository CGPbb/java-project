package com.changgou.system.service;

import com.changgou.system.pojo.Admin;

import java.util.List;

public interface AdminService {

    Admin findById(int id);

    List<Admin> findAll();

    boolean add(Admin admin);

    boolean update(Admin admin);

    boolean delete(int id);

    boolean login(Admin admin);
}

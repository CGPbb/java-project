package com.changgou.system.service.impl;

import com.changgou.system.dao.AdminMapper;
import com.changgou.system.pojo.Admin;
import com.changgou.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findById(int id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Admin> findAll() {
        return adminMapper.selectAll();
    }

    @Override
    public boolean add(Admin admin) {
        String password = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
        admin.setPassword(password);
        admin.setStatus("1");
        return adminMapper.insertSelective(admin) > 0 ? true : false;
    }

    @Override
    public boolean update(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin) > 0 ? true : false;
    }

    @Override
    public boolean delete(int id) {
        return adminMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    @Override
    public boolean login(Admin admin) {
        Admin admin1 = new Admin();
        admin1.setLoginName(admin.getLoginName());
        admin1.setStatus("1");
        Admin admin2 = adminMapper.selectOne(admin1);
        if (admin2==null){
            return false;
        }else {
            return BCrypt.checkpw(admin.getPassword(),admin2.getPassword());
        }
    }
}

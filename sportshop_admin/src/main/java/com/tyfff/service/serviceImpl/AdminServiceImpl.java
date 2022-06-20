package com.tyfff.service.serviceImpl;

import com.tyfff.dao.AdminDao;
import com.tyfff.dao.daoImpl.AdminDaoImpl;
import com.tyfff.entity.Admin;
import com.tyfff.service.AdminService;

public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin getAdmin(String username, String userpwd) {
        return adminDao.selectByUsernameAndPwd(username,userpwd);
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        return adminDao.updateById(admin);
    }
}

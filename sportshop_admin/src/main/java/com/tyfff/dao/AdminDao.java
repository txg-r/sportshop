package com.tyfff.dao;

import com.tyfff.entity.Admin;

public interface AdminDao {
    Admin selectByUsernameAndPwd(String username, String userpwd);

    boolean updateById(Admin admin);
}

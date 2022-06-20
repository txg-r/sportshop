package com.tyfff.service;

import com.tyfff.entity.Admin;

public interface AdminService {
    Admin getAdmin(String username, String userpwd);

    boolean updateAdmin(Admin admin);
}

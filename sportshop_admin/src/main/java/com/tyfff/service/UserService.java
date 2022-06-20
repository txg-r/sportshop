package com.tyfff.service;

import com.tyfff.entity.User;
import com.tyfff.util.PageBean;

public interface UserService {


    PageBean<User> getPage(String word, Integer pageNo, Integer pageSize);
}

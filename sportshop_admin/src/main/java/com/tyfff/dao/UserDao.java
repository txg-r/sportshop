package com.tyfff.dao;

import com.tyfff.entity.User;
import com.tyfff.util.PageBean;

import java.util.List;

public interface UserDao {

    Integer countByWord(String word);

    List<User> listByPage(String word, Integer pageNo, Integer pageSize);
}

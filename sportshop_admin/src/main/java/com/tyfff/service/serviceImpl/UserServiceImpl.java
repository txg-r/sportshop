package com.tyfff.service.serviceImpl;

import com.tyfff.dao.UserDao;
import com.tyfff.dao.daoImpl.UserDaoImpl;
import com.tyfff.entity.User;
import com.tyfff.service.UserService;
import com.tyfff.util.PageBean;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public PageBean<User> getPage(String word, Integer pageNo, Integer pageSize) {
        PageBean<User> userPageBean = new PageBean<>();
        userPageBean.setPageNo(pageNo);
        userPageBean.setPageSize(pageSize);
        userPageBean.setTotalCount(userDao.countByWord(word));
        userPageBean.setData(userDao.listByPage(word, pageNo, pageSize));
        return userPageBean;
    }
}

package com.tyfff.service.serviceImpl;

import com.tyfff.dao.CatelogDao;
import com.tyfff.dao.daoImpl.CatelogDaoImpl;
import com.tyfff.entity.Catelog;
import com.tyfff.service.CatelogService;

import java.util.List;

public class CatelogServiceImpl implements CatelogService {
    private final CatelogDao catelogDao = new CatelogDaoImpl();
    @Override
    public List<Catelog> listAll() {
        return catelogDao.listAll();
    }

    @Override
    public boolean save(String catelog_name, String catelog_miaoshu) {
        return catelogDao.save(catelog_name,catelog_miaoshu);

    }
}

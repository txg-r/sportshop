package com.tyfff.dao;

import com.tyfff.entity.Catelog;

import java.util.List;

public interface CatelogDao {
    List<Catelog> listAll();

    boolean save(String catelog_name, String catelog_miaoshu);

    Catelog getById(int catelog_id);
}

package com.tyfff.service;

import com.tyfff.entity.Catelog;

import java.util.List;

public interface CatelogService {
    List<Catelog> listAll();

    boolean save(String catelog_name, String catelog_miaoshu);
}

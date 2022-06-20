package com.tyfff.service;

import com.tyfff.entity.Catelog;
import com.tyfff.entity.Goods;
import com.tyfff.util.PageBean;

import java.util.List;

public interface GoodsService {
    PageBean<Goods> getPage(String word, Integer pageNo, Integer pageSize);

    Goods getGoodsById(Integer goods_id);

    List<Catelog> listCatelogAll();

    boolean updateByGoods(Goods goods);

    boolean insertGoods(Goods goods);
}

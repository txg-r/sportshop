package com.tyfff.dao;

import com.tyfff.entity.Goods;

import java.util.List;

public interface GoodsDao {
    Integer countByWord(String word);

    List<Goods> listByPage(String word, Integer pageNo, Integer pageSize);

    Goods getGoodsById(Integer goods_id);

    boolean updateByGoods(Goods goods);

    boolean insertGoods(Goods goods);
}

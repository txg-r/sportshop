package pers.tyfff.sportshop.dao;

import pers.tyfff.sportshop.entity.Goods;

import java.util.List;

public interface GoodsDao {
    public List<Goods> findGoodsOrderByEnterDate(int limit);
}

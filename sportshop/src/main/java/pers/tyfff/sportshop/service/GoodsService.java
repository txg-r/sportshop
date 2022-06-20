package pers.tyfff.sportshop.service;

import pers.tyfff.sportshop.entity.Goods;

import java.util.List;

public interface GoodsService {
    public List<Goods> findNewGoods(int limit);
}

package pers.tyfff.sportshop.service.impl;

import pers.tyfff.sportshop.dao.GoodsDao;
import pers.tyfff.sportshop.dao.impl.GoodsDaoImpl;
import pers.tyfff.sportshop.entity.Goods;
import pers.tyfff.sportshop.service.GoodsService;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private final GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public List<Goods> findNewGoods(int limit) {
        return goodsDao.findGoodsOrderByEnterDate(limit);
    }
}

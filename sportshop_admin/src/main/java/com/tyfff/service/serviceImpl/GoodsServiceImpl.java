package com.tyfff.service.serviceImpl;

import com.tyfff.dao.CatelogDao;
import com.tyfff.dao.GoodsDao;
import com.tyfff.dao.daoImpl.CatelogDaoImpl;
import com.tyfff.dao.daoImpl.GoodsDaoImpl;
import com.tyfff.entity.Catelog;
import com.tyfff.entity.Goods;
import com.tyfff.service.GoodsService;
import com.tyfff.util.PageBean;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private final GoodsDao goodsDao = new GoodsDaoImpl();
    private final CatelogDao catelogDao = new CatelogDaoImpl();
    @Override
    public PageBean<Goods> getPage(String word, Integer pageNo, Integer pageSize) {
        PageBean<Goods> userPageBean = new PageBean<>();
        userPageBean.setPageNo(pageNo);
        userPageBean.setPageSize(pageSize);
        userPageBean.setTotalCount(goodsDao.countByWord(word));
        List<Goods> goodsList = goodsDao.listByPage(word, pageNo, pageSize);
        goodsList.forEach(goods -> {
            int catelog_id = goods.getCatelog().getCatelog_id();
            catelogDao.getById(catelog_id);
            goods.setCatelog(catelogDao.getById(catelog_id));
        });
        userPageBean.setData(goodsList);
        return userPageBean;
    }

    @Override
    public Goods getGoodsById(Integer goods_id) {
        return goodsDao.getGoodsById(goods_id);
    }

    @Override
    public List<Catelog> listCatelogAll() {
        return catelogDao.listAll();
    }

    @Override
    public boolean updateByGoods(Goods goods) {
        return goodsDao.updateByGoods(goods);
    }

    @Override
    public boolean insertGoods(Goods goods) {
        return goodsDao.insertGoods(goods);
    }
}

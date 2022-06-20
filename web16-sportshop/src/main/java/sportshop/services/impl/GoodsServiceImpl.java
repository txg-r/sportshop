package sportshop.services.impl;

import sportshop.dao.GoodsDao;
import sportshop.dao.impl.GoodsDaoImpl;
import sportshop.pojo.Goods;
import sportshop.services.GoodsService;
import sportshop.util.PageBean;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    //定义dao实例
   private final GoodsDao goodsDao=new GoodsDaoImpl();
    @Override
    public List<Goods> getTopFiveList() {
        return goodsDao.selectTopFive();
    }

    @Override
    public PageBean<Goods> getByPage(String word, Integer pageNo,Integer pageSize) {
        PageBean<Goods> pageBean = new PageBean<>();
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        Integer integer = goodsDao.queryCount(word);
        pageBean.setTotalCount(integer);
        List<Goods> goods = goodsDao.queryByList(word,pageNo, pageSize);
        pageBean.setData(goods);
        return pageBean;
    }

    @Override
    public Goods getGoodsDetail(Integer id) {
        return goodsDao.queryById(id);
    }
}

package sportshop.services;

import sportshop.pojo.Goods;
import sportshop.util.PageBean;

import java.util.List;

/**
 * 商品的业务接口
 */
public interface GoodsService {
    //获取前5个热门商品(新品)
    List<Goods>  getTopFiveList();
    PageBean<Goods> getByPage(String word, Integer pageNo, Integer pageSize);

    Goods getGoodsDetail(Integer id);
}

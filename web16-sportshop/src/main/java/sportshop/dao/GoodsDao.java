package sportshop.dao;

import sportshop.pojo.Goods;
import sportshop.util.PageBean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GoodsDao {
     List<Goods>  selectTopFive();
     Integer queryCount(String word);
     List<Goods> queryByList(String word,Integer pageNo,Integer pageSize);

     Goods queryById(Integer id);

    void updateGoodsStockNumFromList(Connection connection, List<Goods> goodsList) throws SQLException;
}

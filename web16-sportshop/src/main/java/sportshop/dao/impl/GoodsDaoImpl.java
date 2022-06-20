package sportshop.dao.impl;

import sportshop.dao.GoodsDao;
import sportshop.pojo.Catelog;
import sportshop.pojo.Goods;
import sportshop.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public List<Goods> selectTopFive() {
        String sql = "select goods_id,goods_name,goods_pic,mall_price " +
                " from  t_goods ORDER BY enter_date  desc  LIMIT 5";
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql);
        List<Goods> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Goods gd = new Goods();
            int id = Integer.parseInt(map.get("goods_id").toString());

            gd.setGoods_id(id);
            gd.setGoods_name(map.get("goods_name").toString());
            gd.setGoods_pic(map.get("goods_pic").toString());
            gd.setMall_price(Integer.parseInt(map.get("mall_price").toString()));
            list.add(gd);
        }

        return list;
    }

    @Override
    public Integer queryCount(String word) {
        String sql = "select count(*) cn from t_goods";
        if (null != word && !"".equals(word)) {
            sql += " where goods_name like '%" + word + "%'";

        }
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(sql);
        Map<String, Object> map = maps.get(0);
        return Integer.valueOf(map.get("cn").toString());
    }

    @Override
    public List<Goods> queryByList(String word, Integer pageNo, Integer pageSize) {
        String sql = "select goods_id,goods_name,goods_pic,market_price from  t_goods ";
        StringBuilder stringBuffer = new StringBuilder(sql);
        if (null != word && !"".equals(word)) {
            stringBuffer.append(" where goods_name like '%").append(word).append("%'");

        }
        stringBuffer.append(" limit ?,?");
        List<Map<String, Object>> maps = JdbcUtil.executeQuery(stringBuffer.toString(), pageNo - 1, pageSize);
        List<Goods> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Goods gd = new Goods();
            int id = Integer.parseInt(map.get("goods_id").toString());
            gd.setGoods_id(id);
            gd.setGoods_name(map.get("goods_name").toString());
            gd.setGoods_pic(map.get("goods_pic").toString());
            gd.setMarket_price(Integer.parseInt(map.get("market_price").toString()));
            list.add(gd);
        }
        return list;
    }

    @Override
    public Goods queryById(Integer id) {
        List<Map<String, Object>> maps = JdbcUtil.executeQuery("select * from t_goods where goods_id=?", id);
        if (maps.isEmpty()) {
            return null;
        } else {
            Map<String, Object> stringObjectMap = maps.get(0);
            return new Goods(
                    (Integer) stringObjectMap.get("goods_id"),
                    (String) stringObjectMap.get("goods_name"),
                    (String) stringObjectMap.get("goods_miaoshu"),
                    (String) stringObjectMap.get("goods_pic"),
                    (Integer) stringObjectMap.get("market_price"),
                    (Integer) stringObjectMap.get("mall_price"),
                    new Catelog((Integer) stringObjectMap.get("catelog_id")),
                    (Integer) stringObjectMap.get("stock_num"),
                    (String) stringObjectMap.get("goods_address"),
                    (Date) stringObjectMap.get("enter_date")
                    );
        }
    }

    @Override
    public void updateGoodsStockNumFromList(Connection connection, List<Goods> goodsList) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update t_goods set stock_num=? where goods_id=?");
        for (Goods goods:goodsList) {
            ps.setInt(1,goods.getStock_num());
            ps.setInt(2,goods.getGoods_id());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }
}

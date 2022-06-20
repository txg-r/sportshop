package pers.tyfff.sportshop.dao.impl;

import pers.tyfff.sportshop.dao.GoodsDao;
import pers.tyfff.sportshop.entity.Goods;
import pers.tyfff.sportshop.util.JdbcUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public List<Goods> findGoodsOrderByEnterDate(int limit) {
        List<Map<String, Object>> results = JdbcUtil.executeQuery("select * from t_goods order by enter_date desc limit ?", limit);
        return results.stream().map(map->new Goods(
                (Integer) map.get("goods_id"),
                (String) map.get("goods_name"),
                (String)map.get("goods_miaoshu"),
                (String)map.get("goods_pic"),
                (Integer)map.get("market_price"),
                (Integer)map.get("mall_price"),
                (Integer)map.get("catelog_id"),
                (Integer)map.get("store_num"),
                (String) map.get("goods_address"),
                (Date) map.get("enter_date")
        )).collect(Collectors.toCollection(ArrayList::new));
    }
}

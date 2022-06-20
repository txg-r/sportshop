package com.tyfff.dao.daoImpl;

import com.tyfff.dao.GoodsDao;
import com.tyfff.entity.Catelog;
import com.tyfff.entity.Goods;
import com.tyfff.util.JdbcUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public Integer countByWord(String word) {
        String sql = "select count(*) cn from t_goods";
        if (null != word && !"".equals(word)) {
            sql += " where goods_name like '%" + word + "%'";
        }
        return Math.toIntExact((Long) JdbcUtil.executeQuery(sql).get(0).get("cn"));

    }

    @Override
    public List<Goods> listByPage(String word, Integer pageNo, Integer pageSize) {
        String sql = "select * from  t_goods ";
        StringBuilder stringBuffer = new StringBuilder(sql);
        if (null != word && !"".equals(word)) {
            stringBuffer.append(" where goods_name like '%").append(word).append("%'");

        }
        stringBuffer.append(" limit ?,?");
        return JdbcUtil.executeQuery(stringBuffer.toString(), (pageNo - 1) * pageSize, pageSize).stream().map(map -> new Goods(
                (Integer) map.get("goods_id"),
                (String) map.get("goods_name"),
                (String) map.get("goods_miaoshu"),
                (String) map.get("goods_pic"),
                (Integer) map.get("market_price"),
                (Integer) map.get("mall_price"),
                new Catelog((Integer) map.get("catelog_id")),
                (Integer) map.get("stock_num"),
                (String) map.get("goods_address"),
                (Date) map.get("enter_date")
        )).collect(Collectors.toList());
    }

    @Override
    public Goods getGoodsById(Integer goods_id) {
        List<Map<String, Object>> maps = JdbcUtil.executeQuery("select * from t_goods where goods_id=?", goods_id);
        if (maps.isEmpty()) {
            return null;
        } else {
            Map<String, Object> map = maps.get(0);
            return new Goods(
                    (Integer) map.get("goods_id"),
                    (String) map.get("goods_name"),
                    (String) map.get("goods_miaoshu"),
                    (String) map.get("goods_pic"),
                    (Integer) map.get("market_price"),
                    (Integer) map.get("mall_price"),
                    new Catelog((Integer) map.get("catelog_id")),
                    (Integer) map.get("stock_num"),
                    (String) map.get("goods_address"),
                    (Date) map.get("enter_date")
                    );
        }
    }

    @Override
    public boolean updateByGoods(Goods goods) {
        ArrayList<Object> notNullProperties = new ArrayList<>();
        StringBuilder sb = new StringBuilder("update t_goods set ");
        if (goods.getGoods_name()!=null){
            sb.append("goods_name=?,");
            notNullProperties.add(goods.getGoods_name());
        }
        if (goods.getGoods_miaoshu()!=null){
            sb.append("goods_miaoshu=?,");
            notNullProperties.add(goods.getGoods_miaoshu());
        }
        if (goods.getGoods_pic()!=null){
            sb.append("goods_pic=?,");
            notNullProperties.add(goods.getGoods_pic());
        }
        if (goods.getMarket_price()!=null){
            sb.append("market_price=?,");
            notNullProperties.add(goods.getMarket_price());
        }
        if (goods.getMall_price()!=null){
            sb.append("mall_price=?,");
            notNullProperties.add(goods.getMall_price());
        }
        if (goods.getCatelog()!=null){
            sb.append("catelog_id=?,");
            notNullProperties.add(goods.getCatelog().getCatelog_id());
        }
        if (goods.getStock_num()!=null){
            sb.append("stock_num=?,");
            notNullProperties.add(goods.getStock_num());
        }
        if (goods.getGoods_address()!=null){
            sb.append("goods_address=?,");
            notNullProperties.add(goods.getGoods_address());
        }
        if (goods.getEnter_date()!=null){
            sb.append("enter_date=?,");
            notNullProperties.add(goods.getEnter_date());
        }
        notNullProperties.add(goods.getGoods_id());
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(" where goods_id=?");
        return JdbcUtil.executeUpdate(sb.toString(),notNullProperties.toArray())!=0;
    }

    @Override
    public boolean insertGoods(Goods goods) {
        return JdbcUtil.executeUpdate("insert into t_goods(goods_name,goods_miaoshu,goods_pic,market_price,mall_price,catelog_id,stock_num,goods_address,enter_date) values(?,?,?,?,?,?,?,?,?)",
                goods.getGoods_name(),
                goods.getGoods_miaoshu(),
                goods.getGoods_pic(),
                goods.getMarket_price(),
                goods.getMall_price(),
                goods.getCatelog().getCatelog_id(),
                goods.getStock_num(),
                goods.getGoods_address(),
                goods.getEnter_date())!=0;
    }
}

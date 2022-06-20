package com.tyfff.dao.daoImpl;

import com.tyfff.dao.OrderitemDao;
import com.tyfff.entity.Goods;
import com.tyfff.entity.Order;
import com.tyfff.entity.Orderitem;
import com.tyfff.util.JdbcUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderitemDaoImpl implements OrderitemDao {
    @Override
    public List<Orderitem> getByOrderId(String order_id) {
        List<Map<String, Object>> maps = JdbcUtil.executeQuery("select * from t_orderitem where order_id=?",order_id);
        return maps.stream().map((map)->{
            Order order = new Order();
            order.setOrder_id((String) map.get("order_id"));
            Goods goods = new Goods();
            goods.setGoods_id((Integer) map.get("goods_id"));
            return new Orderitem(
                    (Integer) map.get("orderItem_id"),
                    order,
                    goods,
                    (Integer) map.get("goods_num")
            );
        }).collect(Collectors.toList());
    }
}

package sportshop.dao.impl;

import sportshop.dao.OrderItemDao;
import sportshop.pojo.Goods;
import sportshop.pojo.Order;
import sportshop.pojo.Orderitem;
import sportshop.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderItemDaoImpl implements OrderItemDao {
    @Override
    public void addOrderItemFromList(Connection connection, List<Orderitem> orderitemList) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into t_orderitem(order_id,goods_id,goods_num) values(?,?,?) ");
        for (Orderitem orderitem : orderitemList) {
            ps.setString(1, orderitem.getOrder().getOrder_id());
            ps.setInt(2,orderitem.getGoods().getGoods_id());
            ps.setInt(3,orderitem.getGoods_num());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }

    @Override
    public List<Orderitem> selectByOrderId(String order_id) {
        List<Map<String, Object>> maps = JdbcUtil.executeQuery("select * from t_orderitem where order_id=?",order_id);
        return maps.stream().map((map)->{
            Order order = new Order();
            order.setOrder_id((String) map.get("order_id"));
            Goods goods = new Goods();
            goods.setGoods_id((Integer) map.get("goods_id"));
            return new Orderitem(
                    (Integer) map.get("orderitem_id"),
                    order,
                    goods,
                    (Integer) map.get("goods_num")
            );
        }).collect(Collectors.toList());
    }
}

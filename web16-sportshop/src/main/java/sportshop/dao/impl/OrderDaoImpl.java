package sportshop.dao.impl;

import sportshop.dao.OrderDao;
import sportshop.pojo.Order;
import sportshop.pojo.User;
import sportshop.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> selectByUserId(Integer userId) {
        List<Map<String, Object>> mapList = JdbcUtil.executeQuery("select * from t_order where order_userid=?", userId);
        return mapList.stream().map((map)->{
            Order order = new Order();
            order.setOrder_id((String) map.get("order_id"));
            order.setOrder_time((Timestamp) map.get("order_time"));
            order.setOrder_zhuangtai((Integer) map.get("order_zhuangtai"));
            order.setOrder_jine((Integer) map.get("order_jine"));
            order.setOrder_address((String) map.get("order_address"));
            User user = new User();
            user.setUser_id((Integer) map.get("order_userid"));
            order.setUser(user);
            return order;
        }).collect(Collectors.toList());
    }

    @Override
    public void addOrder(Connection connection, Order order) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into t_order values(?,?,?,?,?,?,?) ");
        ps.setString(1,order.getOrder_id());
        ps.setObject(2,order.getOrder_time());
        ps.setInt(3,order.getOrder_zhuangtai());
        ps.setInt(4,order.getOrder_jine());
        ps.setString(5,order.getOrder_address());
        ps.setString(6,order.getOrder_pay());
        ps.setInt(7,order.getUser().getUser_id());
        ps.executeUpdate();
        ps.close();
    }


}

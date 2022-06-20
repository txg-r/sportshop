package sportshop.dao;

import sportshop.pojo.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    List<Order> selectByUserId(Integer userId);

    void addOrder(Connection connection, Order order) throws SQLException;
}

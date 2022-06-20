package sportshop.dao;

import sportshop.pojo.Orderitem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao {

    void addOrderItemFromList(Connection connection, List<Orderitem> orderitemList) throws SQLException;

    List<Orderitem> selectByOrderId(String order_id);
}

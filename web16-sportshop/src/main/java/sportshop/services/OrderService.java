package sportshop.services;

import sportshop.pojo.Cart.Cart;
import sportshop.pojo.Order;
import sportshop.pojo.Orderitem;
import sportshop.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    public List<Order> queryOrderByUserId(Integer userId);

    Order submitOrder(String order_pay, User user, Cart cart) throws SQLException;
    List<Orderitem> queryOrderitemByOrderId(String order_id);
}

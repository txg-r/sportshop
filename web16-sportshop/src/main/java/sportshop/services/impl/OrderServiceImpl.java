package sportshop.services.impl;

import sportshop.dao.GoodsDao;
import sportshop.dao.OrderDao;
import sportshop.dao.OrderItemDao;
import sportshop.dao.impl.GoodsDaoImpl;
import sportshop.dao.impl.OrderDaoImpl;
import sportshop.dao.impl.OrderItemDaoImpl;
import sportshop.pojo.Cart.Cart;
import sportshop.pojo.Goods;
import sportshop.pojo.Order;
import sportshop.pojo.Orderitem;
import sportshop.pojo.User;
import sportshop.services.OrderService;
import sportshop.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();
    private final OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private final GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        return orderDao.selectByUserId(userId);
    }

    @Override
    public Order submitOrder(String order_pay, User user, Cart cart) throws SQLException {
        /*准备数据*/
        String order_id = UUID.randomUUID().toString().replace("-", "");
        Order order = new Order(
                order_id,
                new Timestamp(System.currentTimeMillis()),
                0,
                cart.getTotalPrice(),
                order_pay,
                user.getUser_address(),
                user
        );

        List<Orderitem> orderitemList = cart.getCartItems().stream().map((cartItem) -> {
            Goods goods = new Goods();
            goods.setGoods_id(cartItem.getGoods_id());
            return new Orderitem(
                    null,
                    order,
                    goods,
                    cartItem.getGoods_quantity()
            );
        }).collect(Collectors.toList());

        List<Goods> goodsList = cart.getCartItems().stream().map((cartItem) -> {
            Goods goods = goodsDao.queryById(cartItem.getGoods_id());
            goods.setStock_num(goods.getStock_num() - cartItem.getGoods_quantity());
            return goods;
        }).collect(Collectors.toList());

        /*事务控制*/
        Connection connection = JdbcUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            orderDao.addOrder(connection, order);
            orderItemDao.addOrderItemFromList(connection, orderitemList);
            goodsDao.updateGoodsStockNumFromList(connection, goodsList);
            connection.commit();
            return order;
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            return null;
        }finally {
            connection.close();
        }
    }

    @Override
    public List<Orderitem> queryOrderitemByOrderId(String order_id) {
        List<Orderitem> orderitems = orderItemDao.selectByOrderId(order_id);
        for (Orderitem orderitem : orderitems) {
            Goods goods = goodsDao.queryById(orderitem.getGoods().getGoods_id());
            orderitem.setGoods(goods);
        }
        return orderitems;
    }


}

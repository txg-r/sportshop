package com.tyfff.dao;

import com.tyfff.entity.Order;

import java.util.List;

public interface OrderDao {
    Integer countByWord(String word);

    List<Order> listByPage(String word, Integer pageNo, Integer pageSize);

    boolean updateOrderStatus(String order_id);

    Order getById(String order_id);

    boolean removeById(String order_id);
}

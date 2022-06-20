package com.tyfff.service;

import com.tyfff.entity.Order;
import com.tyfff.entity.Orderitem;
import com.tyfff.util.PageBean;

import java.util.List;

public interface OrderService {
    PageBean<Order> getPage(String word, Integer pageNo, Integer pageSize);

    boolean updateOrderStatus(String order_id);

    List<Orderitem> getOrderitemByOrderId(String order_id);

    boolean removeOrderByOrderId(String order_id);
}

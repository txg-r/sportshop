package com.tyfff.dao;

import com.tyfff.entity.Orderitem;

import java.util.List;

public interface OrderitemDao {
    List<Orderitem> getByOrderId(String order_id);
}

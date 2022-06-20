package com.tyfff.service.serviceImpl;

import com.tyfff.dao.GoodsDao;
import com.tyfff.dao.OrderDao;
import com.tyfff.dao.OrderitemDao;
import com.tyfff.dao.daoImpl.GoodsDaoImpl;
import com.tyfff.dao.daoImpl.OrderDaoImpl;
import com.tyfff.dao.daoImpl.OrderitemDaoImpl;
import com.tyfff.entity.Goods;
import com.tyfff.entity.Order;
import com.tyfff.entity.Orderitem;
import com.tyfff.service.OrderService;
import com.tyfff.util.PageBean;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();
    private final OrderitemDao orderitemDao = new OrderitemDaoImpl();
    private final GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public PageBean<Order> getPage(String word, Integer pageNo, Integer pageSize) {
        PageBean<Order> userPageBean = new PageBean<>();
        userPageBean.setPageNo(pageNo);
        userPageBean.setPageSize(pageSize);
        userPageBean.setTotalCount(orderDao.countByWord(word));
        List<Order> goodsList = orderDao.listByPage(word, pageNo, pageSize);
        userPageBean.setData(goodsList);
        return userPageBean;
    }

    @Override
    public boolean updateOrderStatus(String order_id) {
        Order order = orderDao.getById(order_id);
        if (order!=null&&order.getOrder_zhuangtai()!=0){
            return false;
        }
        return orderDao.updateOrderStatus(order_id);
    }

    @Override
    public List<Orderitem> getOrderitemByOrderId(String order_id) {
        List<Orderitem> orderitemList = orderitemDao.getByOrderId(order_id);
        orderitemList.forEach(orderitem -> {
            Integer goods_id = orderitem.getGoods().getGoods_id();
            Goods goods = goodsDao.getGoodsById(goods_id);
            orderitem.setGoods(goods);
        });
        return orderitemList;
    }

    @Override
    public boolean removeOrderByOrderId(String order_id) {
        return orderDao.removeById(order_id);
    }
}

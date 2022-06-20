package com.tyfff.dao.daoImpl;

import com.tyfff.dao.OrderDao;
import com.tyfff.entity.Order;
import com.tyfff.entity.User;
import com.tyfff.util.JdbcUtil;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Integer countByWord(String word) {
        String sql = "select count(*) cn from t_order";
        if (null != word && !"".equals(word)) {
            sql += " where order_name like '%" + word + "%'";
        }
        return Math.toIntExact((Long) JdbcUtil.executeQuery(sql).get(0).get("cn"));
    }

    @Override
    public List<Order> listByPage(String word, Integer pageNo, Integer pageSize) {
        String sql = "select * from  t_order ";
        StringBuilder stringBuffer = new StringBuilder(sql);
        if (null != word && !"".equals(word)) {
            stringBuffer.append(" where order_name like '%").append(word).append("%'");
        }
        stringBuffer.append(" limit ?,?");
        return JdbcUtil.executeQuery(stringBuffer.toString(), (pageNo - 1) * pageSize, pageSize).stream().map(map -> new Order(
                (String) map.get("order_id"),
                (Timestamp) map.get("order_time"),
                (Integer) map.get("order_zhuangtai"),
                (Integer) map.get("order_jine"),
                (String) map.get("order_address"),
                (String) map.get("order_pay"),
                new User((Integer) map.get("order_userid"))
        )).collect(Collectors.toList());
    }

    @Override
    public boolean updateOrderStatus(String order_id) {
        return JdbcUtil.executeUpdate("update t_order set order_zhuangtai=1 where order_id=?",order_id)!=0;
    }

    @Override
    public Order getById(String order_id) {
        List<Map<String, Object>> maps = JdbcUtil.executeQuery("select * from t_order where order_id=?", order_id);
        if (maps.isEmpty()){
            return null;
        }else {
            Map<String, Object> map = maps.get(0);
            return new Order(
                    (String) map.get("order_id"),
                    (Timestamp) map.get("order_time"),
                    (Integer) map.get("order_zhuangtai"),
                    (Integer) map.get("order_jine"),
                    (String) map.get("order_address"),
                    (String) map.get("order_pay"),
                    new User((Integer) map.get("order_userid"))
            );
        }
    }

    @Override
    public boolean removeById(String order_id) {
        return JdbcUtil.executeUpdate("delete from t_order where order_id=?",order_id)!=0;
    }
}

package com.tyfff.dao.daoImpl;

import com.tyfff.dao.UserDao;
import com.tyfff.entity.User;
import com.tyfff.util.JdbcUtil;
import com.tyfff.util.PageBean;

import java.util.List;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {
    @Override
    public Integer countByWord(String word) {
        String sql = "select count(*) cn from t_user";
        if (null != word && !"".equals(word)) {
            sql += " where user_name like '%" + word + "%'";
        }
        return Math.toIntExact((Long) JdbcUtil.executeQuery(sql).get(0).get("cn"));
    }

    @Override
    public List<User> listByPage(String word, Integer pageNo, Integer pageSize) {
        String sql = "select * from  t_user ";
        StringBuilder stringBuffer = new StringBuilder(sql);
        if (null != word && !"".equals(word)) {
            stringBuffer.append(" where user_name like '%").append(word).append("%'");

        }
        stringBuffer.append(" limit ?,?");
        return JdbcUtil.executeQuery(stringBuffer.toString(), (pageNo - 1)*pageSize, pageSize).stream().map(map -> new User(
                (Integer) map.get("user_id"),
                (String) map.get("user_name"),
                (String) map.get("user_pwd"),
                (String) map.get("user_realname"),
                (String) map.get("user_address"),
                (String) map.get("user_sex"),
                (String) map.get("user_tel"),
                (String) map.get("user_email"),
                (String) map.get("user_qq")
        )).collect(Collectors.toList());
    }
}

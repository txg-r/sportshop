package com.tyfff.dao.daoImpl;

import com.tyfff.dao.AdminDao;
import com.tyfff.entity.Admin;
import com.tyfff.util.JdbcUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin selectByUsernameAndPwd(String username, String userpwd) {
        List<Map<String, Object>> maps = JdbcUtil.executeQuery("select * from t_admin where username=?&&userpwd=?", username, userpwd);
        if (maps.isEmpty()){
            return null;
        }else {
            Map<String, Object> map = maps.get(0);
            return new Admin(
                    (Integer) map.get("userid"),
                    (String) map.get("username"),
                    (String) map.get("userpwd")
            );
        }
    }

    @Override
    public boolean updateById(Admin admin) {
        ArrayList<Object> notNullProperties = new ArrayList<>();
        StringBuilder sb = new StringBuilder("update t_admin set ");
        if (admin.getUsername()!=null){
            sb.append("username=?,");
            notNullProperties.add(admin.getUsername());
        }
        if (admin.getUserpwd()!=null){
            sb.append("userpwd=?,");
            notNullProperties.add(admin.getUserpwd());
        }
        notNullProperties.add(admin.getUserid());
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(" where userid=?");
        return JdbcUtil.executeUpdate(sb.toString(),notNullProperties.toArray())!=0;
    }
}

package com.tyfff.dao.daoImpl;

import com.tyfff.dao.CatelogDao;
import com.tyfff.entity.Catelog;
import com.tyfff.util.JdbcUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CatelogDaoImpl implements CatelogDao {
    @Override
    public List<Catelog> listAll() {
        return JdbcUtil.executeQuery("select * from t_catelog").stream().map(map -> {
            return new Catelog(
                    (Integer) map.get("catelog_id"),
                    (String) map.get("catelog_name"),
                    (String) map.get("catelog_miaoshu")
            );
        }).collect(Collectors.toList());
    }

    @Override
    public boolean save(String catelog_name, String catelog_miaoshu) {
        return JdbcUtil.executeUpdate("insert into t_catelog(catelog_name,catelog_miaoshu) values(?,?)", catelog_name, catelog_miaoshu) != 0;

    }

    @Override
    public Catelog getById(int catelog_id) {
        List<Map<String, Object>> maps = JdbcUtil.executeQuery("select * from t_catelog where catelog_id=?", catelog_id);
        if (maps.isEmpty()){
            return new Catelog(catelog_id);
        }else {
            Map<String, Object> map = maps.get(0);
            return new Catelog(
                    (Integer) map.get("catelog_id"),
                    (String) map.get("catelog_name"),
                    (String) map.get("catelog_miaoshu")
            );
        }

    }
}

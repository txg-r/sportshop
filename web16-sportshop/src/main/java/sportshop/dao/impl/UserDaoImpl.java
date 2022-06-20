package sportshop.dao.impl;

import sportshop.dao.UserDao;
import sportshop.pojo.User;
import sportshop.util.JdbcUtil;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    @Override
    public User selectUserByNameAndPassword(String user_name, String user_pwd) {
        List<Map<String, Object>> mapList = JdbcUtil.executeQuery("select * from t_user where user_name=?&&user_pwd=?", user_name, user_pwd);
        if (mapList.isEmpty()){
            return null;
        }
        Map<String, Object> map = mapList.get(0);
        return new User(
                (Integer) map.get("user_id"),
                (String)map.get("user_name"),
                (String)map.get("user_pwd"),
                (String)map.get("user_realname"),
                (String)map.get("user_address"),
                (String)map.get("user_sex"),
                (String)map.get("user_tel"),
                (String)map.get("user_email"),
                (String) map.get("user_qq")
                );
    }

    @Override
    public boolean updateUser(User user) {
        return JdbcUtil.executeUpdate("update t_user set user_name=?,user_pwd=?,user_realname=?,user_address=?,user_sex=?,user_tel=?,user_email=?,user_qq=? where user_id=?",
                user.getUser_name(),
                user.getUser_pwd(),
                user.getUser_realname(),
                user.getUser_address(),
                user.getUser_sex(),
                user.getUser_tel(),
                user.getUser_email(),
                user.getUser_qq(),
                user.getUser_id())!=0;
    }

    @Override
    public boolean insertUser(User user) {
        return JdbcUtil.executeUpdate("insert t_user(user_name,user_pwd,user_realname,user_address,user_sex,user_tel,user_email,user_qq) values(?,?,?,?,?,?,?,?)",
                user.getUser_name(),
                user.getUser_pwd(),
                user.getUser_realname(),
                user.getUser_address(),
                user.getUser_sex(),
                user.getUser_tel(),
                user.getUser_email(),
                user.getUser_qq())!=0;
    }
}

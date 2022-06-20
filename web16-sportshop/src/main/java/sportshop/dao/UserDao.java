package sportshop.dao;

import sportshop.pojo.User;

public interface UserDao {
    public User selectUserByNameAndPassword(String user_name,String user_pwd);

    public boolean updateUser(User user);

    boolean insertUser(User user);
}

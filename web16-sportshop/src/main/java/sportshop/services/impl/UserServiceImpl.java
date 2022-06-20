package sportshop.services.impl;

import sportshop.dao.UserDao;
import sportshop.dao.impl.UserDaoImpl;
import sportshop.pojo.User;
import sportshop.services.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();



    @Override
    public User login(String user_name,String user_pwd) {
        return userDao.selectUserByNameAndPassword(user_name, user_pwd);
    }

    @Override
    public boolean register(User user) {
        return userDao.insertUser(user);
    }


    @Override
    public boolean updateUserInfo(User user) {
        return userDao.updateUser(user);

    }
}

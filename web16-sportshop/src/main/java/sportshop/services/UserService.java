package sportshop.services;

import sportshop.pojo.User;

public interface UserService {
    public boolean updateUserInfo(User user);

    public User login(String user_name, String user_pwd);

    public boolean register(User user);
}

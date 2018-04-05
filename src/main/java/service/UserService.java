package service;

import entity.User;

import java.util.List;

/**
 * @Author: Yupi Li
 * @Date: Created in 22:48 2018/4/2
 * @Description:
 * @Modified By:
 */
public interface UserService {
    void insertUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

    User getUserById(int id);

    User getUserByIdAndPassword(int id, String password);

    long getUserRegisterCount();

    List<User> getUserRegisterInfoByPage(int page, int limit);
}

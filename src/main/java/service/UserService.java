package service;

import entity.User;

import java.util.List;

public interface UserService {
    void insertUser(User user);

    void deleteUser(int userId);

    void updateUser(User user);

    User getUserById(int userId);

    User getUserByIdAndPassword(int userId, String password);

    long getUserRegisterCount(int userStatus);

    long getUserRegisterCountAll();

    List<User> getUserRegisterInfoByPage(int page, int limit, int userStatus);

    List<User> getUserRegisterInfoByPageAll(int page, int limit);
}

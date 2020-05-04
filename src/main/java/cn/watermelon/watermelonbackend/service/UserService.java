package cn.watermelon.watermelonbackend.service;

import cn.watermelon.watermelonbackend.entity.User;

import java.util.List;

public interface UserService {

    User createUser(String username, String password);

    User login(String username, String password);

    User getUserById(int userId);

    List<User> getUsers();

    void deleteUser(int userId);

    boolean updateUsername(int userId, String username);

    void updatePassword(int userId, String password);

    void updateEmail(int userId, String email);

    void updateNick(int userId, String nick);

    void updatePrivilege(int userId, Boolean privilege);

}

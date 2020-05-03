package cn.watermelon.watermelonbackend.service.impl;

import cn.watermelon.watermelonbackend.entity.User;
import cn.watermelon.watermelonbackend.mapper.UserMapper;
import cn.watermelon.watermelonbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User createUser(String username, String password) {
        User user = null;
        if (userMapper.checkUsername(username) == 0) {
            userMapper.createUser(username, password);
            user = userMapper.getUserByUsernameAndPwd(username, password);
        }
        return user;
    }

    @Override
    public User login(String username, String password) {
        return userMapper.loginByUsername(username, password);
    }

    @Override
    public User getUserById(int userId) {
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public boolean updateUsername(int userId, String username) {
        if (userMapper.checkUsername(username) != 0) return false;
        userMapper.updateUsername(userId, username);
        return true;
    }

    @Override
    public void updatePassword(int userId, String password) {
        userMapper.updatePassword(userId, password);
    }

    @Override
    public void updateEmail(int userId, String email) {
        userMapper.updateEmail(userId, email);
    }

    @Override
    public void updateNick(int userId, String nick) {
        userMapper.updateNick(userId, nick);
    }

    @Override
    public void updatePrivilege(int userId, Boolean privilege) {
        userMapper.updatePrivilege(userId, privilege);
    }
}

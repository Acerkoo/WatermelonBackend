package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.entity.User;
import cn.watermelon.watermelonbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    User getUserById(int userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    User registerUser(String username, String password, String nick, String email) {
        User user = userService.createUser(username, password);
        if (user != null) {
            user.setNick(nick);
            user.setEmail(email);
            int userId = user.getUserId();
            userService.updateNick(userId, nick);
            userService.updateEmail(userId, email);
        }
        return user;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    User login(String username, String password) {
        User user = userService.login(username, password);
        return user;
    }

    @RequestMapping(value = "/username", method = RequestMethod.PUT)
    Boolean updateUsername(int userId, String username) {
        return userService.updateUsername(userId, username);
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    void updatePassword(int userId, String password) {
        userService.updatePassword(userId, password);
    }

    @RequestMapping(value = "/email", method = RequestMethod.PUT)
    void updateEmail(int userId, String email) {
        userService.updateEmail(userId, email);
    }

    @RequestMapping(value = "/nick", method = RequestMethod.PUT)
    void updateNick(int userId, String nick) {
        userService.updateNick(userId, nick);
    }

    @RequestMapping(value = "/privilege", method = RequestMethod.PUT)
    void updatePrivilege(int userId, boolean privilege) {
        userService.updatePrivilege(userId, privilege);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    void deleteUser(int userId) {
        userService.deleteUser(userId);
    }

}

package cn.watermelon.watermelonbackend.entity;

import lombok.Data;

@Data
public class User {

    int userId;

    String username;

    String password;

    String email;

    String nick;

    Boolean privilege;

}

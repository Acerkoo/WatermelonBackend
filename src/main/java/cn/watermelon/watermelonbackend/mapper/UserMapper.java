package cn.watermelon.watermelonbackend.mapper;

import cn.watermelon.watermelonbackend.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select({"SELECT COUNT(*)",
            "FROM `user`",
            "WHERE `is_delete` = false AND `username` = #{username}",
    })
    int checkUsername(String username);

    @Select({"SELECT *",
            "FROM `user`",
            "WHERE `is_delete` = false AND `username` = #{username} AND `password` = #{password}",
    })
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "nick", column = "nick"),
            @Result(property = "privilege", column = "privilege"),
    })
    User loginByUsername(String username, String password);

    @Insert({"INSERT INTO `user`",
            "(`username`, `password`)",
            "VALUES",
            "(#{username}, #{password})"
    })
    boolean createUser(String username, String password);

    @Select({"SELECT *",
            "FROM `user`",
            "WHERE `is_delete` = false AND `username` = #{username} AND `password` = #{password}",
    })
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "nick", column = "nick"),
            @Result(property = "privilege", column = "privilege"),
    })
    User getUserByUsernameAndPwd(String username, String password);


    @Select({"SELECT *",
            "FROM `user`",
            "WHERE `is_delete` = false AND `user_id` = #{userId}",
    })
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "nick", column = "nick"),
            @Result(property = "privilege", column = "privilege"),
    })
    User getUserByUserId(int userId);

    @Select({"SELECT *",
            "FROM `user`",
            "WHERE `is_delete` = false",
    })
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "nick", column = "nick"),
            @Result(property = "privilege", column = "privilege"),
    })
    List<User> getUsers();

    @Update({"UPDATE `user`",
            "SET `is_delete` = true",
            "WHERE `user_id` = #{userId}",
    })
    void deleteUser(int userId);

    @Update({"UPDATE `user`",
            "SET `username` = #{username}",
            "WHERE `user_id` = #{userId} AND `is_delete` = false",
    })
    boolean updateUsername(int userId, String username);

    @Update({"UPDATE `user`",
            "SET `password` = #{password}",
            "WHERE `user_id` = #{userId} AND `is_delete` = false",
    })
    boolean updatePassword(int userId, String password);

    @Update({"UPDATE `user`",
            "SET `email` = #{email}",
            "WHERE `user_id` = #{userId} AND `is_delete` = false",
    })
    boolean updateEmail(int userId, String email);

    @Update({"UPDATE `user`",
            "SET `nick` = #{nick}",
            "WHERE `user_id` = #{userId} AND `is_delete` = false",
    })
    boolean updateNick(int userId, String nick);

    @Update({"UPDATE `user`",
            "SET `privilege` = #{privilege}",
            "WHERE `user_id` = #{userId} AND `is_delete` = false",
    })
    boolean updatePrivilege(int userId, Boolean privilege);

}

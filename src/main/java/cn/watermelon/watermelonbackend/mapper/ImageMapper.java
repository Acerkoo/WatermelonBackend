package cn.watermelon.watermelonbackend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface ImageMapper {

    @Insert({"INSERT INTO `user_with_image`",
            "(`user_id`, `image_type`, `file_name`, `create_time`)",
            "VALUES",
            "(#{userId}, #{imageType}, #{fileName}, #{date})",
    })
    void insertImage(int userId, int imageType, String fileName, Date date);

    @Select({"SELECT `file_name`",
            "FROM `user_with_image`",
            "WHERE `user_id` = #{userId} AND `image_type` = #{imageType}",
            "ORDER BY `create_time` DESC",
            "LIMIT 0, 1",
    })
    String getUserImage(int userId, int imageType);

}

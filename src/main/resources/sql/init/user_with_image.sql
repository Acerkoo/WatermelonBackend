CREATE TABLE `user_with_image`
(
    `user_id` INT,
    `image_type` int,
    `file_name` TEXT,
    `create_time` TIMESTAMP
)
    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;

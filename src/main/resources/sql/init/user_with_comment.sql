CREATE TABLE `user_with_comment`
(
    `user_id` INT,
    `comment_id` VARCHAR(255),
    `opt_time` TIMESTAMP,
    PRIMARY KEY(`comment_id`, `user_id`)
)
    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;

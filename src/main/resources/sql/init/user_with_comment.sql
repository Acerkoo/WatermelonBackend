CREATE TABLE `user_with_admire`
(
    `user_id` INT,
    `comment_id` VARCHAR(255),
    `opt_time` TIMESTAMP,
    PRIMARY KEY("contest_id", "tag")
)
    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;

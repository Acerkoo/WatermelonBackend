CREATE TABLE IF NOT EXISTS `comments`
(
    `comment_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `username` VARCHAR(255),
    `title` VARCHAR(255),
    `content` TEXT,
    `follow_id` int,
    `create_time` TIMESTAMP NOT NULL,
    `is_delete` BIT DEFAULT FALSE

)

    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;

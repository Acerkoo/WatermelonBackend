CREATE TABLE IF NOT EXISTS `user`
(
    `user_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(255) UNICODE,
    `password` VARCHAR(255),
    `email` VARCHAR(255),
    `nick` VARCHAR(255),
    `privilege` BIT DEFAULT FALSE,
    `is_delete` BIT DEFAULT FALSE
)

    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;

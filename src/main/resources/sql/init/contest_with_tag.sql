CREATE TABLE `contest_with_tag`
(
    `contest_id` INT,
    `tag` VARCHAR(255),
    `num` INT,
    PRIMARY KEY(`contest_id`, `tag`)
)
    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;

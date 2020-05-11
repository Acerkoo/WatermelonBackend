CREATE TABLE `problem_with_tag`
(
    `problem_id` INT,
    `tag` VARCHAR(255),
    `num` INT,
    PRIMARY KEY("problem_id", "tag")
)
    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;

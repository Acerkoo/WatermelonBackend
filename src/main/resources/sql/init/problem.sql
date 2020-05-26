CREATE TABLE IF NOT EXISTS `problem`
(
    `problem_id` INT PRIMARY KEY,
    `title` TEXT,
    `description` TEXT,
    `input` TEXT,
    `output` TEXT,
	`sample_input` VARCHAR(255) ,
	`sample_output` VARCHAR(255) ,
    `hint` VARCHAR(255) ,
    `spj` BIT ,
    `contest_id` VARCHAR(255) ,
    `visible` BIT,
    `tm_limit` VARCHAR(255) ,
    `mem_limit` VARCHAR(255)
);


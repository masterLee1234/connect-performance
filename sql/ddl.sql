CREATE TABLE performance(
    `id` varchar(200) NOT NULL,
    `school` varchar(100) NOT NULL,
    `grade` int NOT NULL,
    `cls` int NOT NULL,
    `subject` varchar(100) NOT NULL,
    `created` DATETIME NOT NULL,
    `updated` DATETIME NOT NULL,
    `due` DATETIME NOT NULL,
    `title` varchar(200) NOT NULL,
    `description` TEXT  NOT NULL,
    PRIMARY KEY(`id`)
);
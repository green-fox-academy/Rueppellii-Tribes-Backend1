CREATE TABLE `application_user` (
  `id`         bigint(20) NOT NULL AUTO_INCREMENT,
  `password`   varchar(255)        DEFAULT NULL,
  `user_email` varchar(255)        DEFAULT NULL,
  `username`   varchar(255)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `kingdom` (
  `id`   bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `application_user_kingdom` (
  `kingdom_id`          bigint(20) DEFAULT NULL,
  `application_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`application_user_id`),
  KEY `FKlri4ciyecg9opp9r3ow0b4lp1` (`kingdom_id`),
  CONSTRAINT `FKj725o1t4n56nlb9fysdcw8r3b` FOREIGN KEY (`application_user_id`) REFERENCES `application_user` (`id`),
  CONSTRAINT `FKlri4ciyecg9opp9r3ow0b4lp1` FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

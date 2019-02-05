CREATE TABLE `application_user`
(
  `id`         bigint(20) NOT NULL AUTO_INCREMENT,
  `password`   varchar(255),
  `user_email` varchar(255) DEFAULT NULL,
  `username`   varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `kingdom`
(
  `id`   bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `application_user_kingdom`
(
  `kingdom_id`          bigint(20) DEFAULT NULL,
  `application_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`application_user_id`),
  KEY kingdom_id (`kingdom_id`),
  CONSTRAINT application_user_id FOREIGN KEY (`application_user_id`) REFERENCES `application_user` (`id`),
  CONSTRAINT kingdom_id FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`)
);
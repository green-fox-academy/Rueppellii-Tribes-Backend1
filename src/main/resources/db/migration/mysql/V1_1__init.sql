CREATE TABLE `kingdom`
(
  `id`                  bigint(20) AUTO_INCREMENT,
  `name`                varchar(255) DEFAULT NULL,
  `application_user_id` bigint(20)   DEFAULT NULL,
  PRIMARY KEY (`id`)
/*  CONSTRAINT kingdom_application_user_id FOREIGN KEY (`application_user_id`) REFERENCES `application_user` (`id`)
*/);

CREATE TABLE `application_user`
(
  `id`         bigint(20) AUTO_INCREMENT,
  `password`   varchar(255),
  `user_email` varchar(255) DEFAULT NULL,
  `username`   varchar(255) DEFAULT NULL,
  `kingdom_id` bigint(20)   DEFAULT NULL,
  PRIMARY KEY (`id`)
/*  CONSTRAINT application_user_kingdom_id FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`)
*/);


/*CREATE TABLE `application_user_kingdom`
(
  `kingdom_id`          bigint(20) DEFAULT NULL,
  `application_user_id` bigint(20),
  PRIMARY KEY (`application_user_id`),
  CONSTRAINT application_user_kingdom_application_user_id FOREIGN KEY (`application_user_id`) REFERENCES `application_user` (`id`),
  CONSTRAINT application_user_kingdom_kingdom_id FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`)
);*/

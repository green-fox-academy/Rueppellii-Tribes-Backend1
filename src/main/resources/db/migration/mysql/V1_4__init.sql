CREATE TABLE `kingdom_resource`
(
  `id`         bigint(20) NOT NULL AUTO_INCREMENT,
  `amount`     bigint(20)  DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `kingdom_resources`
(

  `resources_id` bigint(20) DEFAULT NULL,
  `kingdom_id`   bigint(20) NOT NULL,
  PRIMARY KEY (`kingdom_id`),
  UNIQUE KEY `UK_resources_id` (`resources_id`),
  CONSTRAINT `FK_kingdom_id` FOREIGN KEY (`resources_id`) REFERENCES `kingdom` (`id`),
  CONSTRAINT `FK_kingdom_resource_id` FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom_resource` (`id`)
);
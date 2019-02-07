CREATE TABLE `kingdom_resource`
(
  `resource_type` varchar(255) NOT NULL,
  `id`            bigint(20) AUTO_INCREMENT,
  `amount`        bigint(20)  DEFAULT NULL,
  `updated_at`    datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `kingdom_resources`
(
  `kingdom_id`   bigint(20) NOT NULL,
  `resources_id` bigint(20),
  PRIMARY KEY (`resources_id`),
  CONSTRAINT `kingdom_resources_kingdom_id` FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`),
  CONSTRAINT `kingdom_resources_resources_id` FOREIGN KEY (`resources_id`) REFERENCES `kingdom_resource` (`id`)
);
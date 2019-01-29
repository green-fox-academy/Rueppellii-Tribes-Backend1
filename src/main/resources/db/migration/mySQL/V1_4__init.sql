CREATE TABLE `kingdom_resource` (
  `id`         bigint(20) NOT NULL AUTO_INCREMENT,
  `amount`     bigint(20)          DEFAULT NULL,
  `updated_at` datetime(6)         DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `kingdom_resources` (
  `resources_id` bigint(20) DEFAULT NULL,
  `kingdom_id`   bigint(20) NOT NULL,
  PRIMARY KEY (`kingdom_id`),
  UNIQUE KEY `UK_fkrih015uhtdh6kqoghpumk60` (`resources_id`),
  CONSTRAINT `FKgcnhneeg1ms2eojpn71ysw8lo` FOREIGN KEY (`resources_id`) REFERENCES `kingdom` (`id`),
  CONSTRAINT `FKjjxt7p67b7npt8xeqvo49dmpo` FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom_resource` (`id`)
);

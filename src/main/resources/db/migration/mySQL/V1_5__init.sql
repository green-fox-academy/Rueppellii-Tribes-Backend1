CREATE TABLE `upgrade` (
  `id`         bigint(20) NOT NULL AUTO_INCREMENT,
  `is_created`   boolean,
  `timestamp` timestamp(6),
  PRIMARY KEY (`id`)
);

CREATE TABLE `upgrade_kingdom` (
  `kingdom_id`          bigint(20) not null,
  `upgrade_id` bigint(20) NOT NULL,
  PRIMARY KEY (`upgrade_id`),
  KEY buildings_id (`upgrade_id`),
  CONSTRAINT kingdom_upgrade_kingdom_id FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`),
  CONSTRAINT kingdom_upgrade_ugprade_id FOREIGN KEY (`upgrade_id`) REFERENCES `upgrade` (`id`)
);
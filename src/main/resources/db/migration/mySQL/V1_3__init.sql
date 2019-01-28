CREATE TABLE `troop` (
  `id`           bigint(20) NOT NULL AUTO_INCREMENT,
  `HP`           bigint(20) DEFAULT NULL,
  `attack`       bigint(20) DEFAULT NULL,
  `defense`      bigint(20) DEFAULT NULL,
  `started_at`   bigint(20) DEFAULT NULL,
  `finished_at`  bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `kingdom_troop` (
  `troop_id`          bigint(20) NOT NULL,
  `kingdom_id` bigint(20) NOT NULL,
  PRIMARY KEY (`kingdom_id`),
  KEY troop_id (`troop_id`),
  CONSTRAINT kingdom_troop_kingdom_id FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`),
  CONSTRAINT kingdom_troop_troop_id FOREIGN KEY (`troop_id`) REFERENCES `troop` (`id`)
);
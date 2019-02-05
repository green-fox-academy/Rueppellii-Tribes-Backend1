CREATE TABLE `building`
(
  `id`          bigint(20) NOT NULL AUTO_INCREMENT,
  `level`       bigint(20),
  `HP`          bigint(20),
  `started_at`  timestamp(6),
  `finished_at` timestamp(6),
  PRIMARY KEY (`id`)
);

CREATE TABLE `kingdom_buildings`
(
  `kingdom_id`   bigint(20) not null,
  `buildings_id` bigint(20) NOT NULL,
  PRIMARY KEY (`buildings_id`),
  KEY buildings_id (`buildings_id`),
  CONSTRAINT kingdom_buildings_kingdom_id FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`),
  CONSTRAINT kingdom_buildings_buildings_id FOREIGN KEY (`buildings_id`) REFERENCES `building` (`id`)
);

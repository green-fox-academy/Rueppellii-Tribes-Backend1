CREATE TABLE `troop`
(
  `id`          bigint(20) AUTO_INCREMENT,
  `troop_type`  varchar(255)        NOT NULL,
  `level`       bigint(20) UNSIGNED NOT NULL,
  `HP`          float(20)  DEFAULT NULL,
  `attack`      bigint(20) DEFAULT NULL,
  `defense`     bigint(20) DEFAULT NULL,
  `started_at`  TIMESTAMP(6),
  `finished_at` TIMESTAMP(6),
  PRIMARY KEY (`id`)
);

CREATE TABLE `kingdom_troops`
(
  `troops_id`  bigint(20) NOT NULL,
  `kingdom_id` bigint(20),
  PRIMARY KEY (`kingdom_id`),
  CONSTRAINT kingdom_troops_kingdom_id FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`),
  CONSTRAINT kingdom_troops_troops_id FOREIGN KEY (`troops_id`) REFERENCES `troop` (`id`)
);
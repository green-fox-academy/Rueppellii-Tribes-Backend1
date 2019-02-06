
CREATE TABLE `troop`
(
    `troop_type`          varchar (255)          NOT NULL,
  `id`          bigint(20)          NOT NULL ,
  `level`       bigint(20) UNSIGNED NOT NULL,
  `HP`          bigint(20) DEFAULT NULL,
  `attack`      bigint(20) DEFAULT NULL,
  `defense`     bigint(20) DEFAULT NULL,
  `started_at`  TIMESTAMP(6),
  `finished_at` TIMESTAMP(6),
  PRIMARY KEY (`id`)
);

CREATE TABLE `kingdom_troops`
(
  `troops_id`  bigint(20) NOT NULL,
  `kingdom_id` bigint(20) NOT NULL,
  PRIMARY KEY (`kingdom_id`),
--   KEY troops_id (`troops_id`),
  CONSTRAINT kingdom_troop_kingdom_id FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`),
  CONSTRAINT kingdom_troop_troops_id FOREIGN KEY (`troops_id`) REFERENCES `troop` (`id`)
);
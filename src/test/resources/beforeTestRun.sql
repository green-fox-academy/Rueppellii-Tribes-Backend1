DELETE
FROM application_user_kingdom;

DELETE
FROM kingdom_buildings;

DELETE
FROM kingdom_troops;

DELETE
FROM kingdom_resources;

DELETE
FROM application_user;

DELETE
FROM kingdom;

DELETE
FROM troop;

DELETE
FROM building;

DELETE
FROM resource;

-- testUser1 password: pass1
-- testUser2 password: pass2
-- testUser3 password: pass3
INSERT INTO application_user(id, password, user_email, username)
VALUES (1, '$2a$10$3A7YK9hDUpHN4plBoCphYOzk426CebJwnaFMk0kN4qEXoWUTiwejC', 'test1@test.test', 'testUser1'),
       (2, '$2a$10$y1WkKt52SH8eDm6zvy63v.B0EstAaevqAgfo7plk8v9UuigsMcqxi', 'test2@test.test', 'testUser2'),
       (3, '$2a$10$N.4V.83hs.5X2bI0qY0Tme2PYceHtDf2Suzh0QHEcYVZxeS0YhJL6', 'test3@test.test', 'testUser3');

INSERT INTO kingdom(id, name)
VALUES (1, 'testKingdom1'),
       (2, 'testKingdom2'),
       (3, 'testKingdom3');

INSERT INTO troop(troop_type, id, level, HP, attack, defense, started_at, finished_at)
VALUES ('SingleTroop', 1, 1, 1, 1, 1, '2019-02-04 03:00:00', '2019-02-04 03:12:00'),
       ('SingleTroop', 2, 5, 5, 5, 5, '2019-02-05 13:00:00', '2019-02-05 15:00:00'),
       ('SingleTroop', 3, 10, 10, 10, 10, '2019-02-06 22:30:00', '2019-02-10 22:30:00');

INSERT INTO building(building_type, id, level, HP, started_at, finished_at)
VALUES ('Mine', 1, 1, 1, '2019-02-04 03:00:00', '2019-02-04 03:12:00'),
       ('Barracks', 2, 5, 5, '2019-02-05 13:00:00', '2019-02-05 15:00:00'),
       ('TownHall', 3, 10, 10, '2019-02-06 22:30:00', '2019-02-10 22:30:00');

INSERT INTO application_user_kingdom(kingdom_id, application_user_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO kingdom_buildings(kingdom_id, buildings_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO kingdom_troops(troops_id, kingdom_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO resource(resource_type, id, amount, updated_at)
VALUES ('Gold', 1, 0, '2019-02-04 08:00:00'),
       ('Food', 2, 10, '2019-02-10 16:00:00'),
       ('Gold', 3, 500, '2019-02-11 10:00:00');

INSERT INTO kingdom_resources(resources_id, kingdom_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

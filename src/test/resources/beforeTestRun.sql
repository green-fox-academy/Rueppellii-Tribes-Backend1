DELETE FROM application_user_kingdom;
DELETE FROM kingdom_buildings;
DELETE FROM kingdom_troops;
DELETE FROM application_user;
DELETE FROM kingdom;
DELETE FROM troop;
DELETE FROM building;

INSERT INTO application_user(id, password, user_email, username)
VALUES (1, 'pass1', 'test1@test.test', 'testUser1'),
       (2, 'pass2', 'test2@test.test', 'testUser2'),
       (3, 'pass3', 'test3@test.test', 'testUser3');

INSERT INTO kingdom(id, name)
VALUES (1, 'testKingdom1'),
       (2, 'testKingdom2'),
       (3, 'testKingdom3');

INSERT INTO troop(id, level, HP, attack, defense, started_at, finished_at)
VALUES (1, 1, 1, 1, 1, '2019-02-04 03:00:00', '2019-02-04 03:12:00'),
       (2, 5, 5, 5, 5, '2019-02-05 13:00:00', '2019-02-05 15:00:00'),
       (3, 10, 10, 10, 10, '2019-02-06 22:30:00', '2019-02-10 22:30:00');

INSERT INTO building(id, level, HP, started_at, finished_at)
VALUES (1, 1, 1, '2019-02-04 03:00:00', '2019-02-04 03:12:00'),
       (2, 5, 5, '2019-02-05 13:00:00', '2019-02-05 15:00:00'),
       (3, 10, 10, '2019-02-06 22:30:00', '2019-02-10 22:30:00');

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
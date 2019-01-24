/*nincs értelme a primary key-t NOT NULL-ra tenni, mert ez automatikusan megtörténik*/
CREATE TABLE application_user (
  id         INT auto_increment,
  username   VARCHAR(255),
  password   VARCHAR(255),
  user_email VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE kingdom (
  id   INT auto_increment,
  name VARCHAR(255),
  /*type VARCHAR (255) NOT NULL,*/
  PRIMARY KEY (id)
);


/*SELECT application_user.id, application_user.name,
application_user.pass, application_user.userEmail
INNER JOIN Kingdom ON application_user.id = kingdom.id;*/

INSERT INTO application_user (username, password, user_email)
VALUES ('ly', 'x', 'a@b.c');
INSERT INTO application_user (username, password, user_email)
VALUES ('kovacs', 'passw', 'v@h.hu');
INSERT INTO application_user (username, password, user_email)
VALUES ('lyo', 'x', 'javamaster@kovacs.hu');

INSERT INTO kingdom (name)
VALUES ('Cool');
INSERT INTO kingdom (name)
VALUES ('Easy');
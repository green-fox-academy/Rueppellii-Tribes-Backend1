/*nincs értelme a primary key-t NOT NULL-ra tenni, mert ez automatikusan megtörténik*/
CREATE TABLE ApplicationUser(
  ID INT AUTO_INCREMENT,
  username VARCHAR (255) NOT NULL UNIQUE,
  pass VARCHAR (255) NOT NULL,
  userEmail VARCHAR (255) NOT NULL UNIQUE,
  PRIMARY KEY(ID)
);

INSERT INTO ApplicationUser(username, pass, userEmail)
VALUES ('ly', 'x', 'a@b.c');
INSERT INTO ApplicationUser(username, pass, userEmail)
VALUES ('kovacs', 'passw', 'v@h.hu');
INSERT INTO ApplicationUser(username, pass, userEmail)
VALUES ('lyo', 'x', 'javamaster@kovacs.hu');

CREATE TABLE Kingdom(
  ID INT AUTO_INCREMENT,
  name VARCHAR (255) NOT NULL,
  type VARCHAR (255) NOT NULL,
  PRIMARY KEY (ID)
);

INSERT INTO Kingdom(name, type)
VALUES('Cool','Greek');
INSERT INTO Kingdom(name, type)
VALUES('Easy', 'Greek');
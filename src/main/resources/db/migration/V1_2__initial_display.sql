CREATE TABLE ApplicationUser(
  ID int AUTO_INCREMENT,
  username varchar(255) NOT NULL UNIQUE,
  pass varchar(255) NOT NULL,
  userEmail varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY(ID)
);

INSERT INTO ApplicationUser(username, pass, userEmail)
VALUES ('ly', 'x', 'a@b.c');
INSERT INTO ApplicationUser(username, pass, userEmail)
VALUES ('kovacs', 'passw', 'v@h.hu');
INSERT INTO ApplicationUser(username, pass, userEmail)
VALUES ('lyo', 'x', 'javamaster@kovacs.hu');

CREATE TABLE Kingdom(
  ID int AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  type varchar (255) NOT NULL,
  PRIMARY KEY (ID)
);

INSERT INTO Kingdom(name, type)
VALUES('Cool','Greek');

INSERT INTO Kingdom(name, type)
VALUES('Easy', 'Greek');
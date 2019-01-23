CREATE TABLE ApplicationUser(
  ID int NOT NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL,
  pass varchar(255) NOT NULL,
  userEmail varchar(255) NOT NULL,
  PRIMARY KEY(ID)
);

INSERT INTO ApplicationUser(username, pass, userEmail)
VALUES ('ly', 'x', 'a@b.c');
INSERT INTO ApplicationUser(username, pass, userEmail)
VALUES ('kovacs', 'passw', 'v@h.hu');
INSERT INTO ApplicationUser(username, pass, userEmail)
VALUES ('ly', 'x', 'a@b.c');

CREATE TABLE Kingdom(
  ID int NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  type varchar (255) NOT NULL,
  PRIMARY KEY (ID)
);

INSERT INTO Kingdom(name, type)
VALUES('Cool','Greek');
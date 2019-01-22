CREATE TABLE Fake (
    ID int,
    name varchar(255)
);

CREATE TABLE Persons (
    ID int,
    name varchar(255)
);

INSERT INTO Persons(ID, name)
VALUES(5,'W');

INSERT INTO Persons(ID, name)
VALUES(6,'Wa');

INSERT INTO Fake(id, name)
VALUES (1, 'Xy');
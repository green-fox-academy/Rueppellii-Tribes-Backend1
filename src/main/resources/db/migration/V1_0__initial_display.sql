CREATE TABLE application_user(
  id INT,
  username VARCHAR (255),
  password VARCHAR (255),
  user_email VARCHAR (255),
  PRIMARY KEY(id)
);

CREATE TABLE kingdom(
  id INT,
  name VARCHAR (255),
  /*type VARCHAR (255) NOT NULL,*/
  PRIMARY KEY (id)
);
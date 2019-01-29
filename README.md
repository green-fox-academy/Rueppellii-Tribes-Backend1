# Rueppellii-Tribes-Backend1

Environment variables:

DEV:

- DATASOURCE_URL: jdbc:mysql://localhost/yourTribesDataBaseName
- DATASOURCE_USERNAME: your own username in MySQL
- DATASOURCE_PASSWORD: your own password in MySQL
- HIBERNATE_DIALECT: org.hibernate.dialect.MySQL57Dialect
- DATABASE_HOST: your own localhost so far /NOT USED YET/
- DATABASE_PORT: the port you've set /NOT USED YET/
- PROFILE: dev
 _________________________________________________________________

COMMON ERRORS OCCURRING IN FLYWAY:

1)	Schema-validation: missing table [hibernate_sequence]
Reason: magic
Solution: set GeneationType = IDENTITY in all models

2)	Schema-validation: missing table [anything_else] 
Reason: the models are different from the generated SQL codes,
thus Flyway throws an error instead of generating the tables.
Sometimes the problem is not with the table specified in the
error message.
Solution:
a) Check if all your models are created, have proper fields, have the @Entity annotation and joined correctly.
b) Turn Flyway off, use create-drop instead of validate, run the app, and let Hibernate generate the SQL code
c) Change back to validate. Insert the code into the SQL table from MySQL Workbench or from IntelliJ. 
Check if it's working. If not, repeat the whole process from point a).
d) Make the SQL code cleaner by removing the unnecessary parts and renaming the
randomly generated UQ or FQ names.
e) check if your code works after 

3)	Migration checksum mismatch for migration version: X.Y
Reason: flyway_schema_history logs different checksum from the actual checksum in the correct version.
It means You might have made some changes in your X.Y. version
Solution: drop database; create database;

4)	Detected failed migration to version: X.Y
Reason: something is amiss in your database.
Solution: drop database; create database;

5)	@OneToOne or @ManyToOne on com.example.demo.x.y references an unknown entity: com.example.demo.Dog
Reason: it's not a Flyway error :-). Your @Entity annotation is missing.
Solution: Insert @Entity.

6) If some SQL lines are mentioned in the error message, the problem is usually with the SQL Syntax itself or your model structure.
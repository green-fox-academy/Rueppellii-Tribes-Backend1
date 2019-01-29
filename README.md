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
<br>
Reason: magic
<br>
Solution: set GeneationType = IDENTITY in all models

2)	Schema-validation: missing table [anything_else] 
<br>
Reason: the models are different from the generated SQL codes,
thus Flyway throws an error instead of generating the tables.
Sometimes the problem is not with the table specified in the
error message.
<br>
Solution:
<br>
a) Check if all your models are created, have proper fields, have the @Entity annotation and joined correctly.
<br>
b) Turn Flyway off (build.gradle dependencies + application.properties), use create-drop instead of validate, run the app, and let Hibernate generate the SQL code
<br>
c) Change back to validate. Insert the code into the SQL table from MySQL Workbench or from IntelliJ. 
Check if it's working. If not, repeat the whole process from point a).
<br>
d) Make the SQL code cleaner by removing the unnecessary parts and renaming the
randomly generated UQ or FQ names.
<br>
e) check if your code works after 

3)	Migration checksum mismatch for migration version: X.Y
<br>
Reason: flyway_schema_history logs different checksum from the actual checksum in the correct version.
It means You might have made some changes in your X.Y. version
<br>
Solution: drop database; create database;

4)	Detected failed migration to version: X.Y
<br>
Reason: something is amiss in your database.
<br>
Solution: drop database; create database;

5)	@OneToOne or @ManyToOne on com.example.demo.x.y references an unknown entity: com.example.demo.x
<br>
Reason: it's not a Flyway error :-). Your @Entity annotation is missing.
<br>
Solution: Insert @Entity.

6) If some SQL lines are mentioned in the error message, the problem is usually with the SQL Syntax itself or your model structure.
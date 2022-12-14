# greeting

Greeting Client is for REACT app
Greenting is for JAVA API
On SQL workbench create a database 
 CREATE DATABASE greetings;
   USE greetings;
 CREATE TABLE greeting(
      id INT NOT NULL PRIMARY KEY,
      created_by varchar(255),
      date_created datetime(6),
      greeting varchar(255) ,
      origin_country varchar(255));
 INSERT INTO greeting VALUES (1,"Prapti", "2022-11-30 09:01:16.178000", "Hello","US");
 INSERT INTO greeting VALUES (2,"Tina", "2022-11-30 09:01:16.178000", "Bonjour","UK");
 SELECT * FROM greeting;  

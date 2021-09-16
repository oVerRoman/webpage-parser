CREATE DATABASE  statistician_db;
USE statistician_db;

CREATE TABLE words (
  id int NOT NULL AUTO_INCREMENT,
  word varchar(40),
  value int,
  PRIMARY KEY (id)
);
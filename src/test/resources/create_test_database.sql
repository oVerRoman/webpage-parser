CREATE DATABASE  test_statistician_db;
USE test_statistician_db;

CREATE TABLE words (
  id int NOT NULL AUTO_INCREMENT,
  word varchar(40),
  value int,
  PRIMARY KEY (id)
);
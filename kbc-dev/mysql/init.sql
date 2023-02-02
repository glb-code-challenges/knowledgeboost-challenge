CREATE DATABASE db_security;
USE db_security;

CREATE TABLE IF NOT EXISTS access(
userid INT AUTO_INCREMENT PRIMARY KEY,
fullname VARCHAR(255) NOT NULL,
username VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL
);

INSERT INTO access(fullname, username, password)
VALUES('Raul Vargas', 'rvargasm','123456');
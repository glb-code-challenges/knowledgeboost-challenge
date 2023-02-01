DROP TABLE weather IF EXISTS;

create table weather(
    id int auto_increment,
    realTime date,
    responseCode varchar(255) NOT NULL,
    name varchar(255)

);
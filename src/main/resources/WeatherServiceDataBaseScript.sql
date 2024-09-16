CREATE DATABASE WeatherServiceDataBase;

USE WeatherServiceDataBase;

CREATE TABLE weather (
    id Int NOT NULL AUTO_INCREMENT,
    created_at TIMESTAMP NOT NULL,
    response_code VARCHAR(255) NOT NULL,
    city_name VARCHAR(255),
    coords VARCHAR(255) NOT NULL,
    root_cause VARCHAR(255),
    PRIMARY KEY (id)
);

drop table weather;
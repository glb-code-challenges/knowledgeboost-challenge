CREATE TABLE executions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  datetime TIMESTAMP,
  response_code INTEGER,
  root_cause VARCHAR(255),
  city_name VARCHAR(255)
);
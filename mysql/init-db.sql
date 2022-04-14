USE customerdb;

DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  id bigint NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

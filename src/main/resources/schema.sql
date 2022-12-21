CREATE DATABASE IF NOT EXISTS searchframe;

USE searchframe;

DROP TABLE IF EXISTS vehicle_line;

CREATE TABLE vehicle_line (
  id bigint unsigned auto_increment,
  name varchar(50) NOT NULL,
  text_description varchar(4000) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS vehicle_product;

CREATE TABLE vehicle_product (
  id bigint unsigned auto_increment,
  name varchar(100) not null,
  vehicle_line_id bigint not null,
  manufacturer varchar(50) not null,
  price decimal(13,2) not null,
  currency varchar(3),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
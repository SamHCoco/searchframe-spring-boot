CREATE DATABASE IF NOT EXISTS searchframe;

USE searchframe;

DROP TABLE IF EXISTS product_line;

CREATE TABLE product_line (
  id bigint unsigned auto_increment,
  name varchar(50) NOT NULL,
  text_description varchar(4000) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS product;

CREATE TABLE product (
  id bigint unsigned auto_increment,
  name varchar(100) not null,
  product_line_id bigint not null,
  manufacturer varchar(50) not null,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
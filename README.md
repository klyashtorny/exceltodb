# exceltodb

start with tomcat 8
script sql table create:

CREATE TABLE `filedb`.`table` (
  `segment` VARCHAR(255) NOT NULL,
  `country` VARCHAR(255) NULL,
  `product` VARCHAR(255) NOT NULL,
  `unitssold` INT,
  `saleprice` INT,
  `grosssale` INT ,
  `profit` INT);
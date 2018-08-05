# exceltodb

start with tomcat 8
MySql - 5.x
script sql table create:

CREATE TABLE `filedb`.`table` (
  `segment` VARCHAR(255) NOT NULL,
  `country` VARCHAR(255) NULL,
  `product` VARCHAR(255) NOT NULL,
  `unitssold` INT,
  `saleprice` INT,
  `grosssale` INT ,
  `profit` INT);
  
branch excel_rand:
Random excel file export to MySql - 8.x database with blank cells

link https://github.com/klyashtorny/exceltodb/tree/excel_rand
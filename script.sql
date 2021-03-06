create database if not exists stock2 character set utf8 collate utf8_unicode_ci;

use stock2;

CREATE TABLE IF NOT EXISTS GOODS
(
  PRODUCT_ID INT PRIMARY KEY  AUTO_INCREMENT,
  TYPE_NAME VARCHAR(30),
  NAME VARCHAR(30),
  PRICE DECIMAL(13, 2),
  BRAND VARCHAR(30),
  AMOUNT INT,
  SIZE INT NULL,
  HEEL INT NULL
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS ORDERS
(
  ORDER_ID INT,
  PRODUCT_ID INT,
  AMOUNT INT,
  CUSTOMERS_NAME VARCHAR(30),
  ADDRESS VARCHAR(60),
  ORDER_DATE DATE,
  PRIMARY KEY (ORDER_ID,PRODUCT_ID ),
  FOREIGN KEY (PRODUCT_ID) REFERENCES GOODS(PRODUCT_ID)
  ON DELETE CASCADE,
  FOREIGN KEY (CUSTOMERS_NAME) REFERENCES CUSTOMERS(CUSTOMERS_NAME)
  ON DELETE CASCADE
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS CUSTOMERS
(
  CUSTOMERS_NAME VARCHAR(30) PRIMARY KEY ,
  PHONE VARCHAR(30),
  EMAIL VARCHAR(40)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE TABLE IF NOT EXISTS SUPPLIES
(
  SUPPLIES_ID INT,
  PRODUCT_ID INT,
  AMOUNT INT,
  SUPPLIERS_NAME VARCHAR(30),
  SUPPLIER_DATE DATE,
  PRIMARY KEY (SUPPLIES_ID,PRODUCT_ID ),
  FOREIGN KEY (PRODUCT_ID) REFERENCES GOODS(PRODUCT_ID)
    ON DELETE CASCADE,
  FOREIGN KEY (SUPPLIERS_NAME) REFERENCES SUPPLIERS(SUPPLIERS_NAME)
    ON DELETE CASCADE
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS SUPPLIERS
(
  SUPPLIERS_NAME VARCHAR(30) PRIMARY KEY ,
  PHONE VARCHAR(30),
  EMAIL VARCHAR(40)
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS USERS
(
  LOGIN VARCHAR(30),
  PASSWORD VARCHAR(30),
  ADMIN BOOL,
  PRIMARY KEY ( LOGIN,PASSWORD )
);
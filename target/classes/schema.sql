DROP table if EXISTS  SKU_DETAILS;
DROP table if EXISTS PRICE_DETAILS;

CREATE TABLE `SKU_DETAILS` (
  `PRODUCT_ID` INTEGER (11) NOT NULL,
  `SKU_ID` varchar(20) NOT NULL,
  `PRODUCT_NAME` varchar(45) NOT NULL,
  `PRODUCT_CATEGORY` varchar(45) NOT NULL,
  `UPDATED_TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

  CREATE TABLE `PRICE_DETAILS` (
  `PRODUCT_ID` INTEGER(11) NOT NULL,
  `PRICE` DECIMAL(6,4) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;
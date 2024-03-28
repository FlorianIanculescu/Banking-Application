use `bank`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` INT,
  `date` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `receiving_customer_id` INT,
  `sending_customer_id` INT,
  PRIMARY KEY (`id`),
  
  KEY `FK_RECEIVING_CUSTOMER_idx` (`receiving_customer_id`),
  CONSTRAINT `FK_RECEIVING_CUSTOMER_idx` 
  FOREIGN KEY (`receiving_customer_id`) 
  REFERENCES `customer` (`id`),
  
  KEY `FK_SENDING_CUSTOMER_idx` (`sending_customer_id`),
  CONSTRAINT `FK_SENDING_CUSTOMER_idx` 
  FOREIGN KEY (`sending_customer_id`) 
  REFERENCES `customer` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;






















CREATE TABLE `client` (
  `client_id` VARCHAR(36) NOT NULL,
  `created_at_epoch` LONG NOT NULL,
  `updated_at_epoch` LONG NOT NULL,
  `version` INT(11) NOT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `facilitator` (
  `facilitator_id` VARCHAR(36) NOT NULL,
  `created_at_epoch` LONG NOT NULL,
  `updated_at_epoch` LONG NOT NULL,
  `version` INT(11) NOT NULL,
  PRIMARY KEY (`facilitator_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE `client_facilitator` (
  `client_id` VARCHAR(36) NOT NULL,
  `facilitator_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`client_id`,`facilitator_id`),
  KEY `fk_facilitator_idx` (`facilitator_id`),
  KEY `fk_client_idx` (`client_id`),
  CONSTRAINT `fk_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_facilitator` FOREIGN KEY (`facilitator_id`) REFERENCES `facilitator` (`facilitator_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `ORDER_ENTITY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(11) NULL,
  `ORDER_NAME` VARCHAR(36) NOT NULL,
  `PRODUCT_NAME` VARCHAR(36) NOT NULL,
  `CREATED_BY` VARCHAR(36) NOT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CLIENT_UID` VARCHAR(36) NOT NULL,
  `VENDOR_UID` VARCHAR(36) NULL,
  `VENDOR_USER_NAME` VARCHAR(36) NULL,
  `STATUS_CODE` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
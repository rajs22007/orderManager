CREATE TABLE IF NOT EXISTS `user_template` (
  `template_id` VARCHAR(36) NOT NULL,
  `application_name` VARCHAR(100) NOT NULL,
  `user_type` VARCHAR(100) NOT NULL,
  `data` JSON NOT NULL,
  `created_at_epoch` LONG NOT NULL,
  `updated_at_epoch` LONG NOT NULL,
  `version` INT(11) NOT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tenant_template` (
  `template_id` VARCHAR(36) NOT NULL,
  `application_name` VARCHAR(100) NOT NULL,
  `tenant_type` VARCHAR(100) NOT NULL,
  `data` JSON NOT NULL,
  `created_at_epoch` LONG NOT NULL,
  `updated_at_epoch` LONG NOT NULL,
  `version` INT(11) NOT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
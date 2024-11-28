CREATE TABLE `mydb`.`contact_msg` (
  `contact_id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `mobile_num` VARCHAR(10) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `subject` VARCHAR(100) NOT NULL,
  `message` VARCHAR(500) NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NOT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT NULL,
  `updated_by` VARCHAR(50) NULL DEFAULT NULL
) ENGINE = InnoDB;

CREATE TABLE `mydb`.`holidays` (
  `day` VARCHAR(20) NOT NULL,
  `reason` VARCHAR(100) NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NOT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT NULL,
  `updated_by` VARCHAR(50) NULL DEFAULT NULL
) ENGINE = InnoDB;

CREATE TABLE `mydb`.`roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(50) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NOT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT NULL,
  `updated_by` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS`mydb`.`address` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `address1` VARCHAR(200) NOT NULL,
  `address2` VARCHAR(200) DEFAULT NULL,
  `city` VARCHAR(50) NOT NULL,
  `state` VARCHAR(50) NOT NULL,
  `zip_code` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NOT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT NULL,
  `updated_by` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE = InnoDB;

CREATE TABLE `mydb`.`person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `mobile_number` VARCHAR(20) NOT NULL,
  `pwd` VARCHAR(200) NOT NULL,
  `role_id` INT NOT NULL,
  `address_id` INT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NOT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT NULL,
  `updated_by` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  FOREIGN KEY (`role_id`) REFERENCES `mydb`.`roles`(`role_id`),
  FOREIGN KEY (`address_id`) REFERENCES `mydb`.`address`(`address_id`)
) ENGINE = InnoDB;

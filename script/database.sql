 --- Table create
 CREATE TABLE `gym`.`admins` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NULL,
  `contact_number` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
   CREATE TABLE `gym`.`plans` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `gym`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `contact_number` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `time_slot` VARCHAR(10) NOT NULL,
  `plan_id` INT NOT NULL,
  `is_active` TINYINT(10) NOT NULL DEFAULT 1,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
 CREATE TABLE `gym`.`payments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `amount` INT NOT NULL,
  `is_received` TINYINT(10) NULL DEFAULT 0,
  `due_date` DATE NULL DEFAULT DATE_ADD(sysdate(), INTERVAL 15 DAY),
  `payment_date` DATE NULL,
  `admin_id` INT NULL,
  `description` VARCHAR(500) NOT NULL,
  `entry_date` date DEFAULT sysdate(),
  PRIMARY KEY (`id`));

--- Foreign Key Reference Creation
 ALTER TABLE `gym`.`users` 
ADD CONSTRAINT `fk_users_plan_id`
  FOREIGN KEY (`plan_id`)
  REFERENCES `gym`.`plans` (`id`);
  
ALTER TABLE `gym`.`payments` 
ADD CONSTRAINT `fk_payments_user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `gym`.`users` (`id`);
  
ALTER TABLE `gym`.`payments` 
ADD CONSTRAINT `fk_payments_admin_id`
  FOREIGN KEY (`admin_id`)
  REFERENCES `gym`.`admins` (`id`);

--- Admin insert
INSERT INTO `gym`.`admins` (`id`, `first_name`, `last_name`, `contact_number`, `email`, `password`) VALUES ('1', 'Abhi', 'Patil', '8956856554', 'abhi.patil@gym.com', MD5('admin@123'));
CREATE TABLE `gym`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(64) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `is_admin` VARCHAR(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`));

INSERT INTO `gym`.`user` (`id`, `user_name`, `first_name`, `last_name`, `email`, `password`, `is_admin`) VALUES ('1', 'admin', 'Admin', 'Last', 'admin.last@gmail.com', 'admin', 'Y');
INSERT INTO `gym`.`user` (`id`, `user_name`, `first_name`, `last_name`, `email`, `password`, `is_admin`) VALUES ('2', 'user', 'User', 'Last', 'user.last@gmail.com', 'user', 'N');

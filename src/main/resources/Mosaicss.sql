CREATE DATABASE IF NOT EXISTS Mosaicss;

CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `User` (`id`, `username`, `password`)
VALUES
	(1,'test','test');

CREATE TABLE `Collage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `src` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_user FOREIGN KEY (user_id)
  REFERENCES `User`(id)
);

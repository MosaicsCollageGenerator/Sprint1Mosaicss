 -- Drop is a dangerous command
DROP DATABASE IF EXISTS Mosaicss;

CREATE DATABASE IF NOT EXISTS Mosaicss;

USE Mosaicss;

CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `User` (`id`, `username`, `password`)
VALUES
	(1,'test','098f6bcd4621d373cade4e832627b4f6');

CREATE TABLE `Collage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `src` longblob DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_user FOREIGN KEY (user_id)
  REFERENCES `User`(id)
);
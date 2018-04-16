mysql -u root --password=root -e "DROP DATABASE IF EXISTS Mosaicss;"
mysql -u root --password=root -e "CREATE DATABASE IF NOT EXISTS Mosaicss;"
mysql -u root --password=root -e "CREATE TABLE Mosaicss.User (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(100) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);"
mysql -u root --password=root -e "CREATE TABLE Mosaicss.Collage  (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(100) DEFAULT NULL,
  src varchar(255) DEFAULT NULL,
  user_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user FOREIGN KEY (user_id)
  REFERENCES Mosaicss.User(id)
);
"

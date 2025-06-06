-- Creamos el DB
CREATE DATABASE bd2proyecto;

-- Creamos el usuario ADMIN
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';


-- Le damos permisos al admin
GRANT ALL PRIVILEGES ON bd2Proyecto.* TO 'admin'@'localhost' WITH GRANT OPTION;

GRANT CREATE USER ON *.* TO 'admin'@'localhost';

FLUSH PRIVILEGES;

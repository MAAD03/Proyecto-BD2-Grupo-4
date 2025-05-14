-- Creamos el DB
CREATE DATABASE bd2Proyecto;

-- Creamos el usuario ADMIN
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

-- Le damos permisos al admin
GRANT ALL PRIVILEGES ON bd2Proyecto.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;


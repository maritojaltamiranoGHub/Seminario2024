-- -------------------------------------------------------------------------------------------
					-- CARATULA --
-- -------------------------------------------------------------------------------------------
-- UNIVERSIDAD EMPRESARIAL SIGLO 21 

-- CARRERA DE GRADO 

-- LICENCIATURA EN INFORMATICA 

-- TRABAJO PRACTICO 2  
-- SEMINARIO DE PRACTICA INFORMATICA  

-- Autor: Mario Javier Altamirano 

-- Fecha: 06/10/2024 
-- -------------------------------------------------------------------------------------------
						-- DESARROLLO DEL TRABAJO PRÁCTICO -- 
-- -------------------------------------------------------------------------------------------

-- Crear la base de datos llamada 'etiquetas'
CREATE DATABASE IF NOT EXISTS etiquetas;

-- Usar la base de datos 'etiquetas'
USE etiquetas;


-- Tabla Secciones
CREATE TABLE IF NOT EXISTS secciones (
    id_seccion INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(255) NOT NULL
);

-- Tabla Departamentos
CREATE TABLE IF NOT EXISTS departamentos (
    id_departamento INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    id_seccion INT,
    FOREIGN KEY (id_seccion) REFERENCES secciones(id_seccion)
);

-- Tabla Grupos
CREATE TABLE IF NOT EXISTS grupos (
    id_grupo INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(255) NOT NULL,
    id_seccion INT,
    id_departamento INT,
    FOREIGN KEY (id_seccion) REFERENCES secciones(id_seccion),
    FOREIGN KEY (id_departamento) REFERENCES departamentos(id_departamento)
);


-- Tabla Producto de SGP
CREATE TABLE IF NOT EXISTS  productosSGP (
    id_producto INT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    codigo_barras VARCHAR(13) UNIQUE,
    precio_venta DECIMAL(10,2) NOT NULL,
    stock DECIMAL(10,2) NOT NULL,
    id_seccion INT,
    id_departamento INT,
    id_grupo INT,
    descuento_contado DECIMAL(10,2),
    FOREIGN KEY (id_seccion) REFERENCES secciones(id_seccion),
    FOREIGN KEY (id_departamento) REFERENCES departamentos(id_departamento),
    FOREIGN KEY (id_grupo) REFERENCES grupos(id_grupo)
);

-- Tabla Producto de POS
CREATE TABLE IF NOT EXISTS productosPOS (
    id_producto INT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    codigo_barras VARCHAR(13) UNIQUE,
    precio_venta DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (id_producto) REFERENCES productosSGP(id_producto)
);

-- Tabla Tipos de Novedades
CREATE TABLE IF NOT EXISTS novedades_tipo (
    id_tipo_novedad INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(255) NOT NULL
);

-- Tabla de Novedades SGP (registros de cambios)
CREATE TABLE IF NOT EXISTS novedadessgp (
    id_novedad INT PRIMARY KEY AUTO_INCREMENT,
    id_producto INT,
    fecha_novedad DATETIME NOT NULL,
    descripcion_novedad VARCHAR(255),
    codigo_barras VARCHAR(13),
    precio_novedad DECIMAL(10,2),
    id_tipo_novedad INT,
    operador_novedad VARCHAR(30) NOT NULL,
    vigencia_desde DATE NOT NULL,
    vigencia_hasta DATE,
    FOREIGN KEY (id_producto) REFERENCES productosSGP(id_producto),
    FOREIGN KEY (id_tipo_novedad) REFERENCES novedades_tipo(id_tipo_novedad)
);
-- Tabla Roles
CREATE TABLE IF NOT EXISTS roles (
    id_rol INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(255) NOT NULL
);

-- Tabla Permisos
CREATE TABLE IF NOT EXISTS permisos (
    id_permiso INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(255) NOT NULL
);

-- Tabla Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    id_rol INT,
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);

-- Tabla Roles-Permisos (relación muchos a muchos)
CREATE TABLE IF NOT EXISTS roles_permisos (
    id_rol INT,
    id_permiso INT,
    fecha DATETIME NOT NULL,
    PRIMARY KEY (id_rol, id_permiso),
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol),
    FOREIGN KEY (id_permiso) REFERENCES permisos(id_permiso)
);

-- Tabla Logger de eventos
CREATE TABLE IF NOT EXISTS logger (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo_operacion VARCHAR(30) NOT NULL,
    descripcion VARCHAR(255),
    fecha DATETIME NOT NULL,
    operador VARCHAR(30) NOT NULL
);

-- Tabla para gestionar el estado de las impresiones de novedades
CREATE TABLE IF NOT EXISTS impresiones (
    id_impresion INT PRIMARY KEY AUTO_INCREMENT,   
    id_novedad INT,                                
    id_usuario INT,
    tipo_etiqueta VARCHAR(30),
    fecha_impresion DATETIME NOT NULL,             
    estado_impresion ENUM('PENDIENTE', 'IMPRESA') NOT NULL, 
    cantidad_etiquetas INT NOT NULL,               
    FOREIGN KEY (id_novedad) REFERENCES novedadessgp(id_novedad), 
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) 
);

-- ---------------------------------INSERT,UPDATE, DELETE -----------------------------------------------

-- INSERT en la tabla Secciones
INSERT INTO secciones (descripcion) 
VALUES ('Electrónica'), ('Ropa'), ('Alimentos');

-- UPDATE en la tabla Secciones
UPDATE secciones 
SET descripcion = 'Electrodomésticos' 
WHERE id_seccion = 1;

/*-- DELETE en la tabla Secciones
DELETE FROM secciones 
WHERE id_seccion = 3;*/

-- INSERT en la tabla Departamentos
INSERT INTO departamentos (nombre, id_seccion) 
VALUES ('Televisores', 1), ('Computadoras', 1), ('Camisas', 2);

-- UPDATE en la tabla Departamentos
UPDATE departamentos 
SET nombre = 'Smart TVs' 
WHERE id_departamento = 1;

/*-- DELETE en la tabla Departamentos
DELETE FROM departamentos 
WHERE id_departamento = 3;*/

-- INSERT en la tabla Grupos
INSERT INTO grupos (descripcion, id_seccion, id_departamento) 
VALUES ('Televisores LED', 1, 1), ('Portátiles', 1, 2);

-- UPDATE en la tabla Grupos
UPDATE grupos 
SET descripcion = 'Televisores OLED' 
WHERE id_grupo = 1;

/*-- DELETE en la tabla Grupos
DELETE FROM grupos 
WHERE id_grupo = 3;*/

-- INSERT en la tabla productosSGP
INSERT INTO productosSGP (id_producto, descripcion, codigo_barras, precio_venta, stock, id_seccion, id_departamento, id_grupo, descuento_contado) 
VALUES (1001, 'Televisor 40 pulgadas', '1234567890123', 25000.50, 10, 1, 1, 1, 5.00),
       (1002, 'Laptop Gamer', '9876543210987', 85000.75, 5, 1, 1, 1, 7.50),
       (1003, 'Mouse', '9876543210988',9000, 5, 1, 1, 1, 10);

-- UPDATE en la tabla productosSGP
UPDATE productosSGP 
SET precio_venta = 24000.00 
WHERE id_producto = 1002;

/*-- DELETE en la tabla productosSGP
DELETE FROM productosSGP 
WHERE id_producto = 1003;*/

-- INSERT en la tabla productosPOS
INSERT INTO productosPOS (id_producto, descripcion, codigo_barras, precio_venta) 
VALUES (1001, 'Televisor 40 pulgadas', '1234567890123', 25000.50),
       (1002, 'Laptop Gamer', '9876543210987', 85000.75),
       (1003, 'Mouse', '9876543210988', 9000);

-- UPDATE en la tabla productosPOS
UPDATE productosPOS 
SET precio_venta = 24000.00 
WHERE id_producto = 1001;

/*-- DELETE en la tabla productosPOS
DELETE FROM productosPOS 
WHERE id_producto = 1003;*/

-- INSERT en la tabla novedades_tipo
INSERT INTO novedades_tipo (descripcion) 
VALUES ('Cambio de precio'),('Cambio de Descri.'), ('ALTAS');

-- UPDATE en la tabla novedades_tipo
UPDATE novedades_tipo 
SET descripcion = 'Cambio de Descripción' 
WHERE id_tipo_novedad = 2;

/*-- DELETE en la tabla novedades_tipo
DELETE FROM novedades_tipo 
WHERE id_tipo_novedad = 3;*/

-- INSERT en la tabla novedadessgp
INSERT INTO novedadessgp (id_producto, fecha_novedad, descripcion_novedad, codigo_barras, precio_novedad, id_tipo_novedad, operador_novedad, vigencia_desde, vigencia_hasta) 
VALUES (1001, '2024-10-01 10:00:00', 'Actualización de precio', '1234567890123', 24500.00, 1, 'Operador1', '2024-10-01', '2024-12-31');

-- UPDATE en la tabla novedadessgp
UPDATE novedadessgp 
SET precio_novedad = 23000.00 
WHERE id_novedad = 1;

/*-- DELETE en la tabla novedadessgp
DELETE FROM novedadessgp 
WHERE id_novedad = 3;*/

-- INSERT en la tabla roles
INSERT INTO roles (descripcion) 
VALUES ('Administrador'), ('Repositor');

-- UPDATE en la tabla roles
UPDATE roles 
SET descripcion = 'Super Administrador' 
WHERE id_rol = 1;

/*-- DELETE en la tabla roles
DELETE FROM roles 
WHERE id_rol = 3;*/

-- INSERT en la tabla permisos
INSERT INTO permisos (descripcion) 
VALUES ('Imprimir novedades'), ('Imprimir Reportes');

-- UPDATE en la tabla permisos
UPDATE permisos 
SET descripcion = 'Filtros Avanzados' 
WHERE id_permiso = 1;

/*-- DELETE en la tabla permisos
DELETE FROM permisos 
WHERE id_permiso = 3;*/

-- INSERT en la tabla usuarios
INSERT INTO usuarios (nombre, id_rol) 
VALUES ('Juan Perez', 1), ('Ana Gomez', 2);

-- UPDATE en la tabla usuarios
UPDATE usuarios 
SET nombre = 'Juan Carlos Perez' 
WHERE id_usuario = 1;

/*-- DELETE en la tabla usuarios
DELETE FROM usuarios 
WHERE id_usuario = 3;*/

-- INSERT en la tabla roles_permisos
INSERT INTO roles_permisos (id_rol, id_permiso, fecha) 
VALUES (1, 1, '2024-10-01 09:00:00'), (2, 2, '2024-10-01 09:30:00');

-- UPDATE en la tabla roles_permisos
UPDATE roles_permisos 
SET fecha = '2024-10-01 10:00:00' 
WHERE id_rol = 1 AND id_permiso = 1;

/*-- DELETE en la tabla roles_permisos
DELETE FROM roles_permisos 
WHERE id_rol = 2 AND id_permiso = 3;*/

-- INSERT en la tabla logger
INSERT INTO logger (tipo_operacion, descripcion, fecha, operador) 
VALUES ('INSERT', 'Creación de nueva novedad', '2024-10-01 10:30:00', 'Operador1');

-- INSERT en la tabla impresiones
INSERT INTO impresiones (id_novedad, id_usuario, tipo_etiqueta, fecha_impresion, estado_impresion, cantidad_etiquetas) 
VALUES (1, 1, 'Etiqueta estándar', '2024-10-01 11:00:00', 'PENDIENTE', 100);

-- UPDATE en la tabla impresiones
UPDATE impresiones 
SET estado_impresion = 'IMPRESA', cantidad_etiquetas = 100 
WHERE id_impresion = 1;

/*-- DELETE en la tabla impresiones
DELETE FROM impresiones 
WHERE id_impresion = 3;*/

-- -----------------------------------CONSULTAS ----------------------------------------------------

-- Consulta en la tabla Secciones
SELECT * FROM secciones 
WHERE id_seccion = 1;

-- Consulta en la tabla Departamentos
SELECT * FROM departamentos 
WHERE id_departamento = 1;

-- Consulta en la tabla Grupos
SELECT * FROM grupos 
WHERE id_grupo = 1;

-- Consulta en la tabla productosSGP
SELECT * FROM productosSGP 
WHERE id_producto = 1;

-- Consulta en la tabla productosPOS
SELECT * FROM productosPOS 
WHERE id_producto = 1;

-- Consulta en la tabla novedades_tipo
SELECT * FROM novedades_tipo 
WHERE id_tipo_novedad = 1;


-- Consulta en la tabla productosSGP
SELECT * FROM productosSGP 
WHERE id_producto = 1;

-- Consulta en la tabla roles
SELECT * FROM roles 
WHERE id_rol = 1;

-- Consulta en la tabla permisos
SELECT * FROM permisos 
WHERE id_permiso = 1;

-- Consulta en la tabla usuarios
SELECT * FROM usuarios 
WHERE id_usuario = 1;

-- Consulta en la tabla roles_permisos
SELECT * FROM roles_permisos 
WHERE id_rol = 1 AND id_permiso = 1;

-- Consulta en la tabla logger
SELECT * FROM logger 
WHERE id = 1;


-- Consulta en la tabla impresiones
SELECT * FROM impresiones 
WHERE id_impresion = 1;


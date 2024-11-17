-- -------------------------------------------------------------------------------------------
					-- CARATULA --
-- -------------------------------------------------------------------------------------------
-- UNIVERSIDAD EMPRESARIAL SIGLO 21 

-- CARRERA DE GRADO 

-- LICENCIATURA EN INFORMATICA 

-- TRABAJO PRACTICO M1-M2-M3-M4  
-- SEMINARIO DE PRACTICA INFORMATICA  

-- Autor: Mario Javier Altamirano 
-- Profesora : ANA CAROLINA FERREYRA
-- CATEDRA - A - INF275 - EDH - PR -
-- Fecha: 17/11/2024 
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
    existencia decimal(10,2),
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
    clave CHAR(15) NOT NULL,
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

/*-- UPDATE en la tabla Secciones
UPDATE secciones 
SET descripcion = 'Electrodomésticos' 
WHERE id_seccion = 1;

-- DELETE en la tabla Secciones
DELETE FROM secciones 
WHERE id_seccion = 3;*/

-- INSERT en la tabla Departamentos
INSERT INTO departamentos (nombre, id_seccion) 
VALUES ('Electronica 1', 1), ('Electronica 2', 1), ('Camissas', 2);

/*-- UPDATE en la tabla Departamentos
UPDATE departamentos 
SET nombre = 'Smart TVs' 
WHERE id_departamento = 1;

-- DELETE en la tabla Departamentos
DELETE FROM departamentos 
WHERE id_departamento = 3;*/

-- INSERT en la tabla Grupos
INSERT INTO grupos (descripcion, id_seccion, id_departamento) 
VALUES ('Electronica 1', 1, 1), ('Electronica 2', 1, 2);

/*-- UPDATE en la tabla Grupos
UPDATE grupos 
SET descripcion = 'Televisores OLED' 
WHERE id_grupo = 1;

-- DELETE en la tabla Grupos
DELETE FROM grupos 
WHERE id_grupo = 3;*/

-- INSERT en la tabla productosSGP
INSERT INTO productosSGP (id_producto, descripcion, codigo_barras, precio_venta, stock, id_seccion, id_departamento, id_grupo, descuento_contado) 
VALUES 	(1001, 'Televisor 40 pulgadas', '1234567890123', 25000.50, 10, 1, 1, 1, 5.00),
		(1002, 'Laptop Gamer', '9876543210987', 85000.75, 5, 1, 1, 1, 7.50),
		(1003, 'Mouse', '9876543210988',9000, 5, 1, 1, 1, 10),
		(1004, 'Tablet 10 pulgadas', '1234567890124', 30000.00, 8, 1, 1, 1, 4.50),
		(1005, 'Smartphone', '1234567890125', 55000.00, 15, 1, 1, 1, 5.00),
		(1006, 'Auriculares Bluetooth', '1234567890126', 8000.00, 25, 1, 1, 1, 2.00),
		(1007, 'Parlante Portátil', '1234567890127', 7000.50, 20, 1, 1, 1, 3.00),
		(1008, 'Impresora Multifunción', '1234567890128', 18000.99, 12, 1, 1, 1, 6.00),
		(1009, 'Cámara de Seguridad', '1234567890129', 10500.00, 10, 1, 1, 1, 3.50),
		(1010, 'Cargador Inalámbrico', '1234567890130', 3500.00, 30, 1, 1, 1, 1.50),
		(1011, 'Monitor 24 pulgadas', '1234567890131', 28000.00, 7, 1, 1, 1, 5.00),
		(1012, 'Teclado Mecánico', '1234567890132', 4500.00, 15, 1, 1, 1, 1.80),
		(1013, 'Control de Juego', '1234567890133', 5500.00, 18, 1, 1, 1, 2.20),
		(1014, 'Smartwatch', '1234567890134', 12000.00, 12, 1, 1, 1, 3.00),
		(1015, 'Cámara Deportiva', '1234567890135', 15000.00, 10, 1, 1, 1, 4.00),
		(1016, 'Home Theater', '1234567890136', 32000.00, 5, 1, 1, 1, 7.00),
		(1017, 'Proyector', '1234567890137', 45000.00, 4, 1, 1, 1, 6.50),
		(1018, 'Reproductor Blu-ray', '1234567890138', 12000.00, 8, 1, 1, 1, 3.50),
		(1019, 'Disco Duro Externo', '1234567890139', 9500.00, 20, 1, 1, 1, 2.50),
		(1020, 'Memoria USB 128GB', '1234567890140', 2500.00, 50, 1, 1, 1, 1.00),
		(1021, 'Router WiFi', '1234567890141', 6500.00, 10, 1, 1, 1, 2.50),
		(1022, 'Smart TV 50 pulgadas', '1234567890142', 40000.00, 6, 1, 1, 1, 5.50),
		(1023, 'Mini Proyector', '1234567890143', 12500.00, 8, 1, 1, 1, 3.20),
		(1024, 'Laptop 14 pulgadas', '1234567890144', 60000.00, 7, 1, 1, 1, 7.00),
		(1025, 'Bocina de Alta Fidelidad', '1234567890145', 30000.00, 4, 1, 1, 1, 6.00),
		(1026, 'Microcomponente', '1234567890146', 20000.00, 5, 1, 1, 1, 4.50),
		(1027, 'Adaptador HDMI', '1234567890147', 800.00, 40, 1, 1, 1, 0.50),
		(1028, 'Tarjeta de Memoria 64GB', '1234567890148', 1500.00, 35, 1, 1, 1, 1.20),
		(1029, 'Control Remoto Universal', '1234567890149', 600.00, 50, 1, 1, 1, 0.80),
		(1030, 'MicroSD 128GB', '1234567890150', 2500.00, 30, 1, 1, 1, 1.50),
		(1031, 'Televisor 55 pulgadas', '1234567890151', 48000.00, 6, 1, 1, 1, 6.00),
		(1032, 'Teléfono Inalámbrico', '1234567890152', 5000.00, 15, 1, 1, 1, 2.00),
		(1033, 'Smart Home Hub', '1234567890153', 7000.00, 10, 1, 1, 1, 3.00),
		(1034, 'Receptor de TV Digital', '1234567890154', 3500.00, 20, 1, 1, 1, 2.00),
		(1035, 'Cámara Instantánea', '1234567890155', 14000.00, 10, 1, 1, 1, 4.50),
		(1036, 'Repetidor de WiFi', '1234567890156', 4500.00, 25, 1, 1, 1, 2.20),
		(1037, 'Dock de Carga para Smartphone', '1234567890157', 2500.00, 30, 1, 1, 1, 1.30),
		(1038, 'Adaptador USB-C a HDMI', '1234567890158', 1200.00, 40, 1, 1, 1, 0.80),
		(1039, 'Mousepad Gaming', '1234567890159', 700.00, 50, 1, 1, 1, 0.50),
		(1040, 'Cable HDMI 2 metros', '1234567890160', 500.00, 100, 1, 1, 1, 0.30),
		(1041, 'Batería Portátil 10000mAh', '1234567890161', 3200.00, 25, 1, 1, 1, 2.50),
		(1042, 'Altavoz Inteligente', '1234567890162', 12000.00, 12, 1, 1, 1, 3.50),
		(1043, 'Receptor Bluetooth', '1234567890163', 1500.00, 35, 1, 1, 1, 1.50),
		(1044, 'Lámpara LED Inteligente', '1234567890164', 3000.00, 20, 1, 1, 1, 2.00),
		(1045, 'Monitor 27 pulgadas', '1234567890165', 35000.00, 5, 1, 1, 1, 6.00),
		(1046, 'SSD Externo 1TB', '1234567890166', 22000.00, 8, 1, 1, 1, 4.00),
		(1047, 'Gimbal para Smartphone', '1234567890167', 13000.00, 7, 1, 1, 1, 3.00),
		(1048, 'Cámara de Video', '1234567890168', 45000.00, 3, 1, 1, 1, 7.50),
		(1049, 'Control de Drones', '1234567890169', 17000.00, 4, 1, 1, 1, 4.80),
		(1050, 'Soundbar', '1234567890170', 18000.00, 10, 1, 1, 1, 5.00);

/*-- UPDATE en la tabla productosSGP
UPDATE productosSGP 
SET precio_venta = 24000.00 
WHERE id_producto = 1002;

-- DELETE en la tabla productosSGP
DELETE FROM productosSGP 
WHERE id_producto = 1003;*/

-- INSERT en la tabla productosPOS
INSERT INTO productosPOS (id_producto, descripcion, codigo_barras, precio_venta) 
VALUES (1001, 'Televisor 40 pulgadas', '1234567890123', 25000.50),
       (1002, 'Laptop Gamer', '9876543210987', 85000.75),
       (1003, 'Mouse', '9876543210988', 9000),
       (1004, 'Tablet 10 pulgadas', '1234567890124', 30000.00),
(1005, 'Smartphone', '1234567890125', 55000.00),
(1006, 'Auriculares Bluetooth', '1234567890126', 8000.00),
(1007, 'Parlante Portátil', '1234567890127', 7000.50),
(1008, 'Impresora Multifunción', '1234567890128', 18000.99),
(1009, 'Cámara de Seguridad', '1234567890129', 10500.00),
(1010, 'Cargador Inalámbrico', '1234567890130', 3500.00),
(1011, 'Monitor 24 pulgadas', '1234567890131', 28000.00),
(1012, 'Teclado Mecánico', '1234567890132', 4500.00),
(1013, 'Control de Juego', '1234567890133', 5500.00),
(1014, 'Smartwatch', '1234567890134', 12000.00),
(1015, 'Cámara Deportiva', '1234567890135', 15000.00),
(1016, 'Home Theater', '1234567890136', 32000.00),
(1017, 'Proyector', '1234567890137', 45000.00),
(1018, 'Reproductor Blu-ray', '1234567890138', 12000.00),
(1019, 'Disco Duro Externo', '1234567890139', 9500.00),
(1020, 'Memoria USB 128GB', '1234567890140', 2500.00),
(1021, 'Router WiFi', '1234567890141', 6500.00),
(1022, 'Smart TV 50 pulgadas', '1234567890142', 40000.00),
(1023, 'Mini Proyector', '1234567890143', 12500.00),
(1024, 'Laptop 14 pulgadas', '1234567890144', 60000.00),
(1025, 'Bocina de Alta Fidelidad', '1234567890145', 30000.00),
(1026, 'Microcomponente', '1234567890146', 20000.00),
(1027, 'Adaptador HDMI', '1234567890147', 800.00),
(1028, 'Tarjeta de Memoria 64GB', '1234567890148', 1500.00),
(1029, 'Control Remoto Universal', '1234567890149', 600.00),
(1030, 'MicroSD 128GB', '1234567890150', 2500.00),
(1031, 'Televisor 55 pulgadas', '1234567890151', 48000.00),
(1032, 'Teléfono Inalámbrico', '1234567890152', 5000.00),
(1033, 'Smart Home Hub', '1234567890153', 7000.00),
(1034, 'Receptor de TV Digital', '1234567890154', 3500.00),
(1035, 'Cámara Instantánea', '1234567890155', 14000.00),
(1036, 'Repetidor de WiFi', '1234567890156', 4500.00),
(1037, 'Dock de Carga para Smartphone', '1234567890157', 2500.00),
(1038, 'Adaptador USB-C a HDMI', '1234567890158', 1200.00),
(1039, 'Mousepad Gaming', '1234567890159', 700.00),
(1040, 'Cable HDMI 2 metros', '1234567890160', 500.00),
(1041, 'Batería Portátil 10000mAh', '1234567890161', 3200.00),
(1042, 'Altavoz Inteligente', '1234567890162', 12000.00),
(1043, 'Receptor Bluetooth', '1234567890163', 1500.00),
(1044, 'Lámpara LED Inteligente', '1234567890164', 3000.00),
(1045, 'Monitor 27 pulgadas', '1234567890165', 35000.00),
(1046, 'SSD Externo 1TB', '1234567890166', 22000.00),
(1047, 'Gimbal para Smartphone', '1234567890167', 13000.00),
(1048, 'Cámara de Video', '1234567890168', 45000.00),
(1049, 'Control de Drones', '1234567890169', 17000.00),
(1050, 'Soundbar', '1234567890170', 18000.00);

/*-- UPDATE en la tabla productosPOS
UPDATE productosPOS 
SET precio_venta = 24000.00 
WHERE id_producto = 1001;

-- DELETE en la tabla productosPOS
DELETE FROM productosPOS 
WHERE id_producto = 1003;*/

-- INSERT en la tabla novedades_tipo
INSERT INTO novedades_tipo (descripcion) 
VALUES ('Cambio de precio'),('Cambio de Descri.'), ('ALTAS');

/*-- UPDATE en la tabla novedades_tipo
UPDATE novedades_tipo 
SET descripcion = 'Cambio de Descripción' 
WHERE id_tipo_novedad = 2;

-- DELETE en la tabla novedades_tipo
DELETE FROM novedades_tipo 
WHERE id_tipo_novedad = 3;*/

-- INSERT en la tabla novedadessgp

INSERT INTO novedadessgp (id_producto, fecha_novedad, descripcion_novedad, codigo_barras, precio_novedad, id_tipo_novedad, operador_novedad, vigencia_desde, vigencia_hasta) 
VALUES 
-- Día 1
(1001, '2024-11-01 09:00:00', 'Actualización de precio', '1234567890123', 24750.00, 1, 'Operador1', '2024-11-01', '2024-12-31'),
(1002, '2024-11-01 10:15:00', 'Descuento temporal', '9876543210987', 80000.00, 2, 'Operador2', '2024-11-01', '2024-11-10'),
(1003, '2024-11-01 11:30:00', 'Cambio de descripción', '9876543210988', 9000.00, 3, 'Operador3', '2024-11-01', '2024-12-31'),
(1004, '2024-11-01 13:45:00', 'Actualización de precio', '1234567890124', 30500.00, 1, 'Operador4', '2024-11-01', '2024-12-31'),
(1005, '2024-11-01 15:00:00', 'Descuento temporal', '1234567890125', 50000.00, 2, 'Operador1', '2024-11-01', '2024-11-15'),

-- Día 7
(1006, '2024-11-07 08:30:00', 'Actualización de precio', '1234567890126', 8500.00, 1, 'Operador2', '2024-11-07', '2024-12-31'),
(1007, '2024-11-07 09:45:00', 'Cambio de descripción', '1234567890127', 7000.50, 3, 'Operador3', '2024-11-07', '2024-12-31'),
(1008, '2024-11-07 11:00:00', 'Actualización de precio', '1234567890128', 18500.00, 1, 'Operador4', '2024-11-07', '2024-12-31'),
(1001, '2024-11-07 13:15:00', 'Descuento temporal', '1234567890123', 24000.00, 2, 'Operador1', '2024-11-07', '2024-11-20'),
(1009, '2024-11-07 14:30:00', 'Actualización de precio', '1234567890129', 10500.00, 1, 'Operador2', '2024-11-07', '2024-12-31'),

-- Día 14
(1010, '2024-11-14 09:00:00', 'Cambio de descripción', '1234567890130', 3400.00, 3, 'Operador3', '2024-11-14', '2024-12-31'),
(1011, '2024-11-14 10:15:00', 'Actualización de precio', '1234567890131', 28000.00, 1, 'Operador4', '2024-11-14', '2024-12-31'),
(1012, '2024-11-14 11:30:00', 'Descuento temporal', '1234567890132', 4400.00, 2, 'Operador1', '2024-11-14', '2024-11-25'),
(1013, '2024-11-14 13:45:00', 'Actualización de precio', '1234567890133', 5000.00, 1, 'Operador2', '2024-11-14', '2024-12-31'),
(1014, '2024-11-14 15:00:00', 'Cambio de descripción', '1234567890134', 12500.00, 3, 'Operador3', '2024-11-14', '2024-12-31'),

-- Día 21
(1015, '2024-11-21 08:30:00', 'Actualización de precio', '1234567890135', 15000.00, 1, 'Operador4', '2024-11-21', '2024-12-31'),
(1016, '2024-11-21 09:45:00', 'Descuento temporal', '1234567890136', 32000.00, 2, 'Operador1', '2024-11-21', '2024-11-30'),
(1017, '2024-11-21 11:00:00', 'Cambio de descripción', '1234567890137', 42000.00, 3, 'Operador2', '2024-11-21', '2024-12-31'),
(1018, '2024-11-21 13:15:00', 'Actualización de precio', '1234567890138', 11500.00, 1, 'Operador3', '2024-11-21', '2024-12-31'),
(1019, '2024-11-21 14:30:00', 'Cambio de descripción', '1234567890139', 9500.00, 3, 'Operador4', '2024-11-21', '2024-12-31'),

-- Día 28
(1020, '2024-11-28 09:00:00', 'Actualización de precio', '1234567890140', 2600.00, 1, 'Operador1', '2024-11-28', '2024-12-31'),
(1021, '2024-11-28 10:15:00', 'Descuento temporal', '1234567890141', 6300.00, 2, 'Operador2', '2024-11-28', '2024-12-10'),
(1022, '2024-11-28 11:30:00', 'Actualización de precio', '1234567890142', 39000.00, 1, 'Operador3', '2024-11-28', '2024-12-31'),
(1023, '2024-11-28 13:45:00', 'Cambio de descripción', '1234567890143', 12500.00, 3, 'Operador4', '2024-11-28', '2024-12-31'),
(1024, '2024-11-28 15:00:00', 'Descuento temporal', '1234567890144', 29500.00, 2, 'Operador1', '2024-11-28', '2024-12-05');

/*-- UPDATE en la tabla novedadessgp
UPDATE novedadessgp 
SET precio_novedad = 23000.00 
WHERE id_novedad = 1;

-- DELETE en la tabla novedadessgp
DELETE FROM novedadessgp 
WHERE id_novedad = 3;*/

-- INSERT en la tabla roles
INSERT INTO roles (descripcion) 
VALUES ('Administrador'), ('Repositor');

/*-- UPDATE en la tabla roles
UPDATE roles 
SET descripcion = 'Super Administrador' 
WHERE id_rol = 1;

-- DELETE en la tabla roles
DELETE FROM roles 
WHERE id_rol = 3;*/

-- INSERT en la tabla permisos
INSERT INTO permisos (descripcion) 
VALUES ('Imprimir novedades'), ('Imprimir Reportes');

/*-- UPDATE en la tabla permisos
UPDATE permisos 
SET descripcion = 'Filtros Avanzados' 
WHERE id_permiso = 1;

-- DELETE en la tabla permisos
DELETE FROM permisos 
WHERE id_permiso = 3;*/

-- INSERT en la tabla usuarios
INSERT INTO usuarios (nombre,clave, id_rol) 
VALUES ('Mario Altamirano',1, 1),
 ('Ana Gomez',2,2),
 ('Repositor 1',3, 2),
 ('Repositor 2',4,2),
 ('Repositor 3',5, 2),
 ('Supervisor 1',6,1),
 ('Supervisor 2',7, 1),
 ('Supervisor 3',8,1);

/*-- UPDATE en la tabla usuarios
UPDATE usuarios 
SET nombre = 'Juan Carlos Perez' 
WHERE id_usuario = 1;

-- DELETE en la tabla usuarios
DELETE FROM usuarios 
WHERE id_usuario = 3;*/

-- INSERT en la tabla roles_permisos
INSERT INTO roles_permisos (id_rol, id_permiso, fecha) 
VALUES (1, 1, '2024-10-01 09:00:00'), (2, 2, '2024-10-01 09:30:00');

/*-- UPDATE en la tabla roles_permisos
UPDATE roles_permisos 
SET fecha = '2024-10-01 10:00:00' 
WHERE id_rol = 1 AND id_permiso = 1;
-- DELETE en la tabla roles_permisos
DELETE FROM roles_permisos 
WHERE id_rol = 2 AND id_permiso = 3;*/

-- INSERT en la tabla logger
INSERT INTO logger (tipo_operacion, descripcion, fecha, operador) 
VALUES ('INSERT', 'Creación de nueva novedad', '2024-10-01 10:30:00', 'Operador1');

-- INSERT en la tabla impresiones
INSERT INTO impresiones (id_novedad, id_usuario, tipo_etiqueta, fecha_impresion, estado_impresion, cantidad_etiquetas) 
VALUES (1, 1, 'Etiqueta estándar', '2024-10-01 11:00:00', 'PENDIENTE', 100);

/*-- UPDATE en la tabla impresiones
UPDATE impresiones 
SET estado_impresion = 'IMPRESA', cantidad_etiquetas = 100 
WHERE id_impresion = 1;

-- DELETE en la tabla impresiones
DELETE FROM impresiones 
WHERE id_impresion = 3;*/

-- -----------------------------------CONSULTAS ----------------------------------------------------
/*
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
WHERE id_impresion = 1;*/


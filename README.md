# SRC/  

## Estructura de la Carpeta src/
La carpeta src/ contiene el código fuente completo del proyecto SIGEP, organizado en paquetes que implementan la lógica de negocio, la conexión a la base de datos y la interfaz gráfica de usuario (GUI). A continuación, se describe cada uno de los principales paquetes y su propósito:

- main.java.sigep:

Este paquete contiene la clase principal Main, encargada de iniciar la aplicación SIGEP. Se utiliza la clase java.awt.EventQueue para ejecutar la GUI de forma segura en el hilo de eventos de Swing.

- main.java.sigep.model:

En este paquete se encuentran las clases que manejan la lógica de acceso a datos y la gestión de la base de datos. La clase abstracta Conexion define los métodos para conectar y desconectar de la base de datos MySQL.

- main.java.sigep.model.dao:

Este paquete incluye las clases DAO (Data Access Object), que permiten interactuar con la base de datos. Se destacan clases como ProductosPOSDAO, que maneja la consulta de productos, y UsuariosDAO, encargada de la autenticación y gestión de usuarios.

- main.java.sigep.model.entidades:

Aquí se encuentran las clases que representan las entidades del sistema, como ProductosPOS, Usuarios, y Roles. Estas clases encapsulan los datos de los objetos que interactúan con el sistema.

- main.java.sigep.views:

Este paquete contiene las clases relacionadas con la interfaz gráfica de usuario (GUI) del sistema. La clase Precios es la responsable de mostrar y gestionar la pantalla principal, donde los usuarios pueden buscar productos, seleccionar tipos de etiquetas, y emitir impresiones. También contiene lógica para la interacción con impresoras compatibles, como las impresoras Zebra.

## Funcionalidades Actuales
- Conexión a la base de datos: La clase Conexion permite conectar y desconectar de una base de datos MySQL.
- Gestión de productos: Consultar productos por código de barras o ID de producto mediante ProductosPOSDAO.
- Interfaz gráfica: La clase Precios ofrece una interfaz intuitiva para que los usuarios seleccionen productos y gestionen la impresión de etiquetas.
- Autenticación y roles: A través de UsuariosDAOImpl, se validan los usuarios y se asignan roles para definir el acceso a las diferentes funcionalidades del sistema.
- Gestión de Novedades

## Próximos pasos
El proyecto SIGEP aún está en desarrollo y las próximas etapas incluirán la implementación de nuevas funcionalidades, mejora en la seguridad del sistema y optimización de la interfaz gráfica.



# Base de Datos para Sistema de Integral de Gestion y Etiquetado de Productos

Este repositorio contiene un script SQL que crea y gestiona la base de datos necesaria para un sistema de etiquetado de productos en un Hipermercado. El proyecto está orientado a integrar distintos módulos de gestión de productos, novedades y etiquetado, permitiendo una operación eficiente y actualizada en el manejo de precios y stock en el punto de venta (POS) y el sistema de gestión de productos (SGP).

## Descripción del Proyecto
El sistema busca automatizar el proceso de etiquetado y gestionar las novedades de productos en distintas áreas del supermercado. Esto incluye la creación de etiquetas para productos, la administración de cambios de precios, y la generación de reportes para garantizar que las etiquetas en las góndolas estén actualizadas conforme a las últimas novedades registradas en el sistema.

## Funcionalidades Clave
- Gestión de Productos: Se manejan los productos de distintas secciones, departamentos y grupos, permitiendo la actualización de precios y la gestión de stock en tiempo real.
- Novedades de Productos: Se registra cualquier novedad (cambios de precio, descripción, etc.) en los productos y su vigencia para el etiquetado correcto.
- Usuarios y Roles: Se gestionan los usuarios del sistema con distintos roles y permisos para realizar acciones dentro de la plataforma.
- g de Eventos: Se almacena un registro de eventos importantes para auditoría, como cambios de precios y emisión de etiquetas.
- Impresiones: Se gestiona el estado de las impresiones de etiquetas, permitiendo marcar etiquetas como "Pendiente" o "Impresa".
## Estructura del Script
### Tablas Principales 
- Secciones, Departamentos y Grupos: Estructuran los productos por categorías.
- Productos SGP y POS: Manejan la información de productos tanto en el sistema de gestión de productos como en el punto de venta.
- Novedades SGP: Almacena los cambios de productos que requieren la actualización de etiquetas.
- Usuarios, Roles y Permisos: Gestionan los accesos y las acciones permitidas por cada rol de usuario.
- Logger: Registra los eventos importantes del sistema.
- Impresiones: Gestiona el estado y los detalles de las impresiones de etiquetas de productos.
### Consultas SQL

* El script incluye ejemplos de consultas SQL que permiten: 

- Listar productos por secciones, departamentos y grupos.
- Consultar las novedades registradas para un producto específico.
- Consultar el estado de las impresiones de etiquetas.

## Instrucciones de Uso
Crear la Base de Datos: El primer paso es crear la base de datos llamada etiquetas si no existe:

## sql 
Copiar código
CREATE DATABASE IF NOT EXISTS etiquetas;
Ejecutar el Script: Utiliza un gestor de bases de datos (como MySQL Workbench o phpMyAdmin) para ejecutar el script completo y crear las tablas necesarias.

Inserciones Iniciales: El script incluye inserciones de datos de ejemplo para secciones, departamentos, grupos y productos.

Consultas y Operaciones: El script ofrece ejemplos de consultas SELECT, UPDATE, DELETE y más, para gestionar los datos dentro del sistema.

## Requerimientos
MySQL 5.7 o superior.
Un gestor de bases de datos compatible con MySQL.


# 🚀 Sistema de Gestión - Distribuidora de Tecnología

Este proyecto es una solución integral de **backend** desarrollada para gestionar las operaciones comerciales de una distribuidora de productos tecnológicos. [cite_start]Permite administrar el flujo completo desde el abastecimiento con proveedores hasta la entrega final al cliente. [cite: 13, 33, 37]

## 🛠️ Tecnologías Utilizadas
* **Java** con **Spring Boot** (Framework principal)
* **JPA / Hibernate** (Persistencia de datos)
* **MySQL** (Base de datos relacional)
* **Maven** (Gestión de dependencias)

## 📋 Requisitos Funcionales

A continuación se detallan los requisitos funcionales basados en el diseño del sistema:

* [cite_start]**RF 1:** El sistema debe permitir el registro y gestión de **Clientes**, almacenando nombre, apellido, tipo de cliente, teléfono, email, dirección y CUIT. [cite: 5, 6, 7, 8, 9, 10, 11, 12]
* [cite_start]**RF 2:** El sistema debe permitir la creación de **Pedidos** asociados a un cliente, registrando la fecha y el estado del mismo. [cite: 13, 14, 15, 16, 17]
* [cite_start]**RF 3:** El sistema debe gestionar el **Detalle del Pedido**, vinculando productos con cantidades y precios unitarios específicos. [cite: 27, 29, 30]
* [cite_start]**RF 4:** El sistema debe permitir la gestión del catálogo de **Productos**, incluyendo su nombre, descripción y niveles de stock. [cite: 33, 34]
* [cite_start]**RF 5:** El sistema debe clasificar los productos mediante **Categorías** y **Tipos de Producto**. [cite: 31, 32, 35, 36]
* [cite_start]**RF 6:** El sistema debe administrar diferentes **Precios** de productos en función del tipo de cliente. [cite: 44, 45]
* [cite_start]**RF 7:** El sistema debe generar una **Factura** por cada pedido, detallando el número, la fecha y el monto total. [cite: 21, 22, 23, 25, 26, 28]
* [cite_start]**RF 8:** El sistema debe registrar los **Pagos**, incluyendo el método utilizado, el monto y la fecha de la transacción. [cite: 1, 2, 3, 4]
* [cite_start]**RF 9:** El sistema debe gestionar el **Envío** de los pedidos, registrando el método de entrega, la fecha de envío y el estado. [cite: 18, 19, 20]
* [cite_start]**RF 10:** El sistema debe permitir el registro de **Proveedores** y sus datos de contacto. [cite: 37, 38, 39]
* [cite_start]**RF 11:** El sistema debe gestionar las **Compras** realizadas, registrando la fecha de la operación. [cite: 40, 41]
* [cite_start]**RF 12:** El sistema debe registrar el **Detalle de Compra**, especificando la cantidad y el precio de los productos adquiridos. [cite: 42, 43]

## 📊 Arquitectura de Datos
[cite_start]El sistema utiliza un modelo relacional que asegura la integridad de la información entre los módulos de ventas, inventario y compras. [cite: 1]

## 🚀 Instalación
1. Clonar el repositorio.
2. Configurar las credenciales de la base de datos en `src/main/resources/application.properties`.
3. Ejecutar el proyecto con `./mvnw spring-boot:run`.

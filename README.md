  **Sistema de Gestión - Distribuidora de Tecnología**

  Este proyecto es una solución integral de backend desarrollada para gestionar las operaciones comerciales de una distribuidora de productos tecnológicos. Permite administrar el flujo completo desde el abastecimiento con proveedores hasta la entrega final al cliente.
 
  **Tecnologías Utilizadas:**
* Java con Spring Boot (Framework principal)
* JPA / Hibernate (Persistencia de datos)
* MySQL (Base de datos relacional)
* Maven (Gestión de dependencias)

  **Requisitos Funcionales**

-RF 1: El sistema debe permitir el registro y gestión de "Clientes", almacenando nombre, apellido, tipo de cliente, teléfono, email, dirección y CUIT.

-RF 2: El sistema debe permitir la creación de "Pedidos" asociados a un cliente, registrando la fecha y el estado del mismo. 

-RF 3: El sistema debe gestionar el "Detalle del Pedido", vinculando productos con cantidades y precios unitarios específicos.

-RF 4: El sistema debe permitir la gestión del catálogo de "Productos", incluyendo su nombre, descripción y niveles de stock. 

-RF 5: El sistema debe clasificar los productos mediante "Categorías" y "Tipos de Producto". 

-RF 6: El sistema debe administrar diferentes "Precios" de productos en función del tipo de cliente. 

-RF 7: El sistema debe generar una "Factura" por cada pedido, detallando el número, la fecha y el monto total.

-RF 8: El sistema debe registrar los "Pagos", incluyendo el método utilizado, el monto y la fecha de la transacción.

-RF 9: El sistema debe gestionar el "Envío" de los pedidos, registrando el método de entrega, la fecha de envío y el estado.

-RF 10: El sistema debe permitir el registro de "Proveedores" y sus datos de contacto.

-RF 11: El sistema debe gestionar las "Compras" realizadas, registrando la fecha de la operación. 

-RF 12: El sistema debe registrar el "Detalle de Compra", especificando la cantidad y el precio de los productos adquiridos. 

**Requisitos No Funcionales (RNF)**

-RNF 1: El sistema debe ser capaz de procesar las consultas de stock y precios en un tiempo de respuesta menor a 2 segundos.
-RNF 2: Las contraseñas de los usuarios y la información sensible de los clientes deben estar encriptadas en la base de datos MySQL.
-RNF 3: El sistema debe estar disponible para la consulta de pedidos y facturación el 99% del tiempo durante el horario comercial.
-RNF 4: La arquitectura debe permitir el incremento de la carga de datos sin degradar el rendimiento general.
-RNF 5: El sistema debe garantizar la integridad referencial mediante el uso de JPA/Hibernate.

**Arquitectura de Datos**

  El sistema utiliza un modelo relacional que asegura la integridad de la información entre los módulos de ventas, inventario y compras. 

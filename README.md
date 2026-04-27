  *Sistema de Gestión - Distribuidora de Tecnología*

  Este proyecto es una solución integral de backend desarrollada para gestionar las operaciones comerciales de una distribuidora de productos tecnológicos. Permite administrar el flujo completo desde el abastecimiento con proveedores hasta la entrega final al cliente.
 
  Tecnologías Utilizadas:*
* Java con Spring Boot (Framework principal)
* JPA / Hibernate (Persistencia de datos)
* MySQL (Base de datos relacional)
* Maven (Gestión de dependencias)

  *Requisitos Funcionales*

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


*Arquitectura de Datos*
  
  El sistema utiliza un modelo relacional que asegura la integridad de la información entre los módulos de ventas, inventario y compras. 

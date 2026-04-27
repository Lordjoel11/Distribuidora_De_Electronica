  **Sistema de Gestión - Distribuidora de Tecnología**

  Este proyecto es una solución integral de backend desarrollada para gestionar las operaciones comerciales de una distribuidora de productos tecnológicos. Permite administrar el flujo completo desde el abastecimiento con proveedores hasta la entrega final al cliente.
 
  **Tecnologías Utilizadas:**
* Java con Spring Boot (Framework principal)
* JPA / Hibernate (Persistencia de datos)
* MySQL (Base de datos relacional)
* Maven (Gestión de dependencias)

 **Requisitos Funcionales:**
RF 1: El sistema debe permitir al Administrador registrar y gestionar clientes, almacenando nombre, apellido, tipo de cliente, teléfono, email, dirección y CUIT.
RF 2: El sistema debe permitir al Cliente crear pedidos asociados a su cuenta, registrando la fecha y el estado del mismo.
RF 3: El sistema debe permitir al Cliente visualizar el detalle de su pedido.
RF 4: El sistema debe permitir al Administrador gestionar el detalle de los pedidos, vinculando productos con cantidades y precios unitarios.
RF 5: El sistema debe permitir al Administrador gestionar el catálogo de productos, incluyendo nombre, descripción y niveles de stock.
RF 6: El sistema debe permitir al Administrador clasificar los productos mediante categorías y tipos de producto.
RF 7: El sistema debe permitir al Administrador administrar los precios de los productos según el tipo de cliente.
RF 8: El sistema debe permitir al Administrador generar una factura por cada pedido, detallando número, fecha y monto total.
RF 9: El sistema debe permitir al Cliente, Administrador visualizar las facturas de los pedidos.
RF 10: El sistema debe permitir al Cliente registrar pagos, incluyendo el método utilizado, el monto y la fecha de la transacción.
RF 11: El sistema debe permitir al Administrador gestionar los pagos registrados.
RF 12: El sistema debe permitir al Administrador gestionar el envío de los pedidos, registrando método de entrega, fecha de envío y estado.
RF 13: El sistema debe permitir al Cliente, Administrador consultar el estado del envío de los pedidos.
RF 14: El sistema debe permitir al Administrador registrar y gestionar proveedores junto con sus datos de contacto.
RF 15: El sistema debe permitir al Administrador gestionar las compras realizadas, registrando la fecha de la operación.
RF 16: El sistema debe permitir al Administrador registrar el detalle de compra, especificando la cantidad y el precio de los productos adquiridos.

**Requisitos no Funcionales:**
RNF 1: El sistema debe implementar autenticación obligatoria para el acceso de usuarios.
RNF 2: El sistema debe aplicar control de acceso basado en roles (Cliente y Administrador).
RNF 3: El sistema debe cifrar la información sensible almacenada y transmitida.
RNF 4: El sistema debe responder a las solicitudes en un tiempo menor a 3 segundos en condiciones normales de operación.
RNF 5: El sistema debe soportar múltiples usuarios concurrentes sin degradación significativa del rendimiento.
RNF 6: El sistema debe garantizar una disponibilidad mínima del 99% del tiempo.
RNF 7: El sistema debe asegurar la integridad de los datos en todas las transacciones.
RNF 8: El sistema debe realizar copias de seguridad automáticas de forma periódica.
RNF 9: El sistema debe contar con una interfaz web intuitiva y fácil de usar.
RNF 10: El sistema debe ser compatible con los principales navegadores web modernos.
RNF 11: El sistema debe ser accesible desde distintos dispositivos (PC, tablet y móvil).
RNF 12: El sistema debe estar desarrollado con una arquitectura modular que facilite su mantenimiento.
RNF 13: El sistema debe permitir la escalabilidad ante el crecimiento de usuarios y datos sin requerir rediseño completo.


**Arquitectura de Datos**

  El sistema utiliza un modelo relacional que asegura la integridad de la información entre los módulos de ventas, inventario y compras. 

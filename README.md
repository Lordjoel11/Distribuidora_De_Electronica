 # 🖥️ Sistema de Gestión - Distribuidora de Tecnología

Solución integral de backend desarrollada para gestionar las operaciones comerciales de una distribuidora de productos tecnológicos. Permite administrar el flujo completo desde el abastecimiento con proveedores hasta la entrega final al cliente.

---

## 👥 Integrantes

- Joel Lavanchy
- Gianfranco Di Benedetto
- Ivan Di Clemente
- Marcos Basualdo

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Uso |
|---|---|
| Java 21 | Lenguaje principal |
| Spring Boot 4.0.5 | Framework backend |
| Spring Security 7 + JWT | Autenticación y autorización |
| Spring Data JPA / Hibernate 7 | Persistencia de datos |
| H2 (desarrollo) | Base de datos en memoria |
| ModelMapper | Mapeo de objetos |
| Lombok | Reducción de boilerplate |
| HikariCP | Pool de conexiones |
| Apache Tomcat 11 | Servidor embebido |
| Maven | Gestión de dependencias |

---

## 🔐 Seguridad

El sistema implementa autenticación stateless mediante **JWT (JSON Web Token)** y control de acceso basado en roles.

### Roles disponibles

| Rol | Descripción |
|---|---|
| `ADMIN` | Acceso total al sistema |
| `EMPLEADO` | Gestión comercial y operativa |
| `CLIENT` | Acceso a sus propios pedidos y facturas |

### Endpoints de autenticación

| Método | Endpoint | Descripción | Acceso |
|---|---|---|---|
| POST | `/api/v1/auth/register` | Registro de nuevo usuario (CLIENT) | Público |
| POST | `/api/v1/auth/login` | Login y obtención del token JWT | Público |

### Uso del token

Una vez obtenido el token, incluirlo en cada request en el header:
```
Authorization: Bearer <token>
```

---

## 📋 Requisitos Funcionales

| ID | Actor | Descripción |
|---|---|---|
| RF 1 | Administrador | El sistema debe permitir al Administrador registrar y gestionar clientes, almacenando nombre, apellido, tipo de cliente, teléfono, email, dirección y CUIT. |
| RF 2 | Cliente | El sistema debe permitir al Cliente crear pedidos asociados a su cuenta, registrando la fecha y el estado del mismo. |
| RF 3 | Cliente | El sistema debe permitir al Cliente visualizar el detalle de su pedido. |
| RF 4 | Administrador | El sistema debe permitir al Administrador gestionar el detalle de los pedidos, vinculando productos con cantidades y precios unitarios. |
| RF 5 | Administrador | El sistema debe permitir al Administrador gestionar el catálogo de productos, incluyendo nombre, descripción y niveles de stock. |
| RF 6 | Administrador | El sistema debe permitir al Administrador clasificar los productos mediante categorías y tipos de producto. |
| RF 7 | Administrador | El sistema debe permitir al Administrador administrar los precios de los productos según el tipo de cliente. |
| RF 8 | Administrador | El sistema debe permitir al Administrador generar una factura por cada pedido, detallando número, fecha y monto total. |
| RF 9 | Cliente, Administrador | El sistema debe permitir al Cliente y Administrador visualizar las facturas de los pedidos. |
| RF 10 | Cliente | El sistema debe permitir al Cliente registrar pagos, incluyendo el método utilizado, el monto y la fecha de la transacción. |
| RF 11 | Administrador | El sistema debe permitir al Administrador gestionar los pagos registrados. |
| RF 12 | Administrador | El sistema debe permitir al Administrador gestionar el envío de los pedidos, registrando método de entrega, fecha de envío y estado. |
| RF 13 | Cliente, Administrador | El sistema debe permitir al Cliente y Administrador consultar el estado del envío de los pedidos. |
| RF 14 | Administrador | El sistema debe permitir al Administrador registrar y gestionar proveedores junto con sus datos de contacto. |
| RF 15 | Administrador | El sistema debe permitir al Administrador gestionar las compras realizadas, registrando la fecha de la operación. |
| RF 16 | Administrador | El sistema debe permitir al Administrador registrar el detalle de compra, especificando la cantidad y el precio de los productos adquiridos. |

---

## ⚙️ Requisitos No Funcionales

| ID | Descripción |
|---|---|
| RNF 1 | El sistema debe implementar autenticación obligatoria para el acceso de usuarios. |
| RNF 2 | El sistema debe aplicar control de acceso basado en roles (Cliente, Empleado y Administrador). |
| RNF 3 | El sistema debe cifrar la información sensible almacenada y transmitida. |
| RNF 4 | El sistema debe responder a las solicitudes en un tiempo menor a 3 segundos en condiciones normales de operación. |
| RNF 5 | El sistema debe soportar múltiples usuarios concurrentes sin degradación significativa del rendimiento. |
| RNF 6 | El sistema debe garantizar una disponibilidad mínima del 99% del tiempo. |
| RNF 7 | El sistema debe asegurar la integridad de los datos en todas las transacciones. |
| RNF 8 | El sistema debe realizar copias de seguridad automáticas de forma periódica. |
| RNF 9 | El sistema debe contar con una interfaz web intuitiva y fácil de usar. |
| RNF 10 | El sistema debe ser compatible con los principales navegadores web modernos. |
| RNF 11 | El sistema debe ser accesible desde distintos dispositivos (PC, tablet y móvil). |
| RNF 12 | El sistema debe estar desarrollado con una arquitectura modular que facilite su mantenimiento. |
| RNF 13 | El sistema debe permitir la escalabilidad ante el crecimiento de usuarios y datos sin requerir rediseño completo. |

---

## 🗂️ Arquitectura del Proyecto

```
src/main/java/com/Districto_Tech/distribuidora/
│
├── common/
│   ├── config/
│   │   ├── ApplicationConfig.java       # Beans de seguridad
│   │   ├── JwtAuthenticationFilter.java # Filtro JWT
│   │   ├── JwtService.java              # Generación y validación de tokens
│   │   ├── MapperConfig.java            # Configuración ModelMapper
│   │   └── SecurityConfig.java          # Reglas de acceso por rol
│   └── exceptions/
│       └── GlobalExceptionHandler.java  # Manejo centralizado de errores
│
├── features/
│   ├── clients/       # Gestión de clientes
│   ├── employees/     # Gestión de empleados
│   ├── orders/        # Gestión de pedidos
│   ├── orders_details/ # Detalle de pedidos
│   ├── payments/      # Pagos y métodos de pago
│   ├── products/      # Catálogo de productos
│   ├── suppliers/     # Proveedores
│   └── users/
│       ├── auth/      # Login y registro (JWT)
│       └── ...        # CRUD de usuarios
│
└── DistribuidoraApplication.java
```

---

## 🌐 Endpoints Principales

### Clientes — `/api/v1/admin/clients` 🔒 ADMIN
| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/v1/admin/clients` | Listar todos los clientes |
| GET | `/api/v1/admin/clients/{id}` | Obtener cliente por ID |
| POST | `/api/v1/admin/clients` | Crear cliente |
| PUT | `/api/v1/admin/clients/{id}` | Actualizar cliente |
| DELETE | `/api/v1/admin/clients/{id}` | Eliminar cliente |

### Productos — `/api/products` 🔒 ADMIN, EMPLEADO, CLIENT
| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/products` | Listar productos |
| GET | `/api/products/{id}` | Obtener producto por ID |
| POST | `/api/products` | Crear producto |
| PUT | `/api/products/{id}` | Actualizar producto |
| DELETE | `/api/products/{id}` | Eliminar producto |

### Pedidos — `/api/orders` 🔒 ADMIN, EMPLEADO, CLIENT
| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/orders` | Crear pedido |
| DELETE | `/api/orders/id/{id}` | Cancelar pedido por ID |
| DELETE | `/api/orders/code/{code}` | Cancelar pedido por código |

### Pagos — `/api/payments` 🔒 ADMIN, EMPLEADO
| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/payments` | Listar pagos |
| GET | `/api/payments/{id}` | Obtener pago por ID |
| POST | `/api/payments` | Registrar pago |
| PUT | `/api/payments/{id}` | Actualizar pago |
| DELETE | `/api/payments/{id}` | Eliminar pago |

### Proveedores — `/api/suppliers` 🔒 ADMIN, EMPLEADO
| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/suppliers` | Listar proveedores |
| POST | `/api/suppliers` | Crear proveedor |
| PUT | `/api/suppliers/{id}` | Actualizar proveedor |
| DELETE | `/api/suppliers/{id}` | Eliminar proveedor |

---

## 🚀 Cómo ejecutar el proyecto

**1. Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/Distribuidora_De_Electronica.git
cd Distribuidora_De_Electronica
```

**2. Compilar y ejecutar**
```bash
mvn spring-boot:run
```

**3. La API queda disponible en**
```
http://localhost:8080
```

**4. Consola H2 (base de datos en memoria)**
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:distribuidora
Usuario: sa
Password: (vacío)
```

---

## 🧪 Prueba rápida con Postman

**Paso 1 — Registrar usuario ADMIN**
```http
POST http://localhost:8080/api/v1/auth/register
Content-Type: application/json

{
    "email": "admin@distribuidora.com",
    "password": "1234",
    "roleType": "ADMIN"
}
```

**Paso 2 — Login**
```http
POST http://localhost:8080/api/v1/auth/login
Content-Type: application/json

{
    "username": "admin@distribuidora.com",
    "password": "1234"
}
```

**Paso 3 — Usar el token en cada request**
```
Authorization: Bearer <token_obtenido>
```

---

## 🏗️ Arquitectura de Datos

El sistema utiliza un modelo relacional que asegura la integridad de la información entre los módulos de ventas, inventario y compras.

### Entidades principales

```
UserEntity ──────────── ClientEntity
     │
     └────────────────── EmployeeEntity

OrderEntity ─────────── OrderDetails ──── Product
     │
     └────────────────── Payment ────────── PaymentMethod
                              └──────────── DiscountType

Supplier
```

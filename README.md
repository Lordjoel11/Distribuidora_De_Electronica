 # 🖥️ Sistema de Gestión para Distribuidora de Electrónica

Backend desarrollado en Java y Spring Boot para la gestión integral de una distribuidora de productos electrónicos. El sistema permite administrar clientes, empleados, productos, pedidos, pagos, proveedores, facturación y autenticación basada en JWT.

---

# 👥 Integrantes

* Joel Lavanchy
* Gianfranco Di Benedetto
* Ivan Di Clemente
* Marcos Basualdo

---

# 🎯 Objetivo del Proyecto

Desarrollar una API REST segura y escalable que permita gestionar los procesos comerciales y operativos de una distribuidora de electrónica, contemplando diferentes tipos de usuarios y permisos.

---

# 🛠️ Tecnologías Utilizadas

| Tecnología        | Versión |
| ----------------- | ------- |
| Java              | 21      |
| Spring Boot       | 4.0.5   |
| Spring Security   | 7       |
| JWT               | 0.11.5  |
| Spring Data JPA   | 4       |
| Hibernate         | 7       |
| H2 Database       | 2.x     |
| Maven             | 3.x     |
| Lombok            | 1.18.x  |
| ModelMapper       | 3.1.1   |
| OpenAPI / Swagger | 2.6.0   |
| Tomcat Embedded   | 11      |

---

# 🏗️ Arquitectura

El proyecto sigue una arquitectura por capas:

* Controllers
* Services
* Repositories
* Entities
* DTOs
* Security
* Configuration
* Exception Handling

---

# 🔐 Seguridad

El sistema implementa autenticación y autorización mediante JWT.

## Roles

### ADMIN

* Gestión completa del sistema.
* Gestión de empleados.
* Gestión de clientes.
* Gestión de proveedores.
* Gestión de productos.
* Gestión de pedidos.
* Acceso a reportes e historial.

### EMPLEADO

* Gestión operativa.
* Consulta y procesamiento de pedidos.
* Gestión de productos.
* Gestión de proveedores.

### CLIENT

* Registro.
* Inicio de sesión.
* Gestión de carrito.
* Realización de pedidos.
* Consulta de historial de compras.

---

# 📦 Módulos del Sistema

## Usuarios

* Registro de usuarios.
* Inicio de sesión.
* Gestión de roles.
* Recuperación de contraseña.

## Clientes

* Alta, baja y modificación.
* Consulta de historial de compras.

## Empleados

* Administración de empleados.
* Asignación de funciones.

## Productos

* Gestión de catálogo.
* Control de stock.
* Filtrado y búsqueda.

## Pedidos

* Creación de pedidos.
* Seguimiento de estados.
* Cancelación.
* Historial.

## Facturación

* Generación automática de facturas.
* Consulta de comprobantes.

## Pagos

* Tarjeta de crédito.
* Tarjeta de débito.
* Transferencia bancaria.
* Pago en efectivo.

## Proveedores

* Gestión de proveedores.
* Registro de ingresos de mercadería.

---

# 🚀 Instalación

## Clonar repositorio

```bash
git clone <url-del-repositorio>
```

## Ingresar al proyecto

```bash
cd distribuidora
```

## Compilar

```bash
mvn clean install
```

## Ejecutar

```bash
mvn spring-boot:run
```

---

# 🗄️ Base de Datos

Durante el desarrollo se utiliza H2 Database.

Consola:

```text
http://localhost:8080/h2-console
```

---

# 📖 Documentación Swagger

```text
http://localhost:8080/swagger-ui.html
```

o

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🔑 Autenticación

## Login

```http
POST /api/auth/login
```

Ejemplo:

```json
{
  "email": "usuario@test.com",
  "password": "123456"
}
```

Respuesta:

```json
{
  "token": "jwt_token"
}
```

Para acceder a endpoints protegidos:

```http
Authorization: Bearer <jwt_token>
```

---

# 📋 Requerimientos Funcionales

## Gestión de Usuarios

* Registro de clientes.
* Aprobación o rechazo de clientes.
* Inicio de sesión.
* Recuperación de contraseña.

## Gestión de Productos

* Visualización de catálogo.
* Filtrado de productos.
* Consulta de información detallada.
* Administración de productos.

## Gestión de Compras

* Carrito de compras.
* Generación de pedidos.
* Consulta de pedidos.
* Cancelación de pedidos.
* Historial de compras.

## Gestión de Pagos

* Tarjeta de crédito.
* Tarjeta de débito.
* Transferencia bancaria.
* Pago en efectivo.
* Administración de tarjetas.

## Gestión Administrativa

* Gestión de clientes.
* Gestión de empleados.
* Gestión de proveedores.
* Gestión de pedidos.
* Gestión de stock.

---

# 📑 Requerimientos No Funcionales

* Autenticación obligatoria.
* Disponibilidad permanente.
* Tiempo de respuesta menor a 3 segundos.
* Persistencia en base de datos relacional.
* Arquitectura escalable.
* Seguridad de datos sensibles mediante hash o cifrado.
* Registro de operaciones importantes.
* Compatibilidad con navegadores modernos.
* Generación automática de facturas.
* Notificaciones automáticas por correo electrónico.

---

# 🔮 Mejoras Futuras

* Integración con MySQL o PostgreSQL.
* Sistema de notificaciones por email.
* Dashboard administrativo.
* Reportes de ventas.
* Métricas de stock.
* Integración con pasarelas de pago.
* Auditoría completa de operaciones.

---

# 📄 Licencia

Proyecto académico desarrollado para la Tecnicatura Universitaria en Programación.

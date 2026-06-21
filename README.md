# RetroVideo Manager

Proyecto semestral desarrollado para la asignatura Desarrollo Fullstack I.

**Última actualización:** Junio 2026

## Descripción

RetroVideo Manager es un sistema basado en arquitectura de microservicios para la gestión integral de una tienda de cintas VHS.

## Integrantes

- Fernanda Poblete
- Álvaro Vásquez
- Rafael Gallardo

## Microservicios

| Microservicio | Puerto | Base de datos |
|---|---|---|
| MS-Autenticación | 8081 | autenticacion_db |
| MS-Usuarios | 8082 | usuarios_db |
| MS-Catálogo-Películas | 8083 | peliculasdb |
| MS-Pagos | 8084 | Pago_db |
| MS-Membresías | 8085 | membresiasdb |
| MS-Notificaciones | 8086 | notificacionesdb |
| MS-Taller-Reparación | 8087 | DB_REPARACION |
| MS-Ventas | 8088 | ventas_db |
| MS-Alquileres | 8089 | DB_MS_ALQ |
| MS-Inventario-Físico | 8090 | DB_MS_INV_FIS |

## Tecnologías utilizadas

- **Java 21** — lenguaje de programación principal
- **Spring Boot 3.x** — framework base para cada microservicio
- **Maven** — gestión de dependencias y ciclo de build
- **MySQL 8.0** — motor de base de datos relacional (una BD por microservicio)
- **Docker / Docker Compose** — contenedorización y orquestación de servicios
- **GitHub** — control de versiones y repositorio remoto
- **Postman** — pruebas de endpoints REST

## Dependencias principales

| Dependencia | Descripción |
|---|---|
| `spring-boot-starter-web` / `webmvc` | Exposición de APIs REST |
| `spring-boot-starter-webflux` | Comunicación reactiva entre microservicios (WebClient) |
| `spring-boot-starter-data-jpa` | Persistencia con Hibernate y JPA |
| `spring-boot-starter-data-jdbc` | Acceso directo a BD mediante JDBC (MS-Alquileres) |
| `spring-boot-starter-validation` | Validación de datos de entrada con Bean Validation |
| `spring-boot-starter-security` | Seguridad y protección de endpoints (MS-Autenticación) |
| `spring-boot-starter-hateoas` | Soporte HATEOAS para respuestas hipermedia (MS-Usuarios) |
| `spring-cloud-starter-netflix-eureka-client` | Registro y descubrimiento de servicios (MS-Autenticación) |
| `mysql-connector-j` | Driver JDBC para conexión con MySQL |
| `springdoc-openapi-starter-webmvc-ui` | Documentación automática con Swagger UI (OpenAPI 3) |
| `jjwt-api / jjwt-impl / jjwt-jackson` | Generación y validación de tokens JWT |
| `lombok` | Reducción de código boilerplate (getters, setters, constructores) |
| `datafaker` | Generación de datos de prueba ficticios (MS-Alquileres) |
| `jacoco-maven-plugin` | Reporte de cobertura de pruebas unitarias |

## Arquitectura

Sistema distribuido en 10 microservicios independientes, cada uno con su propia base de datos y responsabilidades específicas. La comunicación entre servicios se realiza mediante HTTP REST usando `WebClient` (reactivo). El MS-Autenticación centraliza la seguridad mediante JWT.

```
┌─────────────────────────────────────────────────────────────┐
│                        Docker Network                        │
│                                                             │
│  ┌──────────────┐   ┌──────────────┐   ┌───────────────┐  │
│  │ Autenticacion│   │   Usuarios   │   │   Peliculas   │  │
│  │   :8081      │   │   :8082      │   │   :8083       │  │
│  └──────────────┘   └──────────────┘   └───────────────┘  │
│  ┌──────────────┐   ┌──────────────┐   ┌───────────────┐  │
│  │     Pago     │   │  Membresias  │   │ Notificaciones│  │
│  │   :8084      │   │   :8085      │   │   :8086       │  │
│  └──────────────┘   └──────────────┘   └───────────────┘  │
│  ┌──────────────┐   ┌──────────────┐   ┌───────────────┐  │
│  │  Reparacion  │   │    Ventas    │   │ MS-Alquileres │  │
│  │   :8087      │   │   :8088      │   │   :8089       │  │
│  └──────────────┘   └──────────────┘   └───────────────┘  │
│  ┌──────────────┐   ┌─────────────────────────────────┐   │
│  │ Inventario   │   │           MySQL 8.0              │   │
│  │   :8090      │   │           :3306                  │   │
│  └──────────────┘   └─────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

## Ejecución con Docker

### Requisitos previos
- Docker Desktop instalado y en ejecución

### Levantar todos los servicios

```bash
docker-compose up --build
```

### Detener todos los servicios

```bash
docker-compose down
```

## Documentación API (Swagger)

Cada microservicio expone su documentación en:

```
http://localhost:{puerto}/doc/swagger-ui.html
```

Ejemplo: `http://localhost:8081/doc/swagger-ui.html`

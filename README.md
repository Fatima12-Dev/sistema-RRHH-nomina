# Sistema de Gestión de RR.HH. y Nómina

Aplicación Spring Boot (API REST + panel web con Thymeleaf) para gestión de
empleados, asistencias, vacaciones y cálculo de nómina.

## Requisitos

- Java 17+
- Docker y Docker Compose
- Maven (incluido vía `mvnw`)

## Levantar el proyecto

1. Iniciar la base de datos MySQL:

   ```bash
   docker compose up -d
   ```

2. Iniciar la aplicación:

   ```bash
   ./mvnw spring-boot:run
   ```

   Al primer arranque se cargan 85 empleados de prueba (`src/main/resources/seed.sql`).

3. Abrir en el navegador: http://localhost:8080

## Acceso

En la pantalla de ingreso se elige el rol:

- **Recursos Humanos**: acceso total (empleados, asistencias, vacaciones).
- **Empleado**: solo sus datos, recibo y asistencia.

## URLs

| Recurso | URL |
|---|---|
| Panel web | http://localhost:8080 |
| API REST | http://localhost:8080/api |
| Documentación API (Scalar) | http://localhost:8080/scalar |
| OpenAPI JSON | http://localhost:8080/v3/api-docs |

## Configuración

La conexión a la base de datos está en `src/main/resources/application.properties`
(coincide con `docker-compose.yml`):

- Base de datos: `rrhh_db`
- Usuario: `root` / Contraseña: `12345`
- Puerto: `3306`

## Detener

```bash
docker compose down
```

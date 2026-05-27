# Ejecución y Pruebas

## Requisitos

- Java 21+
- PostgreSQL corriendo en `localhost:5435`
- Base de datos `muhoco` con schema `softlond`
- Tabla `tiendas` en schema `softlond`

## Ejecutar

```bash
./mvnw spring-boot:run
```

La app arranca en `http://localhost:8081`.

> Las variables de entorno se definen en `.env` (raíz del proyecto).  
> Si no existen variables de entorno, se usan los valores por defecto del `application.yml`.

## Seguridad

La API usa **Basic Auth** con usuarios almacenados en base de datos.

### Credenciales

| Usuario | Contraseña | Rol   | Acceso                     |
| ------- | ---------- | ----- | -------------------------- |
| `admin` | `admin`    | ADMIN | Lectura y escritura        |
| `user`  | `user`     | USER  | Solo lectura               |

### Autenticación en peticiones

Agregar header `Authorization: Basic <base64(user:pass)>` en cada request.

```bash
# admin:admin → base64 → YWRtaW46YWRtaW4=
curl -u admin:admin http://localhost:8081/tienda
# equivalente a:
curl -H "Authorization: Basic YWRtaW46YWRtaW4=" http://localhost:8081/tienda
```

## Endpoints

### GET /tienda — Obtener todas

```bash
curl -u admin:admin http://localhost:8081/tienda
```

### GET /tienda/{id} — Obtener por ID

```bash
curl -u admin:admin http://localhost:8081/tienda/1
```

### POST /tienda — Crear (requiere ADMIN)

```bash
curl -u admin:admin -X POST http://localhost:8081/tienda \
  -H "Content-Type: application/json" \
  -d '{"name": "Tienda Nueva", "description": "Descripción de la tienda"}'
```

### PUT /tienda/{id} — Actualizar (requiere ADMIN)

```bash
curl -u admin:admin -X PUT http://localhost:8081/tienda/1 \
  -H "Content-Type: application/json" \
  -d '{"name": "Nombre Actualizado", "description": "Descripción actualizada"}'
```

### DELETE /tienda/{id} — Eliminar (requiere ADMIN)

```bash
curl -u admin:admin -X DELETE http://localhost:8081/tienda/1
```

> Si se usa el usuario `user` en POST/PUT/DELETE, la API retorna `403 Forbidden`.
> Si no se envía autenticación, retorna `401 Unauthorized`.

## SQL Queries en Debug

Con la configuración actual se loguean automáticamente:

- SQL ejecutado
- Parámetros bindeados
- Tiempos de conexión

Ejemplo de salida en consola:

```
21:55:08.336 DEBUG i.r.postgresql.QUERY - Executing query: SELECT "tiendas".* FROM "tiendas"
21:56:01.920 DEBUG i.r.postgresql.PARAM - Bind parameter [2] to: true
```

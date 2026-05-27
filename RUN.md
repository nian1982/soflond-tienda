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

## Endpoints

### GET /tienda — Obtener todas

```bash
curl http://localhost:8081/tienda
```

### GET /tienda/{id} — Obtener por ID

```bash
curl http://localhost:8081/tienda/1
```

### POST /tienda — Crear

```bash
curl -X POST http://localhost:8081/tienda \
  -H "Content-Type: application/json" \
  -d '{"name": "Tienda Nueva", "description": "Descripción de la tienda"}'
```

### PUT /tienda/{id} — Actualizar

```bash
curl -X PUT http://localhost:8081/tienda/1 \
  -H "Content-Type: application/json" \
  -d '{"name": "Nombre Actualizado", "description": "Descripción actualizada"}'
```

### DELETE /tienda/{id} — Eliminar

```bash
curl -X DELETE http://localhost:8081/tienda/1
```

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

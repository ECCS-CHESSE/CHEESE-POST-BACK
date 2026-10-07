# Auth Endpoints

## POST `/auth/login`

Endpoint público, no requiere token.

### Body

```json
{
  "usuario": "string",
  "pass": "string"
}
```

### Respuesta exitosa

```json
{
  "token": "eyJ...",
  "empleado": {
    "success": true,
    "mensaje": "...",
    "usuario": {
      "id_eccs_empleado": 1,
      "id_estatus_empleado": 1,
      "id_eccs_cliente": 1,
      "eccs_estatus": "ACTIVO",
      "id_eccs_sucursal": 1,
      "id_eccs_sucursal_domicilio": 1,
      "empleado": "Nombre Apellido",
      "eccs_url": "http://...",
      "requiereNip": true
    }
  }
}
```

### Respuesta fallida

```json
{
  "success": false,
  "mensaje": "Credenciales inválidas"
}
```

### Función DB

```sql
SELECT * FROM "controlauth".fn_login(:usuario, :pass)
```

---

## POST `/controlvalidaciones/nip`

Requiere JWT en header.

```
Authorization: Bearer <token>
```

> Usar este endpoint cuando `requiereNip: true` en la respuesta del login.

### Body

```json
{
  "idEmpleado": 1,
  "nip": "string"
}
```

### Respuesta

```json
{
  "success": true,
  "titulo": "ECCS - CONTROL VALIDACIONES - EMPLEADO NIP",
  "mensaje": "CONSULTA DE MANERA EXITOSA",
  "response": { ... }
}
```

### Función DB

```sql
SELECT * FROM "controlauth".fn_validar_nip(:idEmpleado, :nip)
```

---

## Flujo general

```
1. POST /auth/login  →  obtener token
2. Si requiereNip === true  →  POST /controlvalidaciones/nip
3. Usar token en Authorization header para el resto de endpoints
```

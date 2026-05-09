# 🍕 ECCS - Flujo de Sincronización Offline

## ¿Qué problema resuelve?

La pizzería trabaja con internet inestable. Cuando no hay internet, la app levanta en **local** apuntando a una **BD local**. Cuando regresa el internet, los datos se sincronizan automáticamente a la **BD productiva en AWS** sin que el empleado haga nada.

---

## 📦 Componentes involucrados

| Componente | Descripción |
|---|---|
| `Angular` | Frontend de la app |
| `Java LOCAL` | Spring Boot corriendo en la máquina de la pizzería |
| `BD LOCAL` | PostgreSQL local |
| `sync.sql` | Archivo que acumula los queries del día |
| `Java AWS` | Spring Boot en producción (AWS) |
| `BD PRODUCTIVA` | PostgreSQL en AWS RDS |
| `eccs_sync_log` | Tabla en BD productiva que guarda errores por sucursal |
| `SUCURSAL_ID` | Variable de entorno en cada máquina local que identifica la sucursal |

---

## 🔄 ESCENARIO 1 — Sin internet (día normal en la pizzería)

```
👨🍳 Empleado usa la app normalmente
        ↓
🖥️  Angular apunta a Java LOCAL (localhost:8080)
        ↓
🗄️  Java LOCAL ejecuta funciones en BD LOCAL
        ↓
📝  Cada operación exitosa escribe una línea en sync.sql
        ↓
😴  sync.sql espera en C:/app/sync.sql
```

### ¿Qué contiene el sync.sql?

```sql
SELECT "controlempresa".fn_create_sucursal('{"nombre":"Sucursal Centro"}');
SELECT "controlempresa".fn_update_sucursal(3, '{"nombre":"Sucursal Norte"}');
DELETE FROM eccs_sucursal WHERE id = 5;
```

> ✅ Solo se registran operaciones **exitosas**. Si algo falla en local, no se escribe en el archivo.

---

## 🌐 ESCENARIO 2 — Vuelve el internet

```
🌐  Internet regresa
        ↓
⏰  Java LOCAL revisa cada 30 seg "¿hay internet?"
        ↓
✅  Detecta internet (ping a 8.8.8.8)
        ↓
📄  Verifica que existe C:/app/sync.sql y no está vacío
        ↓
📤  Envía sync.sql + id_sucursal automáticamente via POST a Java AWS
        ↓
☁️  Java AWS recibe el archivo, ejecuta query por query (continúa aunque falle)
        ↓
✅  BD Productiva actualizada
        ↓
🗂️  sync.sql se mueve a C:/app/procesados/sync_20250610_143200.sql
        ↓
👨🍳  Empleado nunca se enteró de nada
```

### Endpoint que recibe el archivo

```
POST https://tu-servidor-aws.com/eccs/v1/api/sync/import
Content-Type: multipart/form-data
file:         sync.sql
id_sucursal:  1
```

> ✅ Los queries se ejecutan **uno por uno**. Si uno falla, se guarda el error y **continúa con el siguiente**. Al final el archivo se archiva.

---

## ❌ ESCENARIO 3 — Falla algo al sincronizar

```
📤  Java AWS recibe sync.sql y empieza a ejecutar
        ↓
❌  Un query falla (conflicto de clave, dato inválido, etc.)
        ↓
📋  Java AWS guarda en tabla eccs_sync_log:
    - query_fallido → el script exacto que falló
    - error_msg     → el mensaje de error de PostgreSQL
    - fecha         → timestamp del error
    - resuelto      → false
    - id_sucursal   → qué sucursal generó el error
        ↓
🔔  Frontend consulta GET /api/sync/status/{idSucursal}
        ↓
⚠️  Solo esa sucursal ve el mensaje "Tienes errores pendientes de sync"
    (las demás sucursales no ven nada)
        ↓
🧑💻  Admin entra a la app productiva
        ↓
📊  Ve la pantalla "Logs de Sync" filtrada por sucursal
        ↓
✅  Corrige y marca como resuelto
```

### Tabla eccs_sync_log

```sql
CREATE TABLE eccs_sync_log (
  id            SERIAL PRIMARY KEY,
  query_fallido TEXT,
  error_msg     TEXT,
  fecha         TIMESTAMP DEFAULT NOW(),
  resuelto      BOOLEAN DEFAULT FALSE,
  id_sucursal   INT DEFAULT NULL
);
```

### Endpoints de logs disponibles

```
POST  /api/sync/import                → recibe sync.sql + id_sucursal (Java LOCAL)
GET   /api/sync/status/{idSucursal}   → ¿tiene errores pendientes esta sucursal?
GET   /api/sync/logs                  → todos los logs
GET   /api/sync/logs/pendientes       → solo errores sin resolver
PATCH /api/sync/logs/{id}/resuelto    → marcar como resuelto
```

### Respuesta de /status/{idSucursal}

```json
{
  "pendiente": true,
  "total_errores": 2
}
```

---

## ⚙️ Configuración por ambiente

| Variable | LOCAL | PRODUCCIÓN |
|---|---|---|
| `SYNC_SCHEDULER_ENABLED` | `true` | `false` |
| `SYNC_SQL_PATH` | `C:/app/sync.sql` | — |
| `SYNC_SQL_PROCESADOS` | `C:/app/procesados/` | — |
| `SYNC_PROD_URL` | URL de AWS | — |
| `SUCURSAL_ID` | ID real de la sucursal (ej: `1`, `2`) | — |

> ⚠️ Cada máquina local debe tener su propio `SUCURSAL_ID` en el `.env` para identificarse correctamente.

---

## 🗂️ Estructura del módulo en Spring Boot

```
controlsync/
├── controller/   SyncController.java      → endpoints REST
├── entity/       SyncLogEntity.java       → mapea eccs_sync_log
├── query/        SyncLogQuery.java        → queries a la BD
├── service/      SyncService.java         → interface
│                 SyncServiceImpl.java     → lógica: ejecuta queries, loguea errores
└── scheduler/    SyncScheduler.java       → @Scheduled cada 30 seg
```

---

## 📋 Cómo agregar sync a un nuevo módulo

Cuando agregues un nuevo `ServiceImpl` con operaciones de escritura, solo inyecta `SyncService` y llama `registrarQuery` después de cada operación exitosa:

```java
private final SyncService syncService;

// En create:
syncService.registrarQuery("SELECT \"schema\".fn_create_algo('" + request.getDato() + "')");

// En update:
syncService.registrarQuery("SELECT \"schema\".fn_update_algo(" + id + ", '" + request.getDato() + "')");

// En delete:
syncService.registrarQuery("DELETE FROM tabla WHERE id = " + id);
```

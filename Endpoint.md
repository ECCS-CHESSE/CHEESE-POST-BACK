# GENERATE - Guía de Generación de Módulos

## ¿Cómo usar esta guía?

Pásame lo siguiente y genero el componente completo:

```
En modulo {modulo} agrega componente {componente}
DTO:
- campo1: String
- campo2: String
Endpoint: {metodo HTTP} /{ruta}
```

Ejemplo:
```
En modulo auth agrega componente login
DTO:
- user: String
- pass: String
Endpoint: POST /login
```

---

## Arquitectura

```
eccs/com/
├── core/                             ← compartido (no tocar)
│   ├── config/
│   │   ├── DatabaseConfig.java       ← HikariCP + Bean ObjectMapper
│   │   └── GlobalExceptionHandler.java ← errores globales
│   ├── cors/
│   │   ├── SecurityConfig.java       ← CORS + registro JwtFilter
│   │   └── JwtFilter.java            ← valida token en rutas protegidas
│   ├── dtos/
│   │   └── ResponseDto.java
│   ├── middleware/
│   │   └── JsonParserMiddleware.java ← parsea JSON string de queries nativas
│   └── services/
│       ├── JwtUtil.java              ← genera y valida tokens
│       └── ResponseService.java      ← respuesta estándar + PDFs
├── auth/                             ← rutas públicas (sin token)
│   ├── login/
│   └── checkAuthStatus/
└── modules/                          ← rutas protegidas (requieren token)
    └── {modulo}/
        └── {componente}/
            ├── dto/
            │   └── {Componente}RequestDto.java
            ├── controller/
            │   └── {Componente}Controller.java
            ├── service/
            │   ├── {Componente}Service.java
            │   └── {Componente}ServiceImpl.java
            ├── query/
            │   └── {Componente}Query.java
            └── entity/
                └── {Componente}Entity.java
```

### Package base
```
eccs.com.{modulo}.{componente}
```

### Context path
```
POST /eccs/v1/{modulo}/{componente}
```

---

## Plantillas

### DTO
```java
package eccs.com.{modulo}.{componente}.dto;

@Data
public class {Componente}RequestDto {
    @NotBlank
    private String campo1;
    @NotBlank
    private String campo2;
}
```

### Controller
```java
package eccs.com.modules.{modulo}.{componente}.controller;

@RestController
@RequestMapping("/{modulo}")
@RequiredArgsConstructor
public class {Componente}Controller {

    private final {Componente}Service {componente}Service;

    @PostMapping("/{componente}")
    public ResponseEntity<ResponseDto<Object>> {componente}(@Valid @RequestBody {Componente}RequestDto request) {
        return ResponseEntity.ok({componente}Service.{componente}(request));
    }
}
```

### Interface
```java
package eccs.com.modules.{modulo}.{componente}.service;

public interface {Componente}Service {
    ResponseDto<Object> {componente}({Componente}RequestDto request);
}
```

### Service Impl
```java
package eccs.com.modules.{modulo}.{componente}.service;

@Service
@RequiredArgsConstructor
public class {Componente}ServiceImpl implements {Componente}Service {

    private final {Componente}Query {componente}Query;
    private final JsonParserMiddleware jsonParserMiddleware; // solo si la query retorna JSON string

    @Override
    public ResponseDto<Object> {componente}({Componente}RequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = {componente}Query.findAlgo(request.getCampo());
            // con JSON string - campo nombre conocido (ej. "app_menu"):
            // Object result = jsonParserMiddleware.parseField({componente}Query.findAlgo(id), "nombre_columna");
            // con JSON string - campo con nombre igual a la función PostgreSQL (ej. "fn_get_data_empresa"):
            // Object result = jsonParserMiddleware.parseFunction({componente}Query.findAlgo());
            response.setSuccess(true);
            response.setTitulo("ECCS - {MODULO} - {COMPONENTE}");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - {MODULO} - {COMPONENTE}");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}
```

### Query (JPA + @Query nativo)
```java
package eccs.com.{modulo}.{componente}.query;

@Repository
public interface {Componente}Query extends JpaRepository<{Componente}Entity, Long> {

    @Query(value = "SELECT * FROM {tabla} WHERE {campo} = :valor", nativeQuery = true)
    Map<String, Object> findBy{Campo}(@Param("valor") String valor);
}
```

### Entity (mínima, requerida por JPA)
```java
package eccs.com.{modulo}.{componente}.entity;

@Entity
@Table(name = "{tabla}")
@Data
public class {Componente}Entity {
    @Id
    private Long id;
}
```

---

## Ejemplo Real

**Input:**
```
En modulo auth agrega componente login
DTO:
- user: String
- pass: String
Endpoint: POST /login
```

**Archivos generados en** `eccs/com/auth/login/`:

| Archivo | Ruta |
|---|---|
| LoginRequestDto.java | `auth/login/dto/` |
| LoginController.java | `auth/login/controller/` |
| LoginService.java | `auth/login/service/` |
| LoginServiceImpl.java | `auth/login/service/` |
| LoginQuery.java | `auth/login/query/` |
| LoginEntity.java | `auth/login/entity/` |

Endpoint: `POST /eccs/v1/auth/login`

---

## Módulos Implementados

### controlempresa / empresa

**Input usado:**
```
En modulo controlempresa agrega componente empresa
DTO: no hay
Endpoint: POST /empresa/data
Función PostgreSQL: SELECT * FROM "controlempresa".fn_get_data_empresa()
```

**Archivos generados en** `eccs/com/modules/controlempresa/empresa/`:

| Archivo | Ruta |
|---|---|
| EmpresaController.java | `controller/` |
| EmpresaService.java | `service/` |
| EmpresaServiceImpl.java | `service/` |
| EmpresaQuery.java | `query/` |
| EmpresaEntity.java | `entity/` |

Endpoint: `POST /eccs/v1/controlempresa/empresa/data`

**Notas:**
- Sin DTO (no recibe body)
- Usa `jsonParserMiddleware.parseFunction(rows)` → el campo retornado tiene el mismo nombre que la función: `"fn_get_data_empresa"`

---

### controlventas / catalogo-clientes

**Input usado:**
```
En modulo controlventas agrega componente catalogo-clientes
DTO: no hay
Endpoint: POST /catalogo/clientes
Función PostgreSQL: SELECT * FROM "controlventas".fn_get_catalogo_clientes()
```

**Archivos generados en** `eccs/com/modules/controlventas/catalogoclientes/`:

| Archivo | Ruta |
|---|---|
| CatalogoClientesController.java | `controller/` |
| CatalogoClientesService.java | `service/` |
| CatalogoClientesServiceImpl.java | `service/` |
| CatalogoClientesQuery.java | `query/` |
| CatalogoClientesEntity.java | `entity/` |

Endpoint: `POST /eccs/v1/controlventas/catalogo/clientes`

**Notas:**
- Sin DTO (no recibe body)
- Usa `jsonParserMiddleware.parseFunction(rows)` → el campo retornado tiene el mismo nombre que la función: `"fn_get_catalogo_clientes"`

---

### controlempresa / sucursal

#### DELETE - Eliminar Sucursal

**Endpoint:** `DELETE /eccs/v1/controlempresa/sucursal/delete/{id}`

**Path param:**
| Param | Tipo | Requerido |
|---|---|---|
| id | int | ✅ |

**Query ejecutado:**
```sql
DELETE FROM eccs_sucursal WHERE id = :id
```

---

## Notas

- Siempre `@Valid` en el controller
- Siempre `@RequiredArgsConstructor` + `final` en lugar de `@Autowired`
- El controller solo hace `ResponseEntity.ok(service.metodo())`, no construye nada
- El `ServiceImpl` es quien construye el `ResponseDto` con try/catch
- Queries con `nativeQuery = true` → SQL puro
- Entity solo necesita `@Id`, no mapear todos los campos
- `ResponseDto` → `eccs.com.core.dtos`
- `JsonParserMiddleware` → `eccs.com.core.middleware` — dos métodos disponibles:
  - `parseField(rows, "nombre_columna")` → cuando el campo JSON tiene nombre conocido
  - `parseFunction(rows)` → cuando el campo JSON tiene el mismo nombre que la función PostgreSQL
- Todos los módulos en `modules/` requieren token JWT → `Authorization: Bearer <token>`
- Las rutas públicas solo viven en `auth/`

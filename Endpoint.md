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
│   ├── dtos/ResponseDto.java
│   └── services/ResponseService.java
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
package eccs.com.{modulo}.{componente}.controller;

@RestController
@RequestMapping("/{modulo}")
@RequiredArgsConstructor
public class {Componente}Controller {

    private final {Componente}Service {componente}Service;
    private final ResponseService responseService;

    @PostMapping("/{componente}")
    public ResponseEntity<ResponseDto<Object>> {componente}(@Valid @RequestBody {Componente}RequestDto request) {
        Object result = {componente}Service.{componente}(request);
        return responseService.ServiceResponse(new ResponseDto<>(), "Título", "Mensaje", result);
    }
}
```

### Interface
```java
package eccs.com.{modulo}.{componente}.service;

public interface {Componente}Service {
    Object {componente}({Componente}RequestDto request);
}
```

### Service Impl
```java
package eccs.com.{modulo}.{componente}.service;

@Service
@RequiredArgsConstructor
public class {Componente}ServiceImpl implements {Componente}Service {

    private final {Componente}Query {componente}Query;

    @Override
    public Object {componente}({Componente}RequestDto request) {
        // lógica de negocio aquí
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

## Notas

- Siempre `@Valid` en el controller
- Siempre `@RequiredArgsConstructor` + `final` en lugar de `@Autowired`
- Siempre `ResponseService.ServiceResponse()` para la respuesta
- Queries con `nativeQuery = true` → SQL puro
- Entity solo necesita `@Id`, no mapear todos los campos
- `ResponseDto` → `eccs.com.core.dtos`
- `ResponseService` → `eccs.com.core.services`

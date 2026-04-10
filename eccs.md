# ECCS - Documentación del Proyecto

## 📋 Información General

- **Nombre:** ECCS API
- **Versión:** 0.0.1-SNAPSHOT
- **Framework:** Spring Boot 3.4.0
- **Java:** 23
- **Base de datos:** PostgreSQL
- **Puerto:** 8080
- **Context Path:** `/eccs/v1`

---

## 🏗️ Arquitectura

### Estructura de Directorios

```
eccs/
├── src/main/java/eccs/com/
│   ├── core/                          # Núcleo compartido
│   │   ├── config/                    # Configuraciones
│   │   │   ├── DatabaseConfig.java    # Conexión BD con HikariCP + Bean ObjectMapper
│   │   │   └── GlobalExceptionHandler.java  # Manejo de errores global
│   │   ├── cors/                      # Seguridad
│   │   │   ├── SecurityConfig.java    # CORS, Spring Security y registro de JwtFilter
│   │   │   └── JwtFilter.java         # Filtro JWT - valida token en rutas protegidas
│   │   ├── dtos/                      # DTOs compartidos
│   │   │   └── ResponseDto.java       # Respuesta estándar
│   │   ├── middleware/                # Middlewares reutilizables entre módulos
│   │   │   └── JsonParserMiddleware.java  # Parsea campos JSON string de queries nativas
│   │   └── services/                  # Servicios compartidos
│   │       ├── JwtUtil.java           # Generación y validación de tokens JWT
│   │       └── ResponseService.java   # Respuestas estandarizadas y generación de PDFs
│   ├── auth/                          # Módulo de autenticación (rutas públicas)
│   │   ├── login/                     # POST /auth/login
│   │   └── checkAuthStatus/           # POST /auth/checkAuthStatus
│   ├── modules/                       # Módulos de negocio (requieren token)
│   │   └── controldashboard/
│   │       └── sidebar/               # POST /controldashboard/getMenu/{id}
│   └── EccsApplication.java           # Clase principal
├── src/main/resources/
│   ├── application.yaml               # Configuración
│   └── banner.txt                     # Banner de inicio
└── pom.xml                            # Dependencias Maven
```

---

## 🔧 Tecnologías y Dependencias

### Core
- **Spring Boot Web** - API REST
- **Spring Boot JPA** - ORM para base de datos
- **Spring Security** - Seguridad y CORS
- **PostgreSQL** - Base de datos
- **HikariCP** - Pool de conexiones (incluido en JPA)

### Utilidades
- **Lombok** - Reduce código boilerplate
- **JWT (jjwt)** - Autenticación con tokens
- **JasperReports** - Generación de reportes PDF

### Desarrollo
- **Spring Boot DevTools** - Hot reload en desarrollo

---

## 🔄 Cambios Principales

### ✅ Optimizaciones Realizadas

1. **Simplificación de Base de Datos**
   - ❌ Eliminado: Sistema multi-tenant complejo
   - ✅ Implementado: Conexión única a PostgreSQL
   - ✅ HikariCP gestiona automáticamente:
     - Pool de conexiones
     - Cierre por inactividad (1 minuto)
     - Health checks

2. **Limpieza de Dependencias**
   - ❌ Eliminado: Actuator, Swagger, Apache POI, Barcode4j, ZXing, Batik
   - ✅ Mantenido: Solo dependencias esenciales
   - 📉 Reducción: De 20+ a 10 dependencias

3. **Corrección de Packages**
   - ❌ Antes: `eccs.siriob.com.mx.*`
   - ✅ Ahora: `eccs.com.*`

4. **Archivos Eliminados**
   - `DatabaseConnectionService.java` (multi-tenant)
   - `TenantContext.java` (multi-tenant)
   - `TenantService.java` (multi-tenant)
   - `ConnectionMetrics.java` (métricas complejas)

---

## 🗄️ Configuración de Base de Datos

### HikariCP (Automático)

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/space
    username: postgres
    password: postgres
    hikari:
      maximum-pool-size: 10      # Máximo 10 conexiones
      minimum-idle: 2            # Mínimo 2 conexiones listas
      idle-timeout: 60000        # Cierra conexiones inactivas en 1 min
      connection-timeout: 30000  # Timeout de 30 segundos
```

### Gestión Automática
- ✅ Abre conexiones cuando se necesitan
- ✅ Las reutiliza (pool)
- ✅ Las cierra automáticamente después de usarlas
- ✅ Cierra conexiones inactivas > 1 minuto
- ✅ Health checks automáticos

---

## 🔒 Seguridad y CORS

### Configuración Actual
- **CSRF:** Deshabilitado (API REST)
- **Sesión:** STATELESS (sin sesiones del servidor)
- **Autenticación:** JWT Bearer Token en header `Authorization`
- **CORS:** Habilitado para múltiples orígenes

### Rutas Públicas (sin token)
- `POST /eccs/v1/auth/login`
- `POST /eccs/v1/auth/checkAuthStatus`

### Rutas Protegidas (requieren token)
- Cualquier ruta fuera de `/auth/**` → header obligatorio:
```
Authorization: Bearer <token>
```

### Flujo JWT
```
Cliente → JwtFilter → valida header Authorization
       → token válido → continúa
       → token inválido/ausente → 401 Unauthorized
```

### Orígenes Permitidos
- `http://localhost:4200` - Angular dev
- `http://localhost:3000` - React dev

### Métodos HTTP Permitidos
- GET, POST, PUT, DELETE, OPTIONS, PATCH, HEAD

---

## 📦 Core

### ResponseService
**Funcionalidad:**
- Generación de PDFs desde plantillas JRXML (Base64)
- Respuestas estandarizadas para la API

**Métodos:**
```java
// Genera PDF desde JRXML en Base64
byte[] generatePdfFromJrxml(String JRXML, Map<String, Object> parameters)

// Crea respuesta estándar exitosa
ResponseEntity<ResponseDto<Object>> ServiceResponse(...)
```

### JwtUtil
**Funcionalidad:**
- Generación y validación de tokens JWT (HS256)
- Expiración: 8 horas

**Métodos:**
```java
// Genera token con claims y subject
String generateToken(String usuario, Map<String, Object> claims)

// Valida si el token es válido y no expiró
boolean validateToken(String token)
```

### JsonParserMiddleware (`core/middleware`)
**Funcionalidad:**
- Parsea campos JSON string retornados por funciones nativas de PostgreSQL
- Reutilizable en cualquier módulo que use `nativeQuery = true`

**Uso:**
```java
private final JsonParserMiddleware jsonParserMiddleware;

// Convierte el campo "app_menu" de string a Object JSON
jsonParserMiddleware.parseField(query.findAlgo(id), "nombre_columna");
```

### GlobalExceptionHandler
**Maneja:**
- ❌ Body faltante o mal formado → 400 BAD_REQUEST
- ❌ Argumentos inválidos → 400 BAD_REQUEST
- ❌ Errores inesperados → 500 INTERNAL_SERVER_ERROR

---

## 🚀 Cómo Funciona

### 1. Inicio de la Aplicación
```
EccsApplication.main() 
  → Spring Boot inicia
  → DatabaseConfig crea pool HikariCP
  → SecurityConfig configura CORS
  → Servidor escucha en :8080/eccs/v1
```

### 2. Petición HTTP
```
Cliente → SecurityConfig (CORS)
       → JwtFilter (valida token)
       → Controller
       → Service → [JsonParserMiddleware si hay JSON string de BD]
       → Query/DataSource (BD)
       → ResponseDto (respuesta estándar)
       → Cliente
```

### 3. Manejo de Errores
```
Error → GlobalExceptionHandler
     → ResponseDto con error
     → Cliente recibe JSON con:
        {
          "success": false,
          "titulo": "...",
          "mensaje": "...",
          "response": null
        }
```

### 4. Conexión a Base de Datos
```
@Autowired DataSource dataSource;

try (Connection conn = dataSource.getConnection()) {
    // Usar conexión
} // ← Se cierra automáticamente
  // HikariCP la devuelve al pool
```

---

## 📝 Formato de Respuesta Estándar

```json
{
  "success": true,
  "titulo": "Operación Exitosa",
  "mensaje": "Descripción del resultado",
  "response": {
    // Datos de respuesta
  }
}
```

---

## 🔮 Próximos Pasos

### Para Implementar
1. **Excepciones en GlobalExceptionHandler** - `MethodArgumentNotValidException`, `DataAccessException`, `NoHandlerFoundException`, `AccessDeniedException`
2. **Endpoints de negocio** - Crear controllers, services, repositories
3. **Entidades JPA** - Mapear tablas de PostgreSQL

### Dependencias Opcionales (si las necesitas)
- **Swagger/OpenAPI** - Documentación automática de API
- **Apache POI** - Exportar a Excel
- **Barcode4j/ZXing** - Códigos de barras/QR en reportes

---

## 📌 Notas Importantes

- ✅ **No necesitas** cerrar conexiones manualmente
- ✅ **No necesitas** gestionar pools de conexiones
- ✅ **No necesitas** health checks manuales
- ✅ HikariCP lo hace todo automáticamente
- ✅ El código está optimizado y comentado
- ✅ Solo dependencias esenciales

---

**Última actualización:** Seguridad JWT + Middleware implementados
**Estado:** ✅ En desarrollo

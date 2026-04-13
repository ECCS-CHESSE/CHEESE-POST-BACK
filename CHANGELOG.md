# Changelog

Todos los cambios notables del proyecto ECCS serán documentados en este archivo.

---

## [0.0.1-SNAPSHOT] - 2025-02-16

### 🎉 Inicial
- Inicio del proyecto ECCS API con Spring Boot 3.4.0

### ✅ Configuración Completada
- Terminamos configuración de la app, iniciamos con desarrollo

### ✅ Agregado - Módulo controlempresa
- Componente `empresa` con endpoint `POST /eccs/v1/controlempresa/empresa/data`
- Ejecuta función PostgreSQL `"controlempresa".fn_get_data_empresa()`
- Parseo de campo JSON string con `JsonParserMiddleware`

---

## Tipos de Cambios

- **🎉 Inicial** - Primera versión
- **✅ Agregado** - Nueva funcionalidad
- **🔄 Cambiado** - Cambios en funcionalidad existente
- **❌ Eliminado** - Funcionalidad eliminada
- **🐛 Corregido** - Corrección de bugs
- **🔒 Seguridad** - Cambios de seguridad

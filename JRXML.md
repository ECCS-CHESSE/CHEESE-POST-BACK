# Compilación de JRXML en SirioB

Este documento describe solo cómo se recibe, decodifica, compila y renderiza un JRXML.

## 1. Dependencia

`pom.xml`:

```xml
<dependency>
    <groupId>net.sf.jasperreports</groupId>
    <artifactId>jasperreports</artifactId>
    <version>6.21.4</version>
</dependency>
```

Clases de JasperReports utilizadas (`net.sf.jasperreports.engine`):

| Clase | Función |
|---|---|
| `JasperCompileManager` | Compila el XML JRXML a un objeto `JasperReport` |
| `JasperFillManager` | Ejecuta el query del reporte y llena los datos (`JasperPrint`) |
| `JasperExportManager` | Exporta el `JasperPrint` a PDF |

## 2. Qué es el JRXML y cómo se almacena

- El JRXML es un archivo XML que define el diseño del reporte, sus parámetros (`$P{...}`), el query SQL y los campos (`$F{...}`).
- En la base de datos **no se guarda como XML plano**. Se guarda en **Base64** (texto) en la tabla `eccs_reportes`, columna `reporte`.

```java
@Entity
@Table(name = "eccs_reportes")
public class EccsReporte {
    @Id
    private Integer id;

    @Column(name = "reporte")
    private String reporte;   // JRXML en Base64
}
```

## 3. Pipeline de deserialización y compilación

```
BD (String Base64)
   -> Base64.getDecoder().decode()        -> byte[] (XML del JRXML)
   -> ByteArrayInputStream                -> InputStream
   -> JasperCompileManager.compileReport  -> JasperReport
   -> JasperFillManager.fillReport        -> JasperPrint
   -> JasperExportManager.exportToPdf     -> byte[] (PDF)
```

Código real (`ResponseService.generatePdfFromJrxml`):

```java
// 1. Decodificar Base64 -> bytes del XML
byte[] jrxml = Base64.getDecoder().decode(JRXML);

// 2. Compilar el XML a JasperReport
JasperReport reportJrxml =
        JasperCompileManager.compileReport(new ByteArrayInputStream(jrxml));

// 3. Llenar el reporte con parámetros y conexión a la BD del cliente
try (Connection connection = dataSource.getConnection()) {
    JasperPrint jasperPrint =
            JasperFillManager.fillReport(reportJrxml, parameters, connection);

    // 4. Exportar a PDF
    return JasperExportManager.exportReportToPdf(jasperPrint);
}
```

### Detalle de cada paso

1. **Decodificar Base64.** `Base64.getDecoder().decode(String)` devuelve los bytes originales del XML. Si el texto tiene saltos de línea (MIME), usa `Base64.getMimeDecoder()`. Si el Base64 es inválido lanza `IllegalArgumentException`.
2. **Compilar.** `compileReport(InputStream)` parsea el XML y valida su estructura. Genera el `JasperReport` con las expresiones Java compiladas. Lanza `JRException` si el JRXML es inválido (XML mal formado, expresión incorrecta, parámetro no declarado).
3. **Llenar.** `fillReport(reporte, parameters, connection)`:
   - `parameters` es un `Map<String, Object>` cuyas llaves deben coincidir con los `<parameter name="...">` del JRXML.
   - El tipo de cada valor debe coincidir con `class=` del parámetro (por ejemplo `java.lang.Integer`, `java.util.Date`).
   - La `connection` ejecuta el `<queryString>` del JRXML.
4. **Exportar.** `exportReportToPdf(JasperPrint)` devuelve el PDF como `byte[]`.

## 4. Ejemplo mínimo de JRXML

```xml
<?xml version="1.0" encoding="UTF-8"?>
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports"
              name="factura" pageWidth="595" pageHeight="842"
              columnWidth="555" leftMargin="20" rightMargin="20"
              topMargin="20" bottomMargin="20">

    <parameter name="ID_FACTURA" class="java.lang.Integer"/>

    <queryString>
        <![CDATA[SELECT folio, total FROM facturas WHERE id = $P{ID_FACTURA}]]>
    </queryString>

    <field name="folio" class="java.lang.String"/>
    <field name="total" class="java.math.BigDecimal"/>

    <detail>
        <band height="30">
            <textField>
                <reportElement x="0" y="0" width="200" height="20"/>
                <textFieldExpression><![CDATA[$F{folio}]]></textFieldExpression>
            </textField>
            <textField>
                <reportElement x="200" y="0" width="100" height="20"/>
                <textFieldExpression><![CDATA[$F{total}]]></textFieldExpression>
            </textField>
        </band>
    </detail>
</jasperReport>
```

## 5. Cómo convertir un JRXML a Base64 para guardarlo

PowerShell:

```powershell
[Convert]::ToBase64String([IO.File]::ReadAllBytes("factura.jrxml"))
```

Java:

```java
String base64 = Base64.getEncoder()
        .encodeToString(Files.readAllBytes(Path.of("factura.jrxml")));
```

Para verificar el contenido guardado:

```powershell
[Text.Encoding]::UTF8.GetString([Convert]::FromBase64String($base64))
```

## 6. Errores comunes

| Error | Causa |
|---|---|
| `IllegalArgumentException: Illegal base64 character` | El texto de la BD tiene saltos de línea, espacios o no es Base64 |
| `JRException: Error loading object` / `SAXParseException` | El XML decodificado está mal formado o con BOM/encoding incorrecto |
| `JRValidationException` | Elemento fuera de límites de página o estructura inválida |
| `Parameter not found` / valor `null` en un `$P{}` | Falta la llave en el `Map parameters` |
| `ClassCastException` en fill | El tipo del valor no coincide con el `class` del parámetro |
| `Reporte no encontrado o vacío` | El `id` no existe o la columna `reporte` es null |

## 7. Recomendaciones

- **Caché del compilado.** `compileReport` es costoso. Guarda el `JasperReport` en un `ConcurrentHashMap` por `clientId + id`, y limpia la entrada cuando se actualice el reporte.
- **Seguridad.** Usa `$P{...}` en el SQL (se enlaza como parámetro). Evita `$P!{...}` con datos del usuario porque concatena texto y permite inyección SQL.
- **Origen confiable.** El JRXML puede ejecutar expresiones Java al compilarse. Solo debe poder escribirse en `eccs_reportes` desde fuentes de confianza.

package eccs.com.modules.facturacion.xml.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.services.ResponseService;
import eccs.com.modules.facturacion.xml.dto.FacturasTimbradasRequestDto;
import eccs.com.modules.facturacion.xml.dto.XmlRequestDto;
import eccs.com.modules.facturacion.xml.query.XmlQuery;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class XmlServiceImpl implements XmlService {

    private static final Logger logger = LoggerFactory.getLogger(XmlServiceImpl.class);
    private final XmlQuery xmlQuery;
    private final ObjectMapper objectMapper;
    private final ResponseService responseService;

    @Override
    public ResponseDto<Object> getFacturasTimbradas(FacturasTimbradasRequestDto request) {
        String resultado = xmlQuery.getFacturasTimbradas(
            request.getCorreo(), request.getRfc(), request.getClave_sucursal(), request.getFecha_timbrado());
        if (resultado == null || resultado.isBlank()) {
            throw new IllegalStateException("La funcion de facturas timbradas no devolvio una respuesta");
        }

        JsonNode datos;
        try {
            datos = objectMapper.readTree(resultado);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("La funcion de facturas timbradas devolvio un JSON invalido", e);
        }
        if (datos == null || datos.isNull() || (!datos.isObject() && !datos.isArray())
            || (datos.has("success") && !datos.path("success").isBoolean())) {
            throw new IllegalStateException("La funcion de facturas timbradas devolvio una respuesta invalida");
        }

        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(!datos.has("success") || datos.path("success").booleanValue());
        response.setTitulo("ECCS - FACTURACION - FACTURAS TIMBRADAS");
        response.setMensaje(datos.path("mensaje").isTextual()
            ? datos.path("mensaje").textValue()
            : (response.isSuccess() ? "CONSULTA DE MANERA EXITOSA" : "NO FUE POSIBLE CONSULTAR LAS FACTURAS"));
        response.setResponse(objectMapper.convertValue(datos, Object.class));
        return response;
    }

    @Override
    public ResponseDto<Object> getXml(XmlRequestDto request) {
        String resultado = xmlQuery.getXml(request.getId());
        if (resultado == null || resultado.isBlank()) {
            throw new IllegalStateException("La funcion de XML no devolvio una respuesta");
        }

        JsonNode datos;
        try {
            datos = objectMapper.readTree(resultado);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("La funcion de XML devolvio un JSON invalido", e);
        }
        if (!(datos instanceof ObjectNode respuesta) || !datos.path("success").isBoolean()
            || !datos.path("mensaje").isTextual()) {
            throw new IllegalStateException("La funcion de XML devolvio una respuesta invalida");
        }
        if (datos.path("success").booleanValue()
            && (!datos.path("xml").isTextual() || datos.path("xml").textValue().isBlank())) {
            throw new IllegalStateException("La funcion de XML no devolvio el XML");
        }
        if (respuesta.path("success").booleanValue()) {
            convertirPlantillaAPdf(respuesta, request.getId());
        }

        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(respuesta.path("success").booleanValue());
        response.setTitulo("ECCS - FACTURACION - XML");
        response.setMensaje(respuesta.path("mensaje").textValue());
        response.setResponse(objectMapper.convertValue(respuesta, Object.class));
        return response;
    }

    private void convertirPlantillaAPdf(ObjectNode respuesta, Long id) {
        JsonNode plantilla = respuesta.get("pdf");
        if (plantilla == null || plantilla.isNull()) {
            return;
        }
        if (!plantilla.isTextual() || plantilla.textValue().isBlank()) {
            throw new IllegalStateException("La funcion de XML devolvio un JRXML invalido");
        }

        int idComprobante;
        try {
            idComprobante = Math.toIntExact(id);
        } catch (ArithmeticException e) {
            throw new IllegalStateException("El id del comprobante excede el rango del parametro Integer del reporte", e);
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ID_COMPROBANTE", idComprobante);
        try {
            byte[] pdf = responseService.generatePdfFromJrxml(plantilla.textValue(), parametros);
            respuesta.put("pdf", Base64.getEncoder().encodeToString(pdf));
        } catch (JRException | SQLException | IllegalArgumentException e) {
            logger.error("Error al generar el PDF de la factura", e);
            throw new IllegalStateException("No fue posible generar el PDF de la factura", e);
        }
    }
}

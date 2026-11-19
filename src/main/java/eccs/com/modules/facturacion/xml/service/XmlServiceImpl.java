package eccs.com.modules.facturacion.xml.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.facturacion.xml.dto.FacturasTimbradasRequestDto;
import eccs.com.modules.facturacion.xml.dto.XmlRequestDto;
import eccs.com.modules.facturacion.xml.query.XmlQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class XmlServiceImpl implements XmlService {

    private final XmlQuery xmlQuery;
    private final ObjectMapper objectMapper;

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
        if (datos == null || !datos.isObject() || !datos.path("success").isBoolean()
            || !datos.path("mensaje").isTextual()) {
            throw new IllegalStateException("La funcion de XML devolvio una respuesta invalida");
        }
        if (datos.path("success").booleanValue()
            && (!datos.path("xml").isTextual() || datos.path("xml").textValue().isBlank())) {
            throw new IllegalStateException("La funcion de XML no devolvio el XML");
        }

        ResponseDto<Object> response = new ResponseDto<>();
        response.setSuccess(datos.path("success").booleanValue());
        response.setTitulo("ECCS - FACTURACION - XML");
        response.setMensaje(datos.path("mensaje").textValue());
        response.setResponse(objectMapper.convertValue(datos, Object.class));
        return response;
    }
}

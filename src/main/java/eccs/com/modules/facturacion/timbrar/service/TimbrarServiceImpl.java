package eccs.com.modules.facturacion.timbrar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eccs.com.core.services.HttpService;
import eccs.com.modules.controlventas.comprobanteventa.query.ComprobanteVentaQuery;
import eccs.com.modules.facturacion.factura.query.FacturaQuery;
import eccs.com.modules.facturacion.timbrar.dto.TimbrarRequestDto;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Service
public class TimbrarServiceImpl implements TimbrarService {

    private final HttpService httpService;
    private final ComprobanteVentaQuery comprobanteVentaQuery;
    private final FacturaQuery facturaQuery;
    private final ObjectMapper objectMapper;

    public TimbrarServiceImpl(
        HttpService httpService,
        ComprobanteVentaQuery comprobanteVentaQuery,
        FacturaQuery facturaQuery,
        ObjectMapper objectMapper
    ) {
        this.httpService = httpService;
        this.comprobanteVentaQuery = comprobanteVentaQuery;
        this.facturaQuery = facturaQuery;
        this.objectMapper = objectMapper;
    }



    @Override
    public ResponseEntity<String> test(TimbrarRequestDto request) {
        JsonNode datos = obtenerDatosTimbrado(request.getId_comprobante_venta());
        String rigelUrl = campoRequerido(datos, "url_rigel");
        Map<String, String> rigelRequest = Map.of(
            "api_key", campoRequerido(datos, "key"),
            "usuario", campoRequerido(datos, "usuario"),
            "jsonB64", campoRequerido(datos, "factura")
        );
        try {
            ResponseEntity<String> rigelResponse = httpService.post(rigelUrl, rigelRequest, String.class);
            actualizarComprobante(
                request.getId_comprobante_venta(), rigelResponse.getBody());
            return rigelResponse;
        } catch (RestClientResponseException e) {
            throw new IllegalStateException("Rigel respondio con HTTP " + e.getStatusCode().value(), e);
        } catch (RestClientException e) {
            throw new IllegalStateException("No fue posible completar la peticion a Rigel", e);
        }
    }

    // validaciones por metodos privados por separado.... 
    private JsonNode obtenerDatosTimbrado(Long idComprobante) {
        String resultado = facturaQuery.getDatosTimbrado(idComprobante);
        if (resultado == null || resultado.isBlank()) {
            throw new IllegalStateException("La funcion de timbrado no devolvio datos para el comprobante " + idComprobante);
        }
        try {
            JsonNode datos = objectMapper.readTree(resultado);
            if (datos == null || !datos.isObject() || !datos.path("success").isBoolean()) {
                throw new IllegalStateException("La funcion de timbrado devolvio una respuesta invalida");
            }
            if (!datos.path("success").booleanValue()) {
                throw new IllegalStateException("La funcion de timbrado rechazo el comprobante: "
                    + datos.path("mensaje").asText("sin detalle"));
            }
            return datos;
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("La funcion de timbrado devolvio un JSON invalido", e);
        }
    }

    private String campoRequerido(JsonNode datos, String campo) {
        JsonNode valor = datos.path(campo);
        if (!valor.isTextual() || valor.textValue().isBlank()) {
            throw new IllegalStateException("La funcion de timbrado no devolvio el campo " + campo);
        }
        return valor.textValue();
    }

    private void actualizarComprobante(Long idComprobante, String body) {
        JsonNode root;
        try {
            root = objectMapper.readTree(body);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Rigel devolvio una respuesta JSON invalida", e);
        }

        JsonNode timbrado = root == null ? null : root.path("response").path("timbrado");
        if (root == null || !root.path("success").asBoolean(false)
            || !"200".equals(timbrado.path("code").asText())) {
            throw new IllegalStateException("Rigel no confirmo el timbrado");
        }

        Element timbreFiscal = obtenerTimbreFiscal(timbrado.path("data").path("XML").asText());
        String uuid = atributoRequerido(timbreFiscal, "UUID");
        LocalDateTime fechaTimbrado = LocalDateTime.parse(atributoRequerido(timbreFiscal, "FechaTimbrado"));
        String selloCfd = atributoRequerido(timbreFiscal, "SelloCFD");
        String selloSat = atributoRequerido(timbreFiscal, "SelloSAT");
        String noCertificadoSat = atributoRequerido(timbreFiscal, "NoCertificadoSAT");

        int filasActualizadas = comprobanteVentaQuery.actualizarDatosTimbrado(
            idComprobante, uuid, fechaTimbrado, selloCfd, selloSat, noCertificadoSat
        );
        if (filasActualizadas != 1) {
            throw new IllegalStateException("No se encontró el comprobante de venta " + idComprobante);
        }
    }

    private Element obtenerTimbreFiscal(String xml) {
        if (xml == null || xml.isBlank()) {
            throw new IllegalStateException("Rigel no devolvio el XML del comprobante timbrado");
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

            NodeList timbres = factory.newDocumentBuilder()
                .parse(new InputSource(new StringReader(xml)))
                .getElementsByTagNameNS("*", "TimbreFiscalDigital");
            if (timbres.getLength() == 0 || !(timbres.item(0) instanceof Element timbreFiscal)) {
                throw new IllegalStateException("El XML de Rigel no contiene TimbreFiscalDigital");
            }
            return timbreFiscal;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new IllegalStateException("No fue posible leer el XML timbrado de Rigel", e);
        }
    }

    private String atributoRequerido(Element element, String attribute) {
        String value = element.getAttribute(attribute);
        if (value.isBlank()) {
            throw new IllegalStateException("El TimbreFiscalDigital no contiene el atributo " + attribute);
        }
        return value;
    }
}

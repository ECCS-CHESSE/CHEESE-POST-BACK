package eccs.com.modules.jrxml.factura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.services.ResponseService;
import eccs.com.modules.facturacion.xml.query.XmlQuery;
import eccs.com.modules.jrxml.factura.dto.FacturaRequestDto;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("jrxmlFacturaService")
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    private static final String TITULO = "ECCS - JRXML - FACTURA";

    private final XmlQuery xmlQuery;
    private final ObjectMapper objectMapper;
    private final ResponseService responseService;

    @Override
    public ResponseDto<Object> factura(FacturaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        response.setTitulo(TITULO);
        try {
            String resultado = xmlQuery.getXml(request.getId_comprobante().longValue());
            if (resultado == null || resultado.isBlank()) {
                throw new IllegalStateException("La funcion de XML no devolvio una respuesta");
            }

            JsonNode datos = objectMapper.readTree(resultado);
            if (!datos.path("success").asBoolean(false)) {
                response.setSuccess(false);
                response.setMensaje(datos.path("mensaje").asText("No se encontro el comprobante"));
                response.setResponse(null);
                return response;
            }

            String jrxml = datos.path("pdf").asText("");
            if (jrxml.isBlank()) {
                throw new IllegalStateException("El comprobante no tiene JRXML");
            }

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ID_COMPROBANTE", request.getId_comprobante());

            byte[] pdf = responseService.generatePdfFromJrxml(jrxml, parameters);

            Map<String, Object> data = new HashMap<>();
            data.put("pdf", Base64.getEncoder().encodeToString(pdf));

            response.setSuccess(true);
            response.setMensaje("PDF GENERADO DE MANERA EXITOSA");
            response.setResponse(data);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}

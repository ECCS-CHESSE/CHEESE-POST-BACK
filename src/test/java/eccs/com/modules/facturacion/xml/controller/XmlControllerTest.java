package eccs.com.modules.facturacion.xml.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eccs.com.core.config.GlobalExceptionHandler;
import eccs.com.modules.facturacion.xml.query.XmlQuery;
import eccs.com.modules.facturacion.xml.service.XmlServiceImpl;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class XmlControllerTest {

    private MockMvc mockMvc;
    private XmlQuery xmlQuery;

    @BeforeEach
    void setUp() {
        xmlQuery = mock(XmlQuery.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new XmlController(new XmlServiceImpl(xmlQuery, new ObjectMapper())))
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    @Test
    void devuelveElXmlYLosDatosDeLaFuncion() throws Exception {
        long id = 3000000000L;
        String xml = "<?xml version=\"1.0\"?><comprobante>España</comprobante>";
        String resultado = "{\"success\":true,\"mensaje\":\"XML recuperado correctamente\",\"xml\":"
            + new ObjectMapper().writeValueAsString(xml) + ",\"pdf\":null}";
        when(xmlQuery.getXml(id)).thenReturn(resultado);

        mockMvc.perform(post("/facturacion/xml")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3000000000}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.mensaje").value("XML recuperado correctamente"))
            .andExpect(jsonPath("$.response.xml").value(xml))
            .andExpect(jsonPath("$.response.success").value(true))
            .andExpect(jsonPath("$.response.pdf").isEmpty());

        verify(xmlQuery).getXml(id);
    }

    @Test
    void requiereId() throws Exception {
        mockMvc.perform(post("/facturacion/xml")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.response.id").exists());
        verifyNoInteractions(xmlQuery);
    }

    @Test
    void reflejaElErrorDeLaFuncion() throws Exception {
        when(xmlQuery.getXml(468L))
            .thenReturn("{\"success\":false,\"mensaje\":\"No se encontro el comprobante\",\"xml\":null,\"pdf\":null}");

        mockMvc.perform(post("/facturacion/xml")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":468}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.mensaje").value("No se encontro el comprobante"))
            .andExpect(jsonPath("$.response.success").value(false));
    }

    @Test
    void noDevuelveExitoSiFaltaElXml() throws Exception {
        when(xmlQuery.getXml(468L))
            .thenReturn("{\"success\":true,\"mensaje\":\"XML recuperado correctamente\",\"xml\":null}");

        mockMvc.perform(post("/facturacion/xml")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":468}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void consultaFacturasTimbradasConLosCuatroParametros() throws Exception {
        when(xmlQuery.getFacturasTimbradas("cliente@example.test", "XAXX010101000", "MATRIZ",
            LocalDate.of(2026, 11, 18)))
            .thenReturn("{\"success\":true,\"mensaje\":\"Consulta exitosa\",\"facturas\":[{\"folio\":14}]}");

        mockMvc.perform(post("/facturacion/facturas/timbradas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"cliente@example.test\",\"rfc\":\"XAXX010101000\","
                    + "\"clave_sucursal\":\"MATRIZ\",\"fecha_timbrado\":\"2026-11-18\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.mensaje").value("Consulta exitosa"))
            .andExpect(jsonPath("$.response.facturas[0].folio").value(14));

        verify(xmlQuery).getFacturasTimbradas("cliente@example.test", "XAXX010101000", "MATRIZ",
            LocalDate.of(2026, 11, 18));
    }

    @Test
    void rechazaFiltrosFaltantesOFechaInvalida() throws Exception {
        mockMvc.perform(post("/facturacion/facturas/timbradas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"\",\"rfc\":\"\",\"clave_sucursal\":\"\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.response.fecha_timbrado").exists());
        mockMvc.perform(post("/facturacion/facturas/timbradas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"x\",\"rfc\":\"x\",\"clave_sucursal\":\"x\","
                    + "\"fecha_timbrado\":\"fecha-invalida\"}"))
            .andExpect(status().isBadRequest());
        verifyNoInteractions(xmlQuery);
    }

    @Test
    void reflejaElRechazoDeLaFuncionDeFacturas() throws Exception {
        when(xmlQuery.getFacturasTimbradas("cliente@example.test", "XAXX010101000", "MATRIZ",
            LocalDate.of(2026, 11, 18)))
            .thenReturn("{\"success\":false,\"mensaje\":\"Sin acceso\"}");

        mockMvc.perform(post("/facturacion/facturas/timbradas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"cliente@example.test\",\"rfc\":\"XAXX010101000\","
                    + "\"clave_sucursal\":\"MATRIZ\",\"fecha_timbrado\":\"2026-11-18\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.mensaje").value("Sin acceso"))
            .andExpect(jsonPath("$.response.success").value(false));
    }

    @Test
    void noDevuelveExitoSiLaFuncionDevuelveJsonInvalido() throws Exception {
        when(xmlQuery.getFacturasTimbradas("cliente@example.test", "XAXX010101000", "MATRIZ",
            LocalDate.of(2026, 11, 18))).thenReturn("{invalido");

        mockMvc.perform(post("/facturacion/facturas/timbradas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"cliente@example.test\",\"rfc\":\"XAXX010101000\","
                    + "\"clave_sucursal\":\"MATRIZ\",\"fecha_timbrado\":\"2026-11-18\"}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.success").value(false));
    }
}

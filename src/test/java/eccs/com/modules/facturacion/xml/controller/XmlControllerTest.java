package eccs.com.modules.facturacion.xml.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;
import eccs.com.core.config.GlobalExceptionHandler;
import eccs.com.core.services.ResponseService;
import eccs.com.modules.facturacion.xml.query.XmlQuery;
import eccs.com.modules.facturacion.xml.service.XmlServiceImpl;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Base64;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void setUp() throws Exception {
        xmlQuery = mock(XmlQuery.class);
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(mock(Connection.class));
        ResponseService responseService = new ResponseService();
        ReflectionTestUtils.setField(responseService, "dataSource", dataSource);
        mockMvc = MockMvcBuilders.standaloneSetup(new XmlController(new XmlServiceImpl(xmlQuery, new ObjectMapper(), responseService)))
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    @Test
    void compilaElJrxmlYDevuelveUnPdfEnBase64() throws Exception {
        String jrxml = """
            <?xml version="1.0" encoding="UTF-8"?>
            <jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports"
                xmlns:jr="http://jasperreports.sourceforge.net/jasperreports/components"
                name="factura" pageWidth="595" pageHeight="842" columnWidth="555"
                leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20"
                whenNoDataType="AllSectionsNoDetail">
                <parameter name="ID_COMPROBANTE" class="java.lang.Integer"/>
                <title>
                    <band height="90">
                        <textField>
                            <reportElement x="0" y="0" width="200" height="20"/>
                            <textFieldExpression><![CDATA[$P{ID_COMPROBANTE}]]></textFieldExpression>
                        </textField>
                        <componentElement>
                            <reportElement x="0" y="25" width="60" height="60"/>
                            <jr:QRCode>
                                <jr:codeExpression><![CDATA["factura-468"]]></jr:codeExpression>
                            </jr:QRCode>
                        </componentElement>
                        <componentElement>
                            <reportElement x="80" y="25" width="150" height="50"/>
                            <jr:Code128>
                                <jr:codeExpression><![CDATA["468"]]></jr:codeExpression>
                            </jr:Code128>
                        </componentElement>
                    </band>
                </title>
            </jasperReport>
            """;
        String plantilla = Base64.getEncoder().encodeToString(jrxml.getBytes(StandardCharsets.UTF_8));
        when(xmlQuery.getXml(468L)).thenReturn(
            "{\"success\":true,\"mensaje\":\"Consulta exitosa\",\"xml\":\"<comprobante/>\",\"pdf\":\""
                + plantilla + "\"}");

        MvcResult resultado = mockMvc.perform(post("/facturacion/xml")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":468}"))
            .andReturn();
        if (resultado.getResolvedException() != null) {
            throw new AssertionError("Error al generar el PDF", resultado.getResolvedException());
        }
        assertEquals(200, resultado.getResponse().getStatus());

        var response = new ObjectMapper().readTree(resultado.getResponse().getContentAsString());
        assertTrue(response.path("success").booleanValue());
        assertEquals("<comprobante/>", response.path("response").path("xml").textValue());
        String pdfBase64 = response.path("response").path("pdf").textValue();
        byte[] pdf = Base64.getDecoder().decode(pdfBase64);
        String contenido = new String(pdf, StandardCharsets.ISO_8859_1);
        assertTrue(contenido.startsWith("%PDF-"));
        assertTrue(contenido.trim().endsWith("%%EOF"));
        PdfReader reader = new PdfReader(pdf);
        try {
            assertEquals(1, reader.getNumberOfPages());
            assertTrue(new PdfTextExtractor(reader).getTextFromPage(1).contains("468"));
        } finally {
            reader.close();
        }
    }

    @Test
    void informaErrorSiElJrxmlEsInvalido() throws Exception {
        when(xmlQuery.getXml(468L)).thenReturn(
            "{\"success\":true,\"mensaje\":\"Consulta exitosa\",\"xml\":\"<comprobante/>\",\"pdf\":\"invalido\"}");

        mockMvc.perform(post("/facturacion/xml")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":468}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void informaErrorSiElIdNoCabeEnElParametroIntegerDelReporte() throws Exception {
        when(xmlQuery.getXml(3000000000L)).thenReturn(
            "{\"success\":true,\"mensaje\":\"Consulta exitosa\",\"xml\":\"<comprobante/>\",\"pdf\":\"eA==\"}");

        mockMvc.perform(post("/facturacion/xml")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3000000000}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.success").value(false));
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

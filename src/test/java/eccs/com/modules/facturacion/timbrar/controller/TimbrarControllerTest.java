package eccs.com.modules.facturacion.timbrar.controller;

import eccs.com.core.config.GlobalExceptionHandler;
import eccs.com.core.services.HttpService;
import eccs.com.modules.controlventas.comprobanteventa.query.ComprobanteVentaQuery;
import eccs.com.modules.facturacion.factura.query.FacturaQuery;
import eccs.com.modules.facturacion.timbrar.service.TimbrarServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TimbrarControllerTest {

    private MockMvc mockMvc;
    private HttpService httpService;
    private ComprobanteVentaQuery comprobanteVentaQuery;
    private FacturaQuery facturaQuery;
    private static final String RIGEL_URL = "http://localhost:3000/facturacion/timbrarTest";
    private static final long ID_COMPROBANTE = 73L;
    private static final Map<String, String> RIGEL_REQUEST = Map.of(
        "api_key", "clave-prueba", "usuario", "usuario-prueba", "jsonB64", "e30=");
    private static final String DATOS_TIMBRADO = "{\"success\":true,\"usuario\":\"usuario-prueba\","
        + "\"key\":\"clave-prueba\",\"url_rigel\":\"" + RIGEL_URL + "\",\"factura\":\"e30=\"}";

    @BeforeEach
    void setUp() {
        httpService = mock(HttpService.class);
        comprobanteVentaQuery = mock(ComprobanteVentaQuery.class);
        facturaQuery = mock(FacturaQuery.class);
        when(facturaQuery.getDatosTimbrado(ID_COMPROBANTE)).thenReturn(DATOS_TIMBRADO);
        mockMvc = MockMvcBuilders.standaloneSetup(new TimbrarController(
                new TimbrarServiceImpl(httpService, comprobanteVentaQuery, facturaQuery, new ObjectMapper())))
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    @Test
    void enviaElDtoARigelActualizaElComprobanteYDevuelveSuRespuesta() throws Exception {
        String xml = "<cfdi:Comprobante xmlns:cfdi=\"http://www.sat.gob.mx/cfd/4\" "
            + "xmlns:tfd=\"http://www.sat.gob.mx/TimbreFiscalDigital\">"
            + "<cfdi:Complemento><tfd:TimbreFiscalDigital UUID=\"187f825a-465c-4d52-8550-80befc08c0a3\" "
            + "FechaTimbrado=\"2026-11-17T16:06:44\" SelloCFD=\"sello-cfd\" SelloSAT=\"sello-sat\" "
            + "NoCertificadoSAT=\"30001000000500003456\"/></cfdi:Complemento></cfdi:Comprobante>";
        String respuestaRigel = new ObjectMapper().writeValueAsString(Map.of(
            "success", true,
            "response", Map.of("timbrado", Map.of(
                "code", "200",
                "data", Map.of("XML", xml)
            ))
        ));
        when(httpService.post(eq(RIGEL_URL), eq(RIGEL_REQUEST), eq(String.class)))
            .thenReturn(ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(respuestaRigel));
        when(comprobanteVentaQuery.actualizarDatosTimbrado(
            eq(ID_COMPROBANTE), eq("187f825a-465c-4d52-8550-80befc08c0a3"),
            eq(LocalDateTime.parse("2026-11-17T16:06:44")), eq("sello-cfd"), eq("sello-sat"),
            eq("30001000000500003456"), eq(xml)
        )).thenReturn(1);

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_comprobante_venta\":73}"))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(respuestaRigel));

        verify(facturaQuery).getDatosTimbrado(ID_COMPROBANTE);
        verify(httpService).post(RIGEL_URL, RIGEL_REQUEST, String.class);
        verify(comprobanteVentaQuery).actualizarDatosTimbrado(
            ID_COMPROBANTE, "187f825a-465c-4d52-8550-80befc08c0a3",
            LocalDateTime.parse("2026-11-17T16:06:44"), "sello-cfd", "sello-sat", "30001000000500003456", xml
        );
    }

    @Test
    void noActualizaElComprobanteSiRigelNoDevuelveElXml() throws Exception {
        when(httpService.post(eq(RIGEL_URL), eq(RIGEL_REQUEST), eq(String.class)))
            .thenReturn(ResponseEntity.ok("{\"success\":true,\"response\":{\"timbrado\":{\"code\":\"200\"}}}"));

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_comprobante_venta\":73}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.mensaje").value("Error interno: Rigel no devolvio el XML del comprobante timbrado"));

        verifyNoInteractions(comprobanteVentaQuery);
    }

    @Test
    void rechazaCamposFaltantesOVacios() throws Exception {
        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.response.id_comprobante_venta").exists());
        verifyNoInteractions(httpService);
    }

    @Test
    void devuelveErrorSiRigelNoResponde() throws Exception {
        doThrow(new ResourceAccessException("Rigel no disponible"))
            .when(httpService).post(eq(RIGEL_URL), eq(RIGEL_REQUEST), eq(String.class));

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_comprobante_venta\":73}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.mensaje").value("Error interno: No fue posible completar la peticion a Rigel"));
    }

    @Test
    void devuelveErrorSiRigelRechazaLaPeticion() throws Exception {
        doThrow(HttpClientErrorException.create(HttpStatus.BAD_REQUEST, "Bad Request",
            org.springframework.http.HttpHeaders.EMPTY, "Factura invalida".getBytes(java.nio.charset.StandardCharsets.UTF_8),
            java.nio.charset.StandardCharsets.UTF_8))
            .when(httpService).post(eq(RIGEL_URL), eq(RIGEL_REQUEST), eq(String.class));

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_comprobante_venta\":73}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.mensaje").value("Rigel rechazo la peticion con HTTP 400"))
            .andExpect(jsonPath("$.response").value("Factura invalida"));
        verifyNoInteractions(comprobanteVentaQuery);
    }

    @Test
    void devuelveJsonDeErrorDeRigelComoObjeto() throws Exception {
        String respuestaRigel = "{\"success\":false,\"titulo\":\"Solicitud invalida\","
            + "\"mensaje\":\"El comprobante contiene un timbre previo.\",\"response\":null}";
        doThrow(HttpClientErrorException.create(HttpStatus.BAD_REQUEST, "Bad Request",
            org.springframework.http.HttpHeaders.EMPTY, respuestaRigel.getBytes(java.nio.charset.StandardCharsets.UTF_8),
            java.nio.charset.StandardCharsets.UTF_8))
            .when(httpService).post(eq(RIGEL_URL), eq(RIGEL_REQUEST), eq(String.class));

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_comprobante_venta\":73}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.response.success").value(false))
            .andExpect(jsonPath("$.response.titulo").value("Solicitud invalida"))
            .andExpect(jsonPath("$.response.mensaje").value("El comprobante contiene un timbre previo."))
            .andExpect(jsonPath("$.response.response").isEmpty());

        verifyNoInteractions(comprobanteVentaQuery);
    }

    @Test
    void noActualizaElComprobanteSiRigelNoConfirmaElTimbrado() throws Exception {
        when(httpService.post(eq(RIGEL_URL), eq(RIGEL_REQUEST), eq(String.class)))
            .thenReturn(ResponseEntity.ok("{\"success\":false,\"mensaje\":\"Factura invalida\","
                + "\"response\":{\"detalle\":\"RFC invalido\"}}"));

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_comprobante_venta\":73}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.mensaje").value("Factura invalida"))
            .andExpect(jsonPath("$.response.success").value(false))
            .andExpect(jsonPath("$.response.response.detalle").value("RFC invalido"));

        verifyNoInteractions(comprobanteVentaQuery);
    }

    @Test
    void devuelveDetalleCuandoTimbradoTieneCodigoDeError() throws Exception {
        when(httpService.post(eq(RIGEL_URL), eq(RIGEL_REQUEST), eq(String.class)))
            .thenReturn(ResponseEntity.ok("{\"success\":true,\"mensaje\":\"Operacion realizada\","
                + "\"response\":{\"timbrado\":{\"code\":\"400\",\"message\":\"RFC invalido\"}}}"));

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_comprobante_venta\":73}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.mensaje").value("RFC invalido"))
            .andExpect(jsonPath("$.response.response.timbrado.code").value("400"))
            .andExpect(jsonPath("$.response.response.timbrado.message").value("RFC invalido"));

        verifyNoInteractions(comprobanteVentaQuery);
    }

    @Test
    void devuelveError400SiRigelNoConfirmaElTimbrado() throws Exception {
        when(httpService.post(eq(RIGEL_URL), eq(RIGEL_REQUEST), eq(String.class)))
            .thenReturn(ResponseEntity.ok("{\"success\":true,\"response\":{}}"));

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_comprobante_venta\":73}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.mensaje").value("Rigel no confirmo el timbrado"))
            .andExpect(jsonPath("$.response.success").value(true));

        verifyNoInteractions(comprobanteVentaQuery);
    }

    @Test
    void noEnviaPeticionCuandoLaFuncionRechazaElComprobante() throws Exception {
        when(facturaQuery.getDatosTimbrado(ID_COMPROBANTE))
            .thenReturn("{\"success\":false,\"mensaje\":\"Comprobante inexistente\"}");

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_comprobante_venta\":73}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.mensaje").value(
                "Error interno: La funcion de timbrado rechazo el comprobante: Comprobante inexistente"));

        verifyNoInteractions(httpService, comprobanteVentaQuery);
    }

    @Test
    void noEnviaPeticionSiFaltanDatosDeLaFuncion() throws Exception {
        when(facturaQuery.getDatosTimbrado(ID_COMPROBANTE))
            .thenReturn("{\"success\":true,\"usuario\":\"usuario-prueba\",\"key\":\"clave-prueba\","
                + "\"url_rigel\":\"" + RIGEL_URL + "\"}");

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_comprobante_venta\":73}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.mensaje").value("Error interno: La funcion de timbrado no devolvio el campo factura"));

        verifyNoInteractions(httpService, comprobanteVentaQuery);
    }
}

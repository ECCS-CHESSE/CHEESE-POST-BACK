package eccs.com.modules.facturacion.timbrar.controller;

import eccs.com.core.config.GlobalExceptionHandler;
import eccs.com.core.services.HttpService;
import eccs.com.modules.facturacion.timbrar.dto.TimbrarRequestDto;
import eccs.com.modules.facturacion.timbrar.service.TimbrarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TimbrarControllerTest {

    private MockMvc mockMvc;
    private HttpService httpService;
    private static final String RIGEL_URL = "http://localhost:3000/facturacion/timbrarTest";

    @BeforeEach
    void setUp() {
        httpService = mock(HttpService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new TimbrarController(new TimbrarServiceImpl(httpService, RIGEL_URL)))
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    @Test
    void enviaElDtoARigelYDevuelveSuRespuesta() throws Exception {
        String respuestaRigel = "{\"folio\":\"123\",\"resultado\":\"timbrado\"}";
        when(httpService.post(eq(RIGEL_URL), any(TimbrarRequestDto.class), eq(String.class)))
            .thenReturn(ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(respuestaRigel));

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"api_key\":\"clave-prueba\",\"usuario\":\"usuario-prueba\",\"jsonB64\":\"e30=\"}"))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(respuestaRigel));

        TimbrarRequestDto expected = new TimbrarRequestDto();
        expected.setApi_key("clave-prueba");
        expected.setUsuario("usuario-prueba");
        expected.setJsonB64("e30=");
        verify(httpService).post(RIGEL_URL, expected, String.class);
    }

    @Test
    void rechazaCamposFaltantesOVacios() throws Exception {
        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"api_key\":\" \",\"usuario\":\"\",\"jsonB64\":null}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.response.api_key").exists())
            .andExpect(jsonPath("$.response.usuario").exists())
            .andExpect(jsonPath("$.response.jsonB64").exists());
    }

    @Test
    void devuelveErrorSiRigelNoResponde() throws Exception {
        doThrow(new ResourceAccessException("Rigel no disponible"))
            .when(httpService).post(eq(RIGEL_URL), any(TimbrarRequestDto.class), eq(String.class));

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"api_key\":\"clave-prueba\",\"usuario\":\"usuario-prueba\",\"jsonB64\":\"e30=\"}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.mensaje").value("Error interno: No fue posible completar la peticion a Rigel"));
    }

    @Test
    void devuelveErrorSiRigelRechazaLaPeticion() throws Exception {
        doThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST))
            .when(httpService).post(eq(RIGEL_URL), any(TimbrarRequestDto.class), eq(String.class));

        mockMvc.perform(post("/facturacion/timbrar/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"api_key\":\"clave-prueba\",\"usuario\":\"usuario-prueba\",\"jsonB64\":\"e30=\"}"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.mensaje").value("Error interno: Rigel respondio con HTTP 400"));
    }
}

package eccs.com.core.services;

import java.util.Map;

import eccs.com.modules.facturacion.timbrar.dto.TimbrarRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class HttpServiceTest {

    private HttpService service;
    private MockRestServiceServer server;

    @BeforeEach
    void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        server = MockRestServiceServer.bindTo(restTemplate).build();
        service = new HttpService(restTemplate);
    }

    @Test
    void enviaBodyJsonYDevuelveRespuestaTipada() {
        server.expect(once(), requestTo("https://example.test/timbrar"))
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{\"usuario\":\"prueba\",\"jsonB64\":\"e30=\"}"))
            .andRespond(withSuccess("{\"resultado\":\"recibido\"}", MediaType.APPLICATION_JSON));

        ResponseEntity<Respuesta> response = service.post("https://example.test/timbrar",
            Map.of("usuario", "prueba", "jsonB64", "e30="), Respuesta.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("recibido", response.getBody().resultado());
        server.verify();
    }

    @Test
    void enviaHeadersSinModificarLosOriginales() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("token-prueba");
        server.expect(requestTo("https://example.test/timbrar"))
            .andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer token-prueba"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andRespond(withSuccess());

        service.post("https://example.test/timbrar", Map.of("usuario", "prueba"), headers, String.class);

        assertNull(headers.getContentType());
        server.verify();
    }

    @Test
    void serializaElDtoDeTimbradoConSusNombresOriginales() {
        TimbrarRequestDto request = new TimbrarRequestDto();
        request.setApi_key("clave-prueba");
        request.setUsuario("usuario-prueba");
        request.setJsonB64("e30=");
        server.expect(requestTo("http://localhost:3000/facturacion/timbrarTest"))
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{\"api_key\":\"clave-prueba\",\"usuario\":\"usuario-prueba\",\"jsonB64\":\"e30=\"}"))
            .andRespond(withSuccess("ok", MediaType.TEXT_PLAIN));

        service.post("http://localhost:3000/facturacion/timbrarTest", request, String.class);
        server.verify();
    }

    @Test
    void propagaErroresHttp() {
        server.expect(requestTo("https://example.test/timbrar"))
            .andRespond(withStatus(HttpStatus.BAD_REQUEST).body("Solicitud invalida"));

        HttpClientErrorException error = assertThrows(HttpClientErrorException.class,
            () -> service.post("https://example.test/timbrar", Map.of(), String.class));

        assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
        assertEquals("Solicitud invalida", error.getResponseBodyAsString());
        server.verify();
    }

    record Respuesta(String resultado) {
    }
}

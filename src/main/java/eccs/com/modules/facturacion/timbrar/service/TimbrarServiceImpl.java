package eccs.com.modules.facturacion.timbrar.service;

import eccs.com.core.services.HttpService;
import eccs.com.modules.facturacion.timbrar.dto.TimbrarRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

@Service
public class TimbrarServiceImpl implements TimbrarService {

    private final HttpService httpService;
    private final String rigelUrl;

    public TimbrarServiceImpl(HttpService httpService, @Value("${rigel.timbrar-test-url}") String rigelUrl) {
        this.httpService = httpService;
        this.rigelUrl = rigelUrl;
    }

    @Override
    public ResponseEntity<String> test(TimbrarRequestDto request) {
        try {
            return httpService.post(rigelUrl, request, String.class);
        } catch (RestClientResponseException e) {
            throw new IllegalStateException("Rigel respondio con HTTP " + e.getStatusCode().value(), e);
        } catch (RestClientException e) {
            throw new IllegalStateException("No fue posible completar la peticion a Rigel", e);
        }
    }
}

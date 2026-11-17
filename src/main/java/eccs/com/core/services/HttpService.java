package eccs.com.core.services;

import java.time.Duration;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpService {

    private final RestTemplate restTemplate;

    public HttpService() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(10));
        factory.setReadTimeout(Duration.ofSeconds(30));
        this.restTemplate = new RestTemplate(factory);
    }

    HttpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> post(String url, Object body, Class<T> responseType) {
        return post(url, body, new HttpHeaders(), responseType);
    }

    public <T> ResponseEntity<T> post(String url, Object body, HttpHeaders headers, Class<T> responseType) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.putAll(headers);
        if (requestHeaders.getContentType() == null) {
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        }
        return restTemplate.postForEntity(url, new HttpEntity<>(body, requestHeaders), responseType);
    }
}

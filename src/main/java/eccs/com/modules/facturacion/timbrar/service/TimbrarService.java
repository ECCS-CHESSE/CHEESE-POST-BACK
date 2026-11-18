package eccs.com.modules.facturacion.timbrar.service;

import eccs.com.modules.facturacion.timbrar.dto.TimbrarRequestDto;
import org.springframework.http.ResponseEntity;

public interface TimbrarService {
    public ResponseEntity<String> test(TimbrarRequestDto request);
}

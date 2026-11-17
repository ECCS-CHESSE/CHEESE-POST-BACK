package eccs.com.modules.facturacion.timbrar.controller;

import eccs.com.modules.facturacion.timbrar.dto.TimbrarRequestDto;
import eccs.com.modules.facturacion.timbrar.service.TimbrarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facturacion")
@RequiredArgsConstructor
public class TimbrarController {

    private final TimbrarService timbrarService;

    @PostMapping("/timbrar/test")
    public ResponseEntity<String> test(@Valid @RequestBody TimbrarRequestDto request) {
        return timbrarService.test(request);
    }

    


}

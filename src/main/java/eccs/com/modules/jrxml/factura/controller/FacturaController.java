package eccs.com.modules.jrxml.factura.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.jrxml.factura.dto.FacturaRequestDto;
import eccs.com.modules.jrxml.factura.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("jrxmlFacturaController")
@RequestMapping("/jrxml")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(@Qualifier("jrxmlFacturaService") FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping("/factura")
    public ResponseEntity<ResponseDto<Object>> factura(@Valid @RequestBody FacturaRequestDto request) {
        return ResponseEntity.ok(facturaService.factura(request));
    }
}

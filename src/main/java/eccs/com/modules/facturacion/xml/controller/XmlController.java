package eccs.com.modules.facturacion.xml.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.facturacion.xml.dto.FacturasTimbradasRequestDto;
import eccs.com.modules.facturacion.xml.dto.XmlRequestDto;
import eccs.com.modules.facturacion.xml.service.XmlService;
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
public class XmlController {

    private final XmlService xmlService;

    @PostMapping("/xml")
    public ResponseEntity<ResponseDto<Object>> getXml(@Valid @RequestBody XmlRequestDto request) {
        return ResponseEntity.ok(xmlService.getXml(request));
    }

    @PostMapping("/facturas/timbradas")
    public ResponseEntity<ResponseDto<Object>> getFacturasTimbradas(
        @Valid @RequestBody FacturasTimbradasRequestDto request
    ) {
        return ResponseEntity.ok(xmlService.getFacturasTimbradas(request));
    }
}

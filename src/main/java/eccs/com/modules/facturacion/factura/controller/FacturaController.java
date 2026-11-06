package eccs.com.modules.facturacion.factura.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.facturacion.factura.dto.FacturaRequestDto;
import org.springframework.web.bind.annotation.PathVariable;
import eccs.com.modules.facturacion.factura.service.FacturaService;
import eccs.com.modules.facturacion.factura.service.FacturaServiceImpl;
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
public class FacturaController {

    private final FacturaService facturaService;

    @PostMapping("/factura/data")
    public ResponseEntity<ResponseDto<Object>> getDataFactura(@Valid @RequestBody FacturaRequestDto request) {
        return ResponseEntity.ok(facturaService.getDataFactura(request));
    }

    @PostMapping("/comprobante/{id}")
    public ResponseEntity<ResponseDto<Object>> getDataComprobante(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(facturaService.getDataComprobante(id));
    }

    @PostMapping("/comprobante/cliente/{id}")
    public ResponseEntity<ResponseDto<Object>> getDataComprobanteCliente(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(facturaService.getDataComprobanteCliente(id));
    }
}

package eccs.com.modules.controlventas.comprobanteventa.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.ComprobanteVentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.service.ComprobanteVentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas")
@RequiredArgsConstructor
public class ComprobanteVentaController {

    private final ComprobanteVentaService comprobanteVentaService;

    @PostMapping("/comprobante/venta/create")
    public ResponseEntity<ResponseDto<Object>> createComprobanteVenta(@Valid @RequestBody ComprobanteVentaRequestDto request) {
        return ResponseEntity.ok(comprobanteVentaService.createComprobanteVenta(request));
    }

}

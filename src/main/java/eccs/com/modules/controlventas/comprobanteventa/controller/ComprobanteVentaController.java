package eccs.com.modules.controlventas.comprobanteventa.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.ComprobanteVentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.service.ComprobanteVentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas/comprobante")
@RequiredArgsConstructor
public class ComprobanteVentaController {

    private final ComprobanteVentaService comprobanteVentaService;

    @PostMapping("/venta/data/{IdSucursal}/{IdComprobante}")
    public ResponseEntity<ResponseDto<Object>> Data(
        @PathVariable Integer IdSucursal, 
        @PathVariable Integer IdComprobante) {
        return ResponseEntity.ok(comprobanteVentaService.getData(IdSucursal, IdComprobante));
    }
    @PostMapping("/venta/create")
    public ResponseEntity<ResponseDto<Object>> createComprobanteVenta(@Valid @RequestBody ComprobanteVentaRequestDto request) {
        return ResponseEntity.ok(comprobanteVentaService.createComprobanteVenta(request));
    }

}

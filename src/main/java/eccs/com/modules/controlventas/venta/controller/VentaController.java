package eccs.com.modules.controlventas.venta.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.venta.dto.CrearVentaRequestDto;
import eccs.com.modules.controlventas.venta.dto.DataVentaRequestDto;
import eccs.com.modules.controlventas.venta.service.VentasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentasService VentasService;

    @PostMapping("/venta/data")
    public ResponseEntity<ResponseDto<Object>> DataVenta(@Valid @RequestBody DataVentaRequestDto request) {
        return ResponseEntity.ok(VentasService.getDataVenta(request));
    }

    @PostMapping("/venta/nueva")
    public ResponseEntity<ResponseDto<Object>> crearVenta(@Valid @RequestBody CrearVentaRequestDto request) {
        return ResponseEntity.ok(VentasService.getCrearVenta(request));
    }
  

}

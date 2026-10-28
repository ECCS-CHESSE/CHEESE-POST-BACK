package eccs.com.modules.pos.venta.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.pos.venta.dto.CrearVentaRequestDto;
import eccs.com.modules.pos.venta.dto.DataVentaRequestDto;
import eccs.com.modules.pos.venta.dto.InsertarComprobanteVentaInfoRequestDto;
import eccs.com.modules.pos.venta.dto.ResumenArmaTuPizzaRequestDto;
import eccs.com.modules.pos.venta.service.VentasService;
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

    @PostMapping("/venta/armatupizza/resumen")
    public ResponseEntity<ResponseDto<Object>> getPrecioArmaTuPizzaResumen(@Valid @RequestBody ResumenArmaTuPizzaRequestDto request) {
        return ResponseEntity.ok(VentasService.getPrecioArmaTuPizzaResumen(request));
    }

    @PostMapping("/venta/comprobante/info/insertar")
    public ResponseEntity<ResponseDto<Object>> insertarComprobanteVentaInfo(@Valid @RequestBody InsertarComprobanteVentaInfoRequestDto request) {
        return ResponseEntity.ok(VentasService.insertarComprobanteVentaInfo(request));
    }

}

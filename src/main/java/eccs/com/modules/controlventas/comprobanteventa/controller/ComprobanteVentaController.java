package eccs.com.modules.controlventas.comprobanteventa.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.ComprobanteVentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.AgregarProductoRequestDto;
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
    public ResponseEntity<ResponseDto<Object>> getData(
        @PathVariable Integer IdSucursal, 
        @PathVariable Integer IdComprobante) {
        return ResponseEntity.ok(comprobanteVentaService.getData(IdSucursal, IdComprobante));
    }
  
    @PostMapping("/venta/producto/eliminar/{IdSucursal}/{Id}")
    public ResponseEntity<ResponseDto<Object>> getEliminarProducto(
        @PathVariable Integer IdSucursal, 
        @PathVariable Integer Id) {
        return ResponseEntity.ok(comprobanteVentaService.getEliminarProducto(IdSucursal, Id));
    }
  
    @PostMapping("/ventadetalle/data/{IdSucursal}/{IdComprobante}")
    public ResponseEntity<ResponseDto<Object>> getDataDetallle(
        @PathVariable Integer IdSucursal, 
        @PathVariable Integer IdComprobante) {
        return ResponseEntity.ok(comprobanteVentaService.getDataDetalle(IdSucursal, IdComprobante));
    }

    @PostMapping("/venta/create")
    public ResponseEntity<ResponseDto<Object>> createComprobanteVenta(@Valid @RequestBody ComprobanteVentaRequestDto request) {
        return ResponseEntity.ok(comprobanteVentaService.createComprobanteVenta(request));
    }

    @PostMapping("/producto/agregar")
    public ResponseEntity<ResponseDto<Object>> agregarProducto(@Valid @RequestBody AgregarProductoRequestDto request) {
        return ResponseEntity.ok(comprobanteVentaService.agregarProducto(request));
    }

}

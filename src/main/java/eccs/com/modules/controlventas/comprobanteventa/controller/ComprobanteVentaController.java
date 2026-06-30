package eccs.com.modules.controlventas.comprobanteventa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.AgregarProductoRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.ComprobanteVentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.EccsCancelarComprobanteRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.ItemAumentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.UpdateClienteComprobanteVentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.service.ComprobanteVentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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

    @PostMapping("/venta/cancelar/{IdSucursal}/{Id}")
    public ResponseEntity<ResponseDto<Object>> cancelarComprobanteVenta(
        @PathVariable Integer IdSucursal,
        @PathVariable Integer Id) {
        return ResponseEntity.ok(comprobanteVentaService.cancelarComprobanteVenta(IdSucursal, Id));
    }

    @PostMapping("/venta/cliente/update")
    public ResponseEntity<ResponseDto<Object>> updateClienteComprobanteVenta(@Valid @RequestBody UpdateClienteComprobanteVentaRequestDto request) {
        return ResponseEntity.ok(comprobanteVentaService.updateClienteComprobanteVenta(request));
    }

    @PostMapping("/item/cantidad")
    public ResponseEntity<ResponseDto<Object>> itemAumenta(@Valid @RequestBody ItemAumentaRequestDto request) {
        return ResponseEntity.ok(comprobanteVentaService.itemAumenta(request));
    }

    @PostMapping("/venta/cancelar")
    public ResponseEntity<ResponseDto<Object>> eccsCancelarComprobante(@Valid @RequestBody EccsCancelarComprobanteRequestDto request) {
        return ResponseEntity.ok(comprobanteVentaService.eccsCancelarComprobante(request));
    }

}

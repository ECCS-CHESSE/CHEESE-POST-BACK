package eccs.com.modules.controlventas.productos.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.productos.dto.ProductosRequestDto;
import eccs.com.modules.controlventas.productos.service.ProductosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas/data")
@RequiredArgsConstructor
public class ProductosController {

    private final ProductosService productosService;

    @PostMapping("/pizza/buscar")
    public ResponseEntity<ResponseDto<Object>> buscarProductos(@Valid @RequestBody ProductosRequestDto request) {
        return ResponseEntity.ok(productosService.buscarProductos(request));
    }
}

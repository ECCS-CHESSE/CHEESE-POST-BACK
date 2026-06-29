package eccs.com.modules.controlinventarios.productos.controller;

import eccs.com.modules.controlinventarios.productos.dto.ProductosRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.ProductosCategoriasRequestDto;
import eccs.com.modules.controlinventarios.productos.service.ProductosService;
import eccs.com.core.dtos.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("controlinventariosProductosController")
@RequestMapping("/controlinventarios/productos")
@RequiredArgsConstructor
public class ProductosController {

    private final ProductosService productosService;

    @PostMapping("/data")
    public ResponseEntity<ResponseDto<Object>> getCatalogoProductos(@Valid @RequestBody ProductosRequestDto request) {
        return ResponseEntity.ok(productosService.getCatalogoProductos(request));
    }

    @PostMapping("/data/categorias")
    public ResponseEntity<ResponseDto<Object>> getCatalogoCategorias(@Valid @RequestBody ProductosCategoriasRequestDto request) {
        return ResponseEntity.ok(productosService.getCatalogoCategorias(request));
    }

}

package eccs.com.modules.controlinventarios.productos.controller;

import eccs.com.modules.controlinventarios.productos.dto.ProductosRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.ProductosCategoriasRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.UpdateImagenProductoRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.CreateProductoRequestDto;
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

    @PostMapping("/update/imagen")
    public ResponseEntity<ResponseDto<Object>> updateImagenProducto(@Valid @RequestBody UpdateImagenProductoRequestDto request) {
        return ResponseEntity.ok(productosService.updateImagenProducto(request));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<Object>> createProducto(@Valid @RequestBody CreateProductoRequestDto request) {
        return ResponseEntity.ok(productosService.createProducto(request));
    }

    @PostMapping("/data/clasificacion8")
    public ResponseEntity<ResponseDto<Object>> getProductosClasificacion8(@Valid @RequestBody ProductosRequestDto request) {
        return ResponseEntity.ok(productosService.getProductosClasificacion8(request));
    }

}

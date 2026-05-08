package eccs.com.modules.controlventas.catalogoclientes.controller;

import eccs.com.modules.controlventas.catalogoclientes.service.CatalogoClientesService;
import eccs.com.modules.controlventas.catalogoclientes.dto.CatalogoClientesRequestDto;
import eccs.com.core.dtos.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas")
@RequiredArgsConstructor
public class CatalogoClientesController {

    private final CatalogoClientesService catalogoClientesService;

    @PostMapping("/catalogo/clientes")
    public ResponseEntity<ResponseDto<Object>> getCatalogoClientes(@Valid @RequestBody CatalogoClientesRequestDto request) {
        return ResponseEntity.ok(catalogoClientesService.getCatalogoClientes(request));
    }

    @PostMapping("/lst/estatus")
    public ResponseEntity<ResponseDto<Object>> getlstEstatus() {
        return ResponseEntity.ok(catalogoClientesService.getLstEstatus());
    }

    @PostMapping("/lst/sucursales")
    public ResponseEntity<ResponseDto<Object>> getlstSucursales() {
        return ResponseEntity.ok(catalogoClientesService.getLstSucursales());
    }

}

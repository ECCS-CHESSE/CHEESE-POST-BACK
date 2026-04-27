package eccs.com.modules.controlempresa.catalogosucursales.controller;

import jakarta.validation.Valid;
import eccs.com.modules.controlempresa.catalogosucursales.service.CatalogoSucursalesService;
import eccs.com.modules.controlempresa.catalogosucursales.dto.CatalogoSucursalesRequestDto;
import eccs.com.core.dtos.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlempresa")
@RequiredArgsConstructor
public class CatalogoSucursalesController {

    private final CatalogoSucursalesService catalogoSucursalesService;

    @PostMapping("/catalogo/sucursales")
    public ResponseEntity<ResponseDto<Object>> getCatalogoSucursales(@Valid @RequestBody CatalogoSucursalesRequestDto request) {
        return ResponseEntity.ok(catalogoSucursalesService.getCatalogoSucursales(request));
    }
}

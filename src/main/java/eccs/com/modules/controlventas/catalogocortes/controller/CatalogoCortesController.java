package eccs.com.modules.controlventas.catalogocortes.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.catalogocortes.dto.CatalogoCortesRequestDto;
import eccs.com.modules.controlventas.catalogocortes.service.CatalogoCortesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas")
@RequiredArgsConstructor
public class CatalogoCortesController {

    private final CatalogoCortesService catalogoCortesService;

    @PostMapping("/cortes/catalogo")
    public ResponseEntity<ResponseDto<Object>> getCatalogoCortes(@Valid @RequestBody CatalogoCortesRequestDto request) {
        return ResponseEntity.ok(catalogoCortesService.getCatalogoCortes(request));
    }
}

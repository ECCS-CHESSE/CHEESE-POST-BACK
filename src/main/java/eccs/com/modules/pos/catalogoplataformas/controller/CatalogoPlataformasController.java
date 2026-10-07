package eccs.com.modules.pos.catalogoplataformas.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.pos.catalogoplataformas.dto.CatalogoPlataformasRequestDto;
import eccs.com.modules.pos.catalogoplataformas.service.CatalogoPlataformasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pos")
@RequiredArgsConstructor
public class CatalogoPlataformasController {

    private final CatalogoPlataformasService cataloPlataformasService;

    @PostMapping("/catalogo/plataformas")
    public ResponseEntity<ResponseDto<Object>> getCatalogoPlataformas(@Valid @RequestBody CatalogoPlataformasRequestDto request) {
        return ResponseEntity.ok(cataloPlataformasService.getCatalogoPlataformas(request));
    }
}

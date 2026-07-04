package eccs.com.modules.controlinventarios.equivalencias.controller;

import eccs.com.modules.controlinventarios.equivalencias.dto.EquivalenciasRequestDto;
import eccs.com.modules.controlinventarios.equivalencias.service.EquivalenciasService;
import eccs.com.core.dtos.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("controlinventariosEquivalenciasController")
@RequestMapping("/controlinventarios/equivalencias")
@RequiredArgsConstructor
public class EquivalenciasController {

    private final EquivalenciasService equivalenciasService;

    @PostMapping("/data")
    public ResponseEntity<ResponseDto<Object>> getCatalogoEquivalencias(@Valid @RequestBody EquivalenciasRequestDto request) {
        return ResponseEntity.ok(equivalenciasService.getCatalogoEquivalencias(request));
    }

}

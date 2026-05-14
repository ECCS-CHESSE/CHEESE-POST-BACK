package eccs.com.modules.controlventas.cortes.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.cortes.dto.AbrirCorteRequestDto;
import eccs.com.modules.controlventas.cortes.service.CortesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas")
@RequiredArgsConstructor
public class CortesController {

    private final CortesService cortesService;

    @PostMapping("/cortes/abrir")
    public ResponseEntity<ResponseDto<Object>> abrirCorte(@Valid @RequestBody AbrirCorteRequestDto request) {
        return ResponseEntity.ok(cortesService.abrirCorte(request));
    }
}

package eccs.com.modules.sistemapos.preventa.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.sistemapos.preventa.dto.PreventaRequestDto;
import eccs.com.modules.sistemapos.preventa.service.PreventaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sistemapos/preventa")
@RequiredArgsConstructor
public class PreventaController {

    private final PreventaService preventaService;

    @PostMapping("/precios/pizzas")
    public ResponseEntity<ResponseDto<Object>> getPreciosPizzas(@Valid @RequestBody PreventaRequestDto request) {
        return ResponseEntity.ok(preventaService.getPreciosPizzas(request));
    }
}

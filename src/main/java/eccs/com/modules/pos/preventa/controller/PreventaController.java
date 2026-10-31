package eccs.com.modules.pos.preventa.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.pos.preventa.dto.EliminarIngredientePreventaRequestDto;
import eccs.com.modules.pos.preventa.dto.IngredientesProduccionRequestDto;
import eccs.com.modules.pos.preventa.dto.PreventaRequestDto;
import eccs.com.modules.pos.preventa.service.PreventaService;
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

    @PostMapping("/data/ingredientes/produccion/info")
    public ResponseEntity<ResponseDto<Object>> getDataIngredientesProduccionInfo(
            @Valid @RequestBody IngredientesProduccionRequestDto request) {
        return ResponseEntity.ok(preventaService.getDataIngredientesProduccionInfo(request));
    }

    @PostMapping("/eliminar/ingrediente")
    public ResponseEntity<ResponseDto<Object>> eliminarIngredientePreventa(
            @Valid @RequestBody EliminarIngredientePreventaRequestDto request) {
        return ResponseEntity.ok(preventaService.eliminarIngredientePreventa(request));
    }
}

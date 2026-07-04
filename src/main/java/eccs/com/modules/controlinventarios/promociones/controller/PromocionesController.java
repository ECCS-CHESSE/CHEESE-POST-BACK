package eccs.com.modules.controlinventarios.promociones.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.promociones.dto.ActualizarPromocionRequestDto;
import eccs.com.modules.controlinventarios.promociones.dto.AgregarPromocionRequestDto;
import eccs.com.modules.controlinventarios.promociones.service.PromocionesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlinventarios/promociones")
@RequiredArgsConstructor
public class PromocionesController {

    private final PromocionesService promocionesService;

    @PostMapping("/data")
    public ResponseEntity<ResponseDto<Object>> data() {
        return ResponseEntity.ok(promocionesService.data());
    }

    @PostMapping("/agregar")
    public ResponseEntity<ResponseDto<Object>> agregar(@Valid @RequestBody AgregarPromocionRequestDto request) {
        return ResponseEntity.ok(promocionesService.agregar(request));
    }

    @PostMapping("/actualizar")
    public ResponseEntity<ResponseDto<Object>> actualizar(@Valid @RequestBody ActualizarPromocionRequestDto request) {
        return ResponseEntity.ok(promocionesService.actualizar(request));
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Object>> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(promocionesService.eliminar(id));
    }

}

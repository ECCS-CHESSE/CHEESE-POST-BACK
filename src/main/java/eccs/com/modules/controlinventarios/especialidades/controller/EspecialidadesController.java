package eccs.com.modules.controlinventarios.especialidades.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.especialidades.dto.ActualizarEspecialidadRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.AgregarEspecialidadRequestDto;
import eccs.com.modules.controlinventarios.especialidades.service.EspecialidadesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlinventarios/especialidades")
@RequiredArgsConstructor
public class EspecialidadesController {

    private final EspecialidadesService especialidadesService;

    @PostMapping("/data")
    public ResponseEntity<ResponseDto<Object>> data() {
        return ResponseEntity.ok(especialidadesService.data());
    }

    @PostMapping("/agregar")
    public ResponseEntity<ResponseDto<Object>> agregar(@Valid @RequestBody AgregarEspecialidadRequestDto request) {
        return ResponseEntity.ok(especialidadesService.agregar(request));
    }

    @PostMapping("/actualizar")
    public ResponseEntity<ResponseDto<Object>> actualizar(@Valid @RequestBody ActualizarEspecialidadRequestDto request) {
        return ResponseEntity.ok(especialidadesService.actualizar(request));
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<ResponseDto<Object>> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(especialidadesService.eliminar(id));
    }

}

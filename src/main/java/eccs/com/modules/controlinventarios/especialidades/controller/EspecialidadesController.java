package eccs.com.modules.controlinventarios.especialidades.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.especialidades.dto.ActualizarEspecialidadRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.AgregarEspecialidadRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.AgregarIngredientesRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.EliminarIngredientesRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.ModificarPrecioRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.IngredientesEspecialidadRequestDto;
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

    @PostMapping("/data/ingredientes")
    public ResponseEntity<ResponseDto<Object>> getIngredientes(@Valid @RequestBody IngredientesEspecialidadRequestDto request) {
        return ResponseEntity.ok(especialidadesService.getIngredientes(request));
    }

    @PostMapping("/agregar/ingredientes")
    public ResponseEntity<ResponseDto<Object>> agregarIngredientes(@Valid @RequestBody AgregarIngredientesRequestDto request) {
        return ResponseEntity.ok(especialidadesService.agregarIngredientes(request));
    }

    @PostMapping("/eliminar/ingredientes")
    public ResponseEntity<ResponseDto<Object>> eliminarIngredientes(@Valid @RequestBody EliminarIngredientesRequestDto request) {
        return ResponseEntity.ok(especialidadesService.eliminarIngredientes(request));
    }

    @PostMapping("/eliminar/ingrediente/{id}")
    public ResponseEntity<ResponseDto<Object>> eliminarIngrediente(@PathVariable Long id) {
        return ResponseEntity.ok(especialidadesService.eliminarIngrediente(id));
    }

    @PostMapping("/modificarprecio")
    public ResponseEntity<ResponseDto<Object>> modificarPrecio(@Valid @RequestBody ModificarPrecioRequestDto request) {
        return ResponseEntity.ok(especialidadesService.modificarPrecio(request));
    }

}

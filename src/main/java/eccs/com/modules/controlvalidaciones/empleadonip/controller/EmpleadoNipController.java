package eccs.com.modules.controlvalidaciones.empleadonip.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlvalidaciones.empleadonip.dto.EmpleadoNipRequestDto;
import eccs.com.modules.controlvalidaciones.empleadonip.service.EmpleadoNipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlvalidaciones")
@RequiredArgsConstructor
public class EmpleadoNipController {

    private final EmpleadoNipService empleadoNipService;

    @PostMapping("/nip")
    public ResponseEntity<ResponseDto<Object>> validarNip(@Valid @RequestBody EmpleadoNipRequestDto request) {
        return ResponseEntity.ok(empleadoNipService.validarNip(request));
    }
}

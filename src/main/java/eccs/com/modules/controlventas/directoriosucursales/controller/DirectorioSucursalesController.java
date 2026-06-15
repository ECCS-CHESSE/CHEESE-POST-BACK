package eccs.com.modules.controlventas.directoriosucursales.controller;

import eccs.com.modules.controlventas.directoriosucursales.dto.DirectorioSucursalesRequestDto;
import eccs.com.modules.controlventas.directoriosucursales.service.DirectorioSucursalesService;
import eccs.com.core.dtos.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas")
@RequiredArgsConstructor
public class DirectorioSucursalesController {

    private final DirectorioSucursalesService DirectorioSucursalesService;

    @PostMapping("/directoriosucursales")
    public ResponseEntity<ResponseDto<Object>> getDirectorioSucursales(@Valid @RequestBody DirectorioSucursalesRequestDto request) {
        return ResponseEntity.ok(DirectorioSucursalesService.getDirectorioSucursales(request));
    }
}

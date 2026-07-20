package eccs.com.modules.controlventas.cancelaciones.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.cancelaciones.dto.FolioRequestDto;
import eccs.com.modules.controlventas.cancelaciones.service.FolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("controlventasCancelacionesController")
@RequestMapping("/controlventas/cancelaciones")
@RequiredArgsConstructor
public class FolioController {

    private final FolioService folioService;

    @PostMapping("/buscar")
    public ResponseEntity<ResponseDto<Object>> buscarFolio(@Valid @RequestBody FolioRequestDto request) {
        return ResponseEntity.ok(folioService.buscarFolio(request));
    }

    @PostMapping("/cancelar")
    public ResponseEntity<ResponseDto<Object>> cancelarVenta(@Valid @RequestBody FolioRequestDto request) {
        return ResponseEntity.ok(folioService.cancelarVenta(request));
    }
}

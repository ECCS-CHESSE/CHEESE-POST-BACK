package eccs.com.modules.facturacion.listados.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.facturacion.listados.dto.UsoCfdiRequestDto;
import eccs.com.modules.facturacion.listados.service.ListadosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facturacion")
@RequiredArgsConstructor
public class ListadosController {

    private final ListadosService listadosService;

    @PostMapping("/listados/regimen-fiscal")
    public ResponseEntity<ResponseDto<Object>> getRegimenesFiscales() {
        return ResponseEntity.ok(listadosService.getRegimenesFiscales());
    }

    @PostMapping("/catalogos/uso-cfdi")
    public ResponseEntity<ResponseDto<Object>> getUsosCfdi(@Valid @RequestBody UsoCfdiRequestDto request) {
        return ResponseEntity.ok(listadosService.getUsosCfdi(request));
    }
}

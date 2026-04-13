package eccs.com.modules.controlempresa.empresa.controller;

import eccs.com.modules.controlempresa.empresa.service.EmpresaService;
import eccs.com.core.dtos.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlempresa")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping("/empresa/data")
    public ResponseEntity<ResponseDto<Object>> getData() {
        return ResponseEntity.ok(empresaService.getData());
    }
}

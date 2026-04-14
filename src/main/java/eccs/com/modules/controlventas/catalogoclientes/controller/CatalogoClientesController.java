package eccs.com.modules.controlventas.catalogoclientes.controller;

import eccs.com.modules.controlventas.catalogoclientes.service.CatalogoClientesService;
import eccs.com.core.dtos.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas")
@RequiredArgsConstructor
public class CatalogoClientesController {

    private final CatalogoClientesService catalogoClientesService;

    @PostMapping("/catalogo/clientes")
    public ResponseEntity<ResponseDto<Object>> getCatalogoClientes() {
        return ResponseEntity.ok(catalogoClientesService.getCatalogoClientes());
    }
}

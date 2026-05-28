package eccs.com.modules.controlventas.clientes.controller;

import eccs.com.modules.controlventas.clientes.dto.ClientesCrearRequestDto;
import eccs.com.modules.controlventas.clientes.service.ClientesService;
import eccs.com.core.dtos.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas")
@RequiredArgsConstructor
public class ClientesController {

    private final ClientesService clientesService;

    @PostMapping("/cliente/crear")
    public ResponseEntity<ResponseDto<Object>> crearCliente(@Valid @RequestBody ClientesCrearRequestDto request) {
        return ResponseEntity.ok(clientesService.getCrearCliente(request));
    }
}

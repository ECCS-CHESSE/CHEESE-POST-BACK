package eccs.com.modules.controlventas.clientes.controller;

import eccs.com.modules.controlventas.clientes.dto.ClientesBuscarRequestDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesCrearRequestDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesCrearDomicilioRequestDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesActualizarDomicilioRequestDto;
import eccs.com.modules.controlventas.clientes.dto.ClientesDomicilioRequestDto;
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
   
    @PostMapping("/cliente/buscar")
    public ResponseEntity<ResponseDto<Object>> buscarCliente(@Valid @RequestBody ClientesBuscarRequestDto request) {
        return ResponseEntity.ok(clientesService.getBusquedaCliente(request));
    }

    @PostMapping("/cliente/domicilio")
    public ResponseEntity<ResponseDto<Object>> domicilioCliente(@Valid @RequestBody ClientesDomicilioRequestDto request) {
        return ResponseEntity.ok(clientesService.getDomicilioCliente(request));
    }

    @PostMapping("/cliente/domicilio/crear")
    public ResponseEntity<ResponseDto<Object>> crearDomicilioCliente(@Valid @RequestBody ClientesCrearDomicilioRequestDto request) {
        return ResponseEntity.ok(clientesService.getCrearDomicilioCliente(request));
    }

    @PostMapping("/cliente/domicilio/actualizar")
    public ResponseEntity<ResponseDto<Object>> actualizarDomicilioCliente(@Valid @RequestBody ClientesActualizarDomicilioRequestDto request) {
        return ResponseEntity.ok(clientesService.getActualizarDomicilioCliente(request));
    }
}

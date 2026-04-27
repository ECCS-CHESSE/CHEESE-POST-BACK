package eccs.com.modules.controlempresa.sucursal.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlempresa.sucursal.dto.CreateSucursalRequestDto;
import eccs.com.modules.controlempresa.sucursal.dto.UpdateSucursalRequestDto;
import eccs.com.modules.controlempresa.sucursal.service.SucursalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlempresa")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService sucursalService;

    @PostMapping("/sucursal/create")
    public ResponseEntity<ResponseDto<Object>> createSucursal(@Valid @RequestBody CreateSucursalRequestDto request) {
        return ResponseEntity.ok(sucursalService.createSucursal(request));
    }

    @PutMapping("/sucursal/update")
    public ResponseEntity<ResponseDto<Object>> updateSucursal(@Valid @RequestBody UpdateSucursalRequestDto request) {
        return ResponseEntity.ok(sucursalService.updateSucursal(request));
    }

    @DeleteMapping("/sucursal/delete/{id}")
    public ResponseEntity<ResponseDto<Object>> deleteSucursal(@PathVariable int id) {
        return ResponseEntity.ok(sucursalService.deleteSucursal(id));
    }
}

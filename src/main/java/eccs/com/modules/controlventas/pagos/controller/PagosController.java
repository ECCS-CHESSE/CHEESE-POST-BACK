package eccs.com.modules.controlventas.pagos.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.pagos.dto.PagosInsertRequestDto;
import eccs.com.modules.controlventas.pagos.service.PagosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas")
@RequiredArgsConstructor
public class PagosController {

    private final PagosService pagosService;

    @PostMapping("/pagos/data/{id_venta}")
    public ResponseEntity<ResponseDto<Object>> getDataPago(@PathVariable("id_venta") Integer id_venta) {
        return ResponseEntity.ok(pagosService.getDataPago(id_venta));
    }

    @PostMapping("/pagos/insert")
    public ResponseEntity<ResponseDto<Object>> insertPago(@Valid @RequestBody PagosInsertRequestDto request) {
        return ResponseEntity.ok(pagosService.insertPago(request));
    }

}

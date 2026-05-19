package eccs.com.modules.controlventas.cortes.controller;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.cortes.dto.AbrirCorteRequestDto;
import eccs.com.modules.controlventas.cortes.dto.CerrarCorteRequestDto;
import eccs.com.modules.controlventas.cortes.service.CortesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlventas")
@RequiredArgsConstructor
public class CortesController {

    private final CortesService cortesService;

    @PostMapping("/data/corte/{id}")
    public ResponseEntity<ResponseDto<Object>> corte(@PathVariable int id) {
        return ResponseEntity.ok(cortesService.getDataCorte(id));
    }
   
    @PostMapping("/cortes/abrir")
    public ResponseEntity<ResponseDto<Object>> abrirCorte(@Valid @RequestBody AbrirCorteRequestDto request) {
        return ResponseEntity.ok(cortesService.getAbrirCorte(request));
    }
 
    @PostMapping("/cortes/cerrar")
    public ResponseEntity<ResponseDto<Object>> cerrarCorte(@Valid @RequestBody CerrarCorteRequestDto request) {
        return ResponseEntity.ok(cortesService.getCerrarCorte(request));
    }
   

}

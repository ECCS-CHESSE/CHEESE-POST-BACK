package eccs.com.modules.controlinventarios.listaproduccion.controller;

import eccs.com.modules.controlinventarios.listaproduccion.dto.ListaTipoSalsaRequestDto;
import eccs.com.modules.controlinventarios.listaproduccion.service.ListaProduccionService;
import eccs.com.core.dtos.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("controlinventariosListaProduccionController")
@RequestMapping("/controlinventarios/listaproduccion")
@RequiredArgsConstructor
public class ListaProduccionController {

    private final ListaProduccionService listaProduccionService;

    @PostMapping("/lst/salsas")
    public ResponseEntity<ResponseDto<Object>> getLstTipoSalsa(@Valid @RequestBody ListaTipoSalsaRequestDto request) {
        return ResponseEntity.ok(listaProduccionService.getLstTipoSalsa(request));
    }

    @PostMapping("/lst/orillaqueso")
    public ResponseEntity<ResponseDto<Object>> getLstOrillasQueso(@Valid @RequestBody ListaTipoSalsaRequestDto request) {
        return ResponseEntity.ok(listaProduccionService.getLstOrillasQueso(request));
    }

    @PostMapping("/lst/insumos")
    public ResponseEntity<ResponseDto<Object>> getLstInsumos(@Valid @RequestBody ListaTipoSalsaRequestDto request) {
        return ResponseEntity.ok(listaProduccionService.getLstInsumos(request));
    }

}

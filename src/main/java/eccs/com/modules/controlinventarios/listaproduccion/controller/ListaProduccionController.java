package eccs.com.modules.controlinventarios.listaproduccion.controller;

import eccs.com.modules.controlinventarios.listaproduccion.dto.ListaTipoEquivalenciaRequestDto;
import eccs.com.modules.controlinventarios.listaproduccion.dto.DataIngredientesRequestDto;
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
    public ResponseEntity<ResponseDto<Object>> getLstTipoSalsa(@Valid @RequestBody ListaTipoEquivalenciaRequestDto request) {
        return ResponseEntity.ok(listaProduccionService.getLstTipoSalsa(request));
    }

    @PostMapping("/lst/orillaqueso")
    public ResponseEntity<ResponseDto<Object>> getLstOrillasQueso(@Valid @RequestBody ListaTipoEquivalenciaRequestDto request) {
        return ResponseEntity.ok(listaProduccionService.getLstOrillasQueso(request));
    }

    @PostMapping("/lst/insumos")
    public ResponseEntity<ResponseDto<Object>> getLstInsumos(@Valid @RequestBody ListaTipoEquivalenciaRequestDto request) {
        return ResponseEntity.ok(listaProduccionService.getLstInsumos(request));
    }

    @PostMapping("/lst/especialidades")
    public ResponseEntity<ResponseDto<Object>> getLstEspecialidades() {
        return ResponseEntity.ok(listaProduccionService.getLstEspecialidades());
    }

    @PostMapping("/data/ingredientes")
    public ResponseEntity<ResponseDto<Object>> getDataIngredientes(@Valid @RequestBody DataIngredientesRequestDto request) {
        return ResponseEntity.ok(listaProduccionService.getDataIngredientes(request));
    }

    @PostMapping("/lst/categorias/armatupizza")
    public ResponseEntity<ResponseDto<Object>> getLstCategoriasArmaTuPizza() {
        return ResponseEntity.ok(listaProduccionService.getLstCategoriasArmaTuPizza());
    }

}

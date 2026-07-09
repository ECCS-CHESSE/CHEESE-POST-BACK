package eccs.com.modules.controlinventarios.ordenproduccion.controller;

import eccs.com.modules.controlinventarios.ordenproduccion.dto.OrdenProduccionRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.LimpiarIngredientesRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarSalsaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.UpdateCategoriasArmaTuPizzaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EspecificacionesOrdenRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.service.OrdenProduccionService;
import eccs.com.core.dtos.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("controlinventariosOrdenProduccionController")
@RequestMapping("/controlinventarios/ordenproduccion")
@RequiredArgsConstructor
public class OrdenProduccionController {

    private final OrdenProduccionService ordenProduccionService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<Object>> insertarIngredientesOrden(@Valid @RequestBody OrdenProduccionRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.insertarIngredientesOrden(request));
    }

    @PostMapping("/limpiar/ingredientes")
    public ResponseEntity<ResponseDto<Object>> limpiarIngredientesOrden(@Valid @RequestBody LimpiarIngredientesRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.limpiarIngredientesOrden(request));
    }

    @PostMapping("/agregar/salsa")
    public ResponseEntity<ResponseDto<Object>> insertarSalsa(@Valid @RequestBody InsertarSalsaRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.insertarSalsa(request));
    }

    @PostMapping("/agregar/orillaqueso")
    public ResponseEntity<ResponseDto<Object>> insertarOrillaQueso(@Valid @RequestBody InsertarSalsaRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.insertarOrillaQueso(request));
    }

    @PostMapping("/update/categorias/armatupizza")
    public ResponseEntity<ResponseDto<Object>> updateCategoriasArmaTuPizza(@Valid @RequestBody UpdateCategoriasArmaTuPizzaRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.updateCategoriasArmaTuPizza(request));
    }

    @PostMapping("/agregar/especificaciones")
    public ResponseEntity<ResponseDto<Object>> agregarEspecificaciones(@Valid @RequestBody EspecificacionesOrdenRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.agregarEspecificaciones(request));
    }

}

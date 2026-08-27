package eccs.com.modules.controlinventarios.ordenproduccion.controller;

import eccs.com.modules.controlinventarios.ordenproduccion.dto.OrdenProduccionRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.SucursalRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.LimpiarIngredientesRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.ModalDataIngredientesDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarSalsaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.UpdateCategoriasArmaTuPizzaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EspecificacionesOrdenRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EliminarIngredienteOrdenRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.DataIngredientesDerecharRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EliminarIngredientesDerecharRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarIngredienteIzquierdoRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarIngredienteDerechoRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.LimpiarArmadoPizzaRequestDto;
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

    @PostMapping("/data/ingredientes/derecho")
    public ResponseEntity<ResponseDto<Object>> getIngredientesOrdenDerecho(@Valid @RequestBody ModalDataIngredientesDto request) {
        return ResponseEntity.ok(ordenProduccionService.getIngredientesOrdenDerecho(request));
    }

    @PostMapping("/data/ingredientes/izquierdo")
    public ResponseEntity<ResponseDto<Object>> getIngredientesOrdenIzquierdo(@Valid @RequestBody ModalDataIngredientesDto request) {
        return ResponseEntity.ok(ordenProduccionService.getIngredientesOrdenIzquierdo(request));
    }

    @PostMapping("/eliminar/ingrediente")
    public ResponseEntity<ResponseDto<Object>> eliminarIngredienteOrden(@Valid @RequestBody EliminarIngredienteOrdenRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.eliminarIngredienteOrden(request));
    }

    @PostMapping("/data/ingredientes/data/derecha")
    public ResponseEntity<ResponseDto<Object>> getDataIngredientesDataDerecha(@Valid @RequestBody DataIngredientesDerecharRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.getDataIngredientesDataDerecha(request));
    }

    @PostMapping("/data/ingredientes/data/izquierda")
    public ResponseEntity<ResponseDto<Object>> getDataIngredientesDataIzquierda(@Valid @RequestBody DataIngredientesDerecharRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.getDataIngredientesDataIzquierda(request));
    }

    @PostMapping("/limpiar/ingredientes/derecha")
    public ResponseEntity<ResponseDto<Object>> eliminarIngredientesDerecha(@Valid @RequestBody EliminarIngredientesDerecharRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.eliminarIngredientesDerecha(request));
    }

    @PostMapping("/limpiar/ingredientes/izquierda")
    public ResponseEntity<ResponseDto<Object>> eliminarIngredientesIzquierda(@Valid @RequestBody EliminarIngredientesDerecharRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.eliminarIngredientesIzquierda(request));
    }

    @PostMapping("/total")
    public ResponseEntity<ResponseDto<Object>> getTotalOrdenProduccion(@Valid @RequestBody EliminarIngredienteOrdenRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.getTotalOrdenProduccion(request));
    }

    @PostMapping("/insertar/ingrediente/izquierdo")
    public ResponseEntity<ResponseDto<Object>> insertarIngredienteIzquierdo(@Valid @RequestBody InsertarIngredienteIzquierdoRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.insertarIngredienteIzquierdo(request));
    }

    @PostMapping("/insertar/ingrediente/derecho")
    public ResponseEntity<ResponseDto<Object>> insertarIngredienteDerecho(@Valid @RequestBody InsertarIngredienteDerechoRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.insertarIngredienteDerecho(request));
    }

    @PostMapping("/limpiar/armado/pizza")
    public ResponseEntity<ResponseDto<Object>> limpiarArmadoPizza(@Valid @RequestBody LimpiarArmadoPizzaRequestDto request) {
        return ResponseEntity.ok(ordenProduccionService.limpiarArmadoPizza(request));
    }

}

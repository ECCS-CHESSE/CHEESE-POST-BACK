package eccs.com.modules.controlinventarios.ordenproduccion.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.OrdenProduccionRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.TotalesOrdenRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.LimpiarIngredientesRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.ModalDataIngredientesDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarSalsaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.UpdateCategoriasArmaTuPizzaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EspecificacionesOrdenRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EliminarIngredienteOrdenRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EliminarIngredientesIzquierdaDerecharRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.DataIngredientesDerecharRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarIngredienteIzquierdoRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarIngredienteDerechoRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.LimpiarArmadoPizzaRequestDto;

public interface OrdenProduccionService {
    ResponseDto<Object> insertarIngredientesOrden(OrdenProduccionRequestDto request);
    ResponseDto<Object> limpiarIngredientesOrden(LimpiarIngredientesRequestDto request);
    ResponseDto<Object> insertarSalsa(InsertarSalsaRequestDto request);
    ResponseDto<Object> insertarOrillaQueso(InsertarSalsaRequestDto request);
    ResponseDto<Object> updateCategoriasArmaTuPizza(UpdateCategoriasArmaTuPizzaRequestDto request);
    ResponseDto<Object> agregarEspecificaciones(EspecificacionesOrdenRequestDto request);
    ResponseDto<Object> getIngredientesOrdenDerecho(ModalDataIngredientesDto request);
    ResponseDto<Object> getIngredientesOrdenModal(ModalDataIngredientesDto request);
    ResponseDto<Object> eliminarIngredienteOrden(EliminarIngredienteOrdenRequestDto request);
    ResponseDto<Object> getDataIngredientesData(DataIngredientesDerecharRequestDto request);
    ResponseDto<Object> eliminarIngredientesDerecha(EliminarIngredientesIzquierdaDerecharRequestDto request);
    ResponseDto<Object> getTotalOrdenProduccion(TotalesOrdenRequestDto request);
    ResponseDto<Object> insertarIngrediente(InsertarIngredienteIzquierdoRequestDto request);
    ResponseDto<Object> insertarIngredienteDerecho(InsertarIngredienteDerechoRequestDto request);
    ResponseDto<Object> limpiarArmadoPizza(LimpiarArmadoPizzaRequestDto request);
}

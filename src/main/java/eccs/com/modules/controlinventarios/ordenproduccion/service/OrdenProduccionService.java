package eccs.com.modules.controlinventarios.ordenproduccion.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.OrdenProduccionRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.SucursalRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.LimpiarIngredientesRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarSalsaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.UpdateCategoriasArmaTuPizzaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EspecificacionesOrdenRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EliminarIngredienteOrdenRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.DataIngredientesDerecharRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EliminarIngredientesDerecharRequestDto;

public interface OrdenProduccionService {
    ResponseDto<Object> insertarIngredientesOrden(OrdenProduccionRequestDto request);
    ResponseDto<Object> limpiarIngredientesOrden(LimpiarIngredientesRequestDto request);
    ResponseDto<Object> insertarSalsa(InsertarSalsaRequestDto request);
    ResponseDto<Object> insertarOrillaQueso(InsertarSalsaRequestDto request);
    ResponseDto<Object> updateCategoriasArmaTuPizza(UpdateCategoriasArmaTuPizzaRequestDto request);
    ResponseDto<Object> agregarEspecificaciones(EspecificacionesOrdenRequestDto request);
    ResponseDto<Object> getIngredientesOrdenDerecho(SucursalRequestDto request);
    ResponseDto<Object> getIngredientesOrdenIzquierdo(SucursalRequestDto request);
    ResponseDto<Object> eliminarIngredienteOrden(EliminarIngredienteOrdenRequestDto request);
    ResponseDto<Object> getDataIngredientesDataDerecha(DataIngredientesDerecharRequestDto request);
    ResponseDto<Object> getDataIngredientesDataIzquierda(DataIngredientesDerecharRequestDto request);
    ResponseDto<Object> eliminarIngredientesDerecha(EliminarIngredientesDerecharRequestDto request);
    ResponseDto<Object> eliminarIngredientesIzquierda(EliminarIngredientesDerecharRequestDto request);
    ResponseDto<Object> getTotalOrdenProduccion(EliminarIngredienteOrdenRequestDto request);
}

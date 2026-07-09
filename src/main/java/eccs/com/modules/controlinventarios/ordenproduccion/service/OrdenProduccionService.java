package eccs.com.modules.controlinventarios.ordenproduccion.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.OrdenProduccionRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.LimpiarIngredientesRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarSalsaRequestDto;

public interface OrdenProduccionService {
    ResponseDto<Object> insertarIngredientesOrden(OrdenProduccionRequestDto request);
    ResponseDto<Object> limpiarIngredientesOrden(LimpiarIngredientesRequestDto request);
    ResponseDto<Object> insertarSalsa(InsertarSalsaRequestDto request);
    ResponseDto<Object> insertarOrillaQueso(InsertarSalsaRequestDto request);
}

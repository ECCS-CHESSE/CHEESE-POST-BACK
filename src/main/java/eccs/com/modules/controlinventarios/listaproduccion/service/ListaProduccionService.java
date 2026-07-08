package eccs.com.modules.controlinventarios.listaproduccion.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.listaproduccion.dto.ListaTipoSalsaRequestDto;
import eccs.com.modules.controlinventarios.listaproduccion.dto.DataIngredientesRequestDto;

public interface ListaProduccionService {
    ResponseDto<Object> getLstTipoSalsa(ListaTipoSalsaRequestDto request);
    ResponseDto<Object> getLstOrillasQueso(ListaTipoSalsaRequestDto request);
    ResponseDto<Object> getLstInsumos(ListaTipoSalsaRequestDto request);
    ResponseDto<Object> getLstEspecialidades();
    ResponseDto<Object> getDataIngredientes(DataIngredientesRequestDto request);
}

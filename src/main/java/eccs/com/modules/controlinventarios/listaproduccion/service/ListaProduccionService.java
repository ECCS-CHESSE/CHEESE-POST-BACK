package eccs.com.modules.controlinventarios.listaproduccion.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.listaproduccion.dto.ListaTipoEquivalenciaRequestDto;
import eccs.com.core.dtos.SucursalDto;
import eccs.com.modules.controlinventarios.listaproduccion.dto.DataIngredientesRequestDto;

public interface ListaProduccionService {
    ResponseDto<Object> getLstTipoSalsa(SucursalDto request);
    ResponseDto<Object> getLstOrillasQueso( SucursalDto request);
    ResponseDto<Object> getLstInsumos(ListaTipoEquivalenciaRequestDto request);
    ResponseDto<Object> getLstEspecialidades( SucursalDto request);
    ResponseDto<Object> getDataIngredientes(DataIngredientesRequestDto request);
    ResponseDto<Object> getLstCategoriasArmaTuPizza( SucursalDto request);
}

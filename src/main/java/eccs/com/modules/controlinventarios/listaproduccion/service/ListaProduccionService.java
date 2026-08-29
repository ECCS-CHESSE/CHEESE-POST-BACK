package eccs.com.modules.controlinventarios.listaproduccion.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.listaproduccion.dto.ListaTipoEquivalenciaRequestDto;
import eccs.com.modules.controlinventarios.listaproduccion.dto.DataIngredientesRequestDto;

public interface ListaProduccionService {
    ResponseDto<Object> getLstTipoSalsa(ListaTipoEquivalenciaRequestDto request);
    ResponseDto<Object> getLstOrillasQueso(ListaTipoEquivalenciaRequestDto request);
    ResponseDto<Object> getLstInsumos(ListaTipoEquivalenciaRequestDto request);
    ResponseDto<Object> getLstEspecialidades();
    ResponseDto<Object> getDataIngredientes(DataIngredientesRequestDto request);
    ResponseDto<Object> getLstCategoriasArmaTuPizza();
}

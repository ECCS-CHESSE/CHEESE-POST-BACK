package eccs.com.modules.controlinventarios.equivalencias.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.equivalencias.dto.EquivalenciasRequestDto;

public interface EquivalenciasService {
    ResponseDto<Object> getCatalogoEquivalencias(EquivalenciasRequestDto request);
}

package eccs.com.modules.pos.preventa.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.pos.preventa.dto.IngredientesProduccionRequestDto;
import eccs.com.modules.pos.preventa.dto.PreventaRequestDto;

public interface PreventaService {
    public ResponseDto<Object> getPreciosPizzas(PreventaRequestDto request);
    public ResponseDto<Object> getDataIngredientesProduccionInfo(IngredientesProduccionRequestDto request);
}

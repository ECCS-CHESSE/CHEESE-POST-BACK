package eccs.com.modules.sistemapos.preventa.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.sistemapos.preventa.dto.PreventaRequestDto;

public interface PreventaService {
    ResponseDto<Object> getPreciosPizzas(PreventaRequestDto request);
}

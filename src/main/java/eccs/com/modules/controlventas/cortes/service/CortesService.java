package eccs.com.modules.controlventas.cortes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.cortes.dto.AbrirCorteRequestDto;

public interface CortesService {
    ResponseDto<Object> abrirCorte(AbrirCorteRequestDto request);
}

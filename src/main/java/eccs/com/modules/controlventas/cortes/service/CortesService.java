package eccs.com.modules.controlventas.cortes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.cortes.dto.AbrirCorteRequestDto;

public interface CortesService {
    public ResponseDto<Object> abrirCorte(AbrirCorteRequestDto request);
}

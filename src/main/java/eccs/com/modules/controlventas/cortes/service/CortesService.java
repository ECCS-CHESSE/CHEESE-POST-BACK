package eccs.com.modules.controlventas.cortes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.cortes.dto.AbrirCorteRequestDto;
import eccs.com.modules.controlventas.cortes.dto.CerrarCorteRequestDto;

public interface CortesService {
    public ResponseDto<Object> getAbrirCorte(AbrirCorteRequestDto request);
    public ResponseDto<Object> getCerrarCorte(CerrarCorteRequestDto request);
    public ResponseDto<Object> getDataCorte(int id);
}

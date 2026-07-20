package eccs.com.modules.controlventas.cancelaciones.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.cancelaciones.dto.FolioRequestDto;

public interface FolioService {
    ResponseDto<Object> buscarFolio(FolioRequestDto request);
    ResponseDto<Object> cancelarVenta(FolioRequestDto request);
}

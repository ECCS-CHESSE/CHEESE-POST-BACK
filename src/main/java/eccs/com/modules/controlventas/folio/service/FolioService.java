package eccs.com.modules.controlventas.folio.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.folio.dto.FolioRequestDto;

public interface FolioService {
    ResponseDto<Object> buscarFolio(FolioRequestDto request);
}

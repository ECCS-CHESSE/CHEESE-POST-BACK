package eccs.com.modules.pos.catalogoplataformas.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.pos.catalogoplataformas.dto.CatalogoPlataformasRequestDto;

public interface CatalogoPlataformasService {
    ResponseDto<Object> getCatalogoPlataformas(CatalogoPlataformasRequestDto request);
}

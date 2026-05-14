package eccs.com.modules.controlventas.catalogocortes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.catalogocortes.dto.CatalogoCortesRequestDto;

public interface CatalogoCortesService {
    ResponseDto<Object> getCatalogoCortes(CatalogoCortesRequestDto request);
}

package eccs.com.modules.controlempresa.catalogosucursales.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlempresa.catalogosucursales.dto.CatalogoSucursalesRequestDto;

public interface CatalogoSucursalesService {
    ResponseDto<Object> getCatalogoSucursales(CatalogoSucursalesRequestDto request);
}

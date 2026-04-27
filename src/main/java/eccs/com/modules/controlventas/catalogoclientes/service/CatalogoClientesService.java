package eccs.com.modules.controlventas.catalogoclientes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.catalogoclientes.dto.CatalogoClientesRequestDto;

public interface CatalogoClientesService {
    ResponseDto<Object> getCatalogoClientes(CatalogoClientesRequestDto request);
}

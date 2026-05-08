package eccs.com.modules.controlventas.catalogoclientes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.catalogoclientes.dto.CatalogoClientesRequestDto;

public interface CatalogoClientesService {
    public ResponseDto<Object> getCatalogoClientes(CatalogoClientesRequestDto request);
    public ResponseDto<Object> getLstEstatus();
    public ResponseDto<Object> getLstSucursales();
}

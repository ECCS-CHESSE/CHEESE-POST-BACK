package eccs.com.modules.controlempresa.sucursal.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlempresa.sucursal.dto.CreateSucursalRequestDto;

public interface SucursalService {
    ResponseDto<Object> createSucursal(CreateSucursalRequestDto request);
    ResponseDto<Object> deleteSucursal(int id);
}

package eccs.com.modules.controlempresa.sucursal.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlempresa.sucursal.dto.CreateSucursalRequestDto;
import eccs.com.modules.controlempresa.sucursal.dto.UpdateSucursalRequestDto;

public interface SucursalService {
    ResponseDto<Object> createSucursal(CreateSucursalRequestDto request);
    ResponseDto<Object> updateSucursal(UpdateSucursalRequestDto request);
    ResponseDto<Object> deleteSucursal(int id);
}

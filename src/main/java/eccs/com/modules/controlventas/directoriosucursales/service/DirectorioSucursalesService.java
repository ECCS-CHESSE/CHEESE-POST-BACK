package eccs.com.modules.controlventas.directoriosucursales.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.directoriosucursales.dto.DirectorioSucursalesRequestDto;

public interface DirectorioSucursalesService {
    ResponseDto<Object> getDirectorioSucursales(DirectorioSucursalesRequestDto request);
}

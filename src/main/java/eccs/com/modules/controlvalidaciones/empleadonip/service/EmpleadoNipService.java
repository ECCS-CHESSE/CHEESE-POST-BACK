package eccs.com.modules.controlvalidaciones.empleadonip.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlvalidaciones.empleadonip.dto.EmpleadoNipRequestDto;

public interface EmpleadoNipService {
    public ResponseDto<Object> validarNip(EmpleadoNipRequestDto request);
}

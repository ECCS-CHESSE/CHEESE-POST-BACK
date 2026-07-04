package eccs.com.modules.controlinventarios.especialidades.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.especialidades.dto.AgregarEspecialidadRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.ActualizarEspecialidadRequestDto;

public interface EspecialidadesService {
    ResponseDto<Object> data();
    ResponseDto<Object> agregar(AgregarEspecialidadRequestDto request);
    ResponseDto<Object> actualizar(ActualizarEspecialidadRequestDto request);
    ResponseDto<Object> eliminar(Long id);
}

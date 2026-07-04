package eccs.com.modules.controlinventarios.especialidades.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.especialidades.dto.AgregarEspecialidadRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.ActualizarEspecialidadRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.IngredientesEspecialidadRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.AgregarIngredientesRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.EliminarIngredientesRequestDto;
import eccs.com.modules.controlinventarios.especialidades.dto.ModificarPrecioRequestDto;

public interface EspecialidadesService {
    ResponseDto<Object> data();
    ResponseDto<Object> agregar(AgregarEspecialidadRequestDto request);
    ResponseDto<Object> actualizar(ActualizarEspecialidadRequestDto request);
    ResponseDto<Object> eliminar(Long id);
    ResponseDto<Object> getIngredientes(IngredientesEspecialidadRequestDto request);
    ResponseDto<Object> agregarIngredientes(AgregarIngredientesRequestDto request);
    ResponseDto<Object> eliminarIngredientes(EliminarIngredientesRequestDto request);
    ResponseDto<Object> eliminarIngrediente(Long id);
    ResponseDto<Object> modificarPrecio(ModificarPrecioRequestDto request);
}

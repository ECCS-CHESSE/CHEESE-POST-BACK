package eccs.com.modules.controlinventarios.promociones.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.promociones.dto.AgregarPromocionRequestDto;
import eccs.com.modules.controlinventarios.promociones.dto.ActualizarPromocionRequestDto;

public interface PromocionesService {
    ResponseDto<Object> data();
    ResponseDto<Object> agregar(AgregarPromocionRequestDto request);
    ResponseDto<Object> actualizar(ActualizarPromocionRequestDto request);
    ResponseDto<Object> eliminar(Long id);
}

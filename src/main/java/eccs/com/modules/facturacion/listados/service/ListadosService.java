package eccs.com.modules.facturacion.listados.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.facturacion.listados.dto.UsoCfdiRequestDto;

public interface ListadosService {
    ResponseDto<Object> getRegimenesFiscales();
    ResponseDto<Object> getUsosCfdi(UsoCfdiRequestDto request);
}

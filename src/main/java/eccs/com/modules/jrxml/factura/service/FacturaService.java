package eccs.com.modules.jrxml.factura.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.jrxml.factura.dto.FacturaRequestDto;

public interface FacturaService {
    ResponseDto<Object> factura(FacturaRequestDto request);
}

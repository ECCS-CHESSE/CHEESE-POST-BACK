package eccs.com.modules.facturacion.factura.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.facturacion.factura.dto.FacturaRequestDto;

public interface FacturaService {
    ResponseDto<Object> getDataFactura(FacturaRequestDto request);
}

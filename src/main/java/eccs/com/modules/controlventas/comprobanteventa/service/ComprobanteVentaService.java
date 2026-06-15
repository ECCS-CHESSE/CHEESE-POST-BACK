package eccs.com.modules.controlventas.comprobanteventa.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.ComprobanteVentaRequestDto;

public interface ComprobanteVentaService {
    ResponseDto<Object> getData(int IdSucursal, int IdComprobante);
    ResponseDto<Object> createComprobanteVenta(ComprobanteVentaRequestDto request);
}

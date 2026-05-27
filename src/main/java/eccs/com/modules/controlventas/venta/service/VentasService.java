package eccs.com.modules.controlventas.venta.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.venta.dto.CrearVentaRequestDto;

public interface VentasService {
    public ResponseDto<Object> getCrearVenta(CrearVentaRequestDto request);
}

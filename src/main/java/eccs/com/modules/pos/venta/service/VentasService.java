package eccs.com.modules.pos.venta.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.pos.venta.dto.CrearVentaRequestDto;
import eccs.com.modules.pos.venta.dto.DataVentaRequestDto;

public interface VentasService {
    public ResponseDto<Object> getCrearVenta(CrearVentaRequestDto request);
    public ResponseDto<Object> getDataVenta(DataVentaRequestDto request);
}

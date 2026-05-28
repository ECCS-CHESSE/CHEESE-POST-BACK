package eccs.com.modules.controlventas.venta.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.venta.dto.CrearVentaRequestDto;
import eccs.com.modules.controlventas.venta.dto.DataVentaRequestDto;

public interface VentasService {
    public ResponseDto<Object> getCrearVenta(CrearVentaRequestDto request);
    public ResponseDto<Object> getDataVenta(DataVentaRequestDto request);
}

package eccs.com.modules.pos.venta.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.pos.venta.dto.CrearVentaRequestDto;
import eccs.com.modules.pos.venta.dto.DataVentaRequestDto;
import eccs.com.modules.pos.venta.dto.InsertarComprobanteVentaInfoRequestDto;
import eccs.com.modules.pos.venta.dto.ResumenArmaTuPizzaRequestDto;

public interface VentasService {
    public ResponseDto<Object> getCrearVenta(CrearVentaRequestDto request);
    public ResponseDto<Object> getDataVenta(DataVentaRequestDto request);
    public ResponseDto<Object> getPrecioArmaTuPizzaResumen(ResumenArmaTuPizzaRequestDto request);
    public ResponseDto<Object> insertarComprobanteVentaInfo(InsertarComprobanteVentaInfoRequestDto request);
}

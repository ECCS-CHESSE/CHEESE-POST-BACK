package eccs.com.modules.controlventas.comprobanteventa.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.ComprobanteVentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.AgregarProductoRequestDto;

public interface ComprobanteVentaService {
    ResponseDto<Object> getData(int IdSucursal, int IdComprobante);
    ResponseDto<Object> getDataDetalle(int IdSucursal, int IdComprobante);
    ResponseDto<Object> getEliminarProducto(int IdSucursal, int Id);
    ResponseDto<Object> createComprobanteVenta(ComprobanteVentaRequestDto request);
    ResponseDto<Object> agregarProducto(AgregarProductoRequestDto request);
    ResponseDto<Object> cancelarComprobanteVenta(int idSucursal, int id);
}

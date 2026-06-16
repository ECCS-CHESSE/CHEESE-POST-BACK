package eccs.com.modules.controlventas.productos.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlventas.productos.dto.ProductosRequestDto;

public interface ProductosService {
    ResponseDto<Object> buscarProductos(ProductosRequestDto request);
}

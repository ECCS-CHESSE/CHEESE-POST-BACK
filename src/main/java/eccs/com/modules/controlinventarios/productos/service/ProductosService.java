package eccs.com.modules.controlinventarios.productos.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.productos.dto.ProductosRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.ProductosCategoriasRequestDto;

public interface ProductosService {
    public ResponseDto<Object> getCatalogoProductos(ProductosRequestDto request);
    public ResponseDto<Object> getCatalogoCategorias(ProductosCategoriasRequestDto request);
}

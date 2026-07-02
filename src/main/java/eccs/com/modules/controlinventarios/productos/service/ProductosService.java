package eccs.com.modules.controlinventarios.productos.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.productos.dto.ProductosRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.ProductosCategoriasRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.UpdateImagenProductoRequestDto;

public interface ProductosService {
    public ResponseDto<Object> getCatalogoProductos(ProductosRequestDto request);
    public ResponseDto<Object> getCatalogoCategorias(ProductosCategoriasRequestDto request);
    public ResponseDto<Object> updateImagenProducto(UpdateImagenProductoRequestDto request);
}

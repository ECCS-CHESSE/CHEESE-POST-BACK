package eccs.com.modules.controlinventarios.productos.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.controlinventarios.productos.dto.ProductosRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.ProductosCategoriasRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.UpdateImagenProductoRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.CreateProductoRequestDto;

public interface ProductosService {
    ResponseDto<Object> getCatalogoProductos(ProductosRequestDto request);
    ResponseDto<Object> getCatalogoCategorias(ProductosCategoriasRequestDto request);
    ResponseDto<Object> updateImagenProducto(UpdateImagenProductoRequestDto request);
    ResponseDto<Object> createProducto(CreateProductoRequestDto request);
    ResponseDto<Object> getProductosClasificacion(ProductosRequestDto request);
}

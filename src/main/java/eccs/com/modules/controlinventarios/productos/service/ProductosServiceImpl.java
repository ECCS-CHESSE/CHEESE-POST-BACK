package eccs.com.modules.controlinventarios.productos.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlinventarios.productos.dto.ProductosRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.ProductosCategoriasRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.UpdateImagenProductoRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.CreateProductoRequestDto;
import eccs.com.modules.controlinventarios.productos.entity.ProductoServicioEntity;
import eccs.com.modules.controlinventarios.productos.query.ProductosQuery;
import eccs.com.modules.controlinventarios.productos.query.ProductoServicioQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("controlinventariosProductosServiceImpl")
@RequiredArgsConstructor
public class ProductosServiceImpl implements ProductosService {

    private final ProductosQuery productosQuery;
    private final ProductoServicioQuery productoServicioQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getCatalogoProductos(ProductosRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                productosQuery.getCatalogoProductos(request.getId_sucursal(), request.getId_clasificacion())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PRODUCTOS");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PRODUCTOS");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getCatalogoCategorias(ProductosCategoriasRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                productosQuery.getCatalogoCategorias(request.getId_sucursal())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - CATEGORIAS");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - CATEGORIAS");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> updateImagenProducto(UpdateImagenProductoRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                productosQuery.updateImagenProducto(request.getId_producto(), request.getImagen())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PRODUCTOS");
            response.setMensaje("IMAGEN ACTUALIZADA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PRODUCTOS");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> createProducto(CreateProductoRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            ProductoServicioEntity entity = new ProductoServicioEntity();
            entity.setDescripcion(request.getDescripcion());
            entity.setIdEccsSucursal(request.getId_sucursal());
            entity.setIdEccsClasificacion(request.getId_clasificacion());
            ProductoServicioEntity saved = productoServicioQuery.save(entity);
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PRODUCTOS");
            response.setMensaje("PRODUCTO CREADO DE MANERA EXITOSA");
            response.setResponse(saved);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PRODUCTOS");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getProductosClasificacion(ProductosRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = productoServicioQuery.getProductosByClasificacion(request.getId_sucursal());
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PRODUCTOS");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - PRODUCTOS");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

}

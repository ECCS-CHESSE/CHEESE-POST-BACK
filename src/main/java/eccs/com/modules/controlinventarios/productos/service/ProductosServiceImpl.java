package eccs.com.modules.controlinventarios.productos.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlinventarios.productos.dto.ProductosRequestDto;
import eccs.com.modules.controlinventarios.productos.dto.ProductosCategoriasRequestDto;
import eccs.com.modules.controlinventarios.productos.query.ProductosQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("controlinventariosProductosServiceImpl")
@RequiredArgsConstructor
public class ProductosServiceImpl implements ProductosService {

    private final ProductosQuery productosQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getCatalogoProductos(ProductosRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                productosQuery.getCatalogoProductos(
                    request.getId_sucursal(),
                    request.getId_clasificacion()
                )
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

}

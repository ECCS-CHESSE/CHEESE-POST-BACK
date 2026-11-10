package eccs.com.modules.pos.preventa.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.pos.preventa.dto.EliminarIngredientePreventaRequestDto;
import eccs.com.modules.pos.preventa.dto.IngredientesProduccionRequestDto;
import eccs.com.modules.pos.preventa.dto.PreventaRequestDto;
import eccs.com.modules.pos.preventa.dto.UpdateProductoImagenRequestDto;
import eccs.com.modules.pos.preventa.dto.UpdateTipoVentaRequestDto;
import eccs.com.modules.pos.preventa.query.PreventaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreventaServiceImpl implements PreventaService {

    private final PreventaQuery preventaQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getPreciosPizzas(PreventaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                preventaQuery.getPreciosPizzas(request.getId_sucursal())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - SISTEMA POS - PREVENTA");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - SISTEMA POS - PREVENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getDataIngredientesProduccionInfo(IngredientesProduccionRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                preventaQuery.getDataIngredientesProduccionInfo(
                   
                    request.getId_sucursal(),
                    request.getId_venta(),
                    request.getId_especialidad()
                    
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - SISTEMA POS - PREVENTA");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - SISTEMA POS - PREVENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
    @Override
    public ResponseDto<Object> eliminarIngredientePreventa(EliminarIngredientePreventaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                preventaQuery.eliminarIngredientePreventa(
                    request.getId_sucursal(),
                    request.getId_venta(),
                    request.getId_especialidad()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - SISTEMA POS - PREVENTA");
            response.setMensaje("INGREDIENTE ELIMINADO DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - SISTEMA POS - PREVENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> updateTipoVenta(UpdateTipoVentaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                preventaQuery.updateTipoVenta(
                    request.getId_sucursal(),
                    request.getId_venta(),
                    request.getId_tipo_pedido()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - SISTEMA POS - PREVENTA");
            response.setMensaje("TIPO DE VENTA ACTUALIZADO DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - SISTEMA POS - PREVENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> updateProductoImagen(UpdateProductoImagenRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                preventaQuery.updateProductoImagen(
                    request.getId_producto(),
                    request.getId_sucursal(),
                    request.getImagen()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - SISTEMA POS - PREVENTA");
            response.setMensaje("IMAGEN DEL PRODUCTO ACTUALIZADA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - SISTEMA POS - PREVENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

}

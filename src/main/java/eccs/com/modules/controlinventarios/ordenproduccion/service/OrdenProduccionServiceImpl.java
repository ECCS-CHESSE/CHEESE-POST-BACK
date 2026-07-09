package eccs.com.modules.controlinventarios.ordenproduccion.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.OrdenProduccionRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.LimpiarIngredientesRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarSalsaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.UpdateCategoriasArmaTuPizzaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EspecificacionesOrdenRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.query.OrdenProduccionQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("controlinventariosOrdenProduccionServiceImpl")
@RequiredArgsConstructor
public class OrdenProduccionServiceImpl implements OrdenProduccionService {

    private final OrdenProduccionQuery ordenProduccionQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> insertarIngredientesOrden(OrdenProduccionRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.insertarIngredientesOrden(request.getId_sucursal(), request.getId(), request.getId_venta())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("ORDEN CREADA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> limpiarIngredientesOrden(LimpiarIngredientesRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.limpiarIngredientesOrden(request.getId_sucursal(), request.getId_especialidad1(), request.getId_especialidad2(), request.getId_venta())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("INGREDIENTES LIMPIADOS DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> insertarSalsa(InsertarSalsaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.insertarSalsa(request.getId_sucursal(), request.getId_producto(), request.getId_venta())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("SALSA AGREGADA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> insertarOrillaQueso(InsertarSalsaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.insertarOrillaQueso(request.getId_sucursal(), request.getId_producto(), request.getId_venta())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("ORILLA DE QUESO AGREGADA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> updateCategoriasArmaTuPizza(UpdateCategoriasArmaTuPizzaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.updateCategoriasArmaTuPizza(request.getId_sucursal(), request.getId_venta(), request.getId_equivalencia())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("EQUIVALENCIAS ACTUALIZADAS DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> agregarEspecificaciones(EspecificacionesOrdenRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.agregarEspecificaciones(request.getId_sucursal(), request.getId_venta(), request.getEspecificaciones())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("ESPECIFICACIONES AGREGADAS DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

}

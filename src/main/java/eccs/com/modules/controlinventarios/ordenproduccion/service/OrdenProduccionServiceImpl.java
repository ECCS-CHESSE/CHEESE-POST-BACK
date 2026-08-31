package eccs.com.modules.controlinventarios.ordenproduccion.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.OrdenProduccionRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.SucursalRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.LimpiarIngredientesRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.ModalDataIngredientesDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarSalsaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.UpdateCategoriasArmaTuPizzaRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EspecificacionesOrdenRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EliminarIngredienteOrdenRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.DataIngredientesDerecharRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.EliminarIngredientesDerecharRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarIngredienteIzquierdoRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.InsertarIngredienteDerechoRequestDto;
import eccs.com.modules.controlinventarios.ordenproduccion.dto.LimpiarArmadoPizzaRequestDto;
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
                ordenProduccionQuery.insertarIngredientesOrden(
                    request.getId_sucursal(), 
                    request.getId(), 
                    request.getId_venta(),
                    request.getId_lado_pizza()
                )
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

    @Override
    public ResponseDto<Object> getIngredientesOrdenDerecho(ModalDataIngredientesDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.getIngredientesOrdenDerecho(request.getId_equivalencia(), request.getId_sucursal(), request.getIdVenta())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
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
    public ResponseDto<Object> getIngredientesOrdenIzquierdo(ModalDataIngredientesDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.getIngredientesOrdenIzquierdo(request.getId_equivalencia(), request.getId_sucursal(), request.getIdVenta())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
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
    public ResponseDto<Object> eliminarIngredienteOrden(EliminarIngredienteOrdenRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.eliminarIngredienteOrden(request.getId_sucursal(), request.getId())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("INGREDIENTE ELIMINADO DE MANERA EXITOSA");
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
    public ResponseDto<Object> getDataIngredientesDataDerecha(DataIngredientesDerecharRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.getDataIngredientesDataDerecha(request.getId_sucursal(), request.getId_especialidad())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
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
    public ResponseDto<Object> getDataIngredientesDataIzquierda(DataIngredientesDerecharRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.getDataIngredientesDataIzquierda(request.getId_sucursal(), request.getId_especialidad())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
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
    public ResponseDto<Object> eliminarIngredientesDerecha(EliminarIngredientesDerecharRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.eliminarIngredientesDerecha(request.getId_sucursal(), request.getId_venta(), request.getId_especialidad(), request.getId_lado())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("INGREDIENTES ELIMINADOS DE MANERA EXITOSA");
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
    public ResponseDto<Object> eliminarIngredientesIzquierda(EliminarIngredientesDerecharRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.eliminarIngredientesIzquierda(request.getId_sucursal(), request.getId_venta(), request.getId_especialidad(), request.getId_lado())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("INGREDIENTES ELIMINADOS DE MANERA EXITOSA");
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
    public ResponseDto<Object> getTotalOrdenProduccion(EliminarIngredienteOrdenRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.getTotalOrdenProduccion(request.getId_sucursal(), request.getId())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
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
    public ResponseDto<Object> insertarIngredienteIzquierdo(InsertarIngredienteIzquierdoRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.insertarIngredienteIzquierdo(
                    request.getId_sucursal(),
                    request.getId_venta(),
                    request.getId_ingrediente()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("INGREDIENTE IZQUIERDO INSERTADO DE MANERA EXITOSA");
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
    public ResponseDto<Object> insertarIngredienteDerecho(InsertarIngredienteDerechoRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.insertarIngredienteDerecho(
                    request.getId_sucursal(),
                    request.getId_venta(),
                    request.getId_ingrediente()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("INGREDIENTE DERECHO INSERTADO DE MANERA EXITOSA");
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
    public ResponseDto<Object> limpiarArmadoPizza(LimpiarArmadoPizzaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                ordenProduccionQuery.limpiarArmadoPizza(
                    request.getId_sucursal(),
                    request.getId_venta(),
                    request.getId_config()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL INVENTARIOS - ORDEN PRODUCCION");
            response.setMensaje("ARMADO DE PIZZA LIMPIADO DE MANERA EXITOSA");
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

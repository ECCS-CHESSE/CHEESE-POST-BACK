package eccs.com.modules.controlventas.comprobanteventa.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.comprobanteventa.dto.AgregarProductoRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.ComprobanteVentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.ConfirmarVentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.EccsCancelarComprobanteRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.ItemAumentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.UpdateClienteComprobanteVentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.query.ComprobanteVentaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComprobanteVentaServiceImpl implements ComprobanteVentaService {

    private final ComprobanteVentaQuery comprobanteVentaQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getData(int IdSucursal, int IdComprobante) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                comprobanteVentaQuery.getData( IdSucursal, IdComprobante)
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - COMPROBANTE VENTA");
            response.setMensaje("COMPROBANTE DATA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - COMPROBANTE VENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getDataDetalle(int IdSucursal, int IdComprobante) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                comprobanteVentaQuery.getDataDetalle( IdSucursal, IdComprobante)
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - COMPROBANTE VENTA DETALLE");
            response.setMensaje("COMPROBANTE DATA DETALLE");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - COMPROBANTE VENTA DETALLE");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getEliminarProducto(int IdSucursal, int Id) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                comprobanteVentaQuery.getEliminarProducto( IdSucursal, Id)
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - COMPROBANTE VENTA DETALLE");
            response.setMensaje("COMPROBANTE DATA DETALLE");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - COMPROBANTE VENTA DETALLE");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }


    @Override
    public ResponseDto<Object> createComprobanteVenta(ComprobanteVentaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                comprobanteVentaQuery.createComprobanteVenta(
                    request.getId_eccs_sucursal(),
                    request.getId_eccs_cliente(),
                    request.getId_eccs_empleado(),
                    request.getId_eccs_tipo_comprobante()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - COMPROBANTE VENTA");
            response.setMensaje("COMPROBANTE CREADO DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - COMPROBANTE VENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> agregarProducto(AgregarProductoRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                comprobanteVentaQuery.agregarProducto(
                    request.getId_sucursal(),
                    request.getId_venta(),
                    request.getId_empleado(),
                    request.getPrecio(),
                    request.getTamano(),
                    request.getProducto_descripcion()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - AGREGAR PRODUCTO");
            response.setMensaje("PRODUCTO AGREGADO DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - AGREGAR PRODUCTO");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> cancelarComprobanteVenta(int idSucursal, int id) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                comprobanteVentaQuery.cancelarComprobanteVenta(idSucursal, id)
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - CANCELAR COMPROBANTE");
            response.setMensaje("COMPROBANTE CANCELADO DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - CANCELAR COMPROBANTE");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> updateClienteComprobanteVenta(UpdateClienteComprobanteVentaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                comprobanteVentaQuery.updateClienteComprobanteVenta(
                    request.getId_venta(),
                    request.getId_cliente()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - ACTUALIZAR CLIENTE COMPROBANTE");
            response.setMensaje("CLIENTE ACTUALIZADO DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - ACTUALIZAR CLIENTE COMPROBANTE");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> itemAumenta(ItemAumentaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                comprobanteVentaQuery.itemAumenta(
                    request.getId_venta(),
                    request.getId_cantidad()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - ITEM AUMENTA");
            response.setMensaje("ITEM ACTUALIZADO DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - ITEM AUMENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> confirmarVenta(ConfirmarVentaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                comprobanteVentaQuery.confirmarVenta(
                    request.getId_sucursal(),
                    request.getId(),
                    request.getId_empleado()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - CONFIRMAR VENTA");
            response.setMensaje("VENTA CONFIRMADA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - CONFIRMAR VENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> eccsCancelarComprobante(EccsCancelarComprobanteRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                comprobanteVentaQuery.eccsCancelarComprobante(
                    request.getId_sucursal(),
                    request.getId_venta()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - CANCELAR COMPROBANTE");
            response.setMensaje("COMPROBANTE CANCELADO DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - CANCELAR COMPROBANTE");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

}

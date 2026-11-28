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
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComprobanteVentaServiceImpl implements ComprobanteVentaService {

    // Texto del RAISE de fn_agregar_eccs_productos_pizzas_venta cuando no hay precio del producto.
    private static final String ERROR_BD_SIN_PRECIO = "No se encontraron precios del producto";
    private static final String MENSAJE_SIN_PRECIO =
        "Este producto no tiene un precio configurado. Configura su precio en el catálogo e inténtalo de nuevo.";

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
            // La función de BD lanza un RAISE cuando el producto no tiene precio; se traduce a un mensaje claro.
            String errorBd = mensajeBaseDatos(e);
            boolean sinPrecio = errorBd.contains(ERROR_BD_SIN_PRECIO);

            if (sinPrecio) {
                log.warn("Producto sin precio configurado (venta {}, producto '{}', tamano '{}'): {}",
                    request.getId_venta(), request.getProducto_descripcion(), request.getTamano(), errorBd);
            }

            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - AGREGAR PRODUCTO");
            response.setMensaje(sinPrecio
                ? MENSAJE_SIN_PRECIO
                : "Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    /**
     * Obtiene el mensaje original de la base de datos (la causa más profunda) sin el encabezado "ERROR:"
     * ni el detalle "Where:" de PL/pgSQL; solo la primera línea.
     */
    private String mensajeBaseDatos(Throwable e) {
        String mensaje = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        if (mensaje == null) return "";

        String primeraLinea = mensaje.lines().findFirst().orElse("").trim();
        return primeraLinea.startsWith("ERROR:") ? primeraLinea.substring(6).trim() : primeraLinea;
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
            response.setResponse(null);

            if (isForeignKeyViolation(e)) {
                response.setMensaje(
                    "No se puede asignar cantidad 0, por favor elimine."
                );
            } else {
                response.setMensaje("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Detecta si la excepción (o alguna de sus causas) corresponde a una
     * violación de llave foránea de PostgreSQL (SQLState 23503).
     */
    private boolean isForeignKeyViolation(Throwable e) {
        Throwable cause = e;
        while (cause != null) {
            if (cause instanceof java.sql.SQLException sqlEx) {
                if ("23503".equals(sqlEx.getSQLState())) {
                    return true;
                }
            }
            String msg = cause.getMessage();
            if (msg != null && (msg.contains("foreign key constraint")
                    || msg.contains("violates foreign key"))) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
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

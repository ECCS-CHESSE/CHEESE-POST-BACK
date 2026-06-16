package eccs.com.modules.controlventas.comprobanteventa.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.comprobanteventa.dto.ComprobanteVentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.dto.AgregarProductoRequestDto;
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
                    request.getProducto(),
                    request.getPrecio()
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

}

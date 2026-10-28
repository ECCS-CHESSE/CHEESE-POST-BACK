package eccs.com.modules.pos.venta.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.pos.venta.dto.CrearVentaRequestDto;
import eccs.com.modules.pos.venta.dto.DataVentaRequestDto;
import eccs.com.modules.pos.venta.dto.InsertarComprobanteVentaInfoRequestDto;
import eccs.com.modules.pos.venta.dto.ResumenArmaTuPizzaRequestDto;
import eccs.com.modules.pos.venta.query.VentasQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VentasServiceImpl implements VentasService {

    private final VentasQuery VentasQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getCrearVenta(CrearVentaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                VentasQuery.getCrearVenta(
                    request.getId_eccs_sucursal(),
                    request.getId_eccs_cliente(),
                    request.getId_eccs_empleado(),
                    request.getId_eccs_tipo_pedido()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - VENTA");
            response.setMensaje("Información de la venta creada.");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - VENTA");

            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
    @Override
    public ResponseDto<Object> getDataVenta(DataVentaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                VentasQuery.getDataVenta(
                    request.getId_eccs_sucursal(),
                    request.getId()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - VENTA");
            response.setMensaje("Información de la venta.");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - VENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getPrecioArmaTuPizzaResumen(ResumenArmaTuPizzaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                VentasQuery.getPrecioArmaTuPizzaResumen(
                    request.getId_sucursal(),
                    request.getId_venta(),
                    request.getId_pc(),
                    request.getId_tamano()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - VENTA");
            response.setMensaje("Resumen de precio de arma tu pizza.");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - VENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> insertarComprobanteVentaInfo(InsertarComprobanteVentaInfoRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                VentasQuery.insertarComprobanteVentaInfo(
                    request.getId_sucursal(),
                    request.getId_venta(),
                    request.getId_pc(),
                    request.getTamano(),
                    request.getId_empleado()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - VENTA");
            response.setMensaje("Información del comprobante de venta insertada.");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - VENTA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

}

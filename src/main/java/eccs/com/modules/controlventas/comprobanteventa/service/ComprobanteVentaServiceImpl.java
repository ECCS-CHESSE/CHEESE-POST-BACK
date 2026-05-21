package eccs.com.modules.controlventas.comprobanteventa.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.comprobanteventa.dto.ComprobanteVentaRequestDto;
import eccs.com.modules.controlventas.comprobanteventa.query.ComprobanteVentaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComprobanteVentaServiceImpl implements ComprobanteVentaService {

    private final ComprobanteVentaQuery comprobanteVentaQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> createComprobanteVenta(ComprobanteVentaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                comprobanteVentaQuery.createComprobanteVenta(
                    request.getId_eccs_sucursal(),
                    request.getId_empleado(),
                    request.getId_eccs_cliente(),
                    request.getId_eccs_cliente_domicilio()
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

}

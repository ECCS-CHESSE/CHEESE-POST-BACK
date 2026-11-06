package eccs.com.modules.facturacion.factura.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.facturacion.factura.dto.FacturaRequestDto;
import eccs.com.modules.facturacion.factura.query.FacturaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    private final FacturaQuery facturaQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getDataFactura(FacturaRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                facturaQuery.getDataFactura(request.getFolio(), request.getSucursal())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - FACTURACION - FACTURA");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - FACTURACION - FACTURA");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> getDataComprobante(Integer id) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                facturaQuery.getDataComprobante(id)
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - FACTURACION - COMPROBANTE");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - FACTURACION - COMPROBANTE");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
   
    @Override
    public ResponseDto<Object> getDataComprobanteCliente(Integer id) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                facturaQuery.getDataComprobanteCliente(id)
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - FACTURACION - COMPROBANTE CLIENTE");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - FACTURACION - COMPROBANTE CLIENTE");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}

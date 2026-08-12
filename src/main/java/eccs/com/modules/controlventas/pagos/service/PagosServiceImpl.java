package eccs.com.modules.controlventas.pagos.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.pagos.dto.PagosInsertRequestDto;
import eccs.com.modules.controlventas.pagos.query.PagosQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagosServiceImpl implements PagosService {

    private final PagosQuery pagosQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getDataPago(Integer id_venta) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(pagosQuery.getDataPago(id_venta));
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - PAGOS");
            response.setMensaje("Información de pago de la venta obtenida exitosamente.");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - PAGOS");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }

    @Override
    public ResponseDto<Object> insertPago(PagosInsertRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                pagosQuery.insertPago(
                    request.getId_sucursal(),
                    request.getId(),
                    request.getId_empleado(),
                    request.getId_sat_forma_pago(),
                    request.getImporte()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - PAGOS");
            response.setMensaje("Pago registrado exitosamente.");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - PAGOS");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}

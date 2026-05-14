package eccs.com.modules.controlventas.cortes.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.cortes.dto.AbrirCorteRequestDto;
import eccs.com.modules.controlventas.cortes.query.CortesQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CortesServiceImpl implements CortesService {

    private final CortesQuery cortesQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> abrirCorte(AbrirCorteRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                cortesQuery.abrirCorte(
                    request.getId_eccs_sucursal(),
                    request.getId_eccs_empleado(),
                    request.getMonto()
                )
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - CORTES");
            response.setMensaje("CORTE ABIERTO DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - CORTES");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}

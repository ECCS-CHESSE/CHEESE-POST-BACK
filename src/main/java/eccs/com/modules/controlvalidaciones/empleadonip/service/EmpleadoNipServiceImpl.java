package eccs.com.modules.controlvalidaciones.empleadonip.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlvalidaciones.empleadonip.dto.EmpleadoNipRequestDto;
import eccs.com.modules.controlvalidaciones.empleadonip.query.EmpleadoNipQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpleadoNipServiceImpl implements EmpleadoNipService {

    private final EmpleadoNipQuery empleadoNipQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> validarNip(EmpleadoNipRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(
                    empleadoNipQuery.validarNip(request.getIdEmpleado(), request.getNip())
            );
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VALIDACIONES - EMPLEADO NIP");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VALIDACIONES - EMPLEADO NIP");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}

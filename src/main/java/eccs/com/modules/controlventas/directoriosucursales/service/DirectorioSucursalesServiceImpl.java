package eccs.com.modules.controlventas.directoriosucursales.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.core.middleware.JsonParserMiddleware;
import eccs.com.modules.controlventas.directoriosucursales.dto.DirectorioSucursalesRequestDto;
import eccs.com.modules.controlventas.directoriosucursales.query.DirectorioSucursalesQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorioSucursalesServiceImpl implements DirectorioSucursalesService {

    private final DirectorioSucursalesQuery directorioSucursalesQuery;
    private final JsonParserMiddleware jsonParserMiddleware;

    @Override
    public ResponseDto<Object> getDirectorioSucursales(DirectorioSucursalesRequestDto request) {
        ResponseDto<Object> response = new ResponseDto<>();
        try {
            Object result = jsonParserMiddleware.parseFunction(directorioSucursalesQuery.getDirectorioSucursales(request.getId_sucursal()));
            response.setSuccess(true);
            response.setTitulo("ECCS - CONTROL VENTAS - TICKETS");
            response.setMensaje("CONSULTA DE MANERA EXITOSA");
            response.setResponse(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setTitulo("ECCS - CONTROL VENTAS - TICKETS");
            response.setMensaje("Error: " + e.getMessage());
            response.setResponse(null);
        }
        return response;
    }
}
